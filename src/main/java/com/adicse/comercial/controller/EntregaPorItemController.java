package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.service.EntregaPorItemService;

@RestController
@RequestMapping("/res/entregaPorItem")
public class EntregaPorItemController {
	
	
	@Autowired
	private EntregaPorItemService entregaPorItemService;
	
	@RequestMapping("/getEntregaPorItemByAnnoAndNumeroEntrega")
	@ResponseBody
	public List<EntregaPorItem> getEntregaPorItemByAnnoAndNumeroEntrega(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega){
		return entregaPorItemService.getListEntregaPorAnnoAndNumeroEntrega(anno, numeroEntrega);
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public EntregaPorItem putUdate(@RequestBody EntregaPorItem entregaPorItem) {
		
		String idEntregaPorItem = entregaPorItem.getIdEntregaPorItem();
		
		EntregaPorItem entregaPorItemUpdate = entregaPorItemService.findbyid(idEntregaPorItem ).get();
		
		BeanUtils.copyProperties(entregaPorItem, entregaPorItemUpdate);
		
		return entregaPorItemService.grabar(entregaPorItemUpdate);
	}

}
