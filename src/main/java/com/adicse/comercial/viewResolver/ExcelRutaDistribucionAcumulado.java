package com.adicse.comercial.viewResolver;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.adicse.comercial.clases.AcumuladoRutaDistribucionClass;
import com.adicse.comercial.clases.AcumuladoRutaDistribucionPeso;
import com.adicse.comercial.clases.SumaVolumenPorItem;
import com.adicse.comercial.model.RutaDistribucion;
//import com.adicse.comercial.model.RutaDistribucionDetalle;

public class ExcelRutaDistribucionAcumulado extends AbstractXlsxView {

	public static List<SumaVolumenPorItem> lstSumaVolumenPoritemTotal = new ArrayList<SumaVolumenPorItem>();
	public static List<AcumuladoRutaDistribucionPeso> lstPeso = new ArrayList<>();

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		List<AcumuladoRutaDistribucionClass> lst = (List<AcumuladoRutaDistribucionClass>) model.get("data");
		lstPeso = (List<AcumuladoRutaDistribucionPeso>) model.get("dataPeso");
		
		RutaDistribucion rutaDistribucion = (RutaDistribucion) model.get("data2");
		
		
		Integer anno = rutaDistribucion.getAnno();
		Integer numeroEntrega = rutaDistribucion.getNumeroEntrega();
		String responsable = rutaDistribucion.getEmpleadoDistribuidor().getNombres();

		response.setHeader("Content-Disposition", "attachment: filename=\"d:\\Items.xlsx");

		Row row;
		Cell cell;

		Row rowRes;
		Cell cellRes;

		CellStyle style = null ;
		style = workbook.createCellStyle();
		Sheet sheet =  workbook.createSheet("Relacion de Productos");
				
		CellStyle styleFormatNumero3;
		DataFormat format = workbook.createDataFormat();

		Font font = workbook.createFont();
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		styleFormatNumero3 = workbook.createCellStyle();
		styleFormatNumero3.setDataFormat(format.getFormat("0.000")); // custom number format
		styleFormatNumero3.setFont(font);

		style.setFont(font);
		
		//this.createHeaderResumen(sheet, style, false);
		this.createHeaderTotalPeso(sheet, style, false);
		Integer nRow = 5;
		Integer nRowRes = 5;
		Integer nCell = 1;
		
		this.setWithColumns(workbook, style, sheet);
		
		nCell = 1;
		row = sheet.createRow(2);
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue("PRODUCTOS ACUMULADO PARA DISTRIBUCION");
		
		nCell = 1;
		row = sheet.createRow(4);
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue("Numero entrega :");
		
		nCell++;
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue(numeroEntrega.toString() + " / " + anno.toString());
	
		nCell = 1;
		row = sheet.createRow(5);
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue("Responsable :");
		
		nCell++;
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue(responsable);
		
		nRow = 8;
		
		for (AcumuladoRutaDistribucionClass acumuladoRutaDistribucionClass : lst) {

	
					nCell = 1;
					row = sheet.createRow(nRow);
					cell = row.createCell(nCell);
					cell.setCellStyle(style);
					cell.setCellValue(acumuladoRutaDistribucionClass.getGrupo() );

					nCell++;
					cell = row.createCell(nCell);	
					cell.setCellStyle(style);
					cell.setCellValue(acumuladoRutaDistribucionClass.getAlimentoSeleccionado());

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellStyle(styleFormatNumero3);
					cell.setCellValue(acumuladoRutaDistribucionClass.getCantidad()==null?0:acumuladoRutaDistribucionClass.getCantidad());
					
					nCell++;
					cell = row.createCell(nCell);
					cell.setCellStyle(style);
					cell.setCellStyle(styleFormatNumero3);
					cell.setCellValue(acumuladoRutaDistribucionClass.getFactor() );

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellStyle(style);
					cell.setCellValue(acumuladoRutaDistribucionClass.getLote());

					nCell++;
					cell = row.createCell(nCell);
					cell.setCellStyle(style);
					cell.setCellValue(acumuladoRutaDistribucionClass.getCantidadLote() );
					
					nCell++;
					cell = row.createCell(nCell);
					cell.setCellStyle(style);
					cell.setCellValue(acumuladoRutaDistribucionClass.getDetalle());
				
					nRow++;


		}
		
		
		//cramos la hoja detalle
		this.HojaDetalle(workbook, rutaDistribucion);
		
		FileOutputStream out = new FileOutputStream("d:\\archivos\\Informe.xlsx");
		workbook.write(out);
		out.close();
		// creamos la hora resumen

		System.out.println("Fin generacion de reporte .........................");

	}

	public void setWithColumns(Workbook workbook, CellStyle style, Sheet sheet) {
	
		sheet.setColumnWidth(1, (int) (sheet.getColumnWidth(1) * 2.7));
		sheet.setColumnWidth(2, (int) (sheet.getColumnWidth(2) * 2.7));
		sheet.setColumnWidth(3, (int) (sheet.getColumnWidth(3) * 1.5));
		sheet.setColumnWidth(4, (int) (sheet.getColumnWidth(4) * 1.5));
		sheet.setColumnWidth(5, (int) (sheet.getColumnWidth(5) * 1.7));
		sheet.setColumnWidth(6, (int) (sheet.getColumnWidth(6) * 1.7));
		

	
	}


	@SuppressWarnings("unused")
	public void HojaDetalle(Workbook workbook,	RutaDistribucion rutaDistribucion  ){
		Integer anno = rutaDistribucion.getAnno();
		Integer numeroEntrega = rutaDistribucion.getNumeroEntrega();
		String responsable = rutaDistribucion.getEmpleadoDistribuidor().getNombres();
		
		Row row;
		Cell cell;

		Row rowRes;
		Cell cellRes;

		CellStyle style  ;
		CellStyle styleFormatNumero3;
		style = workbook.createCellStyle();
		
		DataFormat format = workbook.createDataFormat();

		Font font = workbook.createFont();
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		styleFormatNumero3 = workbook.createCellStyle();
		styleFormatNumero3.setDataFormat(format.getFormat("0.000")); // custom number format
		styleFormatNumero3.setFont(font);

		style.setFont(font);
		
		Sheet sheet =  workbook.createSheet("Relacion de CE");
		
		int nCell = 1;
		row = sheet.createRow(2);
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue("PRODUCTOS ACUMULADO PARA DISTRIBUCION");
		
		nCell = 1;
		row = sheet.createRow(4);
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue("Numero entrega :");
		
		nCell++;
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue(numeroEntrega.toString() + " / " + anno.toString());
	
		nCell = 1;
		row = sheet.createRow(5);
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue("Responsable :");
		
		nCell++;
		cell = row.createCell(nCell);
		cell.setCellStyle(style);
		cell.setCellValue(responsable);
		
		int nRow = 8;
		
		this.crearTituloHojaDetalle(workbook, sheet);
		float totalPeso = 0;
		
		for(AcumuladoRutaDistribucionPeso acumuladoRutaDistribucionPeso : lstPeso ) {
			
				
				nCell = 1;
				row = sheet.createRow(nRow);
				cell = row.createCell(nCell);
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getItemDsc());

				nCell++;
				cell = row.createCell(nCell);	
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getCentroPoblado() );

				nCell++;
				cell = row.createCell(nCell);
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getCodigoModular());
				
				nCell++;
				cell = row.createCell(nCell);
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getNombreId());

				nCell++;
				cell = row.createCell(nCell);
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getDireccion());

				nCell++;
				cell = row.createCell(nCell);
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getNivel() );

				nCell++;
				cell = row.createCell(nCell);
				cell.setCellStyle(style);
				cell.setCellValue(acumuladoRutaDistribucionPeso.getPeso() );
				
				totalPeso = (float) (totalPeso + acumuladoRutaDistribucionPeso.getPeso()) ;
			
				
				nRow++;
	
			
		}
		
		nCell++;
		row = sheet.createRow(nRow);
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue(totalPeso );
		
		
	}
	
	public void crearTituloHojaDetalle(Workbook workbook,Sheet sheet) {
		CellStyle style2  ;
		style2 = workbook.createCellStyle();
		
    
			style2.setFillBackgroundColor(IndexedColors.CORAL.index);
			style2.setFillForegroundColor(IndexedColors.RED.index);
		 //style2.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		    //style2.setFillPattern(CellStyle.);
		    
		Font font2 = workbook.createFont();
		font2.setFontHeight((short) ((short) 9 * 20));
		font2.setFontName("Times New Roman");
		font2.setColor(IndexedColors.BLUE.getIndex());
		
		style2.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		
		style2.setFont(font2);
		
		Row header = sheet.createRow(7);
		Cell cellHeader = null;

		Integer colHeader = 1;

		sheet.setColumnWidth(1, (int) (sheet.getColumnWidth(1) * 2.7));
		sheet.setColumnWidth(2, (int) (sheet.getColumnWidth(2) * 2.7));
		sheet.setColumnWidth(3, (int) (sheet.getColumnWidth(3) * 1.5));
		sheet.setColumnWidth(4, (int) (sheet.getColumnWidth(4) * 1.5));
		sheet.setColumnWidth(5, (int) (sheet.getColumnWidth(5) * 2.7));
		sheet.setColumnWidth(6, (int) (sheet.getColumnWidth(6) * 2.7));
		sheet.setColumnWidth(3, (int) (sheet.getColumnWidth(3) * 1.5));
		
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Item");
		cellHeader.setCellStyle(style2);

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Centro Poblado");
		cellHeader.setCellStyle(style2);

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Cod.Modular");
		cellHeader.setCellStyle(style2);

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Nombre IE");
		cellHeader.setCellStyle(style2);
		
		
		colHeader++;
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Direccion");
		cellHeader.setCellStyle(style2);

		colHeader++;
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Nivel");
		cellHeader.setCellStyle(style2);
		
		colHeader++;
		cellHeader = header.createCell(colHeader);
		header.createCell(colHeader).setCellValue("Peso Total");
		cellHeader.setCellStyle(style2);

		
	}
	
	public void createHeaderTotalPeso(Sheet sheet, CellStyle style, Boolean addTitulo) {

		String titulo = addTitulo ? "RESUMEN ACUMULADO ITEM " + sheet.getSheetName().toUpperCase() : "TOTAL ACUMULADO";
		Row header = sheet.createRow(7);
		Cell cellHeader = null;

		Integer colHeader = 1;

		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue(titulo);
		cellHeader.setCellStyle(style);
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue("Grupo");
		cellHeader.setCellStyle(style);
		
		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue("Alimento Seleccionado");
		cellHeader.setCellStyle(style);
		
		
		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		cellHeader.setCellStyle(style);
		header.createCell(colHeader).setCellValue("Cantidad");

		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue("Presentacion");
		cellHeader.setCellStyle(style);
		
		colHeader++;
		// header = sheet.createRow(2);
		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue("Lote");
		cellHeader.setCellStyle(style);
		
		
		colHeader++;
		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue("Cant x Lote");
		cellHeader.setCellStyle(style);
		
		
		colHeader++;
		cellHeader = header.createCell(colHeader);
		
		header.createCell(colHeader).setCellValue("Detalle");
		cellHeader.setCellStyle(style);
	

	}


}
