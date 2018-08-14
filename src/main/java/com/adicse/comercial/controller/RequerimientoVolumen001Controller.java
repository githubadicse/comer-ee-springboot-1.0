package com.adicse.comercial.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.model.RequerimientoVolumen001;
import com.adicse.comercial.service.GuiaRemision001Service;
import com.adicse.comercial.service.RequerimientoVolumen001Service;

@RestController
@RequestMapping("/res/requerimientoVolumen001")
public class RequerimientoVolumen001Controller {
	
	@Autowired
	private RequerimientoVolumen001Service requerimientoVolumen001Service;
	
	@Autowired
	private GuiaRemision001Service guiaRemision001Service;
	
	
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

		Page<RequerimientoVolumen001> page = requerimientoVolumen001Service.paginationParmsExtra (pagenumber, rows, sortdireccion, sortcolumn, filter,paramsExtra);
		
		List<RequerimientoVolumen001> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}	
	

	
	@RequestMapping("/edit")
	@ResponseBody
	public RequerimientoVolumen001 getEdit(@RequestParam("id") String id) {
		return requerimientoVolumen001Service.findbyid(id).get();
	}
	
	
	@RequestMapping("/actualizaPesoTotalDesdeGuiaRemision")
	@ResponseBody	
	public void actualizaPesoTotalDesdeGuiaRemision(@RequestParam("anno") Integer anno,@RequestParam("numeroEntrega") Integer numeroEntrega) {
		System.out.println("actualizaPesoTotalDesdeGuiaRemision");
		
		List<GuiaRemision001> lstGuiaRemision001 = guiaRemision001Service.getGuiaRemisionPorAnoNumeroEntregaV2(anno, numeroEntrega);
		
		RequerimientoVolumen001 requerimientoVolumen001 = null;
		Double totalPeso = 0.0;
		Integer cnt = 1;
		for(GuiaRemision001 guiaRemision001:lstGuiaRemision001) {
			System.out.println("Procesando : " + cnt);
			cnt++;
			requerimientoVolumen001 = guiaRemision001.getRequerimientoVolumen001();
			totalPeso = 0.0;
			
			for(GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
				totalPeso = totalPeso + guiaRemision002.getPesoTotal().doubleValue();
				
			}
			requerimientoVolumen001.setPesoTotal(new BigDecimal( totalPeso));
			
			requerimientoVolumen001Service.grabar(requerimientoVolumen001);
			requerimientoVolumen001 = null;
			
		}
		
	}
	
	
	

}
