package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.ProductoPresentacion;
import com.adicse.comercial.service.ProductoPresentacionService;

@RestController
@RequestMapping("/res/productoPresentacion")
public class ProductoPresentacionController {
	
	@Autowired
	private ProductoPresentacionService productoPresentacionService;
	
	@RequestMapping("getPresentacionByIdAnnoNumeroEntrega")
	public List<ProductoPresentacion> getProductoPresentacionByIdCatalogoAndAnnoAndNumeroEntrega(
			@RequestParam("idCatalogoProducto") String idCatalogoProducto, 
			@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega
			){
		
		return productoPresentacionService.getProductoPresentacionByIdProductoAnnoNumeroEntrega(idCatalogoProducto, anno, numeroEntrega);
		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public ProductoPresentacion postCreate(@RequestBody ProductoPresentacion productoPresentacion) {
		//chofer.setIdChofer(0);
		return productoPresentacionService.grabar(productoPresentacion);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ProductoPresentacion putUdate(@RequestBody ProductoPresentacion productoPresentacion) {
		
		System.out.println("id : " + productoPresentacion.getIdProductoPresentacion());
		ProductoPresentacion productoPresentacionUpdate = productoPresentacionService.findbyid(productoPresentacion.getIdProductoPresentacion()).get();
		
		BeanUtils.copyProperties(productoPresentacion, productoPresentacionUpdate);
		
		return productoPresentacionService.grabar(productoPresentacionUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		productoPresentacionService.deletebyid(id);
	}

}
