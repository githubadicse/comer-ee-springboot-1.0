package com.adicse.comercial.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.clases.GuiaRemision001Class;
import com.adicse.comercial.clases.GuiasResumenPorNumeroUsuarioAndItemClass;
import com.adicse.comercial.clases.ValorizacionEntregaClass;
import com.adicse.comercial.dto.ProgressDetails;
import com.adicse.comercial.dto.QaliwarmaUtil;
import com.adicse.comercial.dto.QaliwarmaUtil.ExcepcionDosMarcasSinPiking;
import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.CierreInstitucion;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.model.Matriz;
import com.adicse.comercial.model.RequerimientoVolumen001;
import com.adicse.comercial.model.RequerimientoVolumen002;
import com.adicse.comercial.model.RequerimientoVolumen002Producto;
import com.adicse.comercial.model.VolumenConvertidoEnvace;
import com.adicse.comercial.service.CierreInstitucionService;
import com.adicse.comercial.service.EntregaPorItemService;
import com.adicse.comercial.service.GuiaRemision001Service;
import com.adicse.comercial.service.ItemEntregaService;
import com.adicse.comercial.service.MatrizService;
import com.adicse.comercial.service.NumeroEntregaService;
import com.adicse.comercial.service.RequerimientoVolumen001Service;
import com.adicse.comercial.service.RequerimientoVolumen002ProductoService;
import com.adicse.comercial.service.RequerimientoVolumen002Service;
import com.adicse.comercial.service.VolumenConvertidoEnvaseService;
import com.adicse.comercial.viewResolver.ControlDespachoXls;
import com.adicse.comercial.viewResolver.ExcelPlanRastreabilidad;
import com.adicse.comercial.viewResolver.ExcelVolumenesPorItem;
import com.adicse.comercial.viewResolver.ExcelVolumenesPorItemPeso;

@RestController
@RequestMapping("/res/qaliwarma")
public class QaliwarmaController {

	@Autowired
	private QaliwarmaUtil qaliwarmaUtil;

	@SuppressWarnings("unused")
	@Autowired
	private NumeroEntregaService numeroEntregaService;

	@Autowired
	private VolumenConvertidoEnvaseService volumenConvertidoEnvaseService;

	@Autowired
	private EntregaPorItemService entregaPorItemService;

	@Autowired
	private ItemEntregaService itemEntregaService; 

	@Autowired
	private RequerimientoVolumen001Service requerimientoVolumen001Service;

	@Autowired
	private RequerimientoVolumen002Service requerimientoVolumen002Service;

	@Autowired
	private RequerimientoVolumen002ProductoService requerimientoVolumen002ProductoService;
	
	@Autowired
	private MatrizService matrizService;
	
	@Autowired
	private GuiaRemision001Service guiaRemision001Service; 
	
	@Autowired
	private CierreInstitucionService cierreInstitucionSerive;

	@RequestMapping("/calcular")
	@ResponseBody
	public Map<String, Object> calcularVolumenPresentacion(@RequestParam("taskIdentity") String taskIdentity,
			@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega) {
		
		
		//actualizamos los codigos modulares que estan cerrados.
		List<CierreInstitucion> lstCierreInstitucion = cierreInstitucionSerive.getall();
		Set<String> lstString = new HashSet<String>();
		
		for(CierreInstitucion row:lstCierreInstitucion) {
			lstString.add(row.getCodigoModular());
		}
		
		requerimientoVolumen001Service.updateCierre(lstString);
		
		//generamos el calculo
		qaliwarmaUtil.calcularVolumenPorPresentacion(anno,numeroEntrega);
		
		
		
		//Actualizamos el total en requerimientoVolumen001
		System.out.println("Iniciando actualizacion peso en requerimiento Volumen 001");
		qaliwarmaUtil.ActualizaPesoTotalRequerimientoVolumen001(anno, numeroEntrega);
		
		System.out.println("Fin peso en requerimiento Volumen 001 ........................");
		
		
		
		@SuppressWarnings("unused")
		CatalogoProductoQaliwarma catalogoProductoQaliwarma = new CatalogoProductoQaliwarma();

		return null;
	}
	
	//public void actualizaPesoEnRequerimientoVolumen001()

	@RequestMapping("/run")
	@ResponseBody
	public Map<String, Object> run(@RequestParam("taskIdentity") String taskIdentity,
			@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntregas) {

		System.out.println("Iniciando proceso run,,,, primero elimina todo ..... en cascada por bd");
		qaliwarmaUtil.deleteTablasParaRecargarDatos(anno, numeroEntregas);
		String archivo = null;// "D:\\archivos\\qaliwarma\\PARDOMIGUEL_SANMARTIN1_SANMARTIN_P1.xlsx";

		FileInputStream file = null;
		// Integer anno = 2018;
		// Integer numeroEntregas = 1; //solo se trabaja con la
		@SuppressWarnings("unused")
		Integer desdeLaEntrega = 1;
		Integer fila = 10;

		try {

			// Antes que nada validamos si los productos del catalogo qaliwarma
			// se encuentran relacionados en su totalidad en la tabla productos de
			// comercial.
			//
			// List<CatalogoProductoQaliwarma> lst =
			// qaliwarmaUtil.validarProductosRelacionados();
			//
			// if(lst.size() > 0) {
			// System.out.println("Existen producto sin relacionar con el maestro de
			// productos comercial");
			//
			// for(CatalogoProductoQaliwarma row:lst) {
			// System.out.println("Producto : " + row.getDscCatalogoProductoQaliwarma());
			// }
			// return null;
			// }
			ProgressDetails taskProgress = new ProgressDetails();
			ProgressDetails.taskProgressHash.put(taskIdentity, taskProgress);
			boolean flagRun = false;
			for (int i = 1; i < 9; i++) {
				ProgressDetails.taskProgressHash.get(taskIdentity).setTotalProcessed(i);

				switch (i) {
				case 1:
					archivo = "D:\\archivos\\qaliwarma\\AWAJUN_SANMARTIN1_SANMARTIN_P1.xlsx";

					flagRun = true;
					break;
				case 2:
					archivo = "D:\\archivos\\qaliwarma\\ELIASSOPLINVARGAS1_SANMARTIN1_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;
				case 3:
					archivo = "D:\\archivos\\qaliwarma\\JEPELACIO_SANMARTIN1_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;
				case 4:
					archivo = "D:\\archivos\\qaliwarma\\MORALES_SANMARTIN2_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;
				case 5:
					archivo = "D:\\archivos\\qaliwarma\\MOYOBAMBA1_SANMARTIN1_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;
				case 6:
					archivo = "D:\\archivos\\qaliwarma\\NUEVACAJAMARCA_SANMARTIN1_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;
				case 7:
					archivo = "D:\\archivos\\qaliwarma\\PARDOMIGUEL_SANMARTIN1_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;
				case 8:
					archivo = "D:\\archivos\\qaliwarma\\SORITOR_SANMARTIN1_SANMARTIN_P1.xlsx";
					flagRun = true;
					break;

				}
				ProgressDetails.taskProgressHash.get(taskIdentity).setMsgExtra(archivo);
				if (flagRun) {
					System.out.println("Escribir productos por entrega");
					file = qaliwarmaUtil.cargarArchivoStream(archivo);
					qaliwarmaUtil.cargarTablaProductoPorNumeroEntrega(file, "Resumen", 18, anno, numeroEntregas);

					file.close();
					file = null;

					System.out.println("Leer Datos de Ubigeo");
					file = qaliwarmaUtil.cargarArchivoStream(archivo);
					qaliwarmaUtil.leerUbigeo(file, "Detalle", fila, 2);
					file.close();

					System.out.println("Leer Region Alimentaria");
					file = qaliwarmaUtil.cargarArchivoStream(archivo);
					qaliwarmaUtil.leerRegionAlimentaria(file, "Detalle", fila, 4, anno, numeroEntregas);
					file.close();

					System.out.println("Leer Datos de Institucion");
					file = qaliwarmaUtil.cargarArchivoStream(archivo);
					qaliwarmaUtil.leerDatosIntitucion(file, "Detalle", fila, 5);

					System.out.println("Leer Requerimiento 001");
					file = qaliwarmaUtil.cargarArchivoStream(archivo);
					qaliwarmaUtil.leerRequerimientoVolumen001(file, "Detalle", fila, 5, anno, numeroEntregas);
					file.close();

					System.out.println("Escribir detalle requerimeinto002");
					file = qaliwarmaUtil.cargarArchivoStream(archivo);
					qaliwarmaUtil.escribirRequerimientoVolumen002(file, "Detalle", fila, anno, numeroEntregas);
					file.close();

					// System.out.println("Escribir detalle requerimeinto002");
					// file = qaliwarmaUtil.cargarArchivoStream(archivo);
					// qaliwarmaUtil.escribirRequerimientoVolumen002Producto(file, "Detalle", fila,
					// anno, numeroEntregas);
					// file.close();

					flagRun = false;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qaliwarmaUtil.cargarCatalogoMarca(anno, numeroEntregas);
		System.out.println("fin de procedimiento .............................................................");
		return null;

	}

	@RequestMapping("/volumen")
	@ResponseBody
	public List<VolumenConvertidoEnvace> getVolumen(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega, @RequestParam("item") String item) {

		List<EntregaPorItem> lstEntregaPorItem = entregaPorItemService.getListEntregaPorItemByAnno(anno, item,
				numeroEntrega);

		List<VolumenConvertidoEnvace> lstVolumen = volumenConvertidoEnvaseService.getVolumenByItem(lstEntregaPorItem);

		return lstVolumen;
	}
	
	@RequestMapping("/matrizPeso")
	@ResponseBody
	public ModelAndView getMatrizPeso(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega

	) {
		
		List<Matriz> lstMatriz = matrizService.getMatrizByAnnoAndNumeroEntrega(anno, numeroEntrega);
		
		
		
		Map<String, Object> response = new HashMap<>();
		response.put("data", lstMatriz);
		response.put("anno", anno);
		response.put("numeroEntrega", numeroEntrega);

		ModelAndView model = new ModelAndView(new ExcelVolumenesPorItemPeso(), response);
		return model;
		
	}
	
	

	@RequestMapping("/volumenPesoXls")
	@ResponseBody
	public ModelAndView getVolumenPeso(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega

	) {

		System.out.println("Iniciando carga para reporte excel....");

		// List<ItemEntrega> lstItems =
		// itemEntregaService.getItemsEntrega(numeroEntrega, anno);
		List<EntregaPorItem> lstEntregaPorItem = entregaPorItemService.getListEntregaPorAnnoAndNumeroEntrega(anno, numeroEntrega);

		//lstEntregaPorItem.sort( (EntregaPorItem e1,EntregaPorItem e2)-> Integer.parseInt( e1.getIdEntregaPorItem()) .compareTo( Integer.parseInt(e2.getIdEntregaPorItem())) ); 
		
			for (EntregaPorItem entregaPorItem : lstEntregaPorItem) {

				List<RequerimientoVolumen001> lstRequerimientoVolumen001 = requerimientoVolumen001Service
						.getReqVol001ByAnnoItem(entregaPorItem.getItemEntrega().getAnno(), entregaPorItem.getItemEntrega().getItem());

				System.out.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem() + " Req. Vol 1 ");
				for (RequerimientoVolumen001 requerimientoVolumen001 : lstRequerimientoVolumen001) {
					List<RequerimientoVolumen002> lstRequerimientoVolumen002 = requerimientoVolumen002Service
							.getRequerimientoVolumen002ByIdR1(requerimientoVolumen001.getIdRequerimientoVolumen001(),
									numeroEntrega);

					System.out.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem() + " Req. Vol 2 ");
					for (RequerimientoVolumen002 requerimientoVolumen002 : lstRequerimientoVolumen002) {

						System.out.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem() + " Req. Vol Producto ");

						List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002Producto = requerimientoVolumen002ProductoService
								.getRequerimientoVolumen002ProductoByIdRequerimientoVolumen002(
										requerimientoVolumen002.getIdRequerimientoVolumen002());

						lstRequerimientoVolumen002Producto
								.sort((RequerimientoVolumen002Producto r1, RequerimientoVolumen002Producto r2) -> r1
										.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma()
										.getDscCatalogoProductoQaliwarma().compareTo(r2.getProductoPorNumeroEntrega()
												.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()));

						requerimientoVolumen002.setRequerimientoVolumen002Productos(lstRequerimientoVolumen002Producto);

					}

					requerimientoVolumen001.setRequerimientoVolumen002s(lstRequerimientoVolumen002);
				}
				entregaPorItem.setRequerimientoVolumen001s(lstRequerimientoVolumen001);
				
			}
			

		System.out.println("Iniciando pase para crearcion de archivo excel despues de generar la lista total....");

		Map<String, Object> response = new HashMap<>();
		response.put("data", lstEntregaPorItem);
		response.put("anno", anno);
		response.put("numeroEntrega", numeroEntrega);

		ModelAndView model = new ModelAndView(new ExcelVolumenesPorItem(), response);
		return model;

	}

	@RequestMapping("/deleteall")
	@ResponseBody
	public void deleteall(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega) {
		System.out.println("Eliminando ....");
		qaliwarmaUtil.deleteTablasParaRecargarDatos(anno, numeroEntrega);
		System.out.println("Fin Eliminando ....");
	}

	@RequestMapping("/cargarCatalogoMarca")
	@ResponseBody
	public void productoMarca(@RequestParam("anno") Integer anno, @RequestParam("nroEntrega") Integer nroEntregas) {

		System.out.println("Iniciando carga ....");
		qaliwarmaUtil.cargarCatalogoMarca(anno, nroEntregas);
		System.out.println("Fin Carga marcas ....");
	}

	@RequestMapping("/crearGuiaRemision")
	@ResponseBody
	public void crearGuiaRemision(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega,
			@RequestParam("idproveedorcliente") Integer idproveedorcliente, @RequestParam("serie") Integer serie, @RequestParam("numeroGuiaInicio") Integer numeroGuiaInicio) throws ExcepcionDosMarcasSinPiking {
		System.out.println("Inicialdo creacion");
		qaliwarmaUtil.crearGuiasDeRemision(anno, numeroEntrega, idproveedorcliente, serie,numeroGuiaInicio);
		System.out.println("Fin creacion ....");
	}

	// =====================================================================================================//
	// PROCESO LEER ARCHIVO PARA REVISAR EL AVANCE DESDE EL CLIENTE //
	// =====================================================================================================//

	@ResponseBody
	@RequestMapping(value = "/leerarchivoproceso")
	public Map<String, Object> leerArchivoProceso(@RequestParam("taskIdentity") String taskIdentity) {
		// System.out.println("leerarchivos ....");
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("total", 0);
		response.put("totalProcessed", 0);
		response.put("value", 0);
		response.put("msgExtra", "");
		@SuppressWarnings("unused")
		BigDecimal porcentaje;
		Integer total;
		Integer totalProcessed;
		try {
			ProgressDetails taskProgress = ProgressDetails.taskProgressHash.get(taskIdentity);
			if (taskProgress == null) {
				response.put("success", false);
				System.out.println("leerarchivos .... false");
			}
			total = taskProgress.getTotal();
			totalProcessed = taskProgress.getTotalProcessed();
			porcentaje = new BigDecimal(totalProcessed.doubleValue() / total.doubleValue());

			response.put("success", true);
			response.put("total", taskProgress.getTotal());
			response.put("totalProcessed", taskProgress.getTotalProcessed());
			response.put("value", taskProgress.getValue());
			response.put("porcentaje", taskProgress.getPorcentaje());
			response.put("msgExtra", taskProgress.getMsgExtra());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}
	
	
	@RequestMapping("/corregir")
	@ResponseBody
	public void corregir(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega,@RequestParam("item") String item) {
		System.out.println("Eliminando ....");
		qaliwarmaUtil.corregirMoyobamba(anno, numeroEntrega, item);
		System.out.println("Fin Eliminando ....");
		
		
	}
	
	@RequestMapping("/deleteVolumenConvertidoByAnnoAndNumeroEntrega")
	@ResponseBody
	public void deleteVolumenConvertidoByAnnoAndNumeroEntrega(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega) {
		System.out.println("Inicioando eliminacion ");
		volumenConvertidoEnvaseService.deleteByAnnoNumeroEntregaFijo(anno, numeroEntrega);
//		Set<String> idEntregaPorItem = new HashSet<>() ;
//		
//		List<EntregaPorItem> lstEntregaPorItem = entregaPorItemService.getListEntregaPorAnnoAndNumeroEntrega(anno, numeroEntrega);
//		
//		for(EntregaPorItem row : lstEntregaPorItem) {
//			idEntregaPorItem.add(row.getIdEntregaPorItem());
//		}
//		System.out.println("Inicioando eliminacion ");
//		
//		
//		volumenConvertidoEnvaseService.deleteByAnnoNumeroEntrega(idEntregaPorItem);
		System.out.println("Fin eliminacion ....");
	}
	
	
	@RequestMapping("/controlDeDespacho")
	@ResponseBody	
	public ModelAndView controlDeDespacho(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega) {
		
		List<GuiaRemision001Class> lstGuiaRemision = guiaRemision001Service.getGuiaRemisionPorAnoNumeroEntrega(anno, numeroEntrega);
		
		List<ItemEntrega> lstItemEntrega = itemEntregaService.getItemByAnno(anno);
		
		Map<String, Object> response = new HashMap<>();
		response.put("data", lstGuiaRemision);
		response.put("dataItemEntrega", lstItemEntrega);
		response.put("numeroEntrega", numeroEntrega);

		ModelAndView model = new ModelAndView(new ControlDespachoXls(), response);
		return model;
		
	}
	
	@RequestMapping("/planRastreabilidad")
	@ResponseBody	
	public ModelAndView planRastreabilidad(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega) {
		
		List<GuiaRemision001Class> lstGuiaRemision = guiaRemision001Service.getGuiaRemisionPorAnoNumeroEntrega(anno, numeroEntrega);
		
		List<ItemEntrega> lstItemEntrega = itemEntregaService.getItemByAnno(anno);
		
		Map<String, Object> response = new HashMap<>();
		response.put("data", lstGuiaRemision);
		response.put("dataItemEntrega", lstItemEntrega);
		response.put("numeroEntrega", numeroEntrega);

		ModelAndView model = new ModelAndView(new ExcelPlanRastreabilidad(), response);
		return model;
		
	}	
	
	@RequestMapping("/guiaValorizacionResumen")
	@ResponseBody	
	public List<ValorizacionEntregaClass> getGuiasResumenPorNumeroUsuarioAndItem(@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega){
		
		ValorizacionEntregaClass valorizacionEntregaClass = null;
		List<ValorizacionEntregaClass> lstValorizacion = new ArrayList<ValorizacionEntregaClass>();
		List<ItemEntrega> lstItems = itemEntregaService.getItemByAnno(anno);
		
		
		List<GuiasResumenPorNumeroUsuarioAndItemClass> lst = null;
		for(ItemEntrega row:lstItems) {
			String item = row.getItem();
			valorizacionEntregaClass = new ValorizacionEntregaClass();
			
			lst = guiaRemision001Service.getGuiasResumenPorNumeroUsuariosAndItem(item, numeroEntrega);
			for(GuiasResumenPorNumeroUsuarioAndItemClass g:lst) {
				g.getEntregaPorItem().setProductoPorNumeroEntregas(null);
				g.getItemEntrega().setEntregaPorItems(null);
				//g.getEntregaPorItem().setNumeroEntrega(null);
			}
			valorizacionEntregaClass.setListaValorizacion(lst);
			valorizacionEntregaClass.setNombreItem(lst.get(0).getItemEntrega().getDscitem());
			lstValorizacion.add(valorizacionEntregaClass);		
		}
		
	
		
		return lstValorizacion;
		
	}
	
	@RequestMapping("/pasarutas")
	public void pasaRutaDistribucion() {
		qaliwarmaUtil.pasarRutaSiguienteEntrega();
	}

	@RequestMapping("/cargarmatriz")
	public void pasaRutaDistribucion(@RequestParam("anno") Integer anno,@RequestParam("numeroEntrega") Integer numeroEntrega) {
		qaliwarmaUtil.cargarTablaMatriz(anno,numeroEntrega);
	}
	
	@RequestMapping("/actualizaLatitudLongitud")
	public void actualizaLatitudLongitud() throws IOException {
		FileInputStream file = null;
		String archivo = "D:\\archivos\\qaliwarma\\PADRON_IE.xlsx";
		System.out.println("Actualiza latitud y longitud ........");
		file = qaliwarmaUtil.cargarArchivoStream(archivo);
		qaliwarmaUtil.actualizaLatitudLongitud(file );
		
	}
}
