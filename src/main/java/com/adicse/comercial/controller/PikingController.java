package com.adicse.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Piking;
import com.adicse.comercial.model.VolumenConvertidoEnvace;
import com.adicse.comercial.service.PikingService;
import com.adicse.comercial.service.VolumenConvertidoEnvaseService;

@RestController
@RequestMapping("/res/piking")
public class PikingController {

	@Autowired
	private PikingService pikingService;
	
	@Autowired
	private VolumenConvertidoEnvaseService volumenConvertidoEnvaseService;
	
	@RequestMapping("/updatePikingLista")
	public List<Piking> updatePikingLista(@RequestBody List<Piking> lstPiking,
			@RequestParam("idProductoPorNumeroEntrega") String idProductoPorNumeroEntrega,
			@RequestParam("idCatalogoLote") String idCatalogoLote){
		
		
		
		for(Piking piking:lstPiking) {
			String idItem = piking.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega().getItem();
			//String idCatalogoMarca = piking.getCatalogoLote().getCatalogoMarca().getIdCatalogoMarca();
			String idProductoPresentacion = piking.getCatalogoLote().getCatalogoMarca().getProductoPresentacion().getIdProductoPresentacion();
			String codigoModular = piking.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular();
			
			List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvase = volumenConvertidoEnvaseService.getVolumenByIdProductoPorNumeroEntrega(idProductoPorNumeroEntrega,idItem,codigoModular,idProductoPresentacion);
			
			for(VolumenConvertidoEnvace rowVolumen:lstVolumenConvertidoEnvase) {
			
				piking.setVolumenConvertidoEnvace(rowVolumen);
				
				break;
				
			}
		
		}
		
		System.out.println("Pikingk");
		return lstPiking;
	}
	
	@RequestMapping("/getPikingByIdCatalogoLote")
	public List<Piking> getPikingByIdCatalogoLote(@RequestParam("idCatalogoLote") String idCatalogoLote){
		return pikingService.getPikingByIdCatalogoLote(idCatalogoLote);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		pikingService.deletebyid(id);
	}
	
}
