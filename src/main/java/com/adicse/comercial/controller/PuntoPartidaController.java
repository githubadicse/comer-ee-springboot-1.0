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

import com.adicse.comercial.model.PuntoPartida;
import com.adicse.comercial.service.PuntoPartidaService;

@RestController
@RequestMapping("/res/puntoPartida")
public class PuntoPartidaController {
	
	@Autowired
	private PuntoPartidaService puntoPartidaService;
	
	
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

		Page<PuntoPartida> page = puntoPartidaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<PuntoPartida> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public PuntoPartida getEdit(@RequestParam("id") Integer id) {
		return puntoPartidaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public PuntoPartida postCreate(@RequestBody PuntoPartida puntoPartida) {
		puntoPartida.setIdPuntoPartida(0);
		return puntoPartidaService.grabar(puntoPartida);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public PuntoPartida putUdate(@RequestBody PuntoPartida puntoPartida) {
		PuntoPartida partida = puntoPartidaService.findbyid(puntoPartida.getIdPuntoPartida()).get();
		
		BeanUtils.copyProperties(puntoPartida, partida);
		
		return puntoPartidaService.grabar(partida);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		puntoPartidaService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<PuntoPartida> getall(){
		return puntoPartidaService.getall();
	}

}
