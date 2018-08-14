package com.adicse.comercial.viewResolver;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adicse.comercial.model.Producto;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfListaProductos extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		@SuppressWarnings("unused")
		DecimalFormat myFormatter = new DecimalFormat("###,##0.000", otherSymbols);

		@SuppressWarnings("unchecked")
		List<Producto> lstProducto = (List<Producto>) model.get("data");

		PdfPTable table = new PdfPTable(14);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);

		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("LISTA DE PRODUCTOS", headFont));
		hcell.setColspan(14);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBorder(Rectangle.NO_BORDER);
		hcell.setPaddingBottom(10);
		table.addCell(hcell);

		PdfPCell cell;
		
		
		cell = new PdfPCell(new Phrase("CODIGO", bodyFont));
		cell.setColspan(2);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("DESCRIPCION", bodyFont));
		cell.setColspan(12);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		
		
	
		for (Producto producto : lstProducto) {

			cell = new PdfPCell(new Phrase( producto.getIdproducto().toString(), bodyFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase( producto.getDscproducto().toUpperCase(), bodyFont));
			cell.setColspan(14);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			//Titulo presentacion
			cell = new PdfPCell(new Phrase( " ", bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase( " Presentacion ", bodyFont));
			cell.setColspan(12);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
//			for(ProductoPresentacion productoPresentacion: producto.getProductoPresentacions()) {
//				
//				cell = new PdfPCell(new Phrase( "", bodyFont));
//				cell.setColspan(2);
//				cell.setBorder(Rectangle.NO_BORDER);
//				table.addCell(cell);
//				
//				cell = new PdfPCell(new Phrase( productoPresentacion.getCantidadPresentacion().toString() , bodyFont));
//				cell.setColspan(2);
//				cell.setBorder(Rectangle.NO_BORDER);
//				table.addCell(cell);
//				
//				cell = new PdfPCell(new Phrase( productoPresentacion.getDscPresentacion() , bodyFont));
//				cell.setColspan(2);
//				cell.setBorder(Rectangle.NO_BORDER);
//				table.addCell(cell);
//				
//				cell = new PdfPCell(new Phrase( "", bodyFont));
//				cell.setColspan(8);
//				cell.setBorder(Rectangle.NO_BORDER);
//				table.addCell(cell);
//				
//			}
			cell = new PdfPCell(new Phrase( " ", bodyFont));
			cell.setColspan(14);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			

		

		}

		document.add(table);

	}

}
