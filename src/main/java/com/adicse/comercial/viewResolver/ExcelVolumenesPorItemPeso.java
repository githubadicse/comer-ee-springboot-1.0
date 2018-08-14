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
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.adicse.comercial.clases.SumaVolumenPorItem;
import com.adicse.comercial.model.Matriz;

public class ExcelVolumenesPorItemPeso extends AbstractXlsxView {

	public static List<SumaVolumenPorItem> lstSumaVolumenPoritemTotal = new ArrayList<SumaVolumenPorItem>();

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		List<Matriz> lst = (List<Matriz>) model.get("data");
		Integer anno = (Integer) model.get("anno");
		Integer numeroEntrega = (Integer) model.get("numeroEntrega");

		response.setHeader("Content-Disposition", "attachment: filename=\"d:\\Items.xlsx");

		Row row;
		Cell cell;

		Row rowRes;
		Cell cellRes;

		CellStyle style = getStyle(workbook);
		Sheet sheet = createsheet(workbook, "IIE");
		Sheet sheetResumen = createsheet(workbook, "Pesos Acumulados");
		this.createHeaderResumen(sheet, style, false);
		this.createHeaderTotalPeso(sheetResumen, style, false);

		CellStyle styleFormatNumero3;
		DataFormat format = workbook.createDataFormat();

		Font font = workbook.createFont();
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		styleFormatNumero3 = workbook.createCellStyle();
		styleFormatNumero3.setDataFormat(format.getFormat("0.000")); // custom number format
		styleFormatNumero3.setFont(font);

		Integer nRow = 5;
		Integer nRowRes = 5;
		Integer nCell = 1;
		String sCodModular = "";
		String sCodModularOld = "";
		Integer nCont = 1;
		Double pesoAcum = 0.0;
		Matriz matrizOld = new Matriz();
		for (Matriz matrizRow : lst) {

			sCodModular = matrizRow.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getCodigoModular();

			if (sCodModular.equals(sCodModularOld)) {
				pesoAcum = pesoAcum + matrizRow.getPeso().doubleValue();
			} else {

				if (nCont != 1) {
					nCell = 1;
					row = sheetResumen.createRow(nRowRes);
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getUbigeo().getNombreProvincia());

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getUbigeo().getNombreDistrito());

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCentroPoblado());
					
					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega().getDscitem() );

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
							.getCodigoModular());

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
							.getNombreInstitucionEducativa());

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
							.getDireccionInstitucionEducativa());

			
					
					nCell++;
					cell = row.createCell(nCell);
					cell.setCellValue(pesoAcum.doubleValue());
					
					nRowRes++;
				}

				pesoAcum = matrizRow.getPeso().doubleValue();
				nCont++;
			}

			nCell = 1;
			row = sheet.createRow(nRow);
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getRequerimientoVolumen001().getUbigeo().getNombreProvincia());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getRequerimientoVolumen001().getUbigeo().getNombreDistrito());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getRequerimientoVolumen001().getCentroPoblado());
			
			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega().getDscitem() );

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(
					matrizRow.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getNombreInstitucionEducativa());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getDireccionInstitucionEducativa());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getPresentacion1());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getPresentacion2() == null ? 0 : matrizRow.getPresentacion2());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getPresentacion3() == null ? 0 : matrizRow.getPresentacion3());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getFactor1() == null ? 0 : matrizRow.getFactor1().doubleValue());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getFactor2() == null ? 0 : matrizRow.getFactor2().doubleValue());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getFactor3() == null ? 0 : matrizRow.getFactor3().doubleValue());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getUnds1() == null ? 0 : matrizRow.getUnds1());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getUnds2() == null ? 0 : matrizRow.getUnds2());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getUnds3() == null ? 0 : matrizRow.getUnds3());

			nCell++;
			cell = row.createCell(nCell);
			cell.setCellValue(matrizRow.getPeso().doubleValue());

			nRow++;
			sCodModularOld = sCodModular;
			BeanUtils.copyProperties(matrizRow, matrizOld);

		}
		
		nCell = 1;
		row = sheetResumen.createRow(nRowRes);
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getUbigeo().getNombreProvincia());

		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getUbigeo().getNombreDistrito());

		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCentroPoblado());
		
		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega().getDscitem() );

		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
				.getCodigoModular());

		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
				.getNombreInstitucionEducativa());

		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(matrizOld.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
				.getDireccionInstitucionEducativa());


		
		nCell++;
		cell = row.createCell(nCell);
		cell.setCellValue(pesoAcum.doubleValue());
		
		nRowRes++;
		// creamos la hora resumen

		System.out.println("Fin generacion de reporte .........................");

	}

	public void CreateSheetResumenGeneral(Workbook workbook, CellStyle style) {
		Sheet sheet = this.createsheet(workbook, "RESUMEN");

		workbook.setSheetOrder("RESUMEN", 0);

		List<String> sheetNames = new ArrayList<String>();
		for (int i = 1; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}

		this.createHeaderResumen(sheet, style, false);

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
		lstSumaVolumenPoritemTotal
				.sort((SumaVolumenPorItem s1, SumaVolumenPorItem s2) -> s1.getProducto().compareTo(s2.getProducto()));
		for (SumaVolumenPorItem suma : lstSumaVolumenPoritemTotal) {
			rowRes = sheet.createRow(nRow++);
			cellRes = rowRes.createCell(4);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getProducto());

			cellRes = rowRes.createCell(5);
			cellRes.setCellStyle(styleFormatNumero3);
			cellRes.setCellValue(suma.getVolumen().doubleValue());

			cellRes = rowRes.createCell(6);
			cellRes.setCellStyle(style);
			cellRes.setCellValue(suma.getUsuarios().doubleValue());

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
		for (String str : sheetNames) {
			line = line + str + "-";
		}
		line = line.substring(0, line.length() - 1);
		cellRes.setCellValue("ITEM(S) : " + line);

	}

	public void ActualizaSumaVolumenTotal(List<SumaVolumenPorItem> lstSumaVolumenPoritem) {
		Boolean existe = false;
		for (SumaVolumenPorItem sumaItem : lstSumaVolumenPoritem) {

			for (SumaVolumenPorItem sumaTotal : lstSumaVolumenPoritemTotal) {

				if (sumaTotal.getProducto().equals(sumaItem.getProducto())) {
					sumaTotal.setVolumen(
							new BigDecimal(sumaTotal.getVolumen().floatValue() + sumaItem.getVolumen().floatValue()));
					sumaTotal.setUsuarios(sumaTotal.getUsuarios() + sumaItem.getUsuarios());
					sumaTotal.setVp1(sumaTotal.getVp1() + sumaItem.getVp1());
					sumaTotal.setVp2(sumaTotal.getVp2() + sumaItem.getVp2());
					sumaTotal.setVp3(sumaTotal.getVp3() + sumaItem.getVp3());
					existe = true;
					break;
				}
			}
			if (!existe) {

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
				existe = false;
			}

		}

	}

	public void CrearResumenAcumulado(Sheet sheet, CellStyle style) {

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

	public void createHeader(Workbook workbook, Sheet sheet) {

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

	public void createHeaderResumen(Sheet sheet, CellStyle style, Boolean addTitulo) {

		String titulo = addTitulo ? "RESUMEN ACUMULADO ITEM " + sheet.getSheetName().toUpperCase() : "TOTAL ACUMULADO";
		Row header = sheet.createRow(4);
		Cell cellHeader = null;

		Integer colHeader = 1;

		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue(titulo);

		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Provincia");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Distrito");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Centro Poblado");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Item");

		
		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Cod.Modular");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("IE");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Direccion");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Producto");

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
		header.createCell(colHeader).setCellValue("Factor. 1");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Factor. 2");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Factor. 3");

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

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Peso");

	}
	
	
	public void createHeaderTotalPeso(Sheet sheet, CellStyle style, Boolean addTitulo) {

		String titulo = addTitulo ? "RESUMEN ACUMULADO ITEM " + sheet.getSheetName().toUpperCase() : "TOTAL ACUMULADO";
		Row header = sheet.createRow(4);
		Cell cellHeader = null;

		Integer colHeader = 1;

		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue(titulo);

		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Provincia");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Distrito");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Centro Poblado");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Item");
		
		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Cod.Modular");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("IE");

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Direccion");

	

		
		

	

		colHeader++;
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Peso");

	}


}
