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

import com.adicse.comercial.model.Transportista;
import com.adicse.comercial.service.TransportistaService;

@RestController
@RequestMapping("/res/transportista")
public class TransportistaController {
	
	
	@Autowired
	private TransportistaService transportistaService; 
	
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

		Page<Transportista> page = transportistaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Transportista> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}		
	
	@RequestMapping("/getall")
	public List<Transportista> getAll(){
		return transportistaService.getall();
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Transportista getEdit(@RequestParam("id") Integer id) {
		return transportistaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Transportista postCreate(@RequestBody Transportista transportista) {
		transportista.setIdTransportista(0);
		return transportistaService.grabar(transportista);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Transportista putUdate(@RequestBody Transportista transportista) {
		
		Transportista transportistaUpdate = transportistaService.findbyid(transportista.getIdTransportista()).get();		
		BeanUtils.copyProperties(transportista, transportistaUpdate);		
		return transportistaService.grabar(transportistaUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		transportistaService.deletebyid(id);
	}

}
