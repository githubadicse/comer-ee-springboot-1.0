package com.adicse.comercial.controller;

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

import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.service.AlmacenService;

@RestController
@RequestMapping("/res/almacen")
public class AlmacenController {
	
	@Autowired
	private AlmacenService almacenService;
	
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

		Page<Almacen> page = almacenService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Almacen> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}	
	
	
	
	@RequestMapping("/edit")
	@ResponseBody
	public Almacen getEdit(@RequestParam("id") Integer id) {
		return almacenService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Almacen postCreate(@RequestBody Almacen almacen) {
		almacen.setIdalmacen(0);
		return almacenService.grabar(almacen);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Almacen putUdate(@RequestBody Almacen almacen) {
		
	Almacen almacenUpdate = almacenService.findbyid(almacen.getIdalmacen() ).get();
		
		BeanUtils.copyProperties(almacen, almacenUpdate);
		
		return almacenService.grabar(almacenUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		almacenService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Almacen> getall(){
		return almacenService.getall();
	}

}


