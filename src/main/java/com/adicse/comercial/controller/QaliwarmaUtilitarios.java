package com.adicse.comercial.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.RequerimientoVolumen001;
import com.adicse.comercial.model.RutaDistribucion;
import com.adicse.comercial.model.RutaDistribucionDetalle;
import com.adicse.comercial.service.RequerimientoVolumen001Service;
import com.adicse.comercial.service.RutaDistribucionDetalleService;
import com.adicse.comercial.service.RutaDistribucionService;

@RestController
@RequestMapping("/qaliwarmaUtilitarios")
public class QaliwarmaUtilitarios {
	
	
	@Autowired
	RutaDistribucionService distribucionService;
	
	@Autowired
	RutaDistribucionDetalleService rutaDistribucionDetalleService;
	
	@Autowired
	RequerimientoVolumen001Service requerimientoVolumen001Service;	
	
	@RequestMapping("/actulizaIdDistribucion")
	public void actuliza() {
		
		List<RutaDistribucion> lstRutaDistribucion = distribucionService.getRutaDistribucionByAnnoAndNumeroEntrega(2018, 7);
		
		List<RutaDistribucionDetalle> lstRutaDistribucionDetalle = new ArrayList<>() ;
		Integer cntDetalle = 0;
		for(RutaDistribucion rutaDistribucionRow:lstRutaDistribucion ) {
			String id = rutaDistribucionRow.getIdRutaDistribucion();
			
			lstRutaDistribucionDetalle = rutaDistribucionRow.getRutaDistribucionDetalles();
			
			for(RutaDistribucionDetalle rutaDistribucionDetalleRow:lstRutaDistribucionDetalle ) {
				
				cntDetalle++;
				System.out.println("Total detalles :" + cntDetalle);
				String id_requerimiento_volumen_001 = rutaDistribucionDetalleRow.getRequerimientoVolumen001().getIdRequerimientoVolumen001();
				if(id_requerimiento_volumen_001.equals("220103-0274142-2018-6-MOYOBAMBA 1-RA3_AMAZONIA_ALTA")) {
					System.out.println("Alto");
				}
				id_requerimiento_volumen_001 = id_requerimiento_volumen_001.replace("2018-6", "2018-7");
				System.out.println(id_requerimiento_volumen_001);
				RequerimientoVolumen001 requerimientoVolumen001 = requerimientoVolumen001Service.findbyid(id_requerimiento_volumen_001).get();
				if(requerimientoVolumen001 == null) {
					System.out.println("No existe el codigo : " + requerimientoVolumen001);
				}else {
					rutaDistribucionDetalleRow.setRequerimientoVolumen001(requerimientoVolumen001);
				}
				
				rutaDistribucionDetalleService.grabar(rutaDistribucionDetalleRow);
				
			}
		}
		System.out.println("Fin procedimeinto .....");
		
	}
	

}
