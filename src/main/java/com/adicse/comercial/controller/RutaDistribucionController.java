package com.adicse.comercial.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.clases.AcumuladoRutaDistribucionClass;
import com.adicse.comercial.clases.AcumuladoRutaDistribucionPeso;
import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.model.ProductoPresentacion;
import com.adicse.comercial.model.RutaDistribucion;
import com.adicse.comercial.model.RutaDistribucionDetalle;
import com.adicse.comercial.service.CatalogoProductoQaliwarmaService;
import com.adicse.comercial.service.GuiaRemision001Service;
import com.adicse.comercial.service.RutaDistribucionService;
import com.adicse.comercial.viewResolver.ExcelRutaDistribucionAcumulado;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/res/rutaDistribucion")
public class RutaDistribucionController {
	
	@Autowired
	private RutaDistribucionService rutaDistribucionService; 
	
//	@Autowired
//	private MatrizService matrizService;
//	
//	@Autowired
//	private ProductoPorNumeroEntregaService productoPorNumeroEntregaService;
	
	@Autowired
	private GuiaRemision001Service guiaRemision001Service;
	
	@Autowired
	private CatalogoProductoQaliwarmaService catalogoProductoQaliwarma;

	public boolean existe= false;
	public AcumuladoRutaDistribucionClass acumuladoRutaDistribucionClass=null;
	public AcumuladoRutaDistribucionPeso acumuladoRutaDistribucionPeso=null;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filter,
			@RequestParam("paramsExtra") Object paramsExtra
			) throws JsonProcessingException{
		
	

		Page<RutaDistribucion> page = rutaDistribucionService.paginationParmsExtra (pagenumber, rows, sortdireccion, sortcolumn, filter,paramsExtra);
		
		List<RutaDistribucion> lst = page.getContent() ;
		
	
	        
		Map<String,Object> map = Map.ofEntries(
				new AbstractMap.SimpleEntry<> ("data",lst),
				new AbstractMap.SimpleEntry<> ("totalCount",page.getTotalElements()),
				new AbstractMap.SimpleEntry<> ("success",true)
				);
		
//		response.put("data", lstFinal);
//		response.put("totalCount", page.getTotalElements());
//		response.put("success", true);		
		System.out.println("Esta paginacion es de Ruta de distribucion");
		
		return map;
				
	}	
	
	@RequestMapping("/edit")
	@ResponseBody
	public RutaDistribucion getEdit(@RequestParam("id") String id) {
		return rutaDistribucionService.findbyid(id).get();
	}
	
	@RequestMapping("/editDetalle")
	@ResponseBody
	public RutaDistribucion getEditDetalle(@RequestParam("id") String id) {
		
		
		RutaDistribucion rutaDistribucion = rutaDistribucionService.findbyid(id).get();
		
		for(RutaDistribucionDetalle rutaDistribucionDetalle: rutaDistribucion.getRutaDistribucionDetalles()) {
			rutaDistribucionDetalle.setRutaDistribucion(null);
		}
		return rutaDistribucion;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public RutaDistribucion postCreate(@RequestBody RutaDistribucion rutaDistribucion) {
		
		return rutaDistribucionService.grabar(rutaDistribucion);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public RutaDistribucion putUdate(@RequestBody RutaDistribucion rutaDistribucion) {
		if(rutaDistribucion.getChofer().getIdChofer() ==null || rutaDistribucion.getChofer().getIdChofer() == 0) {
			rutaDistribucion.setChofer(null);
		}
		if(rutaDistribucion.getVehiculo().getIdVehiculo() == null || rutaDistribucion.getVehiculo().getIdVehiculo() == 0) {
			rutaDistribucion.setVehiculo(null);
		}
		
		RutaDistribucion rutaDistribucionUpdate = rutaDistribucionService.findbyid(rutaDistribucion.getIdRutaDistribucion()).get();
		
		BeanUtils.copyProperties(rutaDistribucion, rutaDistribucionUpdate);
		
		RutaDistribucion rutaDistribucionUpdatex = rutaDistribucionService.grabar(rutaDistribucionUpdate);
		
		return rutaDistribucionUpdatex;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id){	
		
		rutaDistribucionService.deletebyid(id);
	}
	
	@RequestMapping("/getpoints")
	@ResponseBody
	public String getCodigoPointByRutaDistribucion(@RequestParam("id") String id) {
		
		RutaDistribucion rutaDistribucion = rutaDistribucionService.findbyid(id).get();
		
		List<RutaDistribucionDetalle> lst = rutaDistribucion.getRutaDistribucionDetalles();
		
			
	
		
		 JSONObject featureCollection = new JSONObject();
		    try {
		        featureCollection.put("type", "FeatureCollection");
		        //proyeccion
		        JSONObject proyeccion = new JSONObject();
		        JSONObject proyeccionName = new JSONObject();
		        
		        proyeccionName.put("name", "4326");
		        proyeccion.put("properties",proyeccionName );
		        proyeccion.put("type","name" );
		        featureCollection.put("crs", proyeccion);
		        
		        JSONArray featureList = new JSONArray();
		        // iterate through your list
		        for (RutaDistribucionDetalle obj : lst) {
		            // {"geometry": {"type": "Point", "coordinates": [-94.149, 36.33]}
		            JSONObject point = new JSONObject();
		            point.put("type", "Point");
		            // construct a JSONArray from a string; can also use an array or list
		            JSONArray coord = new JSONArray("["+obj.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getLongitud() +","+obj.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getLatitud() +"]");
		            point.put("coordinates", coord);
		            JSONObject feature = new JSONObject();
		            feature.put("type", "Feature");
		            feature.put("geometry", point);
		            feature.put("id", obj.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular());
		            
		            
		            JSONObject propiedades = new JSONObject();
		            propiedades.put("codigomodular", obj.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular());
		            
		            feature.put("properties", propiedades);
		            featureList.put(feature);
		            featureCollection.put("features", featureList);
		        }
		    } catch (JSONException e) {
		        System.out.println("can't save json object: "+e.toString());
		    }
		
	System.out.println(featureCollection.toString());
		
		return featureCollection.toString();
	}

	
	//Creat tabla de productos de distribucion acumulados por nombre del producto
	@RequestMapping("/BuildRutaDistribucion")
	public ModelAndView BuildRutaDistribucion001(@RequestParam("id") String idRutaDistribucion) {
		
		Set<String> ids = new HashSet<String>();
		
		Integer anno,numeroEntrega;
		
		
		RutaDistribucion rutaDistribucion = rutaDistribucionService.findbyid(idRutaDistribucion).get();
		
		anno = rutaDistribucion.getAnno();
		numeroEntrega = rutaDistribucion.getNumeroEntrega();
		
		for(RutaDistribucionDetalle rutaDistribucionDetalle:rutaDistribucion.getRutaDistribucionDetalles()) {
			ids.add(rutaDistribucionDetalle.getRequerimientoVolumen001().getIdRequerimientoVolumen001());
			//System.out.println("id : " + rutaDistribucionDetalle.getRequerimientoVolumen001().getIdRequerimientoVolumen001());
			
		}
		
		//preparamos la clase donde se va a grabar los acumulados
		AcumuladoRutaDistribucionClass acumuladoRutaDistribucionClass = new AcumuladoRutaDistribucionClass();
		List<AcumuladoRutaDistribucionClass> lstAcumuladoRutaDistribucionClass = new ArrayList<>();
		List<AcumuladoRutaDistribucionPeso> lstAcumuladoRutaDistribucionPeso = new ArrayList<>();
		//preparamos la lista de productos por entrega
		
		List<CatalogoProductoQaliwarma> lstCatalogoProducto = catalogoProductoQaliwarma.getall();
		
		//recorremos los productos para el año y el numero de entrega
		for(CatalogoProductoQaliwarma catalogoProductoQaliwarma:lstCatalogoProducto ) {
			
			List<ProductoPresentacion> lstProductoPresentacionsAux =  new ArrayList<ProductoPresentacion>();
			for(ProductoPresentacion rowProductoPresentacion : catalogoProductoQaliwarma.getProductoPresentacions() ) {
				if(rowProductoPresentacion.getAnno().equals(anno) && rowProductoPresentacion.getNumeroEntrega().equals(numeroEntrega)) {
					lstProductoPresentacionsAux.add(rowProductoPresentacion);
				}
				
			}
			lstProductoPresentacionsAux.sort((a,b)-> b.getCantidadPresentacion().compareTo(a.getCantidadPresentacion()));
			
			//ahora buscamos la presentacion del producto para eso obtenemos la presentacion 
			for(ProductoPresentacion productoPresentacion : lstProductoPresentacionsAux ) {
				
				
		
				//List<ProductoPresentacion> lstProductoPresentacions = 
				
				//buscamos la guia de remision para obtener las cantidades segun los ids
				for(String id:ids) {
					GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemision001ByIdRequerimientoVolumen001(id);
					String codigoModular = guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular();
					Double pesoGuia = 0.0;
		
					
					for(GuiaRemision002 guiaRemision002: guiaRemision001.getGuiaRemision002s()) {
						pesoGuia=pesoGuia + guiaRemision002.getPesoTotal().doubleValue();
						String idProductoPresentacion = guiaRemision002.getVolumenConvertidoEnvace().getProductoPresentacion().getIdProductoPresentacion();
						Boolean igual = idProductoPresentacion.equals(productoPresentacion.getIdProductoPresentacion());
						if(igual) {
												
							
							Boolean existe = false;
							existe = this.findInAcumulado(catalogoProductoQaliwarma.getIdCatalogoProductoQaliwarma(),idProductoPresentacion, lstAcumuladoRutaDistribucionClass);
							
							if(!existe) {
								acumuladoRutaDistribucionClass = new AcumuladoRutaDistribucionClass();
								acumuladoRutaDistribucionClass.setIdCatalogoProducto(catalogoProductoQaliwarma.getIdCatalogoProductoQaliwarma());
								acumuladoRutaDistribucionClass.setIdProductoPresentacion(idProductoPresentacion);
								acumuladoRutaDistribucionClass.setGrupo(guiaRemision002.getProductoGrupo());
								acumuladoRutaDistribucionClass.setLote( guiaRemision002.getNumeroLote()+ ";");
								acumuladoRutaDistribucionClass.setAlimentoSeleccionado(guiaRemision002.getProductoSeleccionado() );
								
								acumuladoRutaDistribucionClass.setCantidad(guiaRemision002.getCantidad().doubleValue());
								acumuladoRutaDistribucionClass.setPeso(guiaRemision002.getPesoTotal().doubleValue());
								
								//acumuladoRutaDistribucionClass.setCodigoModular("cm:"+codigoModular+ " cant:" +guiaRemision002.getCantidad().toString()+"; ");
								acumuladoRutaDistribucionClass.setFactor(guiaRemision002.getFactor().doubleValue());
								
								acumuladoRutaDistribucionClass.setDetalle("cm:"+codigoModular+ " cant:" +guiaRemision002.getCantidad().toString()+"; ");
								acumuladoRutaDistribucionClass.setCantidadLote(guiaRemision002.getCantidad().toString()+";" );
								
								lstAcumuladoRutaDistribucionClass.add(acumuladoRutaDistribucionClass);
								
								
							}else {
								acumuladoRutaDistribucionClass = this.getInAcumulado(catalogoProductoQaliwarma.getIdCatalogoProductoQaliwarma(), idProductoPresentacion, lstAcumuladoRutaDistribucionClass);
								Double cantidad = acumuladoRutaDistribucionClass.getCantidad();
								Double peso = acumuladoRutaDistribucionClass.getPeso();
								String detalle = acumuladoRutaDistribucionClass.getDetalle();
								
								acumuladoRutaDistribucionClass.setCantidad(guiaRemision002.getCantidad().doubleValue() + cantidad);
								acumuladoRutaDistribucionClass.setPeso(guiaRemision002.getPesoTotal().doubleValue() + peso );	
								acumuladoRutaDistribucionClass.setDetalle(detalle + " cm:"+codigoModular+ " cant:" +guiaRemision002.getCantidad().toString()+"; ");
								
								//buscamos si el numero lote si ya esta, lo buscamos.
								Integer pos = 0;
								if(guiaRemision002.getNumeroLote() != null ) {
									pos = this.siExisteLoteRegristrado( guiaRemision002.getNumeroLote() );
									if( pos != -1) {									
									
										String[] arrayCantidadLotes;
										
										arrayCantidadLotes = acumuladoRutaDistribucionClass.getCantidadLote().split(";");
										
										Double cantidadLote ;
										cantidadLote = Double.parseDouble( arrayCantidadLotes[pos]) ;
										cantidadLote = cantidadLote + guiaRemision002.getCantidad().doubleValue();
										
										arrayCantidadLotes[pos] = cantidadLote.toString();
										
										String sArray = this.convertirArrayToStringWith(arrayCantidadLotes);
										this.acumuladoRutaDistribucionClass.setCantidadLote(sArray);
										
									}else {
										//con el numero de lote.
										String[] arrayLotes = new String[] {};
										arrayLotes = acumuladoRutaDistribucionClass.getLote().split(";");
										
										List<String> lstString = this.convertirArrayToListString(arrayLotes);
										String nLote = guiaRemision002.getNumeroLote().toString();
										lstString.add(nLote);
										
										arrayLotes = this.convertirListStringToArray(lstString);
										
										String sArray = this.convertirArrayToStringWith(arrayLotes);
										this.acumuladoRutaDistribucionClass.setLote(sArray);
										
										//con las cantidades acumuladas
										String[] arrayCantidadLotes;
										
										arrayCantidadLotes = acumuladoRutaDistribucionClass.getCantidadLote().split(";");
										
										List<String> lstStringCantidades = this.convertirArrayToListString(arrayCantidadLotes);
										
										Double cantidadLote ;										
										cantidadLote = guiaRemision002.getCantidad().doubleValue();
										lstStringCantidades.add(cantidadLote.toString());
										
										arrayCantidadLotes = this.convertirListStringToArray(lstStringCantidades);
										
										String sArrayCantidad = this.convertirArrayToStringWith(arrayCantidadLotes);
										this.acumuladoRutaDistribucionClass.setCantidadLote(sArrayCantidad);
										
										
									}
								}
								
							}
							
						}
	
	
					}
	
					
					Boolean existePeso = false;
					existePeso = this.findInAcumuladoPeso(codigoModular , lstAcumuladoRutaDistribucionPeso);
					if(!existePeso) {
						acumuladoRutaDistribucionPeso = new AcumuladoRutaDistribucionPeso();
						acumuladoRutaDistribucionPeso.setCentroPoblado(guiaRemision001.getRequerimientoVolumen001().getCentroPoblado());
						acumuladoRutaDistribucionPeso.setCodigoModular(codigoModular);
						acumuladoRutaDistribucionPeso.setItemDsc(guiaRemision001.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega().getItem());
						acumuladoRutaDistribucionPeso.setNombreId(guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getNombreInstitucionEducativa());
						acumuladoRutaDistribucionPeso.setDireccion(guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getDireccionInstitucionEducativa());
						acumuladoRutaDistribucionPeso.setNivel(guiaRemision001.getRequerimientoVolumen001().getNivelEducacion().getDscNivelEducacion());
						acumuladoRutaDistribucionPeso.setPeso(pesoGuia);
						
						lstAcumuladoRutaDistribucionPeso.add(acumuladoRutaDistribucionPeso);
						
											
					}
					
				}
				
			}
			
		}
		
		lstAcumuladoRutaDistribucionClass.sort((a,b)-> a.getGrupo().compareTo(b.getGrupo()) );
		
		
//		List<MatrizGroup> lstMatriz = matrizService.getMatrizByIdsRequerimientoVolumen001(ids);
//		
//	
//		
//		lstMatriz.sort((MatrizGroup a,MatrizGroup b)-> a.getDscCatalogoProductoQaliwarma().compareTo(b.getDscCatalogoProductoQaliwarma()));
//		for(MatrizGroup matrizGroup:lstMatriz) {
//			
//			ProductoPorNumeroEntrega ppn = matrizGroup.getProductoPorNumeroEntrega();
//			
//			System.out.println(   matrizGroup.getDscCatalogoProductoQaliwarma() );
//			
//			
//		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("data", lstAcumuladoRutaDistribucionClass);
		response.put("dataPeso", lstAcumuladoRutaDistribucionPeso);
		response.put("data2", rutaDistribucion);
		
		ModelAndView model = new ModelAndView(new ExcelRutaDistribucionAcumulado() , response);
		return model;
	//	return null;
		
		
	}
	
	public String convertirArrayToStringWith(String[] array ) {
		String rtn="";
		for(String s:array) {
			rtn = rtn+s+";";
		}
		return rtn;
	}
	
	public List<String> convertirArrayToListString(String[] array){
		List<String> lst = new ArrayList<>();
		for(String s:array) {
			lst.add(s);
		}
		return lst;
	}
	
	public String[] convertirListStringToArray(List<String> lst) {
		String [] arrayString = new String[lst.size()] ;
		Integer cnt= 0;
		for(String s:lst) {
			arrayString[cnt] = s;
			cnt++;
		}
		
		return arrayString;
	}
	
	public Integer siExisteLoteRegristrado(String lote) {
		Integer rtn = -1;
		String[] arrayLotes;
		arrayLotes = acumuladoRutaDistribucionClass.getLote().split(";");
		
		Integer cnt=0;
		for(String s:arrayLotes) {
			if(s.equals(lote)) {
				rtn = cnt;
				
				return rtn;
			}
			cnt++;
		}
		
		return rtn;
		
	}
	
	public boolean findInAcumulado(String idCatalogoProducto, String idProductoPresentacion, List<AcumuladoRutaDistribucionClass> lstAcumulado) {
		existe = false;
		lstAcumulado.forEach(item -> {
			if(item.getIdCatalogoProducto().equals(idCatalogoProducto) && item.getIdProductoPresentacion().equals(idProductoPresentacion) ) {
				existe = true;
				return;
			}
		});
		return existe;
	}
	public AcumuladoRutaDistribucionClass getInAcumulado(String idCatalogoProducto, String idProductoPresentacion, List<AcumuladoRutaDistribucionClass> lstAcumulado) {
		acumuladoRutaDistribucionClass = new AcumuladoRutaDistribucionClass() ;
		lstAcumulado.forEach(item -> {
			if(item.getIdCatalogoProducto().equals(idCatalogoProducto) && item.getIdProductoPresentacion().equals(idProductoPresentacion) ) {
				acumuladoRutaDistribucionClass = item;
				return;
			}
		});
		return acumuladoRutaDistribucionClass;
	}
	
	public AcumuladoRutaDistribucionPeso getInAcumuladoPeso(String codigoModular, List<AcumuladoRutaDistribucionPeso> lstAcumulado) {
		acumuladoRutaDistribucionPeso = new AcumuladoRutaDistribucionPeso() ;
		lstAcumulado.forEach(item -> {
			if(item.getCodigoModular().equals(codigoModular)) {
				acumuladoRutaDistribucionPeso = item;
				return;
			}
		});
		return acumuladoRutaDistribucionPeso;
	}
	
	public Boolean findInAcumuladoPeso(String codigoModular, List<AcumuladoRutaDistribucionPeso> lstAcumulado) {
		existe = false;
		lstAcumulado.forEach(item -> {
			if(item.getCodigoModular().equals(codigoModular)) {
				existe = true;
				return;
			}
		});
		return existe;
	}
}
