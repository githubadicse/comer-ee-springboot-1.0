package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.MedioPago;
import com.adicse.comercial.service.MedioPagoService;


@RestController
@RequestMapping("/res/mediopago")
public class MedioPagoController {

	
	@Autowired
	private MedioPagoService mediopagoService;
	
	
	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MedioPago> getAll(){
		return mediopagoService.getall(); 
	}
}
