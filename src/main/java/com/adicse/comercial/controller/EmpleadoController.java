package com.adicse.comercial.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Empleado;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.model.Usuarioempleado;
import com.adicse.comercial.service.EmpleadoService;
import com.adicse.comercial.service.UsuarioService;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;

@RestController
@RequestMapping("/res/empleado")
public class EmpleadoController {
	
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson;
	
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

		Page<Empleado> page = empleadoService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Empleado> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}		
	
	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public List<Empleado> getall(){

		
		List<Empleado> lst = empleadoService.getall();
						
		
		return lst;
	}
	
	
	@RequestMapping(value="/getByFilter", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Empleado> getByFilter(@RequestBody Object f) {		
		Filter filter = convertObjectToFormatJson.ConvertObjectToFormatSpecification(f);
		List<Empleado> lst = empleadoService.findByFilter(filter);
		
		for (Empleado em: lst) {
			for (Usuarioempleado rowUs: em.getUsuarioempleados()) {
				rowUs.setEmpleado(null);
			}
		}
		
		return lst;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Empleado getEdit(@RequestParam("id") Integer id) {
		return empleadoService.findbyid(id).get();
	}	
	
	@RequestMapping("/create")
	@ResponseBody
	public Empleado postCreate(@RequestBody Empleado empleado) {
		empleado.setIdempleado(0);
		
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() ;
		Usuario usuario = usuarioService.getAllByLogin(username);
		
		Date utilDate = new Date();
		Timestamp sq = new Timestamp(utilDate.getTime());
		
		empleado.setIdusuario(usuario.getIdusuario());
		empleado.setFechaRegistroSystema(sq);
		
		return empleadoService.grabar(empleado);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Empleado putUdate(@RequestBody Empleado empleado) {
		
		Empleado update = empleadoService.findbyid(empleado.getIdempleado()).get();
		
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() ;
		Usuario usuario = usuarioService.getAllByLogin(username);
		
		Date utilDate = new Date();
		Timestamp sq = new Timestamp(utilDate.getTime());
		
		empleado.setIdusuarioModifica(usuario.getIdusuario());
		empleado.setFechaRegistroSystemaModifica(sq);		
		
		BeanUtils.copyProperties(empleado, update);
		
		return empleadoService.grabar(update);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {	
		
		empleadoService.deletebyid(id);
	}
	
	
	@RequestMapping("/getallbydni")
	@ResponseBody	
	public Map<String,Object> getallbydni(@RequestParam("dni") String dni){
		Map<String,Object> response = new HashMap<>();
		
		Empleado empleado = empleadoService.findAllByDni(dni);
		if(empleado == null){
			response.put("success", false);
		}else{
			response.put("success", true);
		}
		
		response.put("data", empleado);
		return response;
	}
	
	
	@RequestMapping(value="/findByCondicionFilial", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Empleado> findByCondicionFilial(@RequestParam("condicion") Integer condicion) {		
		List<Empleado> lst = empleadoService.findByCondicionFilial(condicion);		
		return lst;
	}

}
