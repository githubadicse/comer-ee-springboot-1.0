package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.service.Ing001Service;
import com.adicse.comercial.service.Ing002KardexService;
import com.adicse.comercial.service.Ing002Service;
import com.adicse.comercial.service.KardexService;
import com.adicse.comercial.service.PeriodoalmacenService;
import com.adicse.comercial.service.ProductoService;
import com.adicse.comercial.viewResolver.PdfListaIngresos;
import com.adicse.comercial.viewResolver.PdfNotaIngreso;


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


		Page<Ing001> page = ing001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Ing001> lst = page.getContent();

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}	
	
	@RequestMapping("/create")
	@ResponseBody
	public Ing001 create(@RequestBody Ing001 ing001) {
		ing001.setIding001(0);
		return ing001Service.grabar(ing001);

	}
	

	@ResponseBody
	@RequestMapping("/findById")
	public Map<String, Object> findById(@RequestParam("id") Integer id){
		Map<String, Object> response = new HashMap<>();
		
		Ing001 ing001 = ing001Service.findbyid(id).get() ;
		
		for(Ing002 row : ing001.getIng002s()){
			row.setIng001(null);
		}
		
		response.put("data", ing001);
		return response;
	}

	
	@RequestMapping("/pdfnotaingreso")
	public ModelAndView reportePdfMarca(Integer iding001
			){
				
		Optional<Ing001> ing001 = ing001Service.findbyid(iding001) ;
		
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
