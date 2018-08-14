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

import com.adicse.comercial.model.Motivosalida;
import com.adicse.comercial.service.MotivosalidaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/res/motivosalida")
public class MotivosalidaController {
	
	@Autowired
	private MotivosalidaService motivosalidaService;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filter			
			){
		
		Map<String,Object> response = new HashMap<String, Object>();

		Page<Motivosalida> page = motivosalidaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Motivosalida> lst = page.getContent() ;
		

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
		
		
	}
	
	@RequestMapping("/save")
	@ResponseBody	
	public Map<String,Object> save(@RequestBody String sMotivosalida){
		Map<String,Object> response = new HashMap<String, Object>();
		

		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Motivosalida Motivosalida = null;
		try {
			Motivosalida = om.readValue(sMotivosalida, Motivosalida.class);
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
			
			Motivosalida = motivosalidaService.grabar(Motivosalida);
			//Integer idMotivosalida = Motivosalida.getIdmotivosalida();
			//Map<String,Object> obj = new HashMap<>();
			

			response.put("success", true);
			response.put("msg", "Registro grabado");
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
	
	@RequestMapping("/delete")
	@ResponseBody	
	public  Map<String,Object> delete(@RequestParam("id") Integer id){
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			
			motivosalidaService.deletebyid(id);
			//Map<String,Object> obj = new HashMap<>();
			

			response.put("success", true);
			response.put("msg", "Registro Eliminado");
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
	
	@RequestMapping("/getall")
	@ResponseBody	
	public Map<String,Object> getall(){
		
		Map<String,Object> response = new HashMap<String, Object>();
		List<Motivosalida> lst = motivosalidaService.getall();
		response.put("data", lst);
		
		return response;		
		
	}
	
	@RequestMapping("/findbyid")
	@ResponseBody	
	public Map<String,Object> findbyid(@RequestParam("id") Integer id){
		
		Map<String,Object> response = new HashMap<String, Object>();
		Optional<Motivosalida> marca = motivosalidaService.findbyid(id);
		
		
		
		response.put("data", marca);
		return response;				
	}			

}
