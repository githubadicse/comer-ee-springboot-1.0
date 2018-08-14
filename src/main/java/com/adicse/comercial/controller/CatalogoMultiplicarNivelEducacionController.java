package com.adicse.comercial.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.CatalogoMultiplicarNivelEducacion;
import com.adicse.comercial.service.CatalogoMultiplicarNivelEducacionService;

@RestController
@RequestMapping("/res/catalogoMultiplicarNivelEducacion")
public class CatalogoMultiplicarNivelEducacionController {
	
	@Autowired
	private CatalogoMultiplicarNivelEducacionService catalogoMultiplicarNivelEducacionService;
	
	@RequestMapping("/getCatalogoMultiplicarByPresentacion")
	public List<CatalogoMultiplicarNivelEducacion> getCatalogoMultiplicarByPresentacion(@RequestBody Map<String, String> json){
		Integer anno = Integer.parseInt(json.get("anno").toString());
		Integer numeroEntrega = Integer.parseInt(json.get("numeroEntrega").toString());
		String idProducto = json.get("idProducto");
		String idProductoPresentacion = json.get("idProductoPresentacion");
		Integer idNivelEducacion = Integer.parseInt(json.get("idNivelEducacion").toString());
		
		return catalogoMultiplicarNivelEducacionService.getCatalogoNivelEducacionByPresentacion(anno, numeroEntrega, idProducto, idProductoPresentacion, idNivelEducacion) ;
	}
	
	@RequestMapping("/getCatalogoMultiplicarByPresentacionPeriodoNumeroEntrega")
	public List<CatalogoMultiplicarNivelEducacion> getCatalogoMultiplicarByPresentacionPeriodoNumeroEntrega(@RequestBody Map<String, String> json){
		Integer anno = Integer.parseInt(json.get("anno").toString());
		Integer numeroEntrega = Integer.parseInt(json.get("numeroEntrega").toString());
		
		
		return catalogoMultiplicarNivelEducacionService.getCatalogoNivelEducacionByPeriodoNumeroEntrega(anno, numeroEntrega);
	}

}
