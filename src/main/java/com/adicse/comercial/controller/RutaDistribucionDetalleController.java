package com.adicse.comercial.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.RutaDistribucionDetalle;
import com.adicse.comercial.service.RutaDistribucionDetalleService;
import com.adicse.comercial.utilitarios.Idunico;

@RestController
@RequestMapping("/res/rutaDistribucionDetalle")
public class RutaDistribucionDetalleController {
	
	@Autowired
	private RutaDistribucionDetalleService rutaDistribucionDetalleService;
	

	
	
	@RequestMapping("/edit")
	@ResponseBody
	public RutaDistribucionDetalle getEdit(@RequestParam("id") String id) {
		return rutaDistribucionDetalleService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public RutaDistribucionDetalle postCreate(@RequestBody RutaDistribucionDetalle rutaDistribucionDetalle) {
		String id = new Idunico().getIdunico();
		rutaDistribucionDetalle.setIdRutaDistribucionDetalle(id);
		
	
		
		return rutaDistribucionDetalleService.grabar(rutaDistribucionDetalle);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public RutaDistribucionDetalle putUdate(@RequestBody RutaDistribucionDetalle rutaDistribucionDetalle) {
		
	RutaDistribucionDetalle rutaDistribucionUpdate = rutaDistribucionDetalleService.findbyid(rutaDistribucionDetalle.getIdRutaDistribucionDetalle() ).get();
		
		BeanUtils.copyProperties(rutaDistribucionDetalle, rutaDistribucionUpdate);
		
		return rutaDistribucionDetalleService.grabar(rutaDistribucionUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		rutaDistribucionDetalleService.deletebyid(id);
	}

}
