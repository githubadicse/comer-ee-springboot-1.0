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

import com.adicse.comercial.model.Vehiculo;
import com.adicse.comercial.service.VehiculoService;
import com.adicse.comercial.specification.Filter;
@RestController
@RequestMapping("/res/vehiculo")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;
	

	
	
	
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
	
		Page<Vehiculo> page = vehiculoService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Vehiculo> lst = page.getContent() ;
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
	public Vehiculo getEdit(@RequestParam("id") Integer id) {
		return vehiculoService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Vehiculo postCreate(@RequestBody Vehiculo vehiculo) {
		vehiculo.setIdVehiculo(0);
		return vehiculoService.grabar(vehiculo);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Vehiculo putUdate(@RequestBody Vehiculo vehiculo) {
		
	Vehiculo vehiculoUpdate = vehiculoService.findbyid(vehiculo.getIdVehiculo()).get();
		
		BeanUtils.copyProperties(vehiculo, vehiculoUpdate);
		
		return vehiculoService.grabar(vehiculoUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		vehiculoService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Vehiculo> getall(){
		return vehiculoService.getall();
	}

	@RequestMapping("/getall2")
	@ResponseBody
	public List<Vehiculo> getallchinito(@RequestBody Filter filter){
		return vehiculoService.lstVehiculo(filter);
	}

}
