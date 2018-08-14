package com.adicse.comercial.viewResolver;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adicse.comercial.model.Salida002;
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

public class PdfListaSalidas extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(','); 
		
		DecimalFormat myFormatter = new DecimalFormat("###,##0.000",otherSymbols);

		@SuppressWarnings("unchecked")
		List<Salida002> lstSalida002 = (List<Salida002>) model.get("data");
		
		if(lstSalida002.size() == 0){
			Phrase phrase1 = new Phrase("No existe informacion");
			document.add(phrase1);
			return;
		}
		
		PdfPTable table = new PdfPTable(14);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1 });
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);

		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("LISTA DE SALIDAS " , headFont));
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

		cell = new PdfPCell(new Phrase(": "+ lstSalida002.get(0).getSalida001().getAlmacen().getDscalmacen() ,bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		//producto
		cell = new PdfPCell(new Phrase("PRODUCTO", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(": "+ lstSalida002.get(0).getProducto().getDscproducto() ,bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("PERIODO", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		String periodo = lstSalida002.get(0).getSalida001(). getPeriodoalmacen().getMes().toString() + " - "
				+ lstSalida002.get(0).getSalida001().getPeriodoalmacen().getAnno().toString();

		cell = new PdfPCell(new Phrase(": " +periodo, bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingBottom(10);
		table.addCell(cell);
		
		
		// -----------------------------------------------------------------------//
		cell = new PdfPCell(new Phrase("N° SALIDA", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("FECHA", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);		
		
		cell = new PdfPCell(new Phrase("CLIENTE", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(8);
		table.addCell(cell);	

		cell = new PdfPCell(new Phrase("CANTIDAD", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);
		
		
		for(Salida002 salida002: lstSalida002){
			
			//nro doc ingreso
			cell = new PdfPCell(new Phrase(salida002.getSalida001().getNrodoc().toString(), bodyFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);	
			
			//fecha
			String sfecha = new UtilitarioFechas().convertirDateToString(salida002.getSalida001().getFecha());
			cell = new PdfPCell(new Phrase(sfecha , bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
	

			//proveedor
			cell = new PdfPCell(new Phrase(salida002.getSalida001().getProveedorcliente().getRazonsocial() , bodyFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(8);
			table.addCell(cell);	
			
			//cantidad
			cell = new PdfPCell(new Phrase( myFormatter.format(salida002.getCantidad() ), bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
		}
		
		document.add(table);
		
	
		
	}

}
