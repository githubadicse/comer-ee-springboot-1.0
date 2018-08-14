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

import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Salida001;
import com.adicse.comercial.model.Salida002;
import com.adicse.comercial.model.Salida002kardex;
import com.adicse.comercial.service.KardexService;
import com.adicse.comercial.service.PeriodoalmacenService;
import com.adicse.comercial.service.ProductoService;
import com.adicse.comercial.service.Salida001Service;
import com.adicse.comercial.service.Salida002KardexService;
import com.adicse.comercial.service.Salida002Service;
import com.adicse.comercial.utilitarios.Idunico;
import com.adicse.comercial.viewResolver.PdfListaSalidas;
import com.adicse.comercial.viewResolver.PdfNotaSalida;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/res/salida001")
public class Salida001Controller {
	
	@Autowired
	private Salida001Service salida001Service;
	
	@Autowired
	private Salida002KardexService salida002KardexService;
	
	@Autowired
	private KardexService kardexService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private PeriodoalmacenService periodoalmacenService;
	
	@Autowired
	private Salida002Service salida002Service;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		sortdireccion = "DESC";
		Page<Salida001> page = salida001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Salida001> lst = page.getContent();

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}	
	
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody String sSalida001) {
		Map<String, Object> response = new HashMap<String, Object>();


		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");		
		om.setTimeZone(TimeZone.getTimeZone("EST"));
		Salida001 salida001 = null;
		
		try {
			salida001 = om.readValue(sSalida001, Salida001.class);
	
			
			//Tambien debemos eliminar las lineas del kardex generados por el documento
			//Pasamos el documento para ver las lineas y estas eliminarlos en el kardex.
			//->
			if( salida001.getIdsalida001() > 0){ 
				System.out.println("idsalida001 mayo a cero");
				
				List<Salida002kardex> lstSalida002Kardex =  salida002KardexService.findBySalida002In(salida001.getSalida002s());
				
				List<Salida002kardex> lstSalida002Kardex2 = new ArrayList<Salida002kardex>();
				for(Salida002kardex row: lstSalida002Kardex){
					lstSalida002Kardex2.add(row);
				}

				System.out.println("Eliminando Salida002kardex");
				salida002KardexService. deleteBySalida002KardexInSalida002(salida001.getSalida002s());
				
				System.out.println("Eliminando kardex");
				kardexService.deleteKardexByIdKardexSalida002(lstSalida002Kardex2);
				

				//ing001.setFecha( ing001.getFecha());		
				salida001Service.deleteSalida002ByIdSalida001( salida001.getIdsalida001() );		
			}

								
			// actualizamos el detalle
			Integer i = 0;
			for (Salida002 row : salida001.getSalida002s() ) {
				
				String id = new Idunico().getIdunico();
				row.setIdsalida002(id);
				row.setSalida001(salida001);
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

			
			salida001 = salida001Service.grabar(salida001);
			Integer idsalida001 = salida001.getIdsalida001();
			//Map<String, Object> obj = new HashMap<>();

			response.put("success", true);
			response.put("msg", "Registro grabado");
			response.put("iding001", idsalida001);
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
		
		Optional<Salida001> salida001 = salida001Service.findbyid(id);
		
		for(Salida002 row : salida001.get().getSalida002s()){
			row.setSalida001(null);
		}
		
		response.put("data", salida001);
		return response;
	}
	
	@RequestMapping("/pdfnotasalida")
	public ModelAndView reportePdfMarca(Integer id
			){
		
		
		Optional<Salida001> salida001 = salida001Service.findbyid(id);
		
		Map<String,Object> model = new HashMap<>();
		
		
		
		model.put("data", salida001.get());
		
	
	
		ModelAndView mv = new ModelAndView(new PdfNotaSalida(),model);
		
		return mv;
		
	}	
	
	@RequestMapping("/pdflistasalidas")
	public ModelAndView reportePdfMarca(Integer idproducto, Integer idperiodoalmacen
			){
		
		Optional<Producto> producto = productoService.findbyid(idproducto) ;
		Optional<Periodoalmacen> periodoalmacen = periodoalmacenService.findbyid(idperiodoalmacen);
				
		List<Salida002> lstsalida002 = salida002Service.getIng002ByProductoAndPeriodoalmacen(periodoalmacen.get(), producto.get());
		
		Map<String,Object> model = new HashMap<>();
		
		model.put("data", lstsalida002);
		
		ModelAndView mv = new ModelAndView(new PdfListaSalidas(),model);
		
		return mv;	
	}	

}







