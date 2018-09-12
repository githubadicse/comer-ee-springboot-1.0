package com.adicse.comercial.utilitarios;

import org.springframework.beans.factory.annotation.Autowired;

import com.adicse.comercial.service.RequerimientoVolumen001Service;
import com.adicse.comercial.service.RutaDistribucionDetalleService;
import com.adicse.comercial.service.RutaDistribucionService;

public class ActulizarIdDistribucionDetalle {
	
	@Autowired
	static
	RutaDistribucionService distribucionService;
	
	@Autowired
	static
	RutaDistribucionDetalleService rutaDistribucionDetalleService;
	
	@Autowired
	static
	RequerimientoVolumen001Service requerimientoVolumen001Service;
	

	
	public static void main(String[] args)  {
		
	


	}
	
	
	
//	public static void actuliza() {
//		
//		List<RutaDistribucion> lstRutaDistribucion = distribucionService.getRutaDistribucionByAnnoAndNumeroEntrega(2018, 7);
//		
//		List<RutaDistribucionDetalle> lstRutaDistribucionDetalle = new ArrayList<>() ;
//		
//		for(RutaDistribucion rutaDistribucionRow:lstRutaDistribucion ) {
//			String id = rutaDistribucionRow.getIdRutaDistribucion();
//			
//			lstRutaDistribucionDetalle = rutaDistribucionRow.getRutaDistribucionDetalles();
//			
//			for(RutaDistribucionDetalle rutaDistribucionDetalleRow:lstRutaDistribucionDetalle ) {
//				String id_requerimiento_volumen_001 = rutaDistribucionDetalleRow.getRequerimientoVolumen001().getIdRequerimientoVolumen001();
//				id_requerimiento_volumen_001 = id_requerimiento_volumen_001.replace("2018-6", "2018-7");
//				
//				RequerimientoVolumen001 requerimientoVolumen001 = requerimientoVolumen001Service.findbyid(id_requerimiento_volumen_001).get();
//				if(requerimientoVolumen001 == null) {
//					System.out.println("No existe el codigo : " + requerimientoVolumen001);
//				}else {
//					rutaDistribucionDetalleRow.setRequerimientoVolumen001(requerimientoVolumen001);
//				}
//				
//				rutaDistribucionDetalleService.grabar(rutaDistribucionDetalleRow);
//				
//			}
//		}
//		
//	}

}
