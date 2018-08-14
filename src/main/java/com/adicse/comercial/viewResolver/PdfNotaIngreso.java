package com.adicse.comercial.viewResolver;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adicse.comercial.model.Ing001;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.utilitarios.UtilitarioFechas;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfNotaIngreso extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(','); 
		
		DecimalFormat myFormatter = new DecimalFormat("###,##0.000",otherSymbols);

		Ing001 Ing001 = (Ing001) model.get("data");
		
		PdfPTable table = new PdfPTable(14);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1 });
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);

		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("NOTA DE SALIDA : " + Ing001.getNrodoc().toString(), headFont));
		hcell.setColspan(14);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBorder(Rectangle.NO_BORDER);
		hcell.setPaddingBottom(10);
		table.addCell(hcell);
		
		PdfPCell cell;

		//Almacen
		cell = new PdfPCell(new Phrase("ALMACEN", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(": "+Ing001.getAlmacen().getDscalmacen(), bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		//Trabajador que recibo
		cell = new PdfPCell(new Phrase("RECIBIDO POR", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(": "+Ing001.getEmpleado().getNomempleado() , bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		//fecha de recepcion
		cell = new PdfPCell(new Phrase("FECHA DE ING.", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
	
		String sfecha = new UtilitarioFechas().convertirDateToString(Ing001.getFecha());
		cell = new PdfPCell(new Phrase(": "+sfecha , bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingBottom(10);
		table.addCell(cell);
		
		//Motivo de ingreso
		cell = new PdfPCell(new Phrase("MOTIVO", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(": "+Ing001.getMotivoingreso().getDscmotivoingreso() , bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		// -----------------------------------------------------------------------//
		cell = new PdfPCell(new Phrase("CODIGO", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("PRODUCTO", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(10);
		table.addCell(cell);		

		cell = new PdfPCell(new Phrase("CANTIDAD", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);
		
		for (Ing002 Ing002 : Ing001.getIng002s()) {
			
			cell = new PdfPCell(new Phrase(Ing002.getProducto().getIdproducto().toString(), bodyFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);	

			cell = new PdfPCell(new Phrase(Ing002.getProducto().getDscproducto(), bodyFont));
			cell.setColspan(10);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			
			
			cell = new PdfPCell(new Phrase( myFormatter.format(Ing002.getCantidad() ), bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
		}
		
		cell = new PdfPCell(new Phrase("", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingBottom(30);
		cell.setColspan(14);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);


		cell = new PdfPCell(new Phrase("RECIBI CONFORME", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setCellEvent(new DashedBorder(PdfPCell.TOP));
		cell.setColspan(7);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("V°B°", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setCellEvent(new DashedBorder(PdfPCell.TOP));
		cell.setColspan(7);
		table.addCell(cell);
		
		
		document.add(table);

		
	}

}
