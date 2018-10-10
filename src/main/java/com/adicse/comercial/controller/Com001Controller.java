package com.adicse.comercial.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Com001;
import com.adicse.comercial.model.Com002;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.service.Com001Service;
import com.adicse.comercial.service.UsuarioService;
import com.adicse.comercial.utilitarios.Idunico;



@RestController
@RequestMapping("/res/com001")
public class Com001Controller {
	
	@Autowired
	private Com001Service com001Service;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
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
	
		Page<Com001> page = com001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Com001> lst = page.getContent() ;
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
	public Com001 getEdit(@RequestParam("id") Long id) {
		return com001Service.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Com001 postCreate(@RequestBody Com001 com001) {
		
		for (Com002 row: com001.getCom002s()) {
			row.setCom001(com001);
			row.setIdcom002(new Idunico().getIdunico());
		}
		
		
//		add usuario que crea y fecha hora sistema
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() ;
		Usuario usuario = usuarioService.getAllByLogin(username);				
		
		Date utilDate = new Date();
		Timestamp sq = new Timestamp(utilDate.getTime());
		
		com001.setIdUsuarioCrea(usuario.getIdusuario());
		com001.setFechaRegistroSystema(sq);
		
		
		Com001 com001_grbar = com001Service.grabar(com001);
		for (Com002 row: com001_grbar.getCom002s()) {
			row.setCom001(null);			
		}
				
		return com001_grbar;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Com001 putUdate(@RequestBody Com001 com001) {
		
		for (Com002 row: com001.getCom002s()) {
			row.setCom001(com001);
			row.setIdcom002(new Idunico().getIdunico());
		}
		
//		add usuario que modifica y fecha hora sistema
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() ;
		Usuario usuario = usuarioService.getAllByLogin(username);				
		
		Date utilDate = new Date();
		Timestamp sq = new Timestamp(utilDate.getTime());
		
		com001.setIdUsuarioModifica(usuario.getIdusuario());
		com001.setFechaRegistroSystemaModifica(sq);
		
		Com001 com001Update = com001Service.findbyid(com001.getIdcom001() ).get();
		BeanUtils.copyProperties(com001 , com001Update);
		com001Update = com001Service.grabar(com001Update);
		
		for (Com002 row: com001Update.getCom002s()) {
			row.setCom001(null);			
		}
		
		return com001Update; 
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {	
		
		com001Service.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Com001> getall(){
		return com001Service.getall();
	}
	
	@ResponseBody
	@RequestMapping("/findById")
	public Map<String, Object> findById(@RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Com001 com001 = com001Service.findbyid(id).get();
						
		for (Com002 rowDt: com001.getCom002s()) {
			rowDt.setCom001(null);
		}
		
		response.put("data", com001);
		return response;
	}
}
