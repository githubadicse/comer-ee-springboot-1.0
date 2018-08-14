package com.adicse.comercial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.RegionAlimentaria;
import com.adicse.comercial.service.RegionAlimentariaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/res/regionalimentaria")
public class RegionAlimentariaController {
	
	@Autowired
	private RegionAlimentariaService regionAlimentariaService;
	
	@ResponseBody
	@RequestMapping("/getall")
	public List<RegionAlimentaria> getAll(){
		
		List<RegionAlimentaria> lst = regionAlimentariaService.getall();
		
//		for (int i = 0; i < lst.size(); i++) {
//			for(CatalogoBonificacion cb:lst.get(i).getCatalogoBonificacions() ) {
//				cb.setRegionAlimentaria(null);
//			}		
//			
//		}
	
		return lst;
		
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String,Object> save(@RequestBody String sRegionalimentaria){
		Map<String,Object> response = new HashMap<String, Object>();
		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		RegionAlimentaria regionAlimentaria = null;
		try {
			regionAlimentaria = om.readValue(sRegionalimentaria, RegionAlimentaria.class);
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
			
			regionAlimentaria = regionAlimentariaService.grabar(regionAlimentaria);
			//Integer idcategoria = categoria.getIdcategoria();
			//Map<String,Object> obj = new HashMap<>();
			

			response.put("success", true);
			response.put("msg", "Registro grabado");
			response.put("data", regionAlimentaria);
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

}
