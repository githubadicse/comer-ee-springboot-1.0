package com.adicse.comercial.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.service.GuiaRemision001Service;
import com.adicse.comercial.viewResolver.PdfGuiaRemision;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@RestController
@RequestMapping("/res/guiaremision")
public class GuiaRemision001Controller {

	@Autowired
	private GuiaRemision001Service guiaRemision001Service;
	
	@Autowired 
	private ApplicationContext appContext;	

	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter) {

		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		Page<GuiaRemision001> page = guiaRemision001Service.pagination(pagenumber, rows, sortdireccion, sortcolumn,
				filter);

		List<GuiaRemision001> lst = page.getContent();

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}

	@RequestMapping("/update")
	@ResponseBody
	public GuiaRemision001 updateGuiaRemision(@RequestBody GuiaRemision001 guiaRemision001) {

		GuiaRemision001 guiaRemisionUpdate = guiaRemision001Service.findbyid(guiaRemision001.getIdGuiaRemision001())
				.get();

		BeanUtils.copyProperties(guiaRemision001, guiaRemisionUpdate);

		return guiaRemision001Service.grabar(guiaRemisionUpdate);

	}

	@RequestMapping("/guiaRemisionPorSerieNumero")
	public ModelAndView getGuiaRemision(@RequestParam("serie") Integer serie, @RequestParam("numero") Integer numero) {

		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorSerieNumero(serie, numero);
		List<GuiaRemision001> lstGuiaRemision001 = new ArrayList<GuiaRemision001>();

		for (GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
			guiaRemision002.setGuiaRemision001(null);
		}

		lstGuiaRemision001.add(guiaRemision001);

		Map<String, Object> model = new HashMap<>();

		model.put("data", lstGuiaRemision001);

		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(), model);

		return mv;

	}

	@RequestMapping("/guiaRemisionPorCodigoModular")
	public ModelAndView getGuiaRemisionPorCodigoModular(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega, @RequestParam("codigoModular") String codigoModular) {

		codigoModular = codigoModular.trim();
		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorCodigoModular(anno, numeroEntrega,
				codigoModular);
		List<GuiaRemision001> lstGuiaRemision001 = new ArrayList<GuiaRemision001>();

		for (GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
			guiaRemision002.setGuiaRemision001(null);
		}

		lstGuiaRemision001.add(guiaRemision001);
		
		

		Map<String, Object> model = new HashMap<>();

		model.put("data", lstGuiaRemision001);

		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(), model);

		return mv;

	}
	
	
	@RequestMapping("/guiaRemisionPorSerieNumeroReport")
	public void getGuiaRemisionReport(@RequestParam("serie") Integer serie, @RequestParam("numero") Integer numero,HttpServletResponse response) throws JRException, IOException {

		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorSerieNumero(serie, numero);
		List<GuiaRemision001> lstGuiaRemision001 = new ArrayList<GuiaRemision001>();

		for (GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
			guiaRemision002.setGuiaRemision001(null);
		}

		lstGuiaRemision001.add(guiaRemision001);
		
		List<Map<String, Object>>  data = this.generateDataToPrintPdf(lstGuiaRemision001);
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream jasperStream = classLoader.getResourceAsStream("reportes\\Blank_A4.jrxml");

		Map<String, Object> params = new HashMap<>();


		JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);		



	}	
	
	@RequestMapping("/guiaRemisionPorCodigoModularReport")
	public void getGuiaRemisionPorCodigoModularReport(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega, @RequestParam("codigoModular") String codigoModular,HttpServletResponse response) throws JRException, IOException {

		codigoModular = codigoModular.trim();
		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorCodigoModular(anno, numeroEntrega,
				codigoModular);
		List<GuiaRemision001> lstGuiaRemision001 = new ArrayList<GuiaRemision001>();

		for (GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
			guiaRemision002.setGuiaRemision001(null);
		}

		lstGuiaRemision001.add(guiaRemision001);
		
		List<Map<String, Object>>  data = this.generateDataToPrintPdf(lstGuiaRemision001);
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream jasperStream = classLoader.getResourceAsStream("reportes\\Blank_A4.jrxml");

		Map<String, Object> params = new HashMap<>();


		JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);		

	}	
	
	@RequestMapping("/guiasRemisionPorItemReport")
	@ResponseBody
	public void getGuiaRemisionPorItemReport(@RequestParam("idItem") String idItem,
			@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega,HttpServletResponse response) throws JRException, IOException {

		List<GuiaRemision001> lstGuiaRemision001 = guiaRemision001Service
				.getGuiaRemisionPorItemAndAnnoNumeroEntrega(idItem, anno, numeroEntrega);

		for (GuiaRemision001 guiaRemision001 : lstGuiaRemision001) {
			for (GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
				guiaRemision002.setGuiaRemision001(null);
			}
		}


		
		List<Map<String, Object>>  data = this.generateDataToPrintPdf(lstGuiaRemision001);
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream jasperStream = classLoader.getResourceAsStream("reportes\\Blank_A4.jrxml");

		Map<String, Object> params = new HashMap<>();


		JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=Guia.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);				

	}	
	

	@RequestMapping("/guiaRemisionPorCodigoModularJson")
	public GuiaRemision001 getGuiaRemisionPorCodigoModularJson(@RequestParam("anno") Integer anno,
			@RequestParam("numeroEntrega") Integer numeroEntrega, @RequestParam("codigoModular") String codigoModular) {

		codigoModular = codigoModular.trim();
		GuiaRemision001 guiaRemision001 = guiaRemision001Service.getGuiaRemisionPorCodigoModular(anno, numeroEntrega,
				codigoModular);

		return guiaRemision001;
	}

	@RequestMapping("/guiasRemisionPorCodigoModular")
	public List<GuiaRemision001> getGuiaRemisionPorCodigoModular(@RequestParam("codigoModular") String CodigoModular) {

		return null;
	}

	@RequestMapping("/guiasRemisionPorItem")
	@ResponseBody
	public ModelAndView getGuiaRemisionPorItem(@RequestParam("idItem") String idItem,
			@RequestParam("anno") Integer anno, @RequestParam("numeroEntrega") Integer numeroEntrega) {

		List<GuiaRemision001> lstGuiaRemision001 = guiaRemision001Service
				.getGuiaRemisionPorItemAndAnnoNumeroEntrega(idItem, anno, numeroEntrega);

		for (GuiaRemision001 guiaRemision001 : lstGuiaRemision001) {
			for (GuiaRemision002 guiaRemision002 : guiaRemision001.getGuiaRemision002s()) {
				guiaRemision002.setGuiaRemision001(null);
			}
		}

		Map<String, Object> model = new HashMap<>();

		model.put("data", lstGuiaRemision001);

		ModelAndView mv = new ModelAndView(new PdfGuiaRemision(), model);

		return mv;

	}
	
	

	@RequestMapping(value = "/printEjemplo", method = RequestMethod.GET)
	@ResponseBody
	public void printGuiaRemision(@RequestParam("anno") Integer _anno,
			@RequestParam("numeroEntrega") Integer _numeroEntrega, @RequestParam("codigoModular") String _codigoModular,HttpServletResponse response) throws JRException, IOException {

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		DecimalFormat myFormatter = new DecimalFormat("###,##0.000", otherSymbols);

		GuiaRemision001 guiaRemision = guiaRemision001Service.getGuiaRemisionPorCodigoModular(_anno, _numeroEntrega, _codigoModular);

		// lista de clases
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		// Se comporta como una clase,,,, propiedad y valor
		Map<String, Object> item = new HashMap<>();

		List<GuiaRemision002> lstGuiaRemision002 = guiaRemision.getGuiaRemision002s();
		Integer itemDetalle = 1;

		lstGuiaRemision002.sort(
				(GuiaRemision002 g1, GuiaRemision002 g2) -> g1.getProductoGrupo().compareTo(g2.getProductoGrupo()));

		Double peso = 0.0;
		Double totalPeso = 0.0;
		for (GuiaRemision002 guiaRemision002 : lstGuiaRemision002) {

			if (guiaRemision002.getCantidad() > 0) {
				item = new HashMap<>();
				
				//Fecha Emision de la Guia de remision
				item.put("fechaEmision", df.format(guiaRemision.getFechaEmision()));

				// numero de orden de la guia
				String numeroOrden = guiaRemision.getOrdenPorItem() == null ? ""
						: guiaRemision.getOrdenPorItem().toString();

				item.put("numeroOrden", numeroOrden);
				
				//direccion de la empresa o punto de donde sale la mercaderia
				item.put("puntoPartida", guiaRemision.getPuntoPartida().getDireccion());


				// ruc del destinatario
				item.put("nroDocumento", guiaRemision.getProveedorcliente().getNrodocumento());

				// razon social
				item.put("razonSocial", guiaRemision.getProveedorcliente().getRazonsocial());

				// nombre del c.e. codigo modular
				String nombreIIEE = guiaRemision.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
						.getNombreInstitucionEducativa();
				String direcionIIEE = guiaRemision.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
						.getDireccionInstitucionEducativa();
				String ubigeo = guiaRemision.getRequerimientoVolumen001().getUbigeo().getNombreProvincia() + "-"
						+ guiaRemision.getRequerimientoVolumen001().getUbigeo().getNombreDistrito();

				String iiee = "II.EE. " + nombreIIEE + " " + direcionIIEE + " " + ubigeo;
				item.put("iiee", iiee);

				String numeroEntrega = guiaRemision.getRequerimientoVolumen001().getEntregaPorItem().getNumeroEntrega()
						.getNumeroEntregaValor().toString();
				String nivelEducacion = guiaRemision.getRequerimientoVolumen001().getNivelEducacion()
						.getDscNivelEducacion();
				String codigoModular = guiaRemision.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
						.getCodigoModular();
				String sItem = guiaRemision.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega()
						.getDscitem();

				item.put("codigoModular", codigoModular);
				item.put("nivelEducacion", nivelEducacion);
				item.put("numeroEntrega", numeroEntrega);
				item.put("item", sItem);

				// DETALLE DEL DOCUMENTO ---- LINEAS

				itemDetalle++;
				String sProductoGrupo = guiaRemision002.getProductoGrupo();
				Integer cantidad = guiaRemision002.getCantidad();
				Boolean printLote = false;

				if (guiaRemision002.getVolumenConvertidoEnvace().getRequerimientoVolumen002Producto()
						.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getPrintLote().equals(1)) {
					printLote = true;
				}
				
				String numeroLote="";
				numeroLote = " LT:" + guiaRemision002.getNumeroLote() == null ? ""
						: " LT:" + guiaRemision002.getNumeroLote();

				String slote = printLote.equals(true) ? numeroLote : "";

				String sProductoComplemento = guiaRemision002.getProductoSeleccionado() + " "
						+ guiaRemision002.getMarca().trim() + " " + slote;

				item.put("sProductoComplemento", sProductoComplemento);
				item.put("cantidad", cantidad);

				// ----------------------------producto presentacion
				// ----------------------------------//
				String sEnvase = guiaRemision002.getEnvase();

				Double factorVolumenPresentacion = guiaRemision002.getFactor().doubleValue();

				String sUnidadMedidaAbr = guiaRemision002.getUnidadMedidaTrabajo();

				String sPresentacion = WordUtils.capitalize(sEnvase.toLowerCase()) + " X "
						+ factorVolumenPresentacion.toString() + " " + sUnidadMedidaAbr;

				item.put("sPresentacion", sPresentacion);

				peso = cantidad * factorVolumenPresentacion;
				totalPeso = totalPeso + peso;
				// si es factor es 1, el volumen sera multiplicado por su peso, si el factor es
				// de volumen osea 1000, el volumen sera dividido entre el peso de la
				// presentacion
				// myFormatter.format(peso)
				item.put("peso", myFormatter.format(peso) + " " + sUnidadMedidaAbr);

				///// DATOS PIE DE PAGINA

				String transportista = "";
				String domicilio = "";
				String chofer = "";
				String rucTransportista = "";
				String placa = "";
				String brevete = "";
				String marcaVehiculo = "";

				if (guiaRemision.getTransportista() != null) {
					if (guiaRemision.getTransportista().getProveedorcliente() != null) {

						if (guiaRemision.getTransportista().getProveedorcliente()
								.getProveedorclientedireccions() != null) {
							domicilio = guiaRemision.getTransportista().getProveedorcliente()
									.getProveedorclientedireccions().get(0).getDireccion();
						}
						if (guiaRemision.getTransportista().getProveedorcliente().getRazonsocial() != null) {
							transportista = guiaRemision.getTransportista().getProveedorcliente().getRazonsocial();
							rucTransportista = guiaRemision.getTransportista().getProveedorcliente().getNrodocumento();
						}

					}
				}

				if (guiaRemision.getChofer() != null) {
					chofer = guiaRemision.getChofer().getNombres();
					brevete = guiaRemision.getChofer().getNumeroBrevete();

				}
				if (guiaRemision.getVehiculo() != null) {
					placa = guiaRemision.getVehiculo().getNumeroPlaca();
					marcaVehiculo = guiaRemision.getVehiculo().getMarcaVehiculo();
				}

				item.put("transportista", transportista);
				item.put("domicilio", domicilio);
				item.put("chofer", chofer);

				item.put("rucTransportista", rucTransportista);
				item.put("placa", placa);
				item.put("brevete", brevete);
				item.put("marcaVehiculo", marcaVehiculo);

				data.add(item);

			}
		}

		for (int i = 0; i < data.size(); i++) {
			item = data.get(i);
			item.put("totalPeso", totalPeso);
		}

		// ArrayList<GuiaRemision001> list = new ArrayList<GuiaRemision001 >();
		// list.add(guiaRemision);

		JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream jasperStream = classLoader.getResourceAsStream("reportes\\Blank_A4.jrxml");

		Map<String, Object> params = new HashMap<>();
		// params.put("DataSource", data);
		// FileInputStream in = new
		// FileInputStream("D:\\proyectos\\backend\\comer-back-end\\src\\main\\java\\com\\adicse\\comercial\\reports\\Blank_A4.jrxml");

//		InputStream employeeReportStream
//		  = getClass().getResourceAsStream("/Blank_A4.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

		// JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
		// new JREmptyDataSource());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		//response.setContentType("application/x-pdf");
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
		
		
		//exporter pdf
//		JRPdfExporter exporter = new JRPdfExporter();
//		 
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(
//		  new SimpleOutputStreamExporterOutput("employeeReport.pdf"));
//		
//		SimplePdfReportConfiguration reportConfig
//		  = new SimplePdfReportConfiguration();
//		reportConfig.setSizePageToContent(true);
//		reportConfig.setForceLineBreakPolicy(false);
//		 
//		SimplePdfExporterConfiguration exportConfig
//		  = new SimplePdfExporterConfiguration();
//		exportConfig.setMetadataAuthor("baeldung");
//		exportConfig.setEncrypted(true);
//		exportConfig.setAllowedPermissionsHint("PRINTING");
//		 
//		exporter.setConfiguration(reportConfig);
//		exporter.setConfiguration(exportConfig);
//		 
//		exporter.exportReport();		

	}
	
	
	public List<Map<String, Object>> generateDataToPrintPdf(List<GuiaRemision001> lstGuiaRemision){
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		DecimalFormat myFormatter = new DecimalFormat("###,##0.000", otherSymbols);
		

		//GuiaRemision001 guiaRemision = guiaRemision001Service.getGuiaRemisionPorCodigoModular(_anno, _numeroEntrega, _codigoModular);

		// lista de clases
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		// Se comporta como una clase,,,, propiedad y valor
		Map<String, Object> item = new HashMap<>();
		
		
		for(GuiaRemision001 guiaRemision:lstGuiaRemision) {
			
			
			//INICIA LA OPERACION GUIA POR GUIA
			List<GuiaRemision002> lstGuiaRemision002 = guiaRemision.getGuiaRemision002s();
			Integer itemDetalle = 1;

			lstGuiaRemision002.sort(
					(GuiaRemision002 g1, GuiaRemision002 g2) -> g1.getProductoGrupo().compareTo(g2.getProductoGrupo()));

			Double peso = 0.0;
			Double totalPeso = 0.0;
			
			for (GuiaRemision002 guiaRemision002 : lstGuiaRemision002) {

				if (guiaRemision002.getCantidad() > 0) {
					item = new HashMap<>();
					
					//id de la guia
					item.put("id", guiaRemision.getIdGuiaRemision001());
					
					//Fecha Emision de la Guia de remision
					item.put("fechaEmision", df.format(guiaRemision.getFechaEmision()));

					// numero de orden de la guia
					String numeroOrden = guiaRemision.getOrdenPorItem() == null ? ""
							: guiaRemision.getOrdenPorItem().toString();

					item.put("numeroOrden", numeroOrden);
					
					//direccion de la empresa o punto de donde sale la mercaderia
					item.put("puntoPartida", guiaRemision.getPuntoPartida().getDireccion());


					// ruc del destinatario
					item.put("nroDocumento", guiaRemision.getProveedorcliente().getNrodocumento());

					// razon social
					item.put("razonSocial", guiaRemision.getProveedorcliente().getRazonsocial());

					// nombre del c.e. codigo modular
					String nombreIIEE = guiaRemision.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
							.getNombreInstitucionEducativa();
					String direcionIIEE = guiaRemision.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
							.getDireccionInstitucionEducativa();
					String ubigeo = guiaRemision.getRequerimientoVolumen001().getUbigeo().getNombreProvincia() + "-"
							+ guiaRemision.getRequerimientoVolumen001().getUbigeo().getNombreDistrito();

					String iiee = "II.EE. " + nombreIIEE + " " + direcionIIEE + " " + ubigeo;
					item.put("iiee", iiee);

					String numeroEntrega = guiaRemision.getRequerimientoVolumen001().getEntregaPorItem().getNumeroEntrega()
							.getNumeroEntregaValor().toString();
					String nivelEducacion = guiaRemision.getRequerimientoVolumen001().getNivelEducacion()
							.getDscNivelEducacion();
					String codigoModular = guiaRemision.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
							.getCodigoModular();
					String sItem = guiaRemision.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega()
							.getDscitem();

					item.put("codigoModular", codigoModular);
					item.put("nivelEducacion", nivelEducacion);
					item.put("numeroEntrega", numeroEntrega);
					item.put("item", sItem);

					// DETALLE DEL DOCUMENTO ---- LINEAS

					itemDetalle++;
					String sProductoGrupo = guiaRemision002.getProductoGrupo();
					Integer cantidad = guiaRemision002.getCantidad();
					Boolean printLote = false;

					if (guiaRemision002.getVolumenConvertidoEnvace().getRequerimientoVolumen002Producto()
							.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getPrintLote().equals(1)) {
						printLote = true;
					}
					
					String numeroLote="";
					numeroLote = " LT:" + guiaRemision002.getNumeroLote() == null ? ""
							: " LT:" + guiaRemision002.getNumeroLote();

					String slote = printLote.equals(true) ? numeroLote : "";

					String sProductoComplemento = guiaRemision002.getProductoSeleccionado() + " "
							+ guiaRemision002.getMarca().trim() + " " + slote;

					item.put("sProductoComplemento", sProductoComplemento);
					item.put("cantidad", cantidad);

					// ----------------------------producto presentacion
					// ----------------------------------//
					String sEnvase = guiaRemision002.getEnvase();

					Double factorVolumenPresentacion = guiaRemision002.getFactor().doubleValue();

					String sUnidadMedidaAbr = guiaRemision002.getUnidadMedidaTrabajo();

					String sPresentacion = WordUtils.capitalize(sEnvase.toLowerCase()) + " X "
							+ factorVolumenPresentacion.toString() + " " + sUnidadMedidaAbr;

					item.put("sPresentacion", sPresentacion);

					peso = cantidad * factorVolumenPresentacion;
					totalPeso = totalPeso + peso;
					// si es factor es 1, el volumen sera multiplicado por su peso, si el factor es
					// de volumen osea 1000, el volumen sera dividido entre el peso de la
					// presentacion
					// myFormatter.format(peso)
					item.put("peso", myFormatter.format(peso) + " " + sUnidadMedidaAbr);
					item.put("pesoDouble", peso);
					///// DATOS PIE DE PAGINA

					String transportista = "";
					String domicilio = "";
					String chofer = "";
					String rucTransportista = "";
					String placa = "";
					String brevete = "";
					String marcaVehiculo = "";

					if (guiaRemision.getTransportista() != null) {
						if (guiaRemision.getTransportista().getProveedorcliente() != null) {

							if (guiaRemision.getTransportista().getProveedorcliente()
									.getProveedorclientedireccions() != null) {
								domicilio = guiaRemision.getTransportista().getProveedorcliente()
										.getProveedorclientedireccions().get(0).getDireccion();
							}
							if (guiaRemision.getTransportista().getProveedorcliente().getRazonsocial() != null) {
								transportista = guiaRemision.getTransportista().getProveedorcliente().getRazonsocial();
								rucTransportista = guiaRemision.getTransportista().getProveedorcliente().getNrodocumento();
							}

						}
					}

					if (guiaRemision.getChofer() != null) {
						chofer = guiaRemision.getChofer().getNombres();
						brevete = guiaRemision.getChofer().getNumeroBrevete();

					}
					if (guiaRemision.getVehiculo() != null) {
						placa = guiaRemision.getVehiculo().getNumeroPlaca();
						marcaVehiculo = guiaRemision.getVehiculo().getMarcaVehiculo();
					}

					item.put("transportista", transportista);
					item.put("domicilio", domicilio);
					item.put("chofer", chofer);

					item.put("rucTransportista", rucTransportista);
					item.put("placa", placa);
					item.put("brevete", brevete);
					item.put("marcaVehiculo", marcaVehiculo);

					data.add(item);
					
					for (int i = 0; i < data.size(); i++) {
						item = data.get(i);
						item.put("totalPeso", totalPeso);
					}
					totalPeso = 0.0;
					

				}
			}			
			
		}
		return data;
		
	}
	
	

//	@RequestMapping("/update")
//	@ResponseBody
//	public GuiaRemision001 putUdate(@RequestBody GuiaRemision001 guiaRemision001) {
//		
//		GuiaRemision001 guiaRemisionUpdate = guiaRemision001Service.findbyid(guiaRemision001.getIdGuiaRemision001()).get();
//		
//		BeanUtils.copyProperties(guiaRemision001, guiaRemisionUpdate);
//		
//		return guiaRemision001Service.grabar(guiaRemisionUpdate);
//	}
	
	
}
