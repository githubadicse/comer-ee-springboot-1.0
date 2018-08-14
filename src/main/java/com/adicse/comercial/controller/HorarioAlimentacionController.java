package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.HorarioAlimentacion;
import com.adicse.comercial.service.HorarioAlimentacionService;

@RestController
@RequestMapping("/res/horarioalimentacion")
public class HorarioAlimentacionController {
	
	@Autowired
	private HorarioAlimentacionService horarioAlimentacionService;
	
	
	@RequestMapping("/getall")
	public List<HorarioAlimentacion> getAll(){
		return horarioAlimentacionService.getall();
	}

}
