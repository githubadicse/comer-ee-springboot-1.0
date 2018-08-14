package com.adicse.comercial.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.service.CierremensualService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/res/cierremensual")
public class CierremensualController {
	
	@Autowired
	private CierremensualService cierremensualService;
	
	
	
	@ResponseBody
	@RequestMapping("/findAllByPeriodoalmacen")
	public Map<String,Object> findAllByPeriodoalmacen(Integer mes, Integer anno, Integer idalmacen){
		
	
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/procesoCierremensual")	
	public Map<String,Object> procesoCierremensual(@RequestBody String sPeriodoalmacen){
		Map<String,Object> response = new HashMap<>();
		
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Periodoalmacen periodoalmacen = null;
		try {
			periodoalmacen = om.readValue(sPeriodoalmacen, Periodoalmacen.class);
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
		System.out.println("Periodo a cerrar " + periodoalmacen.getMes() + " " + periodoalmacen.getAlmacen().getDscalmacen() );
		response = cierremensualService.procesoCierremensual(periodoalmacen);
		
		return response;
	}

}
