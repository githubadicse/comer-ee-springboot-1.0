package com.adicse.comercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.service.MatrizService;

@RestController
@RequestMapping("/matriz")
public class MatrizController {
	
	@Autowired
	public MatrizService matrizService;
	
	
	public Double getSumaPesoByIdRequerimientoVolumen001(@RequestParam("idRequerimientoVolumen001") String idRequerimientoVolumen001) {
		return matrizService.getSumaPesoByIdRequerimientoVolumen001(idRequerimientoVolumen001).doubleValue();
	}

}
