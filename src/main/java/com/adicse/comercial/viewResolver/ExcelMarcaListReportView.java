package com.adicse.comercial.viewResolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.adicse.comercial.model.Marca;

public class ExcelMarcaListReportView extends AbstractXlsxView {
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment: filename=\"d:marcalist.xlsx");
		

		
		Sheet sheet = workbook.createSheet("Marcas Lista");
		
		Row header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("DESCRIPCION");
		
		Integer rowNum = 1;
		List<Marca> lst = (List) model.get("data");
		for(Marca marca:lst){
			Row row = sheet.createRow(rowNum++);
			
			row.createCell(0).setCellValue( marca.getIdmarca() );
			row.createCell(1).setCellValue( marca.getDscmarca() );			
			
		}
		
		
		
		
	}




	

}
