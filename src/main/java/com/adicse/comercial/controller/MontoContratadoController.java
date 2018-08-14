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

import com.adicse.comercial.model.MontoContratado;
import com.adicse.comercial.service.MontoContratadoService;

@RestController
@RequestMapping("/res/montoContratado")
public class MontoContratadoController {

	@Autowired
	public MontoContratadoService montoContratadorService;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filter,
			@RequestParam("paramsExtra") Object paramsExtra
			){
		
		Map<String,Object> response = new HashMap<String, Object>();

		Page<MontoContratado> page = montoContratadorService.paginationParmsExtra (pagenumber, rows, sortdireccion, sortcolumn, filter,paramsExtra);
		
		List<MontoContratado> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}	
	
	@RequestMapping("/edit")
	@ResponseBody
	public MontoContratado getEdit(@RequestParam("id") Integer id) {
		return montoContratadorService.findbyid(id).get();
	}
	

	
	@RequestMapping("/create")
	@ResponseBody
	public MontoContratado postCreate(@RequestBody MontoContratado montoContratado) {
		
		return montoContratadorService.grabar(montoContratado);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public MontoContratado putUdate(@RequestBody MontoContratado montoContratado) {
		
		MontoContratado Update = montoContratadorService.findbyid(montoContratado.getIdMontoContratado()).get();
		
		BeanUtils.copyProperties(montoContratado, Update);
		
		return montoContratadorService.grabar(montoContratado);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		montoContratadorService.deletebyid(id);
	}
	
	
}
