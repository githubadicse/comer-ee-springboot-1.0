package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Moneda;
import com.adicse.comercial.service.MonedaService;

@RestController
@RequestMapping("res/moneda")
public class MonedaController {
	
	
	@Autowired
	private MonedaService monedaService;
	
	
	@RequestMapping("getall")
	public List<Moneda> getall(){
		return monedaService.getall() ;
	}

}
