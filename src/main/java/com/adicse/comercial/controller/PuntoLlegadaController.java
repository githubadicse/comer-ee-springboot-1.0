package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.PuntoLlegada;
import com.adicse.comercial.service.PuntoLlegadaService;

@RestController
@RequestMapping("/puntoLlegada")
public class PuntoLlegadaController {
	
	
	@Autowired
	private PuntoLlegadaService puntoLlegadaService;
	
	
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

		Page<PuntoLlegada> page = puntoLlegadaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<PuntoLlegada> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}		
	
	@RequestMapping("/edit")
	@ResponseBody
	public PuntoLlegada getEdit(@RequestParam("id") Integer id) {
		return puntoLlegadaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public PuntoLlegada postCreate(@RequestBody PuntoLlegada puntoLlegada) {
		puntoLlegada.setIdPuntoLlegada(0);
		return puntoLlegadaService.grabar(puntoLlegada);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public PuntoLlegada putUdate(@RequestBody PuntoLlegada puntoLlegada) {
		
		PuntoLlegada puntoLlegadaUpdate = puntoLlegadaService.findbyid(puntoLlegada.getIdPuntoLlegada()).get();
		
		BeanUtils.copyProperties(puntoLlegada, puntoLlegadaUpdate);
			
		return puntoLlegadaService.grabar(puntoLlegadaUpdate);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody PuntoLlegada puntoLlegada) {	
		Integer idPuntoLlegada = puntoLlegada.getIdPuntoLlegada();
		puntoLlegadaService.deletebyid(idPuntoLlegada);
	}

}
