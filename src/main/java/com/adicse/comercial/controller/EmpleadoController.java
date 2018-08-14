package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Empleado;
import com.adicse.comercial.service.EmpleadoService;

@RestController
@RequestMapping("/res/empleado")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filter			
			){
		
		Map<String,Object> response = new HashMap<String, Object>();

		Page<Empleado> page = empleadoService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Empleado> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}		
	
	@RequestMapping("/getall")
	@ResponseBody	
	public Map<String,Object> getall(){
		Map<String,Object> response = new HashMap<>();
		
		List<Empleado> lst = empleadoService.getall();
		
		response.put("data", lst);
		return response;
	}
	
	@RequestMapping("/getallbydni")
	@ResponseBody	
	public Map<String,Object> getallbydni(@RequestParam("dni") String dni){
		Map<String,Object> response = new HashMap<>();
		
		Empleado empleado = empleadoService.findAllByDni(dni);
		if(empleado == null){
			response.put("success", false);
		}else{
			response.put("success", true);
		}
		
		response.put("data", empleado);
		return response;
	}

}
