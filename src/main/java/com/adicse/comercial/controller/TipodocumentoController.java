package com.adicse.comercial.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Numerador;
import com.adicse.comercial.model.Tipodocumento;
import com.adicse.comercial.service.TipodocumentoService;

@RestController
@RequestMapping("/res/tipodocumento")
public class TipodocumentoController {
	
	@Autowired
	private TipodocumentoService tipodocumentoService;
	
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

		Page<Tipodocumento> page = tipodocumentoService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filter);
		
		List<Tipodocumento> lst = page.getContent() ;
		

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);				
		
		return response;
		
		
	}
	
	@RequestMapping(value="/save", produces=MediaType.APPLICATION_JSON_VALUE)
	public Tipodocumento save( @RequestBody Tipodocumento entidad ) {
		
		// colocamos el id al detalle
		for(Numerador row: entidad.getNumeradors()) {		
			row.setTipodocumento(entidad);
			if (row.getIdNumerador() == 0) {
				row.setIdNumerador(tipodocumentoService.getIdNumerador());
			}
		}
				
		// elimina en perfilesdetalle por idperfil si existe
		Integer idEntidad = entidad.getIdTipoDocumento(); 
		if ( idEntidad != 0 ) {
			tipodocumentoService.deleteNumeradorByIdTipoDocumento(idEntidad);
		}		
				
		// evita recursividad
		Tipodocumento entidadRes = tipodocumentoService.grabar(entidad);
		for (Numerador rowpd: entidadRes.getNumeradors()) {
			rowpd.setTipodocumento(null);
		}
		
		return entidadRes;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Tipodocumento getEdit(@RequestParam("id") Integer id) {
		Tipodocumento entidad = tipodocumentoService.findById(id);
		
		for (Numerador rowPD: entidad.getNumeradors()) {
			rowPD.setTipodocumento(null);
		}
		return entidad;
	}	
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		tipodocumentoService.deletebyid(id);
	}
	
	@RequestMapping(value="/getall", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Tipodocumento> getAll(){
		return tipodocumentoService.getall(); 
	}
	
	@RequestMapping("/findbyid")
	@ResponseBody	
	public Map<String,Object> findbyid(@RequestParam("id") Integer id){
		
		Map<String,Object> response = new HashMap<String, Object>();
		Optional<Tipodocumento> Tipodocumento = tipodocumentoService.findbyid(id);
		
		
		
		response.put("data", Tipodocumento);
		return response;				
	}		

}
