package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.CatalogoBonificacion;
import com.adicse.comercial.service.CatalogoBonificacionService;

@RestController
@RequestMapping("/res/catalogobonificacion")
public class CatalogoBonificacionController {

	@Autowired
	private CatalogoBonificacionService catalogoBonificacionService;
	

	@RequestMapping("/getCatalogoByProductoRegionNumeroEntrega")
	@ResponseBody
	public List<CatalogoBonificacion> getCatalogoByProductoRegionNumeroEntrega(
			@RequestParam("idproducto") String idProducto,
			@RequestParam("idregion") String idRegion,
			@RequestParam("numeroEntrega") Integer numeroEntrega,
			@RequestParam("idHorarioAlimentacion") Integer idHorarioAlimentacion
			){
		
		System.out.println(idProducto+ " -- " + idRegion+ "   "+ numeroEntrega+"   "+ idHorarioAlimentacion);
		return (List<CatalogoBonificacion>) catalogoBonificacionService.getCatalogoBonificacionByProductoHorarioRegio(idProducto
				, idRegion
				, numeroEntrega
				, idHorarioAlimentacion
				);
		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public CatalogoBonificacion save(@RequestBody CatalogoBonificacion catalogoBonificacion) {
		
		CatalogoBonificacion  catalogoBonificacion2 = null;
		catalogoBonificacion2 = catalogoBonificacionService.findbyid(catalogoBonificacion.getIdCatalogoBonificacion()).get();
		catalogoBonificacion2.setNumeroDePreparacionPorEntrega(catalogoBonificacion.getNumeroDePreparacionPorEntrega());
		
		catalogoBonificacion2 = catalogoBonificacionService.grabar(catalogoBonificacion2);
		
		
		return catalogoBonificacion2;
		
	}
}
