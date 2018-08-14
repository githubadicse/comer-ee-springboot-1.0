package com.adicse.comercial.viewResolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.adicse.comercial.clases.GuiaRemision001Class;
import com.adicse.comercial.model.CatalogoLote;
import com.adicse.comercial.model.CatalogoMarca;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.adicse.comercial.model.ItemEntrega;

public class ExcelPlanRastreabilidad extends AbstractXlsxView {


	public static Integer nRow;
	public static CellStyle styleTimeNewRoman,styleFormatNumero3;
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		List<GuiaRemision001Class> lstGuiaRemision = (List<GuiaRemision001Class>) model.get("data");

		@SuppressWarnings("unchecked")
		List<ItemEntrega> lstItemEntrega = (List<ItemEntrega>) model.get("dataItemEntrega");

		Integer numeroEntrega = (Integer) model.get("numeroEntrega");

		Sheet sheet = null;
		@SuppressWarnings("unused")
		Row rowRes;
		@SuppressWarnings("unused")
		Cell cellRes;

		
		DataFormat format = workbook.createDataFormat();

		Font font = workbook.createFont();
		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		styleFormatNumero3 = workbook.createCellStyle();
		styleFormatNumero3.setDataFormat(format.getFormat("0.000")); // custom number format
		styleFormatNumero3.setFont(font);

		styleTimeNewRoman = workbook.createCellStyle();
		styleTimeNewRoman.setFont(font);
		

		nRow = 3;
		Integer cnt = 1;
		for (ItemEntrega itemEntrega : lstItemEntrega) {

			nRow = 3;
			// creamos la hoja con el nombre del item
			sheet = workbook.createSheet(itemEntrega.getDscitem());
			//definimos los anchos de las columnas para la hoja
			this.setWithColumns(sheet);
			// filtramos las guias que son para este item y numero de entrega
			List<GuiaRemision001> lstGuiasPorItem = this.guiasByItemAndNumeroEntrega(itemEntrega, numeroEntrega,
					lstGuiaRemision);
			
			Integer sizeLst = lstGuiasPorItem.size();
			cnt = 1;
			
			for(GuiaRemision001 guiaRemision001 : lstGuiasPorItem) {
				System.out.println("Item : " + itemEntrega.getDscitem() + "  Guias Total : " + sizeLst + " Procesando : " + cnt );
				cnt++;

				// escribimos el titulo
				this.titulo(workbook, sheet);
				nRow++;

				//definimos el tiulo para la tabla
				this.header(workbook, sheet);		
				nRow = nRow + 4;
				List<GuiaRemision002> lstGuiaRemision002 = guiaRemision001.getGuiaRemision002s();
				
				lstGuiaRemision002.sort((p1,p2)-> p1.getProductoGrupo().compareTo(p2.getProductoGrupo()) );
				
				for(GuiaRemision002 guiaRemision002 : lstGuiaRemision002 ) {
					this.fillDataGuia(guiaRemision002, sheet,  itemEntrega);
				}
				nRow = nRow + 2;

			}
	

		}
		System.out.println("FIN DE PROCESO ....");

	}
	
	@SuppressWarnings("unused")
	public void fillDataGuia(GuiaRemision002 guiaRemision002, Sheet sheet,  ItemEntrega itemEntrega) {
		
		
		Row row = sheet.createRow(nRow);
		Cell cellFecha = row.createCell(1);
		Cell cellItem = row.createCell(2);
		Cell cellCantidad = row.createCell(3);
		Cell cellProducto = row.createCell(4);
		Cell cellLote = row.createCell(5);
		Cell cellMarca = row.createCell(6);
		Cell cellFechaVencimiento = row.createCell(7);
		
		//Cell cellTransporte = row.createCell(8);
		Cell cellNroPlaca = row.createCell(8);
		Cell cellNroBrevete = row.createCell(9);
		Cell cellNroGuia = row.createCell(10);
		Cell cellNombreIe = row.createCell(11);
		Cell cellCodigoModular = row.createCell(12);
		Cell cellNroUsuario = row.createCell(13);
		Cell cellDireccionIe = row.createCell(14);
		
		String numeroLote = guiaRemision002.getNumeroLote();
		Date fechaVencimiento = new Date();
		//ProductoPorNumeroEntrega productoPorNumeroEntrega = guiaRemision002.getVolumenConvertidoEnvace().getRequerimientoVolumen002Producto().getProductoPorNumeroEntrega();
		CatalogoMarca catalogoMarca = guiaRemision002.getVolumenConvertidoEnvace().getCatalogoMarca();
		for(CatalogoLote catalogoLote:catalogoMarca.getCatalogoLotes()) {
			String nLote = catalogoLote.getNumeroLote();
			if(numeroLote.equals(nLote)) {
				fechaVencimiento = catalogoLote.getFechaVencimiento();
				break;
			}
		}
		
		
		//----------------------------producto presentacion ----------------------------------//
		String sEnvase = guiaRemision002.getEnvase();
		
		Double factorVolumenPresentacion = guiaRemision002.getFactor().doubleValue();
		
		String sUnidadMedidaAbr = guiaRemision002.getUnidadMedidaTrabajo();
	
		String sPresentacion = WordUtils.capitalize(sEnvase.toLowerCase())+" X "+ factorVolumenPresentacion.toString() + " "+sUnidadMedidaAbr;
		
		cellFecha.setCellValue( dateFormat.format( guiaRemision002.getGuiaRemision001().getFechaEmision() ) );
		cellItem.setCellValue( itemEntrega.getDscitem() );
		cellCantidad.setCellValue(guiaRemision002.getCantidad());
		
		cellProducto.setCellValue(guiaRemision002.getProductoSeleccionado() + " " + sPresentacion );
		cellLote.setCellValue(guiaRemision002.getNumeroLote());
		cellMarca.setCellValue(guiaRemision002.getMarca());
		
		cellFechaVencimiento.setCellValue( dateFormat.format(fechaVencimiento));
		
		cellNroPlaca.setCellValue(guiaRemision002.getGuiaRemision001().getVehiculo().getNumeroPlaca());
		cellNroBrevete.setCellValue(guiaRemision002.getGuiaRemision001().getChofer().getNumeroBrevete());
		
		cellNroGuia.setCellValue(guiaRemision002.getGuiaRemision001().getNumeroFisico());
		cellNombreIe.setCellValue(guiaRemision002.getGuiaRemision001().getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getNombreInstitucionEducativa());
		cellCodigoModular.setCellValue(guiaRemision002.getGuiaRemision001().getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular());
		cellNroUsuario.setCellValue(guiaRemision002.getGuiaRemision001().getRequerimientoVolumen001().getNumeroUsuarios());
		cellDireccionIe.setCellValue(guiaRemision002.getGuiaRemision001().getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getDireccionInstitucionEducativa());
		
		
		
		cellFecha.setCellStyle(styleTimeNewRoman);
		cellProducto.setCellStyle(styleTimeNewRoman);
		cellMarca.setCellStyle(styleTimeNewRoman);
		cellLote.setCellStyle(styleTimeNewRoman);
		cellFechaVencimiento.setCellStyle(styleTimeNewRoman);
		cellCantidad.setCellStyle(styleFormatNumero3);
		cellItem.setCellStyle(styleTimeNewRoman);
		cellNroPlaca.setCellStyle(styleTimeNewRoman);
		cellNroBrevete.setCellStyle(styleTimeNewRoman);
		cellNroGuia.setCellStyle(styleTimeNewRoman);
		cellCodigoModular.setCellStyle(styleTimeNewRoman);
		cellNombreIe.setCellStyle(styleTimeNewRoman);
		cellNroUsuario.setCellStyle(styleTimeNewRoman);
		cellDireccionIe.setCellStyle(styleTimeNewRoman);
		
		nRow++;
	}
	
	public Date getFechaVencimientoLote() {
		return null;
	}

	public List<GuiaRemision001> guiasByItemAndNumeroEntrega(ItemEntrega itemEntrega, Integer numeroEntrega,
			List<GuiaRemision001Class> lstGuiaRemision) {
		List<GuiaRemision001> lstAux = new ArrayList<>();
		Integer len = lstGuiaRemision.size();
		for (int i = 0; i < len; i++) {

			GuiaRemision001 guiaRemision001Aux = lstGuiaRemision.get(i) .getGuiaRemision001();
			ItemEntrega itemEntregaAux = lstGuiaRemision.get(i).getItemEntrega();

			if (itemEntregaAux.equals(itemEntrega) && guiaRemision001Aux.getRequerimientoVolumen001().getEntregaPorItem()
					.getNumeroEntrega().getNumeroEntregaValor().equals(numeroEntrega)) {
				lstAux.add(guiaRemision001Aux);
			}
			
		}
	

		return lstAux;
	}

	public void titulo(Workbook workbook, Sheet sheet) {
		Font font = workbook.createFont();

		font.setFontHeight((short) ((short) 14 * 20));
		font.setFontName("Times New Roman");

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);

		Row rowHeader = sheet.createRow(nRow);

		Cell cellHeader = rowHeader.createCell(1);
		cellHeader.setCellValue("INVERSIONES");
		cellHeader.setCellStyle(style);

		cellHeader = rowHeader.createCell(3);
		cellHeader.setCellValue("PLAN DE RASTREABILIDAD Y PRODUCTOS NO CONFORMES");
		cellHeader.setCellStyle(style);

		cellHeader = rowHeader.createCell(9);
		cellHeader.setCellValue("VERSION");
		cellHeader.setCellStyle(style);

		nRow++;
		rowHeader = sheet.createRow(nRow);
		cellHeader = rowHeader.createCell(1);
		cellHeader.setCellValue("LY SAC");
		cellHeader.setCellStyle(style);

		cellHeader = rowHeader.createCell(4);
		cellHeader.setCellValue("");
		cellHeader.setCellStyle(style);

		cellHeader = rowHeader.createCell(9);
		cellHeader.setCellValue("YLY-000011");
		cellHeader.setCellStyle(style);

	}

	public void header(Workbook workbook, Sheet sheet) {

		nRow++;
		Font font = workbook.createFont();

		font.setFontHeight((short) ((short) 9 * 20));
		font.setFontName("Times New Roman");

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		
		style.setBorderBottom(BorderStyle.THIN );
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 5, 5));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 3, 7, 7));
		
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 1, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(nRow, nRow + 1, 10, 14));
		
		//Sub header
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 8, 8));
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 9, 9));
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 10, 10));
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 11, 11));
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 12, 12));
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 13, 13));
		sheet.addMergedRegion(new CellRangeAddress(nRow + 2, nRow + 3 , 14, 14));
		
		Row rowHeader = sheet.createRow(nRow);  //row1
		Row row2 = sheet.createRow(nRow + 1);   //row 2
		Row rowSubHeader = sheet.createRow(nRow+2);  //row3
		Row row4 = sheet.createRow(nRow+3); //row4
		
		Cell cellHeader = rowHeader.createCell(1);
	
		
		
		Cell cellBorder = null;
		for (int i = 1; i < 15; i++) {
			cellBorder = rowHeader.createCell(i);
			cellBorder.setCellStyle(style);
			
			cellBorder = row2.createCell(i);
			cellBorder.setCellStyle(style);
			
			cellBorder = rowSubHeader.createCell(i);
			cellBorder.setCellStyle(style);
			
			cellBorder = row4.createCell(i);
			cellBorder.setCellStyle(style);
		}


		
		
		
		cellHeader.setCellValue("FECHA");
		cellHeader.setCellStyle(style);
		
	

		cellHeader = rowHeader.createCell(2);
		cellHeader.setCellValue("ITEM");
		cellHeader.setCellStyle(style);

		cellHeader = rowHeader.createCell(3);
		cellHeader.setCellValue("CANTIDAD (Unid.)");
		cellHeader.setCellStyle(style);

		cellHeader = rowHeader.createCell(4);
		cellHeader.setCellValue("PRODUCTO");
		cellHeader.setCellStyle(style);
		
		cellHeader = rowHeader.createCell(5);
		cellHeader.setCellValue("LOTE");
		cellHeader.setCellStyle(style);
		
		cellHeader = rowHeader.createCell(6);
		cellHeader.setCellValue("MARCA/PRESENTACION");
		cellHeader.setCellStyle(style);
		
		cellHeader = rowHeader.createCell(7);
		cellHeader.setCellValue("FECHA VCMTO");
		cellHeader.setCellStyle(style);
		
		cellHeader = rowHeader.createCell(8);
		cellHeader.setCellValue("TRANSPORTISTA");
		cellHeader.setCellStyle(style);
		

		cellHeader = rowSubHeader.createCell(8);
		cellHeader.setCellValue("TRANS. N° PLACA");
		cellHeader.setCellStyle(style);
		
		cellHeader = rowSubHeader.createCell(9);
		cellHeader.setCellValue("TRANS. N° BREVETE");
		cellHeader.setCellStyle(style);		
		
//		cellHeader = row4.createCell(11);
//		cellHeader.setCellStyle(style);
		
		//-----------------PROVINCIA-----------------------
		cellHeader = rowHeader.createCell(10);
		cellHeader.setCellValue("LUGAR DESTINO");
		cellHeader.setCellStyle(style);
	
		
		//-------------------------------
		
		//-----------------DISTRITO----------------------------
		cellHeader = rowSubHeader.createCell(10);
		cellHeader.setCellValue("NRO GUIA REMISION");
		cellHeader.setCellStyle(style);
		
	
		//-----------------------------------
		
		cellHeader = rowSubHeader.createCell(11);
		cellHeader.setCellValue("NOMBRE IE");
		cellHeader.setCellStyle(style);
	
		cellHeader = rowSubHeader.createCell(12);
		cellHeader.setCellValue("CODIGO MODULAR");
		cellHeader.setCellStyle(style);
	
		cellHeader = rowSubHeader.createCell(13);
		cellHeader.setCellValue("NUMERO USUARIOS.");
		cellHeader.setCellStyle(style);
	
		
		cellHeader = rowSubHeader.createCell(14);
		cellHeader.setCellValue("DIRECCION DE LA II.EE.");
		cellHeader.setCellStyle(style);	

		nRow = nRow + 1;
	}

	public void setWithColumns(Sheet sheet) {

		sheet.setColumnWidth(0, (int) (sheet.getColumnWidth(0) * 1.5));
		sheet.setColumnWidth(1, (int) (sheet.getColumnWidth(1) * 2));
		sheet.setColumnWidth(2, (int) (sheet.getColumnWidth(2) * 1.5));
		sheet.setColumnWidth(3, (int) (sheet.getColumnWidth(3) * 2));
		sheet.setColumnWidth(4, (int) (sheet.getColumnWidth(4) * 7));
		sheet.setColumnWidth(5, (int) (sheet.getColumnWidth(5) * 2));
		sheet.setColumnWidth(6, (int) (sheet.getColumnWidth(6) * 2));
		sheet.setColumnWidth(7, (int) (sheet.getColumnWidth(7) * 2));
		sheet.setColumnWidth(8, (int) (sheet.getColumnWidth(8) * 1.5));
		sheet.setColumnWidth(9, (int) (sheet.getColumnWidth(9) * 1.5));
		sheet.setColumnWidth(10, (int) (sheet.getColumnWidth(10) * 1.5));
		sheet.setColumnWidth(11, (int) (sheet.getColumnWidth(11) * 2));
		sheet.setColumnWidth(12, (int) (sheet.getColumnWidth(12) * 2));
		sheet.setColumnWidth(13, (int) (sheet.getColumnWidth(13) * 1.5));
		sheet.setColumnWidth(14, (int) (sheet.getColumnWidth(14) * 4));
	}



}
