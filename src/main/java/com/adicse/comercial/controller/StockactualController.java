package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Stockactual;
import com.adicse.comercial.service.StockactualService;

@RestController
@RequestMapping("/res/stockactual")
public class StockactualController {

	@Autowired
	private StockactualService stockactualService;
	
		
	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {
				
		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		
		Page<Stockactual> page = stockactualService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);

		List<Stockactual> lst = page.getContent();
		

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}
	
	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Stockactual> getAll() {
		return stockactualService.getall();		
	}
	
}
