package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.CatalogoLote;
import com.adicse.comercial.model.Piking;
import com.adicse.comercial.service.CatalogoLoteService;
import com.adicse.comercial.service.PikingService;

@RestController
@RequestMapping("/res/catalogolote")
public class CatalogoLoteController {

	@Autowired
	private CatalogoLoteService catalogoLoteService;
	
	@Autowired
	private PikingService pikingService;
	
	@RequestMapping("/getCatalogoloteByIdCtalogoMarca")
	public List<CatalogoLote> lstCatalogoLoteByIdCatalogoMarca(@RequestParam("idCatalogoMarca") String idCatalogoMarca){
		List<CatalogoLote> lst = catalogoLoteService.lstCatalogoLoteByIdCatalogoMarca(idCatalogoMarca);
		
//		for(CatalogoLote row:lst) {
//			for(Piking piking:row.getPikings()) {
//				piking.setCatalogoLote(null);
//			}
//		}
		
		return lst;
	}
	
	
	@RequestMapping("/edit")
	@ResponseBody
	public CatalogoLote getEdit(@RequestParam("id") String id) {
		return catalogoLoteService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public CatalogoLote postCreate(@RequestBody CatalogoLote catalogoLote) {
		CatalogoLote catalogoLoteUpdate = catalogoLoteService.findbyid(catalogoLote.getIdCatalogoLote()).isPresent()?catalogoLoteService.findbyid(catalogoLote.getIdCatalogoLote()).get():null;
		
	
		
		if(catalogoLoteUpdate == null) {
			catalogoLoteUpdate = new CatalogoLote();		
		}
		
		pikingService.deleteByIdCatalogoLote(catalogoLote.getIdCatalogoLote());
		
		BeanUtils.copyProperties(catalogoLote, catalogoLoteUpdate);
		
		for(Piking row : catalogoLoteUpdate.getPikings()) {
			row.setCatalogoLote(catalogoLoteUpdate);
		}
		
		catalogoLoteUpdate = catalogoLoteService.grabar(catalogoLoteUpdate);
		for(Piking row2 : catalogoLoteUpdate.getPikings()) {
			row2.setCatalogoLote(null);
		}
		return catalogoLoteUpdate;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public CatalogoLote putUdate(@RequestBody CatalogoLote catalogoLote) {
		
		CatalogoLote catalogoLoteUpdate = catalogoLoteService.findbyid(catalogoLote.getIdCatalogoLote()).get();
		
		BeanUtils.copyProperties(catalogoLote, catalogoLoteUpdate);
		
		return catalogoLoteService.grabar(catalogoLoteUpdate);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		catalogoLoteService.deletebyid(id);
	}
}
