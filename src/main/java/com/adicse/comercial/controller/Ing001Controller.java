package com.adicse.comercial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.Ing001;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Ing002kardex;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.service.Ing001Service;
import com.adicse.comercial.service.Ing002KardexService;
import com.adicse.comercial.service.Ing002Service;
import com.adicse.comercial.service.KardexService;
import com.adicse.comercial.service.PeriodoalmacenService;
import com.adicse.comercial.service.ProductoService;
import com.adicse.comercial.utilitarios.Idunico;
import com.adicse.comercial.viewResolver.PdfListaIngresos;
import com.adicse.comercial.viewResolver.PdfNotaIngreso;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/res/ing001")
public class Ing001Controller {
	
	@Autowired
	private Ing001Service ing001Service;
	
	@Autowired
	private Ing002Service ing002Service;
	
	@Autowired
	private Ing002KardexService ing002KardexService;

	@Autowired
	private KardexService kardexService;
	
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private PeriodoalmacenService periodoalmacenService;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		sortdireccion = "DESC";
		Page<Ing001> page = ing001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Ing001> lst = page.getContent();

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody String sIng001) {
		Map<String, Object> response = new HashMap<String, Object>();


		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");		
		om.setTimeZone(TimeZone.getTimeZone("EST"));
		Ing001 ing001 = null;
		
		try {
			ing001 = om.readValue(sIng001, Ing001.class);
	
			
			//Tambien debemos eliminar las lineas del kardex generados por el documento
			//Pasamos el documento para ver las lineas y estas eliminarlos en el kardex.
			//->
			if(ing001.getIding001() > 0){
				List<Ing002kardex> lstIng002Kardex = ing002KardexService.findByIng002In(ing001.getIng002s());
				
				List<Ing002kardex> lstIng002Kardex2 = new ArrayList<Ing002kardex>();
				for(Ing002kardex row: lstIng002Kardex){
					lstIng002Kardex2.add(row);
				}

				System.out.println("Eliminando Ing002kardex");
				ing002KardexService.deleteByIng002KardexInIng002(ing001.getIng002s());
				
				System.out.println("Eliminando kardex");
				kardexService.deleteKardexByIdKardexIng002(lstIng002Kardex2);
				

				//ing001.setFecha( ing001.getFecha());		
				ing002Service.deleteIng002ByIdIng001( ing001.getIding001() );					
			}

		
			
								
			// actualizamos el detalle de codigo de barras
			Integer i = 0;
			for (Ing002 row : ing001.getIng002s() ) {
				
				String id = new Idunico().getIdunico();
				row.setIding002(id);
				row.setIng001(ing001);
				i++;
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;
			// e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			e.printStackTrace();
			return response;
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;
			// e.printStackTrace();
		}

		try {

			
			ing001 = ing001Service.grabar(ing001);
			Integer iding001 = ing001.getIding001();
			//Map<String, Object> obj = new HashMap<>();

			response.put("success", true);
			response.put("msg", "Registro grabado");
			response.put("iding001", iding001);
		} catch (JDBCException e) {
			System.out.println("error 1 :" + e.getMessage());
			SQLException cause = (SQLException) e.getCause();
			// evaluate cause and find out what was the problem
			System.out.println("error 2 :" + cause.getMessage());
			response.put("success", false);
			response.put("msg", cause.getMessage());
		} catch (HibernateException ex) {
			System.out.println("error 3 :" + ex.getMessage());
		}

		return response;

	}
	

	@ResponseBody
	@RequestMapping("/findById")
	public Map<String, Object> findById(@RequestParam("id") Integer id){
		Map<String, Object> response = new HashMap<>();
		
		Optional<Ing001> ing001 = ing001Service.findbyid(id);
		
		for(Ing002 row : ing001.get().getIng002s()){
			row.setIng001(null);
		}
		
		response.put("data", ing001);
		return response;
	}

	
	@RequestMapping("/pdfnotaingreso")
	public ModelAndView reportePdfMarca(Integer iding001
			){
				
		Optional<Ing001> ing001 = ing001Service.findbyid(iding001);
		
		Map<String,Object> model = new HashMap<>();
		
		model.put("data", ing001);
		
		ModelAndView mv = new ModelAndView(new PdfNotaIngreso(),model);
		
		return mv;	
	}	
	
	@RequestMapping("/pdflistaingresos")
	public ModelAndView reportePdfMarca(Integer idproducto, Integer idperiodoalmacen
			){
		
		Optional<Producto> producto = productoService.findbyid(idproducto) ;
		Optional<Periodoalmacen> periodoalmacen = periodoalmacenService.findbyid(idperiodoalmacen);
				
		List<Ing002> lsting002 = ing002Service.getIng002ByProductoAndPeriodoalmacen(periodoalmacen.get(), producto.get());
		
		Map<String,Object> model = new HashMap<>();
		
		model.put("data", lsting002);
		
		ModelAndView mv = new ModelAndView(new PdfListaIngresos(),model);
		
		return mv;	
	}	
	
	
}
