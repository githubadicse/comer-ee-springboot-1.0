package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adicse.comercial.model.EmpleadoDistribuidor;
import com.adicse.comercial.service.EmpleadoDistribuidorService;

@Controller
@RequestMapping("/res/empleadoDistribuidor")
public class EmpleadoDistribuidorController {
	
	@Autowired
	private EmpleadoDistribuidorService empleadoDistribuidorService; 
	
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

		Page<EmpleadoDistribuidor> page = empleadoDistribuidorService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<EmpleadoDistribuidor> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}	
	
	@RequestMapping("/edit")
	@ResponseBody
	public EmpleadoDistribuidor getEdit(@RequestParam("id") String id) {
		return empleadoDistribuidorService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public EmpleadoDistribuidor postCreate(@RequestBody EmpleadoDistribuidor empleadoDistribuidor) {
		
		EmpleadoDistribuidor empleadoDistribuidorUpdate = empleadoDistribuidorService.findbyid(empleadoDistribuidor.getIdEmpleadoDistribuidor() ).isPresent()?empleadoDistribuidorService.findbyid(empleadoDistribuidor.getIdEmpleadoDistribuidor() ).get():null;
		if(empleadoDistribuidorUpdate == null) {
			empleadoDistribuidorUpdate = new EmpleadoDistribuidor();		
		}
		BeanUtils.copyProperties(empleadoDistribuidor, empleadoDistribuidorUpdate);
		return empleadoDistribuidorService.grabar(empleadoDistribuidorUpdate);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public EmpleadoDistribuidor putUdate(@RequestBody EmpleadoDistribuidor empleadoDistribuidor) {
		
	EmpleadoDistribuidor empleadoDistribuidorUpdate = empleadoDistribuidorService.findbyid(empleadoDistribuidor.getIdEmpleadoDistribuidor()).get();
		
		BeanUtils.copyProperties(empleadoDistribuidor, empleadoDistribuidorUpdate);
		
		return empleadoDistribuidorService.grabar(empleadoDistribuidorUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		empleadoDistribuidorService.deletebyid(id);
	}


}
