package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Documentoidentificacion;
import com.adicse.comercial.service.DocumentoidentificacionService;

@RestController
@RequestMapping("/res/documentoidentificacion")
public class DocumentoidentificacionController {
	
	@Autowired
	private DocumentoidentificacionService documentoidentificacionService;
	
	@ResponseBody
	@RequestMapping("/getall")
	public List<Documentoidentificacion> getall(){
		
	
		
		List<Documentoidentificacion> lst = documentoidentificacionService.getall();
		
		
		
		return lst;
		
	}

}
