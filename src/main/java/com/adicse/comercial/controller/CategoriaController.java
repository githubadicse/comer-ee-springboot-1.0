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

import com.adicse.comercial.model.Categoria;
import com.adicse.comercial.service.CategoriaService;

@RestController
@RequestMapping("/res/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
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

		Page<Categoria> page = categoriaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Categoria> lst = page.getContent() ;
		

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
	
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Categoria getEdit(@RequestParam("id") Integer id) {
		return categoriaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Categoria postCreate(@RequestBody Categoria categoria) {
		categoria.setIdcategoria(0);
		return categoriaService.grabar(categoria);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Categoria putUdate(@RequestBody Categoria categoria) {
		
		Categoria categoriaUpdate = categoriaService.findbyid(categoria.getIdcategoria() ).get();
		
		BeanUtils.copyProperties(categoria, categoriaUpdate);
		
		return categoriaService.grabar(categoriaUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {		
		categoriaService.deletebyid(id);
	}
	

	
	@RequestMapping("/getall")
	@ResponseBody	
	public Map<String,Object> getall(){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		
		
		return response;		
		
	}
	

	
	@RequestMapping("/findByDsccategoriaContainingIgnoreCaseOrderByDsccategoriaAsc")
	@ResponseBody		
	public Map<String,Object> findByDsccategoriaContainingIgnoreCaseOrderByDsccategoriaAsc(@RequestParam("dsccategoria") String dsccategoria) {
		System.out.println(dsccategoria);
		Map<String,Object> response = new HashMap<String, Object>();
		List<Categoria> lst = categoriaService.findByDsccategoriaContainingIgnoreCaseOrderByDsccategoriaAsc(dsccategoria);
		response.put("data", lst);
		return response;
	}

}
