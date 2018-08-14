package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.service.ItemEntregaService;
import com.adicse.comercial.viewResolver.ExcelItemEntrega;

@RestController
@RequestMapping("/res/itemEntrega")
public class ItemEntregaController {
	
	@Autowired
	private ItemEntregaService itemEntregaService;
	
	
	
	
	@RequestMapping("/byanno")
	@ResponseBody
	public List<ItemEntrega> getItemEntregaByAnno(@RequestParam("anno") Integer anno){
		
		List<ItemEntrega> lst =  itemEntregaService.getItemByAnno(anno);
		
		return  lst;
		
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<ItemEntrega> getItemAll(@RequestParam("anno") Integer anno){
		
		List<ItemEntrega> lst =  itemEntregaService.getItemByAnno(anno);
		for(ItemEntrega row :lst) {
			row.setEntregaPorItems(null);
		}
		
		return  lst;
		
	}	
	
	@RequestMapping("/xls")
	public ModelAndView getXls(@RequestParam("anno") Integer anno) {
		
		List<ItemEntrega> lst =  itemEntregaService.getItemByAnno(anno);
		
		Map<String,Object> response = new HashMap<>();
		
		response.put("data", lst);
		
		return new ModelAndView(new ExcelItemEntrega(),response);
		
	}
	
	
	

}
