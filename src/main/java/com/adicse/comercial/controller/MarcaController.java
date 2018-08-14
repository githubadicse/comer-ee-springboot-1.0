package com.adicse.comercial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.Marca;
import com.adicse.comercial.service.MarcaService;
import com.adicse.comercial.viewResolver.ExcelMarcaListReportView;
import com.adicse.comercial.viewResolver.PdfMarcaListReportView;


@RestController
@RequestMapping("/res/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;	
	
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

		Page<Marca> page = marcaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Marca> lst = page.getContent() ;
		

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
		
		
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Marca getEdit(@RequestParam("id") Integer id) {
		return marcaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Marca postCreate(@RequestBody Marca marca) {
		marca.setIdmarca(0);
		return marcaService.grabar(marca);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Marca putUdate(@RequestBody Marca marca) {
		
	Marca marcaUpdate = marcaService.findbyid(marca.getIdmarca() ).get();
		
		BeanUtils.copyProperties(marca, marcaUpdate);
		
		return marcaService.grabar(marcaUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		marcaService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody	
	public List<Marca> getall(){
		
		Map<String,Object> response = new HashMap<String, Object>();
		List<Marca> lst = marcaService.getall();
		response.put("data", lst);
		
		return lst;		
		
	}
	
	@RequestMapping("/findbyid")
	@ResponseBody	
	public Marca findbyid(@RequestParam("id") Integer id){
		
	
		Marca marca = marcaService.findbyid(id).get();
		
		return marca;				
	}	
	
	
	@RequestMapping("/excellist")
	public ModelAndView reporteExcelMarca(HttpServletRequest req, HttpServletResponse res){
		
		Map<String,Object> model = new HashMap<>();
		
		List<Marca> lst = marcaService.getall();
		model.put("data", lst);
		
		
		return new ModelAndView(new ExcelMarcaListReportView(),model);

		
		
	}
		
	
	@RequestMapping("/pdflist")
	public ModelAndView reportePdfMarca(HttpServletResponse res){
		
		Map<String,Object> model = new HashMap<>();
		
		List<Marca> lst = marcaService.getall();
		model.put("data", lst);
	
	
		ModelAndView mv = new ModelAndView(new PdfMarcaListReportView(),model);
		
		return mv;

		
		
	}	
	
	@RequestMapping("/findByDscmarcaContainingIgnoreCaseOrderByDscmarcaAsc")
	@ResponseBody		
	public List<Marca> findByDscmarcaContainingIgnoreCaseOrderByDsccategoriaAsc(@RequestParam("dscmarca") String dscmarca) {
		
		Map<String,Object> response = new HashMap<String, Object>();
		List<Marca> lst = marcaService.findByDscmarcaContainingIgnoreCaseOrderByDscmarcaAsc(dscmarca);
		response.put("data", lst);
		return lst;
	}		
	

	

}
