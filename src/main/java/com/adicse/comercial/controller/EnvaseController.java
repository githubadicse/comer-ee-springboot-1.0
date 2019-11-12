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

import com.adicse.comercial.model.Envase;
import com.adicse.comercial.service.EnvaseService;



@RestController
@RequestMapping("/res/envase")
public class EnvaseController {
	
	@Autowired
	private EnvaseService envaseService;
	

	
	
	
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
	
		Page<Envase> page = envaseService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Envase> lst = page.getContent() ;
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
	public Envase getEdit(@RequestParam("id") Integer id) {
		return envaseService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Envase postCreate(@RequestBody Envase envase) {
		envase.setIdEnvase(0);
		Envase EnvaseReturn = envaseService.create(envase);
		return EnvaseReturn;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Envase putUdate(@RequestBody Envase envase) {
		
		return envaseService.update(envase);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		envaseService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Envase> getall(){
		return envaseService.getall();
	}



}
