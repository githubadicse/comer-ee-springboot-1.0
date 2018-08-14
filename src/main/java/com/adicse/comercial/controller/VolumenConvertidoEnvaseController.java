package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.CatalogoLote;
import com.adicse.comercial.model.VolumenConvertidoEnvace;
import com.adicse.comercial.service.VolumenConvertidoEnvaseService;

@RestController
@RequestMapping("/res/volumenConvertidoEnvase")
public class VolumenConvertidoEnvaseController {
	
	@Autowired
	private VolumenConvertidoEnvaseService volumenConvertidoEnvaseService;
	
	@RequestMapping("/getVolumenByPiking")
	public VolumenConvertidoEnvace getVolumenByPiking(@RequestParam("idItem") String idItem,
			@RequestParam("idCatalogoMarca") String idCatalogoMarca,
			@RequestParam("idProductoPresentacion") String idProductoPresentacion,
			@RequestParam("idCatalogoLote") String idCatalogoLote
			) {
		
			List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvase = volumenConvertidoEnvaseService.getVolumenByPiking(idItem, idCatalogoMarca, idProductoPresentacion);
			
			VolumenConvertidoEnvace returnVolumenConvertidoEnvace = new VolumenConvertidoEnvace();
			
			for(VolumenConvertidoEnvace row : lstVolumenConvertidoEnvase) {
				
				for(CatalogoLote catalogoLote : row.getCatalogoMarca().getCatalogoLotes()) {
					if (catalogoLote.getIdCatalogoLote().equals(idCatalogoLote)) {
						returnVolumenConvertidoEnvace = row;
						break;
					}
				}
			}
		
		return returnVolumenConvertidoEnvace;
	}

}
