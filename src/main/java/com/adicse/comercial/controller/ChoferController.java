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

import com.adicse.comercial.model.Chofer;
import com.adicse.comercial.service.ChoferService;

@RestController
@RequestMapping("/res/chofer")
public class ChoferController {
	
	@Autowired
	private ChoferService choferService;
	
	
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

		Page<Chofer> page = choferService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Chofer> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}		
	
	@RequestMapping("/getall")
	public List<Chofer> getAll(){
		return choferService.getall();
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Chofer getEdit(@RequestParam("id") Integer id) {
		return choferService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Chofer postCreate(@RequestBody Chofer chofer) {
		chofer.setIdChofer(0);
		return choferService.grabar(chofer);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Chofer putUdate(@RequestBody Chofer chofer) {
		
		Chofer choferUpdate = choferService.findbyid(chofer.getIdChofer()).get();
		
		BeanUtils.copyProperties(chofer, choferUpdate);
			
		return choferService.grabar(choferUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		choferService.deletebyid(id);
	}


}
