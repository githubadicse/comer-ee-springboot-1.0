package com.adicse.comercial.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.clases.RequerimientoClass;
import com.adicse.comercial.model.CatalogoBonificacion;
import com.adicse.comercial.model.CatalogoLote;
import com.adicse.comercial.model.CatalogoMarca;
import com.adicse.comercial.model.CatalogoMultiplicarNivelEducacion;
import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.CodigomodularIinstitucionEducativa;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.model.HorarioAlimentacion;
import com.adicse.comercial.model.Matriz;
import com.adicse.comercial.model.ModalidadEntregaAlimento;
import com.adicse.comercial.model.NivelEducacion;
import com.adicse.comercial.model.Piking;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;
import com.adicse.comercial.model.ProductoPresentacion;
import com.adicse.comercial.model.RegionAlimentaria;
import com.adicse.comercial.model.RequerimientoVolumen001;
import com.adicse.comercial.model.RequerimientoVolumen002;
import com.adicse.comercial.model.RequerimientoVolumen002Producto;
import com.adicse.comercial.model.RutaDistribucion;
import com.adicse.comercial.model.RutaDistribucionDetalle;
import com.adicse.comercial.model.Ubigeo;
import com.adicse.comercial.model.VolumenConvertidoEnvace;
import com.adicse.comercial.service.CatalogoBonificacionService;
import com.adicse.comercial.service.CatalogoMarcaService;
import com.adicse.comercial.service.CatalogoMultiplicarNivelEducacionService;
import com.adicse.comercial.service.CatalogoProductoQaliwarmaService;
import com.adicse.comercial.service.CodigoModularInstitucionEducativaService;
import com.adicse.comercial.service.EntregaPorItemService;
import com.adicse.comercial.service.GuiaRemision001Service;
import com.adicse.comercial.service.GuiaRemision002Service;
import com.adicse.comercial.service.HorarioAlimentacionService;
import com.adicse.comercial.service.MatrizService;
import com.adicse.comercial.service.ModalidadEntregaAlimentosService;
import com.adicse.comercial.service.NivelEducacionService;
import com.adicse.comercial.service.NumeroEntregaService;
import com.adicse.comercial.service.ProductoPorNumeroEntregaService;
import com.adicse.comercial.service.ProductoPresentacionService;
import com.adicse.comercial.service.RegionAlimentariaService;
import com.adicse.comercial.service.RequerimientoVolumen001Service;
import com.adicse.comercial.service.RequerimientoVolumen002ProductoService;
import com.adicse.comercial.service.RequerimientoVolumen002Service;
import com.adicse.comercial.service.RutaDistribucionDetalleService;
import com.adicse.comercial.service.RutaDistribucionService;
import com.adicse.comercial.service.UbigeoService;
import com.adicse.comercial.utilitarios.Idunico;

@Service
@Transactional
public class QaliwarmaUtil {

	@Autowired
	private UbigeoService ubigeoService;

	@Autowired
	private CodigoModularInstitucionEducativaService codigoModularInstitucionEducativaService;

	@Autowired
	private RequerimientoVolumen001Service requerimientoVolumen001Service;

	@Autowired
	private RequerimientoVolumen002Service requerimientoVolumen002Service;

	@Autowired
	private NivelEducacionService nivelEducacionService;

	@Autowired
	private ModalidadEntregaAlimentosService modalidadEntregaAlimentosService;

	@Autowired
	private HorarioAlimentacionService horarioAlimentacionService;

	@Autowired
	private CatalogoProductoQaliwarmaService catalogoProductoQaliwarmaService;

	@Autowired
	private ProductoPorNumeroEntregaService productoPorNumeroEntregaService;

	@SuppressWarnings("unused")
	@Autowired
	private NumeroEntregaService numeroEntregaService;

	@Autowired
	private EntregaPorItemService entregaPorItemService;

	@Autowired
	private RequerimientoVolumen002ProductoService requerimientoVolumen002ProductoService;

	@SuppressWarnings("unused")
	@Autowired
	private ProductoPresentacionService productoPresentacionService;

	@Autowired
	private RegionAlimentariaService regionAlimentariaService;

	@Autowired
	private CatalogoBonificacionService catalogoBonificacionService;

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@Autowired
	private GuiaRemision001Service guiaRemision001Service;

	@Autowired
	private MatrizService matrizService;

	@Autowired
	private GuiaRemision002Service guiaRemision002Service;

	@Autowired
	private CatalogoMultiplicarNivelEducacionService catalogoMultiplicarNivelEducacionService;

	// @Autowired
	// private PikingService pikingService;

	@Autowired
	private RutaDistribucionService rutaDistribucionService;
	
	@Autowired
	private RutaDistribucionDetalleService rutaDistribucionDetalleService;

	public void run() throws IOException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File("D:\\archivos\\AWAJUN_SANMARTIN1_SANMARTIN_P1.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leerUbigeo(file, "Detalle", 10, 2);

	}

	public FileInputStream cargarArchivoStream(String archivo) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(archivo));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public List<CatalogoProductoQaliwarma> validarProductosRelacionados() {

		// List<CatalogoProductoQaliwarma> lstCatalogo =
		// catalogoProductoQaliwarmaService.getall();
		//
		// List<CatalogoProductoQaliwarma> faltantes = new
		// ArrayList<CatalogoProductoQaliwarma>();
		// for(CatalogoProductoQaliwarma row: lstCatalogo) {
		//
		// Producto producto =
		// productoService.findByCatalogoProductoQaliwarmaEquals(row);
		//
		// if(producto == null) {
		// faltantes.add(row);
		// }
		//
		//
		// }
		// return faltantes;
		return null;

	}

	@SuppressWarnings("resource")
	public void leerRegionAlimentaria(FileInputStream file, String hoja, Integer fila, Integer columna, Integer anno,
			Integer numeroEntrega) throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;

		Row row;
		Cell cell;

		RegionAlimentaria regionAlimentaria;
		String item = sheet.getRow(7).getCell(4).getStringCellValue().trim();

		ProductoPorNumeroEntrega productoPorNumeroEntrega = null;
		CatalogoBonificacion catalogoBonificacion = null;
		@SuppressWarnings("unused")
		List<RegionAlimentaria> lstRegionAlimentaria = new ArrayList<>();
		for (int i = fila; i <= totalRows; i++) {

			row = sheet.getRow(i);

			if (row != null) {
				cell = row.getCell(columna);

				if (cell != null) {

					String sRegion = cell.getStringCellValue();
					regionAlimentaria = new RegionAlimentaria();
					Boolean isPresent = regionAlimentariaService.findbyid(sRegion).isPresent();
					if (isPresent) {
						regionAlimentaria = regionAlimentariaService.findbyid(cell.getStringCellValue()).get();
					} else {

						regionAlimentaria.setIdRegionAlimentaria(cell.getStringCellValue());
						regionAlimentaria.setDscRegionAlimentaria(cell.getStringCellValue());
					}

					String sHorarioAlimentarion = row.getCell(12).getStringCellValue().toUpperCase().trim();
					Integer idHorarioAlimentacion = 0;
					switch (sHorarioAlimentarion) {
					case "DESAYUNO":
						idHorarioAlimentacion = 1;
						break;

					default:
						idHorarioAlimentacion = 2;
						break;
					}

					HorarioAlimentacion horarioAlimentacion = horarioAlimentacionService.findbyid(idHorarioAlimentacion)
							.get();

					String producto = row.getCell(13).getStringCellValue().toUpperCase().trim();

					String idProductoPorNumeroEntrega = producto + "-" + anno + "-" + numeroEntrega + "-" + item + "-"
							+ anno;
					productoPorNumeroEntrega = productoPorNumeroEntregaService.findbyid(idProductoPorNumeroEntrega)
							.get();

					List<CatalogoBonificacion> lstCatalogoBonificacion = new ArrayList<>();

					lstCatalogoBonificacion = regionAlimentaria.getCatalogoBonificacions();

					String idCatalogoBonificacion = regionAlimentaria.getIdRegionAlimentaria() + "-"
							+ idProductoPorNumeroEntrega + "-" + idHorarioAlimentacion;
					catalogoBonificacion = new CatalogoBonificacion();
					for (CatalogoBonificacion cata : lstCatalogoBonificacion) {
						if (cata.getIdCatalogoBonificacion().equals(idCatalogoBonificacion)) {
							catalogoBonificacion = cata;
							break;
						}
					}

					catalogoBonificacion.setIdCatalogoBonificacion(idCatalogoBonificacion);

					catalogoBonificacion.setRegionAlimentaria(regionAlimentaria);
					catalogoBonificacion.setProductoPorNumeroEntrega(productoPorNumeroEntrega);
					catalogoBonificacion.setHorarioAlimentacion(horarioAlimentacion);

					lstCatalogoBonificacion.add(catalogoBonificacion);

					regionAlimentaria.setCatalogoBonificacions(lstCatalogoBonificacion);

					// lstRegionAlimentaria.add(regionAlimentaria);
					regionAlimentariaService.grabar(regionAlimentaria);

				}
			}
		}
		// regionAlimentariaService.grabarList(lstRegionAlimentaria);

		lstRegionAlimentaria = null;
	}

	@SuppressWarnings("resource")
	public void leerUbigeo(FileInputStream file, String hoja, Integer fila, Integer columna) throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;

		Row row;
		Cell cell;
		String idubigeo;
		Ubigeo ubigeo;
		for (int i = fila; i <= totalRows; i++) {

			row = sheet.getRow(i);

			if (row != null) {
				cell = row.getCell(columna);

				if (cell != null) {
					idubigeo = cell.getStringCellValue();
					ubigeo = new Ubigeo();
					ubigeo.setIdubigeo(idubigeo);
					ubigeo.setNombreProvincia(row.getCell(0).getStringCellValue());
					ubigeo.setNombreDistrito(row.getCell(1).getStringCellValue());

					ubigeoService.grabar(ubigeo);

				}
			}
		}
		worbook.close();
		file.close();
	}

	@SuppressWarnings("resource")
	public void leerDatosIntitucion(FileInputStream file, String hoja, Integer fila, Integer columna)
			throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;

		Row row;
		Cell cell;
		String idcodigomodular;
		CodigomodularIinstitucionEducativa codigomodularIinstitucionEducativa;

		for (int i = fila; i <= totalRows; i++) {

			row = sheet.getRow(i);

			if (row != null) {
				cell = row.getCell(columna);

				if (cell != null) {
					idcodigomodular = cell.getStringCellValue();
					codigomodularIinstitucionEducativa = new CodigomodularIinstitucionEducativa();
					codigomodularIinstitucionEducativa.setCodigoModular(idcodigomodular);
					codigomodularIinstitucionEducativa
							.setNombreInstitucionEducativa(row.getCell(7).getStringCellValue());
					codigomodularIinstitucionEducativa
							.setDireccionInstitucionEducativa(row.getCell(8).getStringCellValue());

					codigoModularInstitucionEducativaService.grabar(codigomodularIinstitucionEducativa);

				}
			}
		}
		worbook.close();
		file.close();
		codigomodularIinstitucionEducativa = null;
	}

	@SuppressWarnings("resource")
	public void leerRequerimientoVolumen001(FileInputStream file, String hoja, Integer fila, Integer columna,
			Integer anno, Integer numeroEntregas) throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;

		Row row;
		Cell cell;
		String item = sheet.getRow(7).getCell(4).getStringCellValue().trim();
		// String regionAlimentaria =
		// sheet.getRow(7).getCell(9).getStringCellValue().trim();
		RequerimientoVolumen001 requerimientoVolumen001;

		// ItemEntrega itemEntrega = itemEntregaService.findbyid(item).get();
		EntregaPorItem entregaPorItem = entregaPorItemService.getEntregaPorItemByAnno(anno, item, numeroEntregas);

		List<RequerimientoVolumen001> lstRequerimientoVolumen = new ArrayList<>();

		for (int i = fila; i <= totalRows; i++) {

			row = sheet.getRow(i);

			if (row != null) {
				cell = row.getCell(columna);

				if (cell != null) {

					requerimientoVolumen001 = new RequerimientoVolumen001();

					String idubigeo = row.getCell(2).getStringCellValue();
					Ubigeo ubigeo = ubigeoService.findbyid(idubigeo).get();
					requerimientoVolumen001.setUbigeo(ubigeo);

					String centroPoblado = row.getCell(3).getStringCellValue();
					requerimientoVolumen001.setCentroPoblado(centroPoblado);

					String codigoModular = row.getCell(5).getStringCellValue();
					CodigomodularIinstitucionEducativa cm = codigoModularInstitucionEducativaService
							.findbyid(codigoModular).get();
					requerimientoVolumen001.setCodigomodularIinstitucionEducativa(cm);

					String sNivelEducacion = row.getCell(9).getStringCellValue().trim().toUpperCase();
					Integer idNivelEducacion = 0;
					switch (sNivelEducacion) {
					case "INICIAL":
						idNivelEducacion = 1;

						break;
					case "PRIMARIA":
						idNivelEducacion = 2;

						break;
					case "SECUNDARIA":
						idNivelEducacion = 3;

						break;
					default:
						break;
					}
					NivelEducacion nivelEducacion = new NivelEducacion();
					nivelEducacion = nivelEducacionService.findbyid(idNivelEducacion).get();

					requerimientoVolumen001.setNivelEducacion(nivelEducacion);

					// Nro de usuarios
					Integer numeroUsuarios = Integer.parseInt(row.getCell(10).getStringCellValue());

					requerimientoVolumen001.setNumeroUsuarios(numeroUsuarios);

					// Region alimentaria
					String idRegionAlimentaria = row.getCell(4).getStringCellValue().trim().toUpperCase();

					// modalidad de entrega
					String sModalidad = row.getCell(11).getStringCellValue().trim().toUpperCase();
					Integer idModalidadEntrega = 0;
					switch (sModalidad) {
					case "PRODUCTOS":
						idModalidadEntrega = 1;
						break;

					default:
						break;
					}

					ModalidadEntregaAlimento modalidadEntregaAlimento = modalidadEntregaAlimentosService
							.findbyid(idModalidadEntrega).get();

					requerimientoVolumen001.setModalidadEntregaAlimento(modalidadEntregaAlimento);

					// Horario de alimentacion
					String sHorarioAlimentarion = row.getCell(12).getStringCellValue().toUpperCase().trim();
					Integer idHorarioAlimentacion = 0;
					switch (sHorarioAlimentarion) {
					case "DESAYUNO":
						idHorarioAlimentacion = 1;
						break;

					default:
						idHorarioAlimentacion = 2;
						break;
					}

					HorarioAlimentacion horarioAlimentacion = horarioAlimentacionService.findbyid(idHorarioAlimentacion)
							.get();
					requerimientoVolumen001.setHorarioAlimentacion(horarioAlimentacion);
					requerimientoVolumen001.setAnno(anno);
					requerimientoVolumen001.setEntregaPorItem(entregaPorItem);
					requerimientoVolumen001.setFlagEstado(1);

					RegionAlimentaria regionAlimentaria = regionAlimentariaService.findbyid(idRegionAlimentaria).get();
					requerimientoVolumen001.setRegionAlimentaria(regionAlimentaria);

					// id primer key
					requerimientoVolumen001.setIdRequerimientoVolumen001(idubigeo + "-" + codigoModular + "-" + anno
							+ "-" + numeroEntregas + "-" + item + "-" + idRegionAlimentaria);// +"-"+entregaPorItem.getIdEntregaPorItem().trim());

					lstRequerimientoVolumen.add(requerimientoVolumen001);
					// requerimientoVolumen001Service.grabar(requerimientoVolumen001);

				}
			}
		}

		requerimientoVolumen001Service.grabarList(lstRequerimientoVolumen);
		worbook.close();
		file.close();
		lstRequerimientoVolumen = null;
	}

	@SuppressWarnings("resource")
	public void escribirRequerimientoVolumen002(FileInputStream file, String hoja, Integer fila, Integer anno,
			Integer nroEntregas) throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 10;

		String item = sheet.getRow(7).getCell(4).getStringCellValue().trim();

		// List<RequerimientoVolumen001> lst =
		// requerimientoVolumen001Service.getReqVol001ByAnnoItem(anno, item);

		Row row;
		RequerimientoVolumen001 requerimientoVolumen001 = null;
		ProductoPorNumeroEntrega productoPorNumeroEntrega = null;
		Integer cntCustom = 1;

		// EntregaPorItem entregaPorItem = null;
		List<RequerimientoVolumen001> lstRequerimientoVolumen001 = new ArrayList<>();
		for (Integer i = fila; i <= totalRows; i++) {
			System.out.println("Proceso v002 " + i + " de " + totalRows + "   item :" + item);
			if (i == 1629) {
				System.out.println("suspend");
			}
			row = sheet.getRow(i);

			if (row != null) {
				String xUbigeo = row.getCell(2).getStringCellValue().toUpperCase().trim();
				String xCodigoModular = row.getCell(5).getStringCellValue().toUpperCase().trim();

				String idRegionAlimentaria = row.getCell(4).getStringCellValue().trim().toUpperCase();

				// entregaPorItem = entregaPorItemService.getEntregaPorItemByAnno(anno, item,
				// nroEntregas);
				String id_req_ven001 = xUbigeo + "-" + xCodigoModular + "-" + anno + "-" + nroEntregas + "-" + item
						+ "-" + idRegionAlimentaria;// +"-"+entregaPorItem.getIdEntregaPorItem().trim();

				requerimientoVolumen001 = requerimientoVolumen001Service.findbyid(id_req_ven001).get();
				@SuppressWarnings("unused")
				Integer colVolumen = 0;

				// insertamos por el numero de entregas en el año
				RequerimientoVolumen002 requerimientoVolumen002 = null;

				List<RequerimientoVolumen002> lstRequerimientoVolumen002 = new ArrayList<>();
				// List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002Producto= new
				// ArrayList<>();
				for (int j = nroEntregas; j <= nroEntregas; j++) {

					String producto = row.getCell(13).getStringCellValue().toUpperCase().trim();

					String sHorario = row.getCell(12).getStringCellValue().trim().toUpperCase();
					Integer idHorario = 0;
					switch (sHorario) {
					case "DESAYUNO":
						idHorario = 1;

						break;

					default:
						idHorario = 2;
						break;
					}

					String idProductoPorNumeroEntrega = producto + "-" + anno + "-" + j + "-" + item + "-" + anno;
					productoPorNumeroEntrega = new ProductoPorNumeroEntrega();

					if (!productoPorNumeroEntregaService.findbyid(idProductoPorNumeroEntrega).isPresent()) {
						System.out.println("Producto no registrado en entregas ...");
					} else {
						productoPorNumeroEntrega = productoPorNumeroEntregaService.findbyid(idProductoPorNumeroEntrega)
								.get();
					}

					Float volumen = (float) row.getCell(14 + j).getNumericCellValue();

					requerimientoVolumen002 = new RequerimientoVolumen002();

					requerimientoVolumen002
							.setIdRequerimientoVolumen002(requerimientoVolumen001.getIdRequerimientoVolumen001() + "-"
									+ j + "-" + item + "-" + xUbigeo + "-" + xCodigoModular + "-" + cntCustom);

					requerimientoVolumen002.setRequerimientoVolumen001(requerimientoVolumen001);
					requerimientoVolumen002
							.setNumeroEntrega(productoPorNumeroEntrega.getEntregaPorItem().getNumeroEntrega());
					Integer nUsuarios = Integer.parseInt(row.getCell(10).getStringCellValue());
					requerimientoVolumen002.setNumeroUsuarios(nUsuarios);

					// grabar se cambia por add a list
					// requerimientoVolumen002 =
					// requerimientoVolumen002Service.grabar(requerimientoVolumen002);
					// lstRequerimientoVolumen002.add(requerimientoVolumen002);

					// RequerimientoVolumen002Producto requerimientoVolumen002Producto;
					// Buscamos el producto en

					// requerimientoVolumen002Producto = new RequerimientoVolumen002Producto();
					//
					// requerimientoVolumen002Producto
					// .setIdRequerimientoVolumen002Producto(requerimientoVolumen001.getIdRequerimientoVolumen001()
					// + "-" + requerimientoVolumen002.getIdRequerimientoVolumen002() + "-" + j +
					// "-"
					// + productoPorNumeroEntrega.getIdProductoPorNumeroEntrega());
					// requerimientoVolumen002Producto.setRequerimientoVolumen002(requerimientoVolumen002);
					// requerimientoVolumen002Producto.setProductoPorNumeroEntrega(productoPorNumeroEntrega);
					// requerimientoVolumen002Producto.setVolumen(new BigDecimal(volumen));
					//
					//
					// lstRequerimientoVolumen002Producto.add(requerimientoVolumen002Producto);
					// requerimientoVolumen002.setRequerimientoVolumen002Productos(lstRequerimientoVolumen002Producto);

					lstRequerimientoVolumen002.add(requerimientoVolumen002);

					requerimientoVolumen001.setRequerimientoVolumen002s(lstRequerimientoVolumen002);
					lstRequerimientoVolumen001.add(requerimientoVolumen001);
					requerimientoVolumen001Service.grabar(requerimientoVolumen001);

					// PROPONEMOS GRABA EL DETALLE DE PRODUCTOS
					RequerimientoVolumen002Producto requerimientoVolumen002Producto;
					List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002Producto = new ArrayList<>();

					requerimientoVolumen002Producto = new RequerimientoVolumen002Producto();

					requerimientoVolumen002Producto
							.setIdRequerimientoVolumen002Producto(requerimientoVolumen001.getIdRequerimientoVolumen001()
									+ "-" + requerimientoVolumen002.getIdRequerimientoVolumen002() + "-" + j + "-"
									+ productoPorNumeroEntrega.getIdProductoPorNumeroEntrega());

					requerimientoVolumen002Producto.setRequerimientoVolumen002(requerimientoVolumen002);
					requerimientoVolumen002Producto.setProductoPorNumeroEntrega(productoPorNumeroEntrega);
					requerimientoVolumen002Producto.setVolumen(new BigDecimal(volumen));

					@SuppressWarnings("unused")
					RegionAlimentaria regionAlimentaria = regionAlimentariaService.findbyid(idRegionAlimentaria).get();
					requerimientoVolumen002Producto.setIdRegionAlimentariaAux(idRegionAlimentaria);

					requerimientoVolumen002Producto.setIdHorarioAlimentacionAux(idHorario);
					requerimientoVolumen002Producto
							.setIdProductoPorNumeroEntregaAux(productoPorNumeroEntrega.getIdProductoPorNumeroEntrega());

					lstRequerimientoVolumen002Producto.add(requerimientoVolumen002Producto);

					requerimientoVolumen002.setRequerimientoVolumen002Productos(lstRequerimientoVolumen002Producto);
					requerimientoVolumen002Service.grabar(requerimientoVolumen002);
					cntCustom++;

					// requerimientoVolumen002ProductoService.grabar(requerimientoVolumen002Producto);

				}
				// System.out.println("Grabando ...........");
				// requerimientoVolumen001Service.grabar(requerimientoVolumen001);
				// lstRequerimientoVolumen001.add(requerimientoVolumen001);

			}
			//
			// requerimientoVolumen001Service.grabarList(lstRequerimientoVolumen001);
			worbook.close();
			file.close();

		}
		lstRequerimientoVolumen001 = null;

	}

	@SuppressWarnings("resource")
	public void escribirRequerimientoVolumen002Producto(FileInputStream file, String hoja, Integer fila, Integer anno,
			Integer nroEntregas) throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 2;

		String item = sheet.getRow(7).getCell(4).getStringCellValue().trim();

		// List<RequerimientoVolumen001> lst =
		// requerimientoVolumen001Service.getReqVol001ByAnnoItem(anno, item);

		Row row;
		RequerimientoVolumen001 requerimientoVolumen001 = null;
		ProductoPorNumeroEntrega productoPorNumeroEntrega = null;
		Integer cntCustom = 1;

		List<RequerimientoVolumen001> lstRequerimientoVolumen001 = new ArrayList<>();

		for (int i = fila; i < totalRows; i++) {
			System.out.println("Proceso v002p " + i + " de " + totalRows);

			row = sheet.getRow(i);

			if (row != null) {
				String xUbigeo = row.getCell(2).getStringCellValue().toUpperCase().trim();
				String xCodigoModular = row.getCell(5).getStringCellValue().toUpperCase().trim();

				String idRegionAlimentaria = row.getCell(4).getStringCellValue().trim().toUpperCase();

				String sNivelEducacion = row.getCell(9).getStringCellValue().trim().toUpperCase();
				Integer idNivelEducacion = 0;
				switch (sNivelEducacion) {
				case "INICIAL":
					idNivelEducacion = 1;

					break;
				case "PRIMARIA":
					idNivelEducacion = 2;

					break;
				case "SECUNDARIA":
					idNivelEducacion = 3;

					break;
				default:
					break;
				}
				String id_req_ven001 = xUbigeo + "-" + xCodigoModular + "-" + item + "-" + idRegionAlimentaria;

				requerimientoVolumen001 = requerimientoVolumen001Service.findbyid(id_req_ven001).get();
				@SuppressWarnings("unused")
				Integer colVolumen = 0;

				// insertamos por el numero de entregas en el año
				RequerimientoVolumen002 requerimientoVolumen002 = requerimientoVolumen001.getRequerimientoVolumen002s()
						.get(0);

				List<RequerimientoVolumen002> lstRequerimientoVolumen002 = new ArrayList<>();
				List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002Producto = new ArrayList<>();

				for (int j = nroEntregas; j <= nroEntregas; j++) {

					String producto = row.getCell(13).getStringCellValue().toUpperCase().trim();

					String idProductoPorNumeroEntrega = producto + "-" + anno + "-" + j + "-" + item + "-" + anno;
					productoPorNumeroEntrega = new ProductoPorNumeroEntrega();

					productoPorNumeroEntrega = productoPorNumeroEntregaService.findbyid(idProductoPorNumeroEntrega)
							.get();

					Float volumen = (float) row.getCell(14 + j).getNumericCellValue();

					// requerimientoVolumen002 = new RequerimientoVolumen002();

					// requerimientoVolumen002
					// .setIdRequerimientoVolumen002(requerimientoVolumen001.getIdRequerimientoVolumen001()
					// + "-"
					// + j + "-" + item + "-" + xUbigeo + "-" + xCodigoModular+"-"+cntCustom);

					// requerimientoVolumen002.setRequerimientoVolumen001(requerimientoVolumen001);
					// requerimientoVolumen002.setNumeroEntrega(productoPorNumeroEntrega.getEntregaPorItem().getNumeroEntrega());
					// Integer nUsuarios = Integer.parseInt(row.getCell(10).getStringCellValue());
					// requerimientoVolumen002.setNumeroUsuarios(nUsuarios);

					// grabar se cambia por add a list
					// requerimientoVolumen002 =
					// requerimientoVolumen002Service.grabar(requerimientoVolumen002);
					// lstRequerimientoVolumen002.add(requerimientoVolumen002);

					RequerimientoVolumen002Producto requerimientoVolumen002Producto;
					// Buscamos el producto en

					requerimientoVolumen002Producto = new RequerimientoVolumen002Producto();

					requerimientoVolumen002Producto
							.setIdRequerimientoVolumen002Producto(requerimientoVolumen001.getIdRequerimientoVolumen001()
									+ "-" + requerimientoVolumen002.getIdRequerimientoVolumen002() + "-" + j + "-"
									+ productoPorNumeroEntrega.getIdProductoPorNumeroEntrega() + "-" + cntCustom);

					requerimientoVolumen002Producto.setRequerimientoVolumen002(requerimientoVolumen002);
					requerimientoVolumen002Producto.setProductoPorNumeroEntrega(productoPorNumeroEntrega);
					requerimientoVolumen002Producto.setVolumen(new BigDecimal(volumen));

					requerimientoVolumen002Producto.setIdRegionAlimentariaAux(idRegionAlimentaria);
					requerimientoVolumen002Producto.setIdHorarioAlimentacionAux(idNivelEducacion);
					requerimientoVolumen002Producto
							.setIdProductoPorNumeroEntregaAux(productoPorNumeroEntrega.getIdProductoPorNumeroEntrega());

					lstRequerimientoVolumen002Producto.add(requerimientoVolumen002Producto);

					requerimientoVolumen002.setRequerimientoVolumen002Productos(lstRequerimientoVolumen002Producto);

					lstRequerimientoVolumen002.add(requerimientoVolumen002);

					requerimientoVolumen001.setRequerimientoVolumen002s(lstRequerimientoVolumen002);

					lstRequerimientoVolumen001.add(requerimientoVolumen001);
					cntCustom++;
					// requerimientoVolumen002ProductoService.grabar(requerimientoVolumen002Producto);

				}
				// System.out.println("Grabando ...........");
				// requerimientoVolumen002Service.grabar(requerimientoVolumen002lstRequerimientoVolumen001);

			}
		}

		//
		requerimientoVolumen001Service.grabarList(lstRequerimientoVolumen001);
		worbook.close();
		file.close();
		lstRequerimientoVolumen001 = null;

	}

	@SuppressWarnings("resource")
	public void cargarTablaProductoPorNumeroEntrega(FileInputStream file, String hoja, Integer fila, Integer anno,
			Integer nroEntregas) throws IOException {

		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet(hoja);
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;

		@SuppressWarnings("unused")
		Row row;

		@SuppressWarnings("unused")
		Cell cell;

		String producto = "";
		String item = sheet.getRow(7).getCell(7).getStringCellValue().toUpperCase().trim();
		ProductoPorNumeroEntrega productoPorNumeroEntrega = null;
		double presentacionMinima = 0.0;

		// ItemEntrega itemEntrega = itemEntregaService.findbyid(item).get();
		List<ProductoPorNumeroEntrega> lstProductoPorNumeroEntrega = new ArrayList<>();
		for (int i = fila; i <= totalRows; i++) {
			System.out.println("cargar tabla producto por numero entrega ... proceso " + i + " de " + totalRows);

			if (sheet.getRow(i) == null) {
				break;
			}

			producto = sheet.getRow(i).getCell(1).getStringCellValue().toUpperCase().trim() + "-" + anno;

			if (catalogoProductoQaliwarmaService.getCatalogoProductoById(producto) != null) {

				presentacionMinima = sheet.getRow(i).getCell(2).getNumericCellValue();
				for (int j = nroEntregas; j <= nroEntregas; j++) {
					productoPorNumeroEntrega = new ProductoPorNumeroEntrega();

					productoPorNumeroEntrega
							.setIdProductoPorNumeroEntrega(producto + "-" + j + "-" + item + "-" + anno);

					CatalogoProductoQaliwarma catalogoProductoQaliwarma = catalogoProductoQaliwarmaService
							.findbyid(producto).get();
					productoPorNumeroEntrega.setCatalogoProductoQaliwarma(catalogoProductoQaliwarma);
					productoPorNumeroEntrega.setPresentacionMinima(new BigDecimal(presentacionMinima));
					// NumeroEntrega numeroEntrega = numeroEntregaService.findbyid(j).get();

					EntregaPorItem entregaPorItem = entregaPorItemService.getEntregaPorItemByAnno(anno, item, j);

					productoPorNumeroEntrega.setEntregaPorItem(entregaPorItem);

					// if(entregaPorItem.getItemEntrega().getItem().equals("AWAJUN")) {
					// System.out.println("AWAJUN");
					// }
					// productoPorNumeroEntrega.setItemEntrega(itemEntrega);

					// productoPorNumeroEntregaService.grabar(productoPorNumeroEntrega);
					lstProductoPorNumeroEntrega.add(productoPorNumeroEntrega);

				}

				productoPorNumeroEntregaService.grabarList(lstProductoPorNumeroEntrega);
			}

		}
		worbook.close();
		file.close();
		lstProductoPorNumeroEntrega = null;

	}

	@SuppressWarnings("unused")
	public void calcularVolumenPorPresentacion(Integer anno, Integer numeroEntrega) {
		System.out.println("Por favor espere iniciando proceso ....");

		// cargamos catalogo de productos a dumplicar por entrega
		List<CatalogoMultiplicarNivelEducacion> lstCatalogoMultiplicarNivelEducacon = catalogoMultiplicarNivelEducacionService
				.getCatalogoNivelEducacionByPeriodoNumeroEntrega(anno, numeroEntrega);

		List<RequerimientoClass> lstRequerimientoVolumen002Producto = requerimientoVolumen002ProductoService
				.listarTodo(anno, numeroEntrega);

		EntregaPorItem entregaPorItem = null;
		Integer totalFilas = lstRequerimientoVolumen002Producto.size();
		Double volumen = null, volumenAux = null;
		Double volumenUnidadMinima = null;
		Integer cntFilas = 1;
		RequerimientoVolumen002Producto requerimientoVolumen002Producto = null;

		List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002ProductoAcum = new ArrayList<>();

		for (Integer i = 0; i < totalFilas; i++) {
			System.out.println("Proceso " + cntFilas + " de " + totalFilas);
			
			if(cntFilas.equals(44)) {
				System.out.println("Proceso 44");
			}
			cntFilas++;
			

			volumen = lstRequerimientoVolumen002Producto.get(i).getVolumen().doubleValue();
			volumenAux = lstRequerimientoVolumen002Producto.get(i).getVolumen().doubleValue();

			requerimientoVolumen002Producto = new RequerimientoVolumen002Producto();
			requerimientoVolumen002Producto = lstRequerimientoVolumen002Producto.get(i)
					.getRequerimientoVolumen002Producto();

			ProductoPorNumeroEntrega productoPorNumeroEntrega = lstRequerimientoVolumen002Producto.get(i)
					.getProductoPorNumeroEntrega();

			CatalogoProductoQaliwarma catalogoProductoQaliwarma = lstRequerimientoVolumen002Producto.get(i)
					.getCatalogoProductoQaliwarma();

			entregaPorItem = productoPorNumeroEntrega.getEntregaPorItem();

			// Revisamos las veces que debe ser entregado el producto en la entrega, si el
			// producto se encuentra en la tabla
			// producto bonificacion, entonces revisamos las veces que se entrega, esto se
			// divide entre el volumen y se trabaja la presentacion
			// el resultado de esta division pasaria a ser el volumen a calcular segun su
			// presentacion.
			String idProductoPorNumeroEntrega = requerimientoVolumen002Producto.getIdProductoPorNumeroEntregaAux();
			Integer idHoradioAlimentacion = requerimientoVolumen002Producto.getIdHorarioAlimentacionAux();
			String idRegionAlimentaria = requerimientoVolumen002Producto.getIdRegionAlimentariaAux();
			String idCatalogoBonificacion = idRegionAlimentaria + "-" + idProductoPorNumeroEntrega + "-"
					+ idHoradioAlimentacion;
			CatalogoBonificacion catalogoBonificacion = null;
			Double presentacionMinima = productoPorNumeroEntrega.getPresentacionMinima().doubleValue();

			try {
				catalogoBonificacion = catalogoBonificacionService.findbyid(idCatalogoBonificacion).get();
			} catch (Exception e) {
				// TODO: handle exception
				// System.out.println("id no existe : " + idCatalogoBonificacion);
				e.getMessage();

			}

			if (requerimientoVolumen002Producto.getRequerimientoVolumen002().getRequerimientoVolumen001()
					.getCodigomodularIinstitucionEducativa().getCodigoModular().equals("0726000")) {
				System.out.println("alto");

				if (requerimientoVolumen002Producto.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma()
						.getIdCatalogoProductoQaliwarma().equals("POA NH 1-2018")) {
					if (volumen == 21)
						System.out.println("detener");
				}
			}

			Integer nPreparacion = 1;

			// preparamos la lista de presentacion de producto
			List<ProductoPresentacion> lstProductoPresentacion = catalogoProductoQaliwarma.getProductoPresentacions();

			List<ProductoPresentacion> lstProductoPresentacionAux = new ArrayList<>();
			for (int j = 0; j < lstProductoPresentacion.size(); j++) {
				Integer a = lstProductoPresentacion.get(j).getAnno();
				Integer b = lstProductoPresentacion.get(j).getNumeroEntrega();

				if (a.equals(anno) && b.equals(numeroEntrega)) {
					lstProductoPresentacionAux.add(lstProductoPresentacion.get(j));
				}

			}
			Collections.sort(lstProductoPresentacionAux,
					(p1, p2) -> p2.getCantidadPresentacion().compareTo(p1.getCantidadPresentacion()));

			// si la presentacion solo es 1, verificamos si es igual a la presentacion
			// minima
			// de ser igual entonces nPreparaciones siempre sera 1
			Boolean isEquealPresentacionMinima = false;
			if (lstProductoPresentacionAux.size() == 1) {
				if (lstProductoPresentacionAux.get(0).getFactorVolumenPresentacion()
						.doubleValue() == presentacionMinima) {
					isEquealPresentacionMinima = true;
				}
			}

			// si no es igual a la presentacion minima se busca las presentacines minimas
			if (!isEquealPresentacionMinima) {

				if (catalogoBonificacion != null) {
					nPreparacion = catalogoBonificacion.getNumeroDePreparacionPorEntrega() == null ? 1
							: catalogoBonificacion.getNumeroDePreparacionPorEntrega();
					if (nPreparacion > 1) {
						nPreparacion = catalogoBonificacion.getNumeroDePreparacionPorEntrega();
						volumen = volumen / nPreparacion;
					}
				}
				if (nPreparacion > 1) {
					// System.out.println(" preparacion mayor a uno");
				}
			}

			VolumenConvertidoEnvace volumenConvertidoEnvace = null;
			Matriz matriz = null;
			List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvace = new ArrayList<VolumenConvertidoEnvace>();

			Boolean existeEnLista = false;

			for (int n = 1; n <= nPreparacion; n++) {

				float xx = (float) 84.40;

				if (volumenAux.floatValue() == xx && nPreparacion == 1) {
					System.out.println("Producto desayuno");
				}

				volumenUnidadMinima = volumen;

				volumenUnidadMinima = volumen * 1000;

				if (catalogoProductoQaliwarma.getUnidadmedida().getIdunidadmedida() == 3) {
					volumenUnidadMinima = volumen;
				} else {
					volumenUnidadMinima = volumen * 1000;
				}

				Integer parteEntera = 0, restante = 0;
				volumenConvertidoEnvace = null;
				matriz = null;

				// lstVolumenConvertidoEnvace = new ArrayList<VolumenConvertidoEnvace>();
				CatalogoMarca catalogoMarca = null;
				Integer cntPresentacion = 1;

				if (requerimientoVolumen002Producto.getRequerimientoVolumen002().getRequerimientoVolumen001()
						.getIdRequerimientoVolumen001().equals("220802-1400928-AWAJUN-RA3_AMAZONIA_ALTA")) {
					System.out.println("Una presentacion");
				}

				for (ProductoPresentacion productoPresentacion : lstProductoPresentacionAux) {

					// System.out.println(
					// productoPresentacion.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()
					// + " - " + productoPresentacion.getDscPresentacion());
					volumenConvertidoEnvace = new VolumenConvertidoEnvace();

					String id = requerimientoVolumen002Producto.getIdRequerimientoVolumen002Producto() + "-"
							+ productoPorNumeroEntrega.getIdProductoPorNumeroEntrega()
							+ productoPresentacion.getIdProductoPresentacion();

					volumenConvertidoEnvace.setEntregaPorItem(entregaPorItem);

					// buscamos en lstVolumenConvertidoEnvace si existe el id de la presentacion que
					// se esta trabajando siempre y cuando nPreparacion sea mayor que 1
					if (nPreparacion > 1) {
						for (VolumenConvertidoEnvace vc : lstVolumenConvertidoEnvace) {
							if (vc.getIdVolumenConvertidoEnvace().equals(id)) {
								volumenConvertidoEnvace = vc;
								existeEnLista = true;
								break;
							}
						}
					}
					String idCatalogoMarca = productoPorNumeroEntrega.getIdProductoPorNumeroEntrega() + "-"
							+ productoPresentacion.getIdProductoPresentacion();

					catalogoMarca = catalogoMarcaService.findbyid(idCatalogoMarca).isPresent()
							? catalogoMarcaService.findbyid(idCatalogoMarca).get()
							: null;

					volumenConvertidoEnvace.setIdVolumenConvertidoEnvace(id);
					volumenConvertidoEnvace.setRequerimientoVolumen002Producto(requerimientoVolumen002Producto);
					volumenConvertidoEnvace.setProductoPresentacion(productoPresentacion);
					volumenConvertidoEnvace.setAnno(anno);
					volumenConvertidoEnvace.setNumeroEntrega(numeroEntrega );

					if (volumenUnidadMinima.floatValue() > 0 && !isEquealPresentacionMinima) {

						if (volumenUnidadMinima.floatValue() >= productoPresentacion.getCantidadPresentacion()) {

							parteEntera = (int) (volumenUnidadMinima.floatValue()
									/ productoPresentacion.getCantidadPresentacion());

							restante = (int) (volumenUnidadMinima.floatValue() - (parteEntera.floatValue()
									* productoPresentacion.getCantidadPresentacion().floatValue()));

							volumenConvertidoEnvace
									.setCantidad(volumenConvertidoEnvace.getCantidad() == null ? parteEntera
											: volumenConvertidoEnvace.getCantidad() + parteEntera);

							// Verificamos si el producto se encunetra en la tabla catalogo multiplicar
							// nivel educacion segun el numero de entrega
							// si se encuentra la cantidad se multiplicara por el factor que se encuentre en
							// la tabla y se actualizara la cantidad.
							// a la vez se colocara en observacion el motivo de la actualizacion.
							volumenConvertidoEnvace = this.verficaSiMultiplica(volumenConvertidoEnvace, anno,
									numeroEntrega, productoPresentacion, lstCatalogoMultiplicarNivelEducacon);

							// grabamos la marca de acuerdo a la presentacion del producto. siempre y cuando
							// este definido la marca en la tabla catalogo_marca
							volumenConvertidoEnvace.setCatalogoMarca(catalogoMarca);

							if (!existeEnLista) {
								lstVolumenConvertidoEnvace.add(volumenConvertidoEnvace);
								existeEnLista = false;
							}

						} else {
							restante = volumenUnidadMinima.intValue();
							volumenConvertidoEnvace.setCantidad(volumenConvertidoEnvace.getCantidad() == null ? 0
									: volumenConvertidoEnvace.getCantidad() + 0);

							volumenConvertidoEnvace.setCatalogoMarca(catalogoMarca);
							lstVolumenConvertidoEnvace.add(volumenConvertidoEnvace);
						}

						volumenUnidadMinima = restante.doubleValue();

					} else {
						// si es igual a la presentacion minima

						parteEntera = (int) (volumenUnidadMinima.floatValue()
								/ productoPresentacion.getCantidadPresentacion());

						volumenConvertidoEnvace.setCantidad(volumenConvertidoEnvace.getCantidad() == null ? parteEntera
								: volumenConvertidoEnvace.getCantidad() + parteEntera);

						// grabamos la marca de acuerdo a la presentacion del producto. siempre y cuando
						// este definido la marca en la tabla catalogo_marca
						volumenConvertidoEnvace.setCatalogoMarca(catalogoMarca);

						if (!existeEnLista) {
							lstVolumenConvertidoEnvace.add(volumenConvertidoEnvace);
							existeEnLista = false;
						}
						volumenUnidadMinima = 0.0;

					}

				}

				if (volumenUnidadMinima.intValue() > 0) {
					Integer bonificacion = (int) (volumenConvertidoEnvace.getProductoPresentacion()
							.getCantidadPresentacion() - volumenUnidadMinima.floatValue());

					volumenConvertidoEnvace.setCantidad(volumenConvertidoEnvace.getCantidad() + 1);
					volumenConvertidoEnvace.setBonificacion(volumenConvertidoEnvace.getBonificacion() == null ? 0
							: volumenConvertidoEnvace.getBonificacion() + bonificacion);

					volumenConvertidoEnvace.setObs(volumenConvertidoEnvace.getObs() + " // " + "Producto sobrante "
							+ volumenUnidadMinima + ", se agrego : " + bonificacion + " para completar : "
							+ volumenConvertidoEnvace.getProductoPresentacion().getDscPresentacion() + "  " + n + "/"
							+ " : nPreparacion : " + nPreparacion + " ");

					volumenConvertidoEnvace.setCatalogoMarca(catalogoMarca);

					if (!existeEnLista) {
						lstVolumenConvertidoEnvace.add(volumenConvertidoEnvace);
					}

				}

			}
			requerimientoVolumen002Producto.setVolumenConvertidoEnvaces(lstVolumenConvertidoEnvace);
			lstRequerimientoVolumen002ProductoAcum.add(requerimientoVolumen002Producto);
			// requerimientoVolumen002ProductoService.grabar(requerimientoVolumen002Producto);

		}
		System.out.println("Grabando calculo");
		requerimientoVolumen002ProductoService.GrabarAll(lstRequerimientoVolumen002ProductoAcum);
		System.out.println("Fin Grabando calculo");
		// lstRequerimientoVolumen002ProductoAcum = null;

		System.out.println("Cargando tabla matriz .....");
		this.cargarTablaMatriz(anno, numeroEntrega);
		System.out.println("Fin carga matriz ...........");

		// actualizar pesotoal en requerimiento1
		System.out.println("Actualizando pesoTotal requerimientoVolumen Convertido");
		// this.actualizaPesoTotalRequerimientoDesdeVolumenConverdito(anno,
		// numeroEntrega);
		System.out.println("Fin pesoTotal requerimientoVolumen Convertido");

	}

	// funcion que sirve para el proceso de calcular volumen.
	public VolumenConvertidoEnvace verficaSiMultiplica(VolumenConvertidoEnvace volumenConvertidoEnvace, Integer anno,
			Integer numeroEntrega, ProductoPresentacion productoPresentacion,
			List<CatalogoMultiplicarNivelEducacion> lstCatalogoMultiplicarNivelEducacon) {

		String idProducto = volumenConvertidoEnvace.getRequerimientoVolumen002Producto().getProductoPorNumeroEntrega()
				.getCatalogoProductoQaliwarma().getIdCatalogoProductoQaliwarma();
		String idPresentacion = productoPresentacion.getIdProductoPresentacion();
		Integer idNivel = volumenConvertidoEnvace.getRequerimientoVolumen002Producto().getRequerimientoVolumen002()
				.getRequerimientoVolumen001().getNivelEducacion().getIdNivelEducacion();

		for (CatalogoMultiplicarNivelEducacion row : lstCatalogoMultiplicarNivelEducacon) {
			if (row.getCatalogoProductoQaliwarma().getIdCatalogoProductoQaliwarma().equals(idProducto)
					&& row.getProductoPresentacion().getIdProductoPresentacion().equals(idPresentacion)
					&& row.getNivelEducacion().getIdNivelEducacion().equals(idNivel)) {

				Integer factorMultiplicar = row.getFactor();
				volumenConvertidoEnvace.setObs(volumenConvertidoEnvace.getObs() + " " + "Cantidad Original : "
						+ volumenConvertidoEnvace.getCantidad().toString());
				volumenConvertidoEnvace.setCantidad(volumenConvertidoEnvace.getCantidad() * factorMultiplicar);
				volumenConvertidoEnvace.setObs(volumenConvertidoEnvace.getObs() + " " + "Cantidad Final : "
						+ volumenConvertidoEnvace.getCantidad().toString());

			}
		}

		return volumenConvertidoEnvace;

	}

	public void actualizaPesoTotalRequerimientoDesdeVolumenConverdito(Integer anno, Integer numeroEntrega) {
		// TODO Auto-generated method stub
		List<RequerimientoVolumen001> lstRequerimientoVolumen001 = requerimientoVolumen001Service
				.getReqVol001ByAnnoAndNumeroEntrega(anno, numeroEntrega);

		@SuppressWarnings("unused")
		List<RequerimientoVolumen001> lstRequerimientoVolumen001Aux = new ArrayList<>();

		Integer totalRows = lstRequerimientoVolumen001.size();

		Integer cntRow = 1;

		for (RequerimientoVolumen001 requerimientoVolumen001 : lstRequerimientoVolumen001) {
			System.out.println("Procesando " + cntRow + " de " + totalRows);
			cntRow++;

			List<RequerimientoVolumen002> lstRequerimientoVolumen002 = requerimientoVolumen001
					.getRequerimientoVolumen002s();

			for (RequerimientoVolumen002 requerimientoVolumen002 : lstRequerimientoVolumen002) {

				List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002Producto = requerimientoVolumen002
						.getRequerimientoVolumen002Productos();

				lstRequerimientoVolumen002Producto
						.sort((RequerimientoVolumen002Producto r1, RequerimientoVolumen002Producto r2) -> r1
								.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma()
								.getDscCatalogoProductoQaliwarma().compareTo(r2.getProductoPorNumeroEntrega()
										.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()));

				for (RequerimientoVolumen002Producto requerimientoVolumen002Producto : lstRequerimientoVolumen002Producto) {

					List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvase = requerimientoVolumen002Producto
							.getVolumenConvertidoEnvaces();

					for (VolumenConvertidoEnvace volumenConvertidoEnvace : lstVolumenConvertidoEnvase) {

						// si volumenConvertidoEnvace.getCantidad() > 0
						if (volumenConvertidoEnvace.getCantidad() > 0) {

							Double cantidad = volumenConvertidoEnvace.getCantidad().doubleValue();

							Double factor = volumenConvertidoEnvace.getProductoPresentacion()
									.getFactorVolumenPresentacion().doubleValue();

							BigDecimal dTotal = new BigDecimal(0);
							dTotal = new BigDecimal(cantidad * factor);
							requerimientoVolumen001.setPesoTotal(dTotal);

							requerimientoVolumen001Service.grabar(requerimientoVolumen001);

						}
					}
					lstVolumenConvertidoEnvase = null;

				}

				lstRequerimientoVolumen002Producto = null;
			}
		}

	}

	public void cargarCatalogoMarca(Integer anno, Integer nroEntregas) {

		List<ProductoPorNumeroEntrega> lstProductoPorNumeroEntrega = productoPorNumeroEntregaService
				.getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoF(nroEntregas, anno);

		CatalogoMarca catalogoMarca = null;
		String idProductoPorNumeroEntrega = null, idCatalogoProductoQaliwarma;

		for (ProductoPorNumeroEntrega productoPorNumeroEntrega : lstProductoPorNumeroEntrega) {
			idProductoPorNumeroEntrega = productoPorNumeroEntrega.getIdProductoPorNumeroEntrega();
			idCatalogoProductoQaliwarma = productoPorNumeroEntrega.getCatalogoProductoQaliwarma()
					.getIdCatalogoProductoQaliwarma();

			@SuppressWarnings("unused")
			CatalogoProductoQaliwarma catalogoProductoQaliwarma = catalogoProductoQaliwarmaService
					.findbyid(idCatalogoProductoQaliwarma).get();
			;

			// llamar al producto presentacion por el anno y el numero de entrega.
			List<ProductoPresentacion> lstProductoPresentacion = productoPresentacionService
					.getProductoPresentacionByIdProductoAnnoNumeroEntrega(idCatalogoProductoQaliwarma, anno,
							nroEntregas);

			for (ProductoPresentacion presentacion : lstProductoPresentacion) {

				// busco el catalogo_marca por su producoNumeroEntrega y productoPresentacion
				// (ids)
				catalogoMarca = catalogoMarcaService.getCatalogoMarcaByIdProductoPorNumeroEntregaIdProductoPresentacion(
						idProductoPorNumeroEntrega, presentacion.getIdProductoPresentacion());
				
				if(catalogoMarca != null) {
					catalogoMarcaService.deletebyid(catalogoMarca.getIdCatalogoMarca());
					catalogoMarca=null;
				}
				
				if (catalogoMarca == null) {
					catalogoMarca = new CatalogoMarca();
					catalogoMarca.setIdCatalogoMarca(
							idProductoPorNumeroEntrega + "-" + presentacion.getIdProductoPresentacion());
					catalogoMarca.setProductoPorNumeroEntrega(productoPorNumeroEntrega);
					catalogoMarca.setProductoPresentacion(presentacion);
					catalogoMarcaService.grabar(catalogoMarca);
				}

			}
		}

	}

	public void deleteTablasParaRecargarDatos(Integer anno, Integer numeroEntrega) {
		// volumenConvertidoEnvaseService.deleteAll(anno,numeroEntrega);
		// requerimientoVolumen002ProductoService.deleteAll(anno,numeroEntrega);
		// requerimientoVolumen002Service.deleteAll(anno,numeroEntrega);
		requerimientoVolumen001Service.deleteAll(anno, numeroEntrega);
	}

	// ---------------------------------------------------------------------------------------------//
	// funcion genera guias de remision, para su impresion por anno y numero de
	// entrega
	// ---------------------------------------------------------------------------------------------//

	public class ExcepcionDosMarcasSinPiking extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExcepcionDosMarcasSinPiking(String msg) {
			super(msg);
		}
	}

	public void crearGuiasDeRemision(Integer anno, Integer numeroEntrega, Integer idproveedorcliente, Integer serie,
			Integer numeroGuiaInicio) throws ExcepcionDosMarcasSinPiking {
		
		//List<RutaDistribucion> lstRutaDistribucion = rutaDistribucionService.getRutaDistribucionByAnnoAndNumeroEntrega(anno, numeroEntrega);
		

		List<RequerimientoVolumen001> lstRequerimientoVolumen001 = requerimientoVolumen001Service
				.getReqVol001ByAnnoAndNumeroEntrega(anno, numeroEntrega);

		lstRequerimientoVolumen001.sort((a, b) -> a.getCodigomodularIinstitucionEducativa().getCodigoModular()
				.compareTo(b.getCodigomodularIinstitucionEducativa().getCodigoModular()));

		// @SuppressWarnings("unused")
		// List<RequerimientoVolumen001> lstRequerimientoVolumen001Aux = new
		// ArrayList<>();
		// for (RequerimientoVolumen001 row : lstRequerimientoVolumen001) {
		//
		// if
		// (row.getCodigomodularIinstitucionEducativa().getCodigoModular().equals("1218395"))
		// {
		// lstRequerimientoVolumen001Aux.add(row);
		// }
		// }

		// List<EntregaPorItem> lst = this.getItemsEntregaParaProcesos(anno,
		// numeroEntrega);
		Integer totalRows = lstRequerimientoVolumen001.size();
		GuiaRemision001 guiaRemision001 = null;
		GuiaRemision002 guiaRemision002 = null;

		// Proveedorcliente proveedorcliente =
		// proveedorclienteService.findbyid(idproveedorcliente).get();
		Integer idGuiaRemision = guiaRemision001Service.getMax() == null ? 1 : guiaRemision001Service.getMax() + 1;
		numeroGuiaInicio = idGuiaRemision;
		Integer cntRow = 1;
		List<GuiaRemision002> lstGuiaRemision002 = new ArrayList<>();
		List<CatalogoMarca> lstCatalogoMarcaAux = new ArrayList<>();
		List<String> lstString = new ArrayList<>();
		for (RequerimientoVolumen001 requerimientoVolumen001 : lstRequerimientoVolumen001) {
			System.out.println("Procesando " + cntRow + " de " + totalRows + " Codigo Modular : "
					+ requerimientoVolumen001.getCodigomodularIinstitucionEducativa().getCodigoModular());
			cntRow++;
			guiaRemision001 = new GuiaRemision001();
			guiaRemision001.setIdGuiaRemision001(idGuiaRemision++);
			guiaRemision001.setFechaEmision(new Date());
			guiaRemision001.setProveedorcliente(requerimientoVolumen001.getEntregaPorItem().getProveedorcliente());
			guiaRemision001.setRequerimientoVolumen001(requerimientoVolumen001);
			guiaRemision001.setSerie(serie);
			guiaRemision001.setNumero(numeroGuiaInicio);

			guiaRemision001.setPuntoPartida(requerimientoVolumen001.getEntregaPorItem().getPuntoPartida());
			
			//buscar en al funcion para optener los datos del vehiculo y chofer del codigo modular
			
			String codigoModular = requerimientoVolumen001.getCodigomodularIinstitucionEducativa().getCodigoModular();
			//RutaDistribucion rd = this.getRequerimientoVolumenFromRd(codigoModular, lstRutaDistribucion);
			RutaDistribucionDetalle rdDao = rutaDistribucionDetalleService.getRutaDistribucionByCodigoModularAndNumeroEntregaAndAnno(codigoModular, numeroEntrega, anno);
			//rd RutaDistribucion
			guiaRemision001.setTransportista(requerimientoVolumen001.getEntregaPorItem().getTransportista());
			if(rdDao == null) {
				guiaRemision001.setVehiculo(requerimientoVolumen001.getEntregaPorItem().getVehiculo());
				guiaRemision001.setChofer(requerimientoVolumen001.getEntregaPorItem().getChofer());
				
				lstString.add(codigoModular);
			}else {
				RutaDistribucion rd = rdDao.getRutaDistribucion();
				guiaRemision001.setVehiculo(rd.getVehiculo());
				guiaRemision001.setChofer(rd.getChofer());	
				guiaRemision001.setTransportista(rd.getTransportista() );
				guiaRemision001.setFechaEmision(rd.getFechaDistribucion());
			}
			rdDao = null;
//			
//			if(rd == null) {
//				guiaRemision001.setVehiculo(requerimientoVolumen001.getEntregaPorItem().getVehiculo());
//				guiaRemision001.setChofer(requerimientoVolumen001.getEntregaPorItem().getChofer());
//				lstString.add(codigoModular);
//				
//			}else {
//				guiaRemision001.setVehiculo(rd.getVehiculo());
//				guiaRemision001.setChofer(rd.getChofer());	
//			}
			
			
//			guiaRemision001.setVehiculo(requerimientoVolumen001.getEntregaPorItem().getVehiculo());
//			guiaRemision001.setChofer(requerimientoVolumen001.getEntregaPorItem().getChofer());
			
		
			
			//

			Integer cntDetalle = 1;
			List<RequerimientoVolumen002> lstRequerimientoVolumen002 = requerimientoVolumen001
					.getRequerimientoVolumen002s();
			for (RequerimientoVolumen002 requerimientoVolumen002 : lstRequerimientoVolumen002) {

				List<RequerimientoVolumen002Producto> lstRequerimientoVolumen002Producto = requerimientoVolumen002
						.getRequerimientoVolumen002Productos();

				lstRequerimientoVolumen002Producto
						.sort((RequerimientoVolumen002Producto r1, RequerimientoVolumen002Producto r2) -> r1
								.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma()
								.getDscCatalogoProductoQaliwarma().compareTo(r2.getProductoPorNumeroEntrega()
										.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()));

				Piking piking = null;

				for (RequerimientoVolumen002Producto requerimientoVolumen002Producto : lstRequerimientoVolumen002Producto) {

					List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvase = requerimientoVolumen002Producto
							.getVolumenConvertidoEnvaces();

					for (VolumenConvertidoEnvace volumenConvertidoEnvace : lstVolumenConvertidoEnvase) {

						// si volumenConvertidoEnvace.getCantidad() > 0
						if (volumenConvertidoEnvace.getCantidad() > 0) {

							List<CatalogoMarca> lstCatalogoMarca = volumenConvertidoEnvace.getProductoPresentacion()
									.getCatalogoMarcas();

							lstCatalogoMarcaAux = new ArrayList<>();
							if (lstCatalogoMarca.size() > 1) {

								for (int j = 0; j < lstCatalogoMarca.size(); j++) {
									String a = lstCatalogoMarca.get(j).getProductoPorNumeroEntrega().getEntregaPorItem()
											.getItemEntrega().getItem();
									String b = requerimientoVolumen001.getEntregaPorItem().getItemEntrega().getItem();
									Boolean c = lstCatalogoMarca.get(j).getProductoPorNumeroEntrega()
											.equals(volumenConvertidoEnvace.getRequerimientoVolumen002Producto()
													.getProductoPorNumeroEntrega());
									if (a.equals(b) && c) {
										lstCatalogoMarcaAux.add(lstCatalogoMarca.get(j));
									}

								}
							}

							for (CatalogoMarca catalogoMarca : lstCatalogoMarcaAux) {

								//System.out.println("Catalogo Marca :" + catalogoMarca.getMarca().getDscmarca());
								if (this.siExistePresentacionDuplicado(
										catalogoMarca.getProductoPresentacion().getCantidadPresentacion(),
										lstCatalogoMarcaAux)) {
									//System.out.println(
									//		"Existen dos presentaciones con diferentes marcas las cuales deben ser verificadas si estan explicitas en el piking");

									// buscamos en volumenConvertido pinking si esta registrado segun el codigo
									// modular

									// Trabajamos directamente con el pikin

									if (volumenConvertidoEnvace.getPikings().size() == 0) {
										//System.out.println(
										//		"Existe dos presentaciones con diferentes marcas,,, las cuasles deben ser indicadas en el pikink ... y no estan");
										throw new ExcepcionDosMarcasSinPiking(
												"EXISTE DOS MARCAS O LOTES CON LA MISMA PRESENTACION QUE NO FUE REGISTRADA EN EL PIKING :: "
														+

														volumenConvertidoEnvace.getRequerimientoVolumen002Producto()
																.getRequerimientoVolumen002()
																.getRequerimientoVolumen001().getEntregaPorItem()
																.getItemEntrega().getDscitem()
														+ "-"
														+ volumenConvertidoEnvace.getProductoPresentacion()
																.getCatalogoProductoQaliwarma()
																.getDscCatalogoProductoQaliwarma()
														+ " - " + catalogoMarca.getMarca().getDscmarca());

									}

									for (Piking rowPikin : volumenConvertidoEnvace.getPikings()) {

										guiaRemision002 = new GuiaRemision002();

										guiaRemision002 = this.fillGuiaRemision002Item(requerimientoVolumen001,
												volumenConvertidoEnvace, guiaRemision001, guiaRemision002, rowPikin,
												catalogoMarca, numeroGuiaInicio, cntDetalle);

										lstGuiaRemision002.add(guiaRemision002);
										cntDetalle++;

									}
									break;

									

								} else // si no existe duplicado de presentacion.

								{
									if (lstCatalogoMarcaAux.size() > 1) {
										if (volumenConvertidoEnvace.getPikings().size() == 0) {
											throw new ExcepcionDosMarcasSinPiking(
													"EXISTE DOS MARCAS O LOTES CON LA MISMA PRESENTACION QUE NO FUE REGISTRADA EN EL PIKING");
										}
									}

									if (volumenConvertidoEnvace.getPikings().size() > 0) {

										for (Piking rowPikink : volumenConvertidoEnvace.getPikings()) {
											piking = rowPikink;

											guiaRemision002 = new GuiaRemision002();

											guiaRemision002 = this.fillGuiaRemision002Item(requerimientoVolumen001,
													volumenConvertidoEnvace, guiaRemision001, guiaRemision002, piking,
													catalogoMarca, numeroGuiaInicio, cntDetalle);

											lstGuiaRemision002.add(guiaRemision002);
											cntDetalle++;

										}
									} else {
										guiaRemision002 = new GuiaRemision002();
										piking = null;

										guiaRemision002 = this.fillGuiaRemision002Item(requerimientoVolumen001,
												volumenConvertidoEnvace, guiaRemision001, guiaRemision002, piking,
												catalogoMarca, numeroGuiaInicio, cntDetalle);

										List<CatalogoLote> lstCatalogoLotes = catalogoMarca.getCatalogoLotes();

										if (lstCatalogoLotes.size() > 0) {
											Integer nCatalogoLote = 1;
											for (CatalogoLote catalogoLote : lstCatalogoLotes) {

												if (nCatalogoLote == 1) {
													guiaRemision002.setNumeroLote(catalogoLote.getNumeroLote());
													// lstGuiaRemision002.add(guiaRemision002);
													nCatalogoLote++;
												} else {

												}

											}

											lstGuiaRemision002.add(guiaRemision002);
											cntDetalle++;
										} else {
											lstGuiaRemision002.add(guiaRemision002);
											cntDetalle++;
										}
										lstCatalogoLotes = null;
									}

								}

							}
							lstCatalogoMarca = null;

						}
						// Fin volumenConvertidoEnvace.getCantidad() > 0

					}
					lstVolumenConvertidoEnvase = null;

				}
				lstRequerimientoVolumen002Producto = null;

			}

			guiaRemision001.setGuiaRemision002s(lstGuiaRemision002);

			// for(GuiaRemision002 g:lstGuiaRemision002) {
			// System.out.println(g.getProductoSeleccionado() + "-" + g.getFactor());
			// }

			guiaRemision001 = guiaRemision001Service.grabar(guiaRemision001);

			lstGuiaRemision002 = new ArrayList<>();

			numeroGuiaInicio++;

		}
		lstGuiaRemision002 = null;
		lstString.forEach(r-> {
			System.out.println(r);
		});
		
		
		this.actulizaNumeroOrdenGuiaRemision(anno, numeroEntrega);

	}

//	private RutaDistribucion getRequerimientoVolumenFromRd(String codigoModular, List<RutaDistribucion> lst) {
//		// TODO Auto-generated method stub
//		
//		for (RutaDistribucion rutaDistribucion : lst) {
//			
//			for(RutaDistribucionDetalle rd : rutaDistribucion.getRutaDistribucionDetalles()) {
//				
//				if(rd.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular().equals(codigoModular)) {
//					return rutaDistribucion;
//					
//				}
//				
//			}
//		}
//	
//		return null;
//	}

	public boolean siExistePresentacionDuplicado(Integer presentacion, List<CatalogoMarca> lst) {
		boolean rtn = false;
		Integer cntUbicado = 0;
		for (CatalogoMarca row : lst) {
			if (row.getProductoPresentacion().getCantidadPresentacion().equals(presentacion)) {
				cntUbicado++;
			}
		}
		if (cntUbicado > 1) {
			rtn = true;
		}
		return rtn;
	}

	public GuiaRemision002 fillGuiaRemision002Item(RequerimientoVolumen001 requerimientoVolumen001,
			VolumenConvertidoEnvace volumenConvertidoEnvace, GuiaRemision001 guiaRemision001,
			GuiaRemision002 guiaRemision002, Piking piking, CatalogoMarca catalogoMarca, Integer numeroGuiaInicio,
			Integer cntDetalle) {

		String idGuiaRemision002 = requerimientoVolumen001.getIdRequerimientoVolumen001() + "-"
				+ volumenConvertidoEnvace.getIdVolumenConvertidoEnvace() + "-" + numeroGuiaInicio.toString()
				+ "-NDETALLE" + cntDetalle.toString() + "-"
				+ volumenConvertidoEnvace.getProductoPresentacion().getFactorVolumenPresentacion().toString();

		guiaRemision002.setIdGuiaRemision002(idGuiaRemision002);
		// cntDetalle++;

		//System.out.println(idGuiaRemision002);

		guiaRemision002.setGuiaRemision001(guiaRemision001);

		Double cantidad = piking == null ? volumenConvertidoEnvace.getCantidad().doubleValue()
				: piking.getCantidad().doubleValue();

		guiaRemision002.setCantidad(cantidad.intValue());

		Double factor = volumenConvertidoEnvace.getProductoPresentacion().getFactorVolumenPresentacion().doubleValue();

		guiaRemision002.setFactor(volumenConvertidoEnvace.getProductoPresentacion().getFactorVolumenPresentacion());

		guiaRemision002.setPesoTotal(new BigDecimal(cantidad * factor));

		guiaRemision002.setVolumenConvertidoEnvace(volumenConvertidoEnvace);

		guiaRemision002.setProductoGrupo(volumenConvertidoEnvace.getRequerimientoVolumen002Producto()
				.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma());

		guiaRemision002.setProductoSeleccionado(volumenConvertidoEnvace.getRequerimientoVolumen002Producto()
				.getProductoPorNumeroEntrega().getDscComplementoProducto());

		if (piking != null) {
			guiaRemision002.setMarca(piking.getCatalogoLote().getCatalogoMarca().getMarca().getDscmarca());
		} else {
			guiaRemision002.setMarca(catalogoMarca.getMarca() == null ? "" : catalogoMarca.getMarca().getDscmarca());
		}

		guiaRemision002.setUnidadMedidaTrabajo(
				volumenConvertidoEnvace.getRequerimientoVolumen002Producto().getProductoPorNumeroEntrega()
						.getCatalogoProductoQaliwarma().getUnidadMedidaTrabajo().getDscUnidadMedidaTrabajo());

		guiaRemision002.setEnvase(volumenConvertidoEnvace.getRequerimientoVolumen002Producto()
				.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getEnvase().getDscEnvase());

		if (piking != null) {
			guiaRemision002.setNumeroLote(piking.getCatalogoLote().getNumeroLote());
			guiaRemision002.setIdGuiaRemision002(guiaRemision002.getIdGuiaRemision002() + "PK-" + piking.getIdPiking());

		}

		guiaRemision002.setFlagOverLote(false);

		return guiaRemision002;

	}

	public void actulizaNumeroOrdenGuiaRemision(Integer anno, Integer numeroEntrega) {
		System.out.println("Inicio para actualizar el orden de las guias");
		List<EntregaPorItem> lstItems = entregaPorItemService.getListEntregaPorAnnoAndNumeroEntrega(anno,
				numeroEntrega);
		Integer cnt = 1;
		for (EntregaPorItem entregaPorItem : lstItems) {
			cnt = 1;
			String item = entregaPorItem.getItemEntrega().getItem();
			List<GuiaRemision001> lstGuiaRemision001 = guiaRemision001Service
					.getGuiaRemisionPorAnoNumeroEntregaItem(anno, numeroEntrega, item);

			for (GuiaRemision001 guiaRemision001 : lstGuiaRemision001) {
				guiaRemision001.setOrdenPorItem(cnt);
				guiaRemision001Service.grabar(guiaRemision001);
				cnt++;
			}
		}

		System.out.println("Fin actualizacon orden de guias");
	}

	// -------------------------------------------------------------------------------------------------//
	// devuelve lista de items entrega con las tablas relacionadas para proceso de
	// generacion de excel y para generacion de guias de remision
	// ------------------------------------------------------------------------------------------------//
	public List<EntregaPorItem> getItemsEntregaParaProcesos(Integer anno, Integer numeroEntrega) {

		List<EntregaPorItem> lstEntregaPorItems = entregaPorItemService.getListEntregaPorAnnoAndNumeroEntrega(anno,
				numeroEntrega);// getEntregaPorItemByAnno(anno, numeroEntrega);//
								// itemEntregaService.getItemByItem(idItem,
		// anno);

		// List<EntregaPorItem> lstEntregaPorItems = new ArrayList<>();

		// lstEntregaPorItems.add(entregaPorItems);

		for (EntregaPorItem entregaPorItem : lstEntregaPorItems) {
			System.out.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem());
			List<RequerimientoVolumen001> lstRequerimientoVolumen001 = requerimientoVolumen001Service
					.getReqVol001ByAnnoItem(entregaPorItem.getItemEntrega().getAnno(),
							entregaPorItem.getItemEntrega().getItem());

			System.out.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem() + " Req. Vol 1 ");
			for (RequerimientoVolumen001 requerimientoVolumen001 : lstRequerimientoVolumen001) {
				List<RequerimientoVolumen002> lstRequerimientoVolumen002 = requerimientoVolumen002Service
						.getRequerimientoVolumen002ByIdR1(requerimientoVolumen001.getIdRequerimientoVolumen001(),
								numeroEntrega);

				System.out
						.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem() + " Req. Vol 2 ");
				for (RequerimientoVolumen002 requerimientoVolumen002 : lstRequerimientoVolumen002) {

					System.out.println("Preparando Item : " + entregaPorItem.getItemEntrega().getDscitem()
							+ " Req. Vol Producto ");

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

		return lstEntregaPorItems;

	}

	@SuppressWarnings("unused")
	public void ActualizaPesoTotalRequerimientoVolumen001(Integer anno, Integer numeroEntrega) {
		System.out.println("Cargando lista requerimiento año : " + anno + " Entrega : " + numeroEntrega);
		List<Matriz> lst = matrizService.getMatrizByAnnoAndNumeroEntrega(anno, numeroEntrega);

		Integer nCont = 1;
		Double pesoAcum = 0.0;
		Matriz matrizOld = new Matriz();

		String sCodModular = "";
		String sCodModularOld = "";

		String idRequerimientoVolumen001 = null;
		String idRequerimientoVolumen001Old = null;
		RequerimientoVolumen001 requerimientoVolumen001 = null;
		for (Matriz matrizRow : lst) {

			sCodModular = matrizRow.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getCodigoModular();

			idRequerimientoVolumen001 = matrizRow.getRequerimientoVolumen001().getIdRequerimientoVolumen001();

			System.out.println("Codigo Modular : " + sCodModular);
			if (sCodModular.equals("0266965")) {
				System.out.println("Codigo Modular : " + sCodModular);
			}

			if (matrizRow.getRequerimientoVolumen001().getIdRequerimientoVolumen001()
					.equals("220101-0266965-MOYOBAMBA 1-RA3_AMAZONIA_ALTA")) {
				System.out.println(" xxxxxx ");
			}

			if (sCodModular.equals(sCodModularOld)) {
				pesoAcum = pesoAcum + matrizRow.getPeso().doubleValue();
			} else {

				if (nCont != 1) {

					requerimientoVolumen001 = requerimientoVolumen001Service.findbyid(idRequerimientoVolumen001Old)
							.get();
					requerimientoVolumen001.setPesoTotal(new BigDecimal(pesoAcum));
					System.out.println("Grabando .. : " + sCodModularOld + " id :" + idRequerimientoVolumen001Old
							+ " Peso :" + pesoAcum);
					requerimientoVolumen001Service.grabar(requerimientoVolumen001);
					System.out.println("Grabado id reque001 : " + idRequerimientoVolumen001);
				} else {

				}

				pesoAcum = matrizRow.getPeso().doubleValue();
				nCont++;
			}

			sCodModularOld = sCodModular;
			idRequerimientoVolumen001Old = idRequerimientoVolumen001;

			BeanUtils.copyProperties(matrizRow, matrizOld);

		}
		if (lst.size() - 1 > -1) {
			idRequerimientoVolumen001 = lst.get(lst.size() - 1).getRequerimientoVolumen001()
					.getIdRequerimientoVolumen001();
			requerimientoVolumen001 = requerimientoVolumen001Service.findbyid(idRequerimientoVolumen001).get();
			requerimientoVolumen001.setPesoTotal(new BigDecimal(pesoAcum));
			requerimientoVolumen001Service.grabar(requerimientoVolumen001);
			lst = null;
			System.out.println("Ultimo id reque001 : " + idRequerimientoVolumen001);

		}

		// List<RequerimientoVolumen001> lstRequerimientoVolumen001 =
		// requerimientoVolumen001Service.getReqVol001ByAnnoAndNumeroEntrega(anno,
		// numeroEntrega);
		// String idRequerimientoVolumen001 = null;
		// BigDecimal pesoTotal = null;
		// Integer totalRegistros = lstRequerimientoVolumen001.size();
		// Integer cntRegistros = 1;
		// System.out.println();
		// for(RequerimientoVolumen001
		// requerimientoVolumen001:lstRequerimientoVolumen001) {
		// System.out.println("Proceso Registro " + cntRegistros + " de " +
		// totalRegistros);
		// cntRegistros++;
		// idRequerimientoVolumen001 =
		// requerimientoVolumen001.getIdRequerimientoVolumen001();
		//
		// pesoTotal =
		// matrizService.getSumaPesoByIdRequerimientoVolumen001(idRequerimientoVolumen001);
		// requerimientoVolumen001.setPesoTotal(pesoTotal);
		// requerimientoVolumen001Service.grabar(requerimientoVolumen001);
		//
		// }
		// lstRequerimientoVolumen001 = null;

	}

	public void cargarTablaMatriz(Integer anno, Integer numeroEntrega) {

		System.out.println("Por favor espere iniciando proceso ....  carga matris");
		List<RequerimientoClass> lstRequerimientoVolumen002Producto = requerimientoVolumen002ProductoService
				.listarTodo(anno, numeroEntrega);

		Integer totalFilas = lstRequerimientoVolumen002Producto.size();

		RequerimientoVolumen002Producto requerimientoVolumen002Producto = null;

		Matriz matriz = null;
		List<Matriz> lstMatriz = new ArrayList<>();
		long id = 0;
		List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvace = null;
		@SuppressWarnings("unused")
		Boolean existe = false;
		for (Integer i = 0; i < totalFilas; i++) {
			System.out.println("Procesando " + i + " de " + totalFilas);
			id++;
			requerimientoVolumen002Producto = lstRequerimientoVolumen002Producto.get(i)
					.getRequerimientoVolumen002Producto();

			lstVolumenConvertidoEnvace = requerimientoVolumen002Producto.getVolumenConvertidoEnvaces();
			// List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvaceAux = new
			// ArrayList<>();

			List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvaceAux = new ArrayList<>();
			existe = false;
			for (VolumenConvertidoEnvace row : lstVolumenConvertidoEnvace) {
				if (lstVolumenConvertidoEnvace.size() > 2) {
					System.out.println("Mayor que ....");
				}
				Integer annoAux = row.getRequerimientoVolumen002Producto().getProductoPorNumeroEntrega()
						.getEntregaPorItem().getItemEntrega().getAnno();
				Integer numeroEntregaAux = row.getRequerimientoVolumen002Producto().getProductoPorNumeroEntrega()
						.getEntregaPorItem().getNumeroEntrega().getNumeroEntregaValor();

				if (annoAux.equals(anno) || numeroEntregaAux.equals(numeroEntrega)) {

					lstVolumenConvertidoEnvaceAux.add(row);
				}
				// else {
				//
				// for (VolumenConvertidoEnvace rowFind : lstVolumenConvertidoEnvaceAux) {
				// if
				// (rowFind.getIdVolumenConvertidoEnvace().equals(row.getIdVolumenConvertidoEnvace()))
				// {
				// existe = true;
				// }
				// }
				// if (!existe) {
				// lstVolumenConvertidoEnvaceAux.add(row);
				// existe = false;
				// }
				//
				// }
			}
			lstVolumenConvertidoEnvace = null;

			//
			matriz = new Matriz();

			matriz.setIdMatriz(id);
			matriz.setRequerimientoVolumen001(
					requerimientoVolumen002Producto.getRequerimientoVolumen002().getRequerimientoVolumen001());

			matriz.setProductoPorNumeroEntrega(requerimientoVolumen002Producto.getProductoPorNumeroEntrega());

			matriz.setVolumen(requerimientoVolumen002Producto.getVolumen());
			matriz.setUsuarios(requerimientoVolumen002Producto.getRequerimientoVolumen002().getNumeroUsuarios());

			matriz.setDscGrupoAlimento(requerimientoVolumen002Producto.getProductoPorNumeroEntrega()
					.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma());
			matriz.setAlimentoSeleccionado(
					requerimientoVolumen002Producto.getProductoPorNumeroEntrega().getDscComplementoProducto());
			matriz.setAbrUnidadMedida(requerimientoVolumen002Producto.getProductoPorNumeroEntrega()
					.getCatalogoProductoQaliwarma().getUnidadmedida().getAbrunidadmedida());

			Integer cnt = 1;
			Integer cntLote = 1;
			for (VolumenConvertidoEnvace rowVolumenConvertidoEnvace : lstVolumenConvertidoEnvaceAux) {

				System.out.println("Volumen convertido enase : producto ::: "
						+ rowVolumenConvertidoEnvace.getRequerimientoVolumen002Producto().getProductoPorNumeroEntrega()
								.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma());

				String xx = rowVolumenConvertidoEnvace.getRequerimientoVolumen002Producto()
						.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma();
				if (xx.equals("CEREAL 2")) {
					System.out.println("alto");
				}
				if (rowVolumenConvertidoEnvace.getRequerimientoVolumen002Producto().getRequerimientoVolumen002()
						.getRequerimientoVolumen001().getIdRequerimientoVolumen001()
						.equals("220103-0274142-2018-2-MOYOBAMBA 1-RA3_AMAZONIA_ALTA") && xx.equals("CEREAL 2")) {
					System.out.println("alto");
				}
				switch (cnt) {
				case 1:

					matriz.setPresentacion1(
							rowVolumenConvertidoEnvace.getProductoPresentacion().getCantidadPresentacion());
					matriz.setUnds1(rowVolumenConvertidoEnvace.getCantidad());

					matriz.setFactor1(
							rowVolumenConvertidoEnvace.getProductoPresentacion().getFactorVolumenPresentacion());

					try {

						if (rowVolumenConvertidoEnvace.getCatalogoMarca().getMarca() != null)
							matriz.setMarca1(rowVolumenConvertidoEnvace.getCatalogoMarca().getMarca().getDscmarca());

						for (CatalogoLote catalogoLote : rowVolumenConvertidoEnvace.getCatalogoMarca()
								.getCatalogoLotes()) {
							// Actualiza matriz segun lote
							matriz = this.ActualizaMatrizSegunLote(matriz, catalogoLote, cntLote);
							cntLote++;
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("No existe marca definida ....................");
					}

					break;

				case 2:

					if (rowVolumenConvertidoEnvace.getRequerimientoVolumen002Producto().getRequerimientoVolumen002()
							.getRequerimientoVolumen001().getIdRequerimientoVolumen001()
							.equals("220103-0274142-2018-2-MOYOBAMBA 1-RA3_AMAZONIA_ALTA") && xx.equals("CEREAL 2")) {
						System.out.println("alto");
					}
					matriz.setPresentacion2(
							rowVolumenConvertidoEnvace.getProductoPresentacion().getCantidadPresentacion());
					matriz.setUnds2(rowVolumenConvertidoEnvace.getCantidad());
					matriz.setFactor2(
							rowVolumenConvertidoEnvace.getProductoPresentacion().getFactorVolumenPresentacion());

					try {
						if (rowVolumenConvertidoEnvace.getCatalogoMarca().getMarca() != null)
							matriz.setMarca2(rowVolumenConvertidoEnvace.getCatalogoMarca().getMarca().getDscmarca());

						for (CatalogoLote catalogoLote : rowVolumenConvertidoEnvace.getCatalogoMarca()
								.getCatalogoLotes()) {
							// Actualiza matriz segun lote
							matriz = this.ActualizaMatrizSegunLote(matriz, catalogoLote, cntLote);
							cntLote++;
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("No existe la marca ....... no definido");
					}

					break;
				case 3:
					matriz.setPresentacion3(
							rowVolumenConvertidoEnvace.getProductoPresentacion().getCantidadPresentacion());
					matriz.setUnds3(rowVolumenConvertidoEnvace.getCantidad());
					matriz.setFactor3(
							rowVolumenConvertidoEnvace.getProductoPresentacion().getFactorVolumenPresentacion());

					try {
						if (rowVolumenConvertidoEnvace.getCatalogoMarca().getMarca() != null)
							matriz.setMarca3(rowVolumenConvertidoEnvace.getCatalogoMarca().getMarca().getDscmarca());

						for (CatalogoLote catalogoLote : rowVolumenConvertidoEnvace.getCatalogoMarca()
								.getCatalogoLotes()) {
							// Actualiza matriz segun lote
							matriz = this.ActualizaMatrizSegunLote(matriz, catalogoLote, cntLote);
							cntLote++;
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("No existe la marca ....... no definido");
					}

					break;
				default:
					break;
				}
				cnt++;
			}
			lstMatriz.add(matriz);

		}
		// actualizamos los pesos
		Double peso1 = 0.0, peso2 = 0.0, peso3 = 0.0;
		for (Matriz row : lstMatriz) {
			peso1 = 0.0;
			peso2 = 0.0;
			peso3 = 0.0;
			if (row.getUnds1() != null) {
				peso1 = (row.getUnds1().doubleValue() * row.getFactor1().doubleValue());
			}
			if (row.getUnds2() != null) {
				peso2 = (row.getUnds2().doubleValue() * row.getFactor2().doubleValue());
			}
			if (row.getUnds3() != null) {
				peso3 = (row.getUnds3().doubleValue() * row.getFactor3().doubleValue());
			}
			row.setPeso(new BigDecimal(peso1 + peso2 + peso3));
		}
		matrizService.grabarList(lstMatriz);

		System.out.println("Fin Proceso cargar matriz ........");
	}

	public Matriz ActualizaMatrizSegunLote(Matriz matriz, CatalogoLote catalogoLote, Integer cnt) {

		String idProducPorNumeroEntrega = matriz.getProductoPorNumeroEntrega().getIdProductoPorNumeroEntrega();
		ProductoPorNumeroEntrega productoPorNumeroEntrega = productoPorNumeroEntregaService
				.findbyid(idProducPorNumeroEntrega).get();
		switch (cnt) {
		case 1:
			matriz.setLote1(catalogoLote.getNumeroLote());
			matriz.setCantidadLote1(catalogoLote.getCantidad());
			matriz.setFechaVencimiento1(catalogoLote.getFechaVencimiento());
			matriz.setRegistroSanitario1(catalogoLote.getNumeroRegistro());

			productoPorNumeroEntrega.setLote1(catalogoLote.getNumeroLote());
			productoPorNumeroEntrega.setCantidadLote1(catalogoLote.getCantidad());
			productoPorNumeroEntrega.setFechaVencimiento1(catalogoLote.getFechaVencimiento());
			productoPorNumeroEntrega.setRegistroSanitario1(catalogoLote.getNumeroRegistro());
			break;
		case 2:
			matriz.setLote2(catalogoLote.getNumeroLote());
			matriz.setCantidadLote2(catalogoLote.getCantidad());
			matriz.setFechaVencimiento2(catalogoLote.getFechaVencimiento());
			matriz.setRegistroSanitario2(catalogoLote.getNumeroRegistro());

			productoPorNumeroEntrega.setLote2(catalogoLote.getNumeroLote());
			productoPorNumeroEntrega.setCantidadLote2(catalogoLote.getCantidad());
			productoPorNumeroEntrega.setFechaVencimiento2(catalogoLote.getFechaVencimiento());
			productoPorNumeroEntrega.setRegistroSanitario2(catalogoLote.getNumeroRegistro());
			break;
		case 3:
			matriz.setLote3(catalogoLote.getNumeroLote());
			matriz.setCantidadLote3(catalogoLote.getCantidad());
			matriz.setFechaVencimiento3(catalogoLote.getFechaVencimiento());
			matriz.setRegistroSanitario3(catalogoLote.getNumeroRegistro());

			productoPorNumeroEntrega.setLote3(catalogoLote.getNumeroLote());
			productoPorNumeroEntrega.setCantidadLote3(catalogoLote.getCantidad());
			productoPorNumeroEntrega.setFechaVencimiento3(catalogoLote.getFechaVencimiento());
			productoPorNumeroEntrega.setRegistroSanitario3(catalogoLote.getNumeroRegistro());
			break;
		case 4:
			matriz.setLote4(catalogoLote.getNumeroLote());
			matriz.setCantidadLote4(catalogoLote.getCantidad());
			matriz.setFechaVencimiento4(catalogoLote.getFechaVencimiento());
			matriz.setRegistroSanitario4(catalogoLote.getNumeroRegistro());

			productoPorNumeroEntrega.setLote4(catalogoLote.getNumeroLote());
			productoPorNumeroEntrega.setCantidadLote4(catalogoLote.getCantidad());
			productoPorNumeroEntrega.setFechaVencimiento4(catalogoLote.getFechaVencimiento());
			productoPorNumeroEntrega.setRegistroSanitario4(catalogoLote.getNumeroRegistro());
			break;
		case 5:
			matriz.setLote5(catalogoLote.getNumeroLote());
			matriz.setCantidadLote5(catalogoLote.getCantidad());
			matriz.setFechaVencimiento5(catalogoLote.getFechaVencimiento());
			matriz.setRegistroSanitario5(catalogoLote.getNumeroRegistro());

			productoPorNumeroEntrega.setLote5(catalogoLote.getNumeroLote());
			productoPorNumeroEntrega.setCantidadLote5(catalogoLote.getCantidad());
			productoPorNumeroEntrega.setFechaVencimiento5(catalogoLote.getFechaVencimiento());
			productoPorNumeroEntrega.setRegistroSanitario5(catalogoLote.getNumeroRegistro());
			break;
		default:
			break;
		}

		productoPorNumeroEntrega = productoPorNumeroEntregaService.grabar(productoPorNumeroEntrega);

		return matriz;

	}

	@SuppressWarnings("unused")
	public void corregirMoyobamba(Integer anno, Integer numeroEntrega, String item) {

		List<GuiaRemision001> lst = guiaRemision001Service.getGuiaRemisionPorAnoNumeroEntregaItem(anno, numeroEntrega,
				item);
		GuiaRemision002 rowAux = null;
		Double cantidad;
		Integer acumCantidad = 0;
		Integer filas = lst.size();
		Integer cnt = 1;

		List<GuiaRemision001> lstGuias = new ArrayList<>();

		List<String> lstString = new ArrayList<String>();

		String lista = "0512822;" + "0564344;" + "0603340;" + "0761973;" + "0834838;" + "0603332;" + "1585835;";

		String[] lstStrings = lista.split(";");

		for (int i = 0; i < lstStrings.length; i++) {
			String s = lstStrings[i];
			lstString.add(s);

		}

		for (GuiaRemision001 guiaRemision001 : lst) {
			String sCodigoModular = guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getCodigoModular();
			lstString.forEach(itemCodigo -> {
				if (itemCodigo.equals(sCodigoModular)) {
					lstGuias.add(guiaRemision001);
				}
			});
		}

		// inicar el cambio
		for (GuiaRemision001 guiaRemision001 : lstGuias) {

			System.out.println(" Procesando : " + cnt + " de " + filas);
			cnt++;
			acumCantidad = 0;
			List<GuiaRemision002> lstGuiaRemision002 = guiaRemision001.getGuiaRemision002s();

			for (GuiaRemision002 row : lstGuiaRemision002) {
				if (row.getProductoGrupo().equals("POA H 1") && row.getNumeroLote().equals("CCTPA4")) {
					acumCantidad = row.getCantidad();
					row.setCantidad(0);
					row = guiaRemision002Service.grabar(row);
					break;
				}

			}
			if (acumCantidad > 0) {
				for (GuiaRemision002 row : lstGuiaRemision002) {

					if (row.getProductoGrupo().equals("POA NH 1") && (row.getNumeroLote().equals("501217289"))) {

						row.setCantidad(row.getCantidad() + acumCantidad);
						row = guiaRemision002Service.grabar(row);
						break;

					}

				}
			}

		}

		System.out.println(" Fin proceso ");

	}

	// este proceso elimina el calculo de la tabla volumen convertido
	// envace y la tabla matriz del año y numero de entrega correspondiente
	@SuppressWarnings("unused")
	public void eliminarCalculo(Integer anno, Integer numeroEntrega) {

		// obtenemos los entrega por item del periodo a eliminar
		List<EntregaPorItem> lstEntregaPorItem = entregaPorItemService.getListEntregaPorAnnoAndNumeroEntrega(anno,
				numeroEntrega);

	}

	// para trasladar ruta de distribucion
	public void pasarRutaSiguienteEntrega() {

		Integer anno = 2018;
		Integer numeroEntregaOrigen = 1;
		Integer numeroEntregaDestino = 2;
		List<RutaDistribucion> lstRutaDistribucion = rutaDistribucionService
				.getRutaDistribucionByAnnoAndNumeroEntrega(anno, numeroEntregaOrigen);
		RequerimientoVolumen001 requerimientoVolumen001Destino;

		RutaDistribucion rutaDistribucionDestino = new RutaDistribucion();
		RutaDistribucionDetalle rutaDistribucionDetalleDestino;
		List<RutaDistribucionDetalle> lstDetalle = new ArrayList<>();

		for (RutaDistribucion rutaDistribucion : lstRutaDistribucion) {
			System.out.println(rutaDistribucion.getDscRutaDistribucion());

			for (RutaDistribucionDetalle rutaDistribucionDetalle : rutaDistribucion.getRutaDistribucionDetalles()) {
				RequerimientoVolumen001 requerimientoVolumen001 = rutaDistribucionDetalle.getRequerimientoVolumen001();

				rutaDistribucionDetalleDestino = new RutaDistribucionDetalle();

				BeanUtils.copyProperties(rutaDistribucionDetalle, rutaDistribucionDetalleDestino);

				String codigoModular = requerimientoVolumen001.getCodigomodularIinstitucionEducativa()
						.getCodigoModular();

				requerimientoVolumen001Destino = new RequerimientoVolumen001();
				requerimientoVolumen001Destino = requerimientoVolumen001Service
						.getRequerimientoVolumen001ByCodigoModular(codigoModular, anno, numeroEntregaDestino);

				rutaDistribucionDetalleDestino.setIdRutaDistribucionDetalle(new Idunico().getIdunico());
				rutaDistribucionDetalleDestino.setRequerimientoVolumen001(requerimientoVolumen001Destino);

				lstDetalle.add(rutaDistribucionDetalleDestino);

			}

			BeanUtils.copyProperties(rutaDistribucion, rutaDistribucionDestino);
			rutaDistribucionDestino.setNumeroEntrega(numeroEntregaDestino);
			rutaDistribucionDestino.setIdRutaDistribucion(
					rutaDistribucion.getIdRutaDistribucion() + "-" + rutaDistribucion.getNumeroEntrega());
			rutaDistribucionDestino.setRutaDistribucionDetalles(lstDetalle);

			for (RutaDistribucionDetalle row : rutaDistribucionDestino.getRutaDistribucionDetalles()) {
				row.setRutaDistribucion(rutaDistribucionDestino);
			}

			@SuppressWarnings("unused")
			RutaDistribucion rd;
			rd = rutaDistribucionService.grabar(rutaDistribucionDestino);
			lstDetalle = new ArrayList<>();

		}

		System.out.println("Fin Traslado .................");
	}

	public void actualizaLatitudLongitud(FileInputStream file) throws IOException {
		XSSFWorkbook worbook = new XSSFWorkbook(file);
		XSSFSheet sheet = worbook.getSheet("PADRON_IE");
		Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;

		Row row;
		Cell cellLatitud, cellLongitud, cellCodigoModular;
		String codigoModular = null;

		Integer fila = 1;
		Double latitud, longitud;
		CodigomodularIinstitucionEducativa codigomodularIinstitucionEducativa = new CodigomodularIinstitucionEducativa();
		Boolean existe = false;
		for (int i = fila; i <= totalRows; i++) {
			System.out.println("Procesando : " + i);

			row = sheet.getRow(i);

			if (i == 69) {
				System.out.println("verificando : " + codigoModular);
			}
			if (row != null) {
				cellLatitud = row.getCell(2);
				cellLongitud = row.getCell(1);
				cellCodigoModular = row.getCell(0);
				if (cellCodigoModular != null) {

					codigoModular = cellCodigoModular.getStringCellValue();
					if (codigoModular.equals("0297903")) {
						System.out.println("verificando : " + codigoModular);
					}
					latitud = cellLatitud.getNumericCellValue();
					longitud = cellLongitud.getNumericCellValue();

					existe = codigoModularInstitucionEducativaService.findbyid(codigoModular).isPresent() ? true
							: false;
					if (existe) {
						codigomodularIinstitucionEducativa = codigoModularInstitucionEducativaService
								.findbyid(codigoModular).get();
						codigomodularIinstitucionEducativa.setLatitud(latitud);
						codigomodularIinstitucionEducativa.setLongitud(longitud);

						codigoModularInstitucionEducativaService.grabar(codigomodularIinstitucionEducativa);
					}

				}
			}
		}
		worbook.close();
		file.close();

	}

}
