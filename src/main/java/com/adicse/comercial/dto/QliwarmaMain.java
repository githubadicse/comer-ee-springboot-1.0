package com.adicse.comercial.dto;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QliwarmaMain {

	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {

		String nombreArchivo = "archivo2.xlsx";
		String rutaArchivo = "D:\\archivos\\" + nombreArchivo;
		//String hoja = "Hoja1";

		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))
		// InputStream file = new FileInputStream(rutaArchivo);

		) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			// obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(1);

			Integer totalRows = sheet.getPhysicalNumberOfRows() + 1;
			System.out.println("Numero de filas : " + totalRows.toString());
			// obtener todas las filas de la hoja excel
			// Iterator<Row> rowIterator = sheet.iterator();

			Row row;
			Cell cell, cell2;
			Integer columna = 2;
			Integer fila = 10;
			for (int i = fila; i <= totalRows; i++) {
				System.out.println("valor i : " + i);
				String string = null;
				row = sheet.getRow(i);

				if (row != null) {
					
					cell = row.getCell(columna);
					
					if (cell != null) {
						cell2 = row.getCell(13);
						System.out.println("Dato : " + cell.getStringCellValue() + " - " + cell2.getStringCellValue());
					}

						
				}

			}

		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage());
		}

	}

}
