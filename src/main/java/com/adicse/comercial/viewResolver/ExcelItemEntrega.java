package com.adicse.comercial.viewResolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;

public class ExcelItemEntrega extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<ItemEntrega> lst = (List<ItemEntrega>) model.get("data");
		
	
		

		response.setHeader("Content-Disposition", "attachment: filename=\"d:Items.xlsx");

		Sheet sheet = workbook.createSheet("Entrega de productos por item");
		
		//Create a new font and alter it.
	      Font font = workbook.createFont();
	      font.setFontHeightInPoints((short) 10);
	      font.setFontHeight((short) ((short)10*20));
	      font.setFontName("Arial");
	      font.setBold(true);
	      //font.setItalic(true);
	      //font.setColor(HSSFColor.BRIGHT_GREEN.index);

	      //Set font into style
	      CellStyle style = workbook.createCellStyle();
	      style.setFont(font);

	      
		Row header = sheet.createRow(2);

		header.createCell(0).setCellValue("PERIODO");
		header.createCell(1).setCellValue("ITEM");
		header.createCell(2).setCellValue("Nro ENTREGA");
		header.createCell(3).setCellValue("PRODUCTO");

		Integer rowNum = 3;
		
		for (ItemEntrega itemEntrega : lst) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(itemEntrega.getAnno());
			row.createCell(1).setCellValue(itemEntrega.getItem());
			
			Integer cntG2=0;
			for(EntregaPorItem entregaPorItem: itemEntrega.getEntregaPorItems()) {
				if(cntG2 > 0) {
					row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(itemEntrega.getAnno());
					row.createCell(1).setCellValue(itemEntrega.getItem());
					
				}
				cntG2++;
		
				row.createCell(2).setCellValue(entregaPorItem.getNumeroEntrega().getDscNumeroEntrega() );
				
				Integer cntG3=0;
				for(ProductoPorNumeroEntrega productoPorNumeroEntrega: entregaPorItem.getProductoPorNumeroEntregas() ) {
					if(cntG3 > 0) {
						row = sheet.createRow(rowNum++);
						row.createCell(0).setCellValue(itemEntrega.getAnno());
						row.createCell(1).setCellValue(itemEntrega.getItem());	
						row.createCell(2).setCellValue(entregaPorItem.getNumeroEntrega().getDscNumeroEntrega() );
					}
					cntG3++;
					//row = sheet.createRow(rowNum++);
					
					Cell cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellValue(productoPorNumeroEntrega.getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma() );
					
				}
				
				
			}

		}

	}

}
