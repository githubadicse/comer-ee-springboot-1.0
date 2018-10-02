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

import com.adicse.comercial.model.ModalidadCompraVenta;
import com.adicse.comercial.service.ModalidadCompraVentaService;
import com.adicse.comercial.specification.Filter;

public class ModalidadCompraVentaController {
	
	@Autowired
	private ModalidadCompraVentaService modalidadCompraVentaService;
	
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
	
		Page<ModalidadCompraVenta> page = modalidadCompraVentaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<ModalidadCompraVenta> lst = page.getContent() ;
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
	public ModalidadCompraVenta getEdit(@RequestParam("id") Integer id) {
		return modalidadCompraVentaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public ModalidadCompraVenta postCreate(@RequestBody ModalidadCompraVenta modalidadCompraVenta) {
		modalidadCompraVenta.setIdModalidadCompraVenta(0);
		return modalidadCompraVentaService.grabar(modalidadCompraVenta);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ModalidadCompraVenta putUdate(@RequestBody ModalidadCompraVenta modalidadCompraVenta) {
		
	ModalidadCompraVenta modalidadCompraVentaUpdate = modalidadCompraVentaService.findbyid(modalidadCompraVenta.getIdModalidadCompraVenta()).get();
		
		BeanUtils.copyProperties(modalidadCompraVenta, modalidadCompraVentaUpdate);
		
		return modalidadCompraVentaService.grabar(modalidadCompraVentaUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		modalidadCompraVentaService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<ModalidadCompraVenta> getall(){
		return modalidadCompraVentaService.getall();
	}



}
