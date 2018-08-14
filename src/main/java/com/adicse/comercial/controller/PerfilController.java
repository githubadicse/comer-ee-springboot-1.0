package com.adicse.comercial.controller;

import org.springframework.beans.BeanUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Perfil;
import com.adicse.comercial.model.Perfilesdetalle;
import com.adicse.comercial.service.PerfilService;
import com.adicse.comercial.utilitarios.Idunico;

@RestController
@RequestMapping("/res/perfil")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
		
	@RequestMapping(value="/save", produces=MediaType.APPLICATION_JSON_VALUE)
	public Perfil save( @RequestBody Perfil entidad ) {
		
		// elimina en perfilesdetalle por idperfil si existe
		Integer idperfil = entidad.getIdperfil(); 
		if ( idperfil != 0 ) {
			perfilService.deletePerfilesdetalleByIdPerfil(idperfil);
		}		
		
		for(Perfilesdetalle row: entidad.getPerfilesdetalles()) {		
			row.setPerfil(entidad);
			row.setIdsysperfilesdetalle(new Idunico().getIdunico());			
		}
				
		
		return perfilService.grabar(entidad);
	}
	
	@RequestMapping("/update")	
	public Perfil putUdate(@RequestBody Perfil entidad) {		
		Perfil perfilUpdate = perfilService.findbyid(entidad.getIdperfil()).get();
			
		BeanUtils.copyProperties(entidad, perfilUpdate);
		
		return perfilService.grabar(perfilUpdate);		
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Perfil getEdit(@RequestParam("id") Integer id) {
		Perfil perfil = perfilService.findById(id);
		
		for (Perfilesdetalle rowPD: perfil.getPerfilesdetalles()) {
			rowPD.setPerfil(null);
		}
		return perfil;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		// perfilService.deletePerfilesdetalleByIdPerfil(id);
		perfilService.deletebyid(id);
	}
//	
//	@RequestMapping("/edit")
//	@ResponseBody
//	public Map<String, Object> getbyid(@RequestParam("id") Integer id){
//		Map<String, Object> response = new HashMap<>();
//		//Perfil entidad = perfilService.findById(id);
//		Optional<Perfil> entidad = perfilService.findbyid(id);
//		response.put("data", entidad);
//		return response;
//	}
	
	
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

		Page<Perfil> page = perfilService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Perfil> lst = page.getContent() ;
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
				
	}		
		
	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Perfil> getAll(){
		return perfilService.getall(); 
	}
	
}
