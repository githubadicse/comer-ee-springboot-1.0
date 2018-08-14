package com.adicse.comercial.viewResolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adicse.comercial.model.Marca;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfMarcaListReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Marca> lst = (List<Marca>) model.get("data");

		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(60);
		table.setWidths(new int[] { 1, 3 });
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER );

		Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN , 10);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9);

		PdfPCell hcell ;
		hcell = new PdfPCell(new Phrase("MAESTRO DE MARCAS", headFont));
		hcell.setColspan(2);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBorder(Rectangle.NO_BORDER);
		hcell.setPaddingBottom(10);
		table.addCell(hcell);		
		
	
		hcell = new PdfPCell(new Phrase("CODIGO", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBorder(PdfPCell.NO_BORDER);
		hcell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("DESCRIPCION", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBorder(PdfPCell.NO_BORDER);
		hcell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));	
		table.addCell(hcell);

		for (Marca marca : lst) {
			PdfPCell cell;
			
			cell = new PdfPCell(new Phrase(marca.getIdmarca().toString(), bodyFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER );
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(marca.getDscmarca(), bodyFont));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER );
			table.addCell(cell);

		}

		document.add(table);

	}

}
