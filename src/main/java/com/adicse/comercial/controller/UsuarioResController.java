package com.adicse.comercial.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.service.UsuarioService;
import com.adicse.comercial.specification.Filter;
@RestController
@RequestMapping("/res/usuario")
public class UsuarioResController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
	
		Page<Usuario> page = usuarioService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Usuario> lst = page.getContent() ;
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
	public Usuario getEdit(@RequestParam("id") Integer id) {
		Usuario usuario= usuarioService.findbyid(id).get();
		usuario.setClave("");
		return usuario;
		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Usuario postCreate(@RequestBody Usuario usuario) {
		usuario.setIdusuario(0);
		String ps = passwordEncoder.encode(usuario.getClave());
		usuario.setClave(ps);
		return usuarioService.grabar(usuario);
		
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Usuario putUdate(@RequestBody Usuario usuario) {
		
		Usuario usuarioUpdate = usuarioService.findbyid(usuario.getIdusuario()).get();
		
		BeanUtils.copyProperties(usuario, usuarioUpdate);
		String ps = passwordEncoder.encode(usuarioUpdate.getClave());
		usuarioUpdate.setClave(ps);
		return usuarioService.grabar(usuarioUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		usuarioService.deletebyid(id);
	}
	

	@RequestMapping("/getall")
	@ResponseBody
	public List<Usuario> getall(){
		return usuarioService.getall();
	}
	

	@RequestMapping("/getall2")
	@ResponseBody
	public List<Usuario> getallchinito(@RequestBody Filter filter){
		return usuarioService.lstUsuario(filter);
	}
	

}