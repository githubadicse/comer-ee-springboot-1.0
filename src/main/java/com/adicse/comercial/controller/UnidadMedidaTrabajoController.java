package com.adicse.comercial.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.UnidadMedidaTrabajo;
import com.adicse.comercial.service.UnidadMedidaTrabajoService;



@RestController
@RequestMapping("/res/unidadmedidatrabajo")
public class UnidadMedidaTrabajoController {
	
	@Autowired
	private UnidadMedidaTrabajoService unidadmedidatrabajoService;
	

	
	
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filters		
			){
	
		Map<String,Object> response = new HashMap<String, Object>();
	
		Page<UnidadMedidaTrabajo> page = unidadmedidatrabajoService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<UnidadMedidaTrabajo> lst = page.getContent() ;
		if(lst.size() == 0 ) {
			 lst = new ArrayList<>();
		}
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		
		return response;
	
				
	}	
	
	@RequestMapping("/edit")
	@ResponseBody
	public UnidadMedidaTrabajo getEdit(@RequestParam("id") Integer id) {
		return unidadmedidatrabajoService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public UnidadMedidaTrabajo postCreate(@RequestBody UnidadMedidaTrabajo unidadmedidatrabajo) {
		unidadmedidatrabajo.setIdUnidadMedidaTrabajo(0);
		UnidadMedidaTrabajo UnidadMedidaTrabajoReturn = unidadmedidatrabajoService.create(unidadmedidatrabajo);
		return UnidadMedidaTrabajoReturn;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public UnidadMedidaTrabajo putUdate(@RequestBody UnidadMedidaTrabajo unidadmedidatrabajo) {
		
		return unidadmedidatrabajoService.update(unidadmedidatrabajo);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		unidadmedidatrabajoService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<UnidadMedidaTrabajo> getall(){
		return unidadmedidatrabajoService.getall();
	}



}
