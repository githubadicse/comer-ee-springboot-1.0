package com.adicse.comercial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.service.AlmacenService;
import com.adicse.comercial.service.PeriodoalmacenService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/res/periodoalmacen")
public class PeriodoalmacenController {
	
	@Autowired
	private PeriodoalmacenService  periodoalmacenService;
	
	@Autowired
	private AlmacenService almacenService;
	
	@ResponseBody
	@RequestMapping("/getPeriodoAlmacenByEstado")
	public Map<String,Object> getPeriodoAlmacenByEstado(Integer anno,Integer mes, String estado, Integer idalmacen){
		
		Map<String,Object> response = new HashMap<>();
		
		Periodoalmacen periodoalmacen = periodoalmacenService.getPeriodoAlmacenByEstado(anno, mes, estado,idalmacen);
		if(periodoalmacen == null){
			response.put("success", false);
		}else{
			response.put("success", true);
		}
		response.put("data", periodoalmacen);
		
		return response;
		
	}
	
	@ResponseBody
	@RequestMapping("/findAllByIniciooperacionesEqualsAndAlmacenIdalmacenEquals")	
	public Map<String,Object> findAllByIniciooperacionesEqualsAndAlmacenIdalmacenEquals(Integer inicio,Integer idalmacen){
		Periodoalmacen periodoalmacen = periodoalmacenService.findAllByIniciooperacionesEqualsAndAlmacenIdalmacenEquals(inicio,idalmacen);
		
		Map<String,Object> response = new HashMap<>();
		if(periodoalmacen == null){
			response.put("success", false);
		}else{
			response.put("success", true);
		}
		response.put("data", periodoalmacen);
		
		return response;		
	}
	
	@RequestMapping("/save")
	@ResponseBody	
	public Map<String,Object> save(@RequestBody String sPeriodoalmacen){
		Map<String,Object> response = new HashMap<String, Object>();
		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Periodoalmacen periodoalmacen = null;
		try {
			periodoalmacen = om.readValue(sPeriodoalmacen, Periodoalmacen.class);
			response.put("success", true);
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;						
			//e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;						
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;			
			//e.printStackTrace();
		}		
		
		try {
			
			periodoalmacen = periodoalmacenService.grabar(periodoalmacen);
			//Integer idperiodoalmacen = periodoalmacen.getIdperiodoalmacen();
			//Map<String,Object> obj = new HashMap<>();
			

			response.put("success", true);
			response.put("msg", "Registro grabado");
			response.put("data", periodoalmacen);
		} catch (JDBCException e) {
			System.out.println("error 1 :" + e.getMessage());
			SQLException cause = (SQLException) e.getCause();
			// evaluate cause and find out what was the problem
			System.out.println("error 2 :" + cause.getMessage());
			response.put("success", false);
			response.put("msg",  cause.getMessage());
		} catch (HibernateException ex) {
			System.out.println("error 3 :" + ex.getMessage());
		}				

		
		return response;
		
	}	
	
	@RequestMapping("/findAllByAlmacenEqualsAndEstadoEquals")
	@ResponseBody
	public Map<String,Object> findAllByAlmacenEqualsAndEstadoEquals(Integer idalmacen,String estado){
		Map<String,Object>  response = new HashMap<>();
		
		
		Optional<Almacen> almacen = almacenService.findbyid(idalmacen);
		
		Periodoalmacen periodoalmacen = periodoalmacenService.findAllByAlmacenEqualsAndEstadoEquals(almacen.get() , estado);
		if(periodoalmacen == null){
			response.put("success", false);
			response.put("msg", "No existe un periodo con el check de Aperturado");
		}else{
			response.put("success", true);
			response.put("msg", "Periodo si existe con el check de aperturado");
			response.put("data", periodoalmacen);
		}
		
		return response;
	}
	

	@RequestMapping("/PeriodoalmacenfindAllByAlmacen")
	@ResponseBody
	public Map<String,Object> findAllByAlmacen(Integer idalmacen){
		
		Map<String,Object>  response = new HashMap<>();
		
		Optional<Almacen> almacen = almacenService.findbyid(idalmacen);
		
		List<Periodoalmacen> periodoalmacen = periodoalmacenService.findAllByAlmacen(almacen.get());
		
		response.put("data", periodoalmacen);
		
		
		return response;
	}
	
	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String,Object> findbyid(Integer id){
		
		Map<String,Object>  response = new HashMap<>();
		
		Optional<Periodoalmacen> periodoalmacen = periodoalmacenService.findbyid(id);

		response.put("data", periodoalmacen);
		
		
		return response;
	}
	
	@RequestMapping("/paginationByAlmacen")
	@ResponseBody
	public Map<String,Object> paginationByAlmacen(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("idalmacen") Integer idalmacen
			){
		
		Map<String,Object> response = new HashMap<>();
		
		
		Page<Periodoalmacen> page = periodoalmacenService.paginationByAlmacen(pagenumber, rows,  idalmacen);
		response.put("data", page);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);		
		
		return response;
	}

	
}
