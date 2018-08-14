package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.NivelEducacion;
import com.adicse.comercial.service.NivelEducacionService;

@RestController
@RequestMapping("/res/nivelEducacion")
public class NivelEducacionController {

	@Autowired
	private NivelEducacionService nivelEducacionService; 
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<NivelEducacion> getAll(){
		return this.nivelEducacionService.getall();
	}
}
