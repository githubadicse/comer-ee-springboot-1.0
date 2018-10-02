package com.adicse.comercial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Com001;
import com.adicse.comercial.service.Com001Service;



@RestController
@RequestMapping("/res/com001")
public class Com001Controller {
	
	@Autowired
	private Com001Service com001Service;
	
	
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
	
		Page<Com001> page = com001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Com001> lst = page.getContent() ;
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
	public Com001 getEdit(@RequestParam("id") Long id) {
		return com001Service.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Com001 postCreate(@RequestBody Com001 com001) {
		com001.setIdcom001(0l);
		return com001Service.grabar(com001);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Com001 putUdate(@RequestBody Com001 com001) {
		
		Com001 com001Update = com001Service.findbyid(com001.getIdcom001() ).get();
		
		BeanUtils.copyProperties(com001 , com001Update);
		
		return com001Service.grabar(com001Update);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {	
		
		com001Service.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Com001> getall(){
		return com001Service.getall();
	}
}
