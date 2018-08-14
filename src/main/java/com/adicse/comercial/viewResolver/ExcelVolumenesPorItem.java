package com.adicse.comercial.viewResolver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.adicse.comercial.clases.SumaVolumenPorItem;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.ProductoPresentacion;
import com.adicse.comercial.model.RequerimientoVolumen001;
import com.adicse.comercial.model.RequerimientoVolumen002;
import com.adicse.comercial.model.RequerimientoVolumen002Producto;
import com.adicse.comercial.model.VolumenConvertidoEnvace;



public class ExcelVolumenesPorItem extends AbstractXlsxView {
	
	public static List<SumaVolumenPorItem> lstSumaVolumenPoritemTotal = new ArrayList<SumaVolumenPorItem>();

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		List<EntregaPorItem> lst = (List<EntregaPorItem>) model.get("data");
		Integer anno = (Integer) model.get("anno");
		Integer numeroEntrega = (Integer) model.get("numeroEntrega");

		response.setHeader("Content-Disposition", "attachment: filename=\"d:\\Items.xlsx");

	
		String sCodigoModular = null, sRegionAlimentaria = null, sHorarioAlimentario = null, sNivelEducacion = null,
				sProducto = null;
		BigDecimal volumen;
		
		SumaVolumenPorItem sumaVolumenPorItem;
		Row row;
		Cell cell;
		CellStyle style = getStyle(workbook);
		
		
		
		for (EntregaPorItem entregaPorItem : lst) {
			Integer rowNum = 3;
			Sheet sheet = createsheet(workbook, entregaPorItem.getItemEntrega().getDscitem().toString());
			System.out.println("ITEM : " + entregaPorItem.getItemEntrega().getDscitem());
			if(entregaPorItem.getItemEntrega().getDscitem().equals("MORALES")) {
				System.out.println("Item : detener"  );
			}
			//creamos la cabecera de la hoja
			createHeader(workbook,sheet);
			
			Integer cnt = 0;
			List<RequerimientoVolumen001> lstRequerimientoVolumen001 = entregaPorItem.getRequerimientoVolumen001s();
			
			List<RequerimientoVolumen001> lstRequerimientoVolumen001Aux = new ArrayList<>();
			
			for(RequerimientoVolumen001 requerimientoVolumen001 : lstRequerimientoVolumen001) {
				Integer annoAux = requerimientoVolumen001.getEntregaPorItem().getItemEntrega().getAnno();
				Integer numeroEntregaAux = requerimientoVolumen001.getEntregaPorItem().getNumeroEntrega().getNumeroEntregaValor();
				
				if(annoAux.equals(anno) && numeroEntregaAux.equals(numeroEntrega) ) {
					lstRequerimientoVolumen001Aux.add(requerimientoVolumen001);
					
				}
			}

			List<SumaVolumenPorItem> lstSumaVolumenPoritem = new ArrayList<SumaVolumenPorItem>();
			for (RequerimientoVolumen001 requerimientoVolumen001 : lstRequerimientoVolumen001Aux) {

				sCodigoModular = requerimientoVolumen001.getCodigomodularIinstitucionEducativa().getCodigoModular();
				sRegionAlimentaria = requerimientoVolumen001.getRegionAlimentaria().getDscRegionAlimentaria();
				sHorarioAlimentario = requerimientoVolumen001.getHorarioAlimentacion().getDscHorarioAlimentacion();
				sNivelEducacion = requerimientoVolumen001.getNivelEducacion().getDscNivelEducacion();

				row = sheet.createRow(rowNum++);

				//if (cnt == 0) {

					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(sCodigoModular);

					cell = row.createCell(1);
					cell.setCellStyle(style);
					cell.setCellValue(sRegionAlimentaria);

					cell = row.createCell(2);
					cell.setCellStyle(style);
					cell.setCellValue(sHorarioAlimentario);

					cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellValue(sNivelEducacion);
					cnt++;
				//}

				for (RequerimientoVolumen002 requerimientoVolumen002 : requerimientoVolumen001
						.getRequerimientoVolumen002s() ) {
					
					

					for (RequerimientoVolumen002Producto requerimientoVolumen002Producto : requerimientoVolumen002
							.getRequerimientoVolumen002Productos()) {
						
						

						sProducto = requerimientoVolumen002Producto.getProductoPorNumeroEntrega()
								.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma();
						
						volumen = requerimientoVolumen002Producto.getVolumen();
//						if(sProducto.equals("LECHE EVAPORADA") && entregaPorItem.getItemEntrega().getDscitem().equals("MORALES") ) {
//							System.out.println("Morales");
//						}
						
						cell = row.createCell(0);
						cell.setCellStyle(style);
						cell.setCellValue(sCodigoModular);

						cell = row.createCell(1);
						cell.setCellStyle(style);
						cell.setCellValue(sRegionAlimentaria);

						cell = row.createCell(2);
						cell.setCellStyle(style);
						cell.setCellValue(sHorarioAlimentario);

						cell = row.createCell(3);
						cell.setCellStyle(style);
						cell.setCellValue(sNivelEducacion);
						
						cell = row.createCell(4);
						cell.setCellStyle(style);
						cell.setCellValue(sProducto);

						cell = row.createCell(5);
						cell.setCellStyle(style);
						cell.setCellValue(volumen.doubleValue());

						cell = row.createCell(6);
						cell.setCellStyle(style);
						cell.setCellValue(requerimientoVolumen002.getNumeroUsuarios().doubleValue());
						
						
						

						Integer col = 7;

						List<ProductoPresentacion> lstProductoPresentacion = requerimientoVolumen002Producto
								.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma()
								.getProductoPresentacions();
						
						List<ProductoPresentacion> lstProductoPresentacionAux = new ArrayList<>();
						for (int j = 0; j < lstProductoPresentacion.size(); j++) {
							Integer a = lstProductoPresentacion.get(j).getAnno();
							Integer b = lstProductoPresentacion.get(j).getNumeroEntrega();
							
							if (a.equals(anno)
									&& b.equals(numeroEntrega)) {
								lstProductoPresentacionAux.add(lstProductoPresentacion.get(j));
							}

						}
//						Collections.sort(lstProductoPresentacionAux,
//								(p1, p2) -> p2.getCantidadPresentacion().compareTo(p1.getCantidadPresentacion()));
						
						lstProductoPresentacionAux.sort((ProductoPresentacion p1, ProductoPresentacion p2) -> p2
								.getCantidadPresentacion().compareTo(p1.getCantidadPresentacion()));

//						
//						for (int j = 0; j < lstProductoPresentacion.size(); j++) {
//							if(!lstProductoPresentacion.get(j).getAnno().equals(anno) || !lstProductoPresentacion.get(j).getNumeroEntrega().equals(numeroEntrega)) {
//								lstProductoPresentacion.remove(j);
//							}
//							
//						}
//
//						lstProductoPresentacion.sort((ProductoPresentacion p1, ProductoPresentacion p2) -> p2
//								.getCantidadPresentacion().compareTo(p1.getCantidadPresentacion()));

						Integer cntPresentacion = 0;
						for (ProductoPresentacion productoPresentacion : lstProductoPresentacionAux) {
							cell = row.createCell(col);
							cell.setCellStyle(style);
							cell.setCellValue(productoPresentacion.getCantidadPresentacion().doubleValue());

							List<VolumenConvertidoEnvace> lstVolumenConvertidoEnvace = requerimientoVolumen002Producto
									.getVolumenConvertidoEnvaces();
							
							for (int i = 0; i < lstVolumenConvertidoEnvace.size(); i++) {
								VolumenConvertidoEnvace volumenConvertidoEnvace = lstVolumenConvertidoEnvace.get(i);

								if (volumenConvertidoEnvace.getProductoPresentacion() == productoPresentacion) {
									cell = row.createCell(col + 3);
									cell.setCellStyle(style);
									cell.setCellValue(volumenConvertidoEnvace.getCantidad().doubleValue());
									
									//observacion de la marca segun presentacion
									cell = row.createCell(col + 7);
									cell.setCellStyle(style);
									
									//String sMarca = volumenConvertidoEnvace.getCatalogoMarca().getMarca()==null?"Marca sin definir":volumenConvertidoEnvace.getCatalogoMarca().getMarca().getDscmarca().toString();
									String sMarca ="";
									try {
										sMarca = volumenConvertidoEnvace.getCatalogoMarca()==null?"Marca sin definir":volumenConvertidoEnvace.getCatalogoMarca().getMarca().getDscmarca().toString();
									} catch (Exception e) {
										// TODO: handle exception
										sMarca = "Error ... " + e.getMessage();
									}
									//String sMarca = volumenConvertidoEnvace.getCatalogoMarca()==null?"Marca sin definir":volumenConvertidoEnvace.getCatalogoMarca().getMarca().getDscmarca().toString();
									cell.setCellValue(sMarca);
								}

							}

							col++;

							// Acumulamos los datos.
							Boolean existe = false;
							for (SumaVolumenPorItem suma : lstSumaVolumenPoritem) {
								if (suma.getProducto().equals(sProducto)) {
									if(cntPresentacion == 0) {
										suma.setVolumen(
												new BigDecimal(suma.getVolumen().floatValue() + volumen.floatValue()));
										Integer vp1 = (int) (row.getCell(10)==null?0:row.getCell(10).getNumericCellValue());
										
										suma.setUsuarios(suma.getUsuarios() + requerimientoVolumen002.getNumeroUsuarios());
							
										suma.setVp1( suma.getVp1() + vp1 );
										
										
										cntPresentacion++;
									}else if(cntPresentacion == 1) 
									{
										
										Integer p2 = (int) (row.getCell(8)==null?0:row.getCell(8).getNumericCellValue());		
										suma.setP2(p2);																				
										Integer vp2 = (int) (row.getCell(11)==null?0:row.getCell(11).getNumericCellValue());																			
										suma.setVp2( suma.getVp2() + vp2 );
										cntPresentacion++;
									}else if(cntPresentacion == 2) 
									{										
										Integer p3 = (int) (row.getCell(9)==null?0:row.getCell(9).getNumericCellValue());		
										suma.setP3(p3);																				
										Integer vp3 = (int) (row.getCell(12)==null?0:row.getCell(12).getNumericCellValue());																				
										suma.setVp3( suma.getVp3() + vp3 );
										cntPresentacion++;
									}
									existe = true;
									break;
								}
							}
							if (!existe) {
								sumaVolumenPorItem = new SumaVolumenPorItem();
								sumaVolumenPorItem.setCodigoModular(sCodigoModular);
								sumaVolumenPorItem.setProducto(sProducto);
								sumaVolumenPorItem.setVolumen(volumen);
								
								
								
								Integer p1 = (int) (row.getCell(7)==null?0:row.getCell(7).getNumericCellValue());
								Integer p2 = (int) (row.getCell(8)==null?0:row.getCell(8).getNumericCellValue());
								Integer p3 = (int) (row.getCell(9)==null?0:row.getCell(9).getNumericCellValue());
								sumaVolumenPorItem.setP1(p1);
								sumaVolumenPorItem.setP2(p2);
								sumaVolumenPorItem.setP3(p3);
								
								Integer vp1 = (int) (row.getCell(10)==null?0:row.getCell(10).getNumericCellValue());
								Integer vp2 = (int) (row.getCell(11)==null?0:row.getCell(11).getNumericCellValue());
								Integer vp3 = (int) (row.getCell(12)==null?0:row.getCell(12).getNumericCellValue());
								sumaVolumenPorItem.setVp1( vp1 );
								sumaVolumenPorItem.setVp2( vp2 );
								sumaVolumenPorItem.setVp3( vp3 );
								
								sumaVolumenPorItem.setUsuarios(requerimientoVolumen002.getNumeroUsuarios());
								
								lstSumaVolumenPoritem.add(sumaVolumenPorItem);
								cntPresentacion++;
							}

						}
						row = sheet.createRow(rowNum++);
					}

				}
				cnt = 0;

			}
			
			//insertamos rowsss
			int createNewRowAt = 0; //Add the new row between row 9 and 10
			Integer rowsResumen = lstSumaVolumenPoritem.size() + 5;
			sheet.shiftRows(createNewRowAt, sheet.getLastRowNum(), rowsResumen, true, false);
			
			createHeaderResumen(sheet,style,true);
			
			Row rowRes;
			Cell cellRes;
			
			CellStyle styleFormatNumero3;
			DataFormat format = workbook.createDataFormat();
			
			Font font = workbook.createFont();
			font.setFontHeight((short) ((short) 9 * 20));
			font.setFontName("Times New Roman");
			  
			styleFormatNumero3 = workbook.createCellStyle();
			styleFormatNumero3.setDataFormat(format.getFormat("0.000")); // custom number format
			styleFormatNumero3.setFont(font);
			
			Integer nRow = 3;
			lstSumaVolumenPoritem.sort((SumaVolumenPorItem s1,SumaVolumenPorItem s2)-> s1.getProducto().compareTo(s2.getProducto()));
			for(SumaVolumenPorItem suma:lstSumaVolumenPoritem) {
				rowRes = sheet.createRow(nRow++);
				cellRes = rowRes.createCell(4);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getProducto());
				
				cellRes = rowRes.createCell(5);
				cellRes.setCellStyle(styleFormatNumero3);
				cellRes.setCellValue(suma.getVolumen().doubleValue());
				
				cellRes = rowRes.createCell(6);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getUsuarios().doubleValue() );
				
				cellRes = rowRes.createCell(7);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getP1());
				
				cellRes = rowRes.createCell(8);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getP2());
				
				cellRes = rowRes.createCell(9);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getP3());
				
				cellRes = rowRes.createCell(10);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getVp1());
				
				cellRes = rowRes.createCell(11);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getVp2());
				
				cellRes = rowRes.createCell(12);
				cellRes.setCellStyle(style);
				cellRes.setCellValue(suma.getVp3());
				
			}
			
			//Actualizamos el acumulado total
			ActualizaSumaVolumenTotal(lstSumaVolumenPoritem);
			lstSumaVolumenPoritem = new ArrayList<>();
			

		}
		//creamos la hora resumen
		CreateSheetResumenGeneral(workbook,style, numeroEntrega, anno);
//		FileOutputStream out = new FileOutputStream("C:\\Documents and Settings\\INGENIERIA2\\Mis documentos\\Informe.xls");
//		workbook.write(out);
//		out.close();
		System.out.println("Fin generacion de reporte .........................");

	}
	
	public void CreateSheetResumenGeneral(Workbook workbook, CellStyle style, Integer numeroEntrega,Integer anno) {
		Sheet sheet = this.createsheet(workbook, "RESUMEN");
		
		workbook.setSheetOrder("RESUMEN", 0);
		
		List<String> sheetNames = new ArrayList<String>();
		for (int i=1; i<workbook.getNumberOfSheets(); i++) {
		    sheetNames.add( workbook.getSheetName(i) );
		}
		
		
		this.createHeaderResumen(sheet, style,false);
		
		sheet.setColumnWidth(4, (int) (sheet.getColumnWidth(5) * 2.5));
		sheet.setColumnWidth(5, (int) (sheet.getColumnWidth(5) * 1.2));
		sheet.setColumnWidth(6, (int) (sheet.getColumnWidth(6) * 1.2));
		sheet.setColumnWidth(7, (int) (sheet.getColumnWidth(7) * 1.2));
		sheet.setColumnWidth(8, (int) (sheet.getColumnWidth(8) * 1.2));
		sheet.setColumnWidth(9, (int) (sheet.getColumnWidth(9) * 1.2));
		sheet.setColumnWidth(10, (int) (sheet.getColumnWidth(10) * 1.2));
		sheet.setColumnWidth(11, (int) (sheet.getColumnWidth(11) * 1.2));
		sheet.setColumnWidth(12, (int) (sheet.getColumnWidth(12) * 1.2));
		sheet.setColumnWidth(13, (int) (sheet.getColumnWidth(13) * 1.2));
		sheet.setColumnWidth(14, (int) (sheet.getColumnWidth(14) * 1.2));
		
		CellStyle styleFormatNumero3;
		DataFormat format = workbook.createDataFormat();
		
		Font font = workbook.createFont();
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");
		  
		styleFormatNumero3 = workbook.createCellStyle();
		styleFormatNumero3.setDataFormat(format.getFormat("0.000")); // custom number format
		styleFormatNumero3.setFont(font);
		
		Row rowRes;
		Cell cellRes;
		Integer nRow = 3;
		
		
		lstSumaVolumenPoritemTotal.sort((SumaVolumenPorItem s1,SumaVolumenPorItem s2)-> s1.getProducto().compareTo(s2.getProducto()));
		for(SumaVolumenPorItem suma:lstSumaVolumenPoritemTotal) {
			rowRes = sheet.createRow(nRow++);
			cellRes = rowRes.createCell(4);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getProducto());
			
			cellRes = rowRes.createCell(5);
			cellRes.setCellStyle(styleFormatNumero3);
			cellRes.setCellValue(suma.getVolumen().doubleValue());
			
			cellRes = rowRes.createCell(6);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getUsuarios().doubleValue() );
			
			cellRes = rowRes.createCell(7);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getP1());
			
			cellRes = rowRes.createCell(8);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getP2());
			
			cellRes = rowRes.createCell(9);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getP3());
			
			cellRes = rowRes.createCell(10);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getVp1());
			
			cellRes = rowRes.createCell(11);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getVp2());
			
			cellRes = rowRes.createCell(12);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getVp3());
			
		}
		
		lstSumaVolumenPoritemTotal = new ArrayList<>();
		
		nRow++;
		rowRes = sheet.createRow(nRow++);
		cellRes = rowRes.createCell(4);
		cellRes.setCellStyle(style);
		
		String line = "";
		for(String str:sheetNames) {
			line = line + str+"-";
		}
		line = line.substring(0, line.length()-1);
		cellRes.setCellValue("ITEM(S) : " + line );
		
		nRow++;
		rowRes = sheet.createRow(nRow++);
		cellRes = rowRes.createCell(4);
		cellRes.setCellStyle(style);
		cellRes.setCellValue("ENTREGA Nro : " + numeroEntrega + "  Año :" + anno );
	
		
	}
	
	public void ActualizaSumaVolumenTotal(List<SumaVolumenPorItem> lstSumaVolumenPoritem) {
		Boolean existe = false;
		for(SumaVolumenPorItem sumaItem:lstSumaVolumenPoritem){
			
			
			for(SumaVolumenPorItem sumaTotal:lstSumaVolumenPoritemTotal) {
				System.out.println("Nombre de producto ExcelVolumenesPorItem fila 484 :" +sumaItem.getProducto()); 
				if(sumaTotal.getProducto().equals(sumaItem.getProducto())) {
					sumaTotal.setVolumen( new BigDecimal(sumaTotal.getVolumen().floatValue() + sumaItem.getVolumen().floatValue() ));
					sumaTotal.setUsuarios(sumaTotal.getUsuarios() + sumaItem.getUsuarios());
					sumaTotal.setVp1( sumaTotal.getVp1() + sumaItem.getVp1());
					sumaTotal.setVp2( sumaTotal.getVp2() + sumaItem.getVp2());
					sumaTotal.setVp3( sumaTotal.getVp3() + sumaItem.getVp3());
					existe=true;
					break;
				}
			}
			if(!existe) {
				
				SumaVolumenPorItem sumaVolumenPorItem = new SumaVolumenPorItem();
				
				sumaVolumenPorItem.setCodigoModular(sumaItem.getCodigoModular());
				sumaVolumenPorItem.setProducto(sumaItem.getProducto());
				sumaVolumenPorItem.setVolumen(sumaItem.getVolumen());
				sumaVolumenPorItem.setUsuarios(sumaItem.getUsuarios());
				
				sumaVolumenPorItem.setP1(sumaItem.getP1());
				sumaVolumenPorItem.setP2(sumaItem.getP2());
				sumaVolumenPorItem.setP3(sumaItem.getP3());
				
				sumaVolumenPorItem.setVp1(sumaItem.getVp1());
				sumaVolumenPorItem.setVp2(sumaItem.getVp2());
				sumaVolumenPorItem.setVp3(sumaItem.getVp3());
				
				lstSumaVolumenPoritemTotal.add(sumaVolumenPorItem);
				existe=false;
			}
			existe=false;
			
		}
		
	}
	
	public void CrearResumenAcumulado(Sheet sheet,CellStyle style) {
		
		Row header = sheet.createRow(2);
		Cell cellHeader = null;

		Integer colHeader = 15;
	
	

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Sub Grupo");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("VOLUMEN");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Usuarios");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("presentacion 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("presentacion 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("presentacion 3");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Unidades 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Unidades 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Unidades 3");
		
	}
	
	public CellStyle getStyle(Workbook workbook) {
		// Create a new font and alter it.
		Font font = workbook.createFont();
		// font.setFontHeightInPoints((short) 8);
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
		
	}

	public Sheet createsheet(Workbook workbook, String titulo) {
		Sheet sheet = workbook.createSheet(titulo);

	

		
		return sheet;

	}
	
	
	public void createHeader(Workbook workbook,Sheet sheet) {
		
		
		Font font = workbook.createFont();
		
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		CellStyle style2 = workbook.createCellStyle();
		style2.setFont(font);
		
		
		
		sheet.setColumnWidth(0, (int) (sheet.getColumnWidth(0) * 1.5));
		sheet.setColumnWidth(1, (int) (sheet.getColumnWidth(1) * 1.5));
		sheet.setColumnWidth(2, (int) (sheet.getColumnWidth(2) * 2));
		sheet.setColumnWidth(3, (int) (sheet.getColumnWidth(3) * 1.2));
		sheet.setColumnWidth(4, (int) (sheet.getColumnWidth(4) * 2.5));
		sheet.setColumnWidth(5, (int) (sheet.getColumnWidth(5) * 1.5));
		sheet.setColumnWidth(6, (int) (sheet.getColumnWidth(6) * 1.5));
		sheet.setColumnWidth(7, (int) (sheet.getColumnWidth(7) * 1.5));
		sheet.setColumnWidth(8, (int) (sheet.getColumnWidth(8) * 1.5));
		sheet.setColumnWidth(9, (int) (sheet.getColumnWidth(9) * 1.5));
		sheet.setColumnWidth(10, (int) (sheet.getColumnWidth(10) * 1.5));
		sheet.setColumnWidth(10, (int) (sheet.getColumnWidth(11) * 1.5));
		sheet.setColumnWidth(10, (int) (sheet.getColumnWidth(12) * 1.5));

		Row header = sheet.createRow(2);
		Cell cellHeader = null;

		Integer colHeader = 0;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Codigo Modular");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Region Alimentario");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Desayuno/Desayuno + Almuerzo");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Nivel");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Sub Grupo");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("VOLUMEN");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Usuarios");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("present. 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("present. 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("present. 3");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Unds 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Unds 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style2);
		header.createCell(colHeader).setCellValue("Unds 3");
		
	}
	
	public void createHeaderResumen(Sheet sheet,CellStyle style, Boolean addTitulo) {
		
		String titulo = addTitulo?"RESUMEN ACUMULADO ITEM " + sheet.getSheetName().toUpperCase():"TOTAL ACUMULADO";
		Row header = sheet.createRow(1);
		Cell cellHeader = null;

		Integer colHeader = 4;
	
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue(titulo);
		
		header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Sub Grupo");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("VOLUMEN");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Usuarios");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("present. 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("present. 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("present. 3");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Unds 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Unds 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Unds 3");
		
	}

}
