package com.adicse.comercial.viewResolver;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adicse.comercial.model.Cierremensual;
import com.adicse.comercial.model.Kardex;
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

public class PdfKardexProducto extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(','); 
		
		DecimalFormat myFormatter = new DecimalFormat("###,##0.000",otherSymbols);

		List<Kardex> lst = (List<Kardex>) model.get("data");
		if(lst.size() == 0){
			document.add(new Phrase("No existen datos"));
			return;
		}
		Cierremensual cierremensual = (Cierremensual) model.get("cierremensual");

		String producto = "";
		String almacen = "";
		String periodo = "";

		producto = lst.get(0).getProducto().getDscproducto();
		almacen = lst.get(0).getPeriodoalmacen().getAlmacen().getDscalmacen();
		periodo = lst.get(0).getPeriodoalmacen().getMes().toString() + " - "
				+ lst.get(0).getPeriodoalmacen().getAnno().toString();

		PdfPTable table = new PdfPTable(14);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1 });
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);

		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("KARDEX FISICO", headFont));
		hcell.setColspan(14);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBorder(Rectangle.NO_BORDER);
		hcell.setPaddingBottom(10);
		table.addCell(hcell);

		PdfPCell cell;

		cell = new PdfPCell(new Phrase("ALMACEN : ", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(almacen, bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("PRODUCTO : ", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(producto, bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("PERIODO : ", bodyFont));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(periodo, bodyFont));
		cell.setColspan(12);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingBottom(10);
		table.addCell(cell);

		// -----------------------------------------------------------------------//
		cell = new PdfPCell(new Phrase("FECHA", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("N° DOC", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);		

		cell = new PdfPCell(new Phrase("MOTIVO", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(4);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("INGRESOS", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("SALIDAS", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//cell.setBorder(PdfPCell.NO_BORDER);
		// cell.setCellEvent(new DashedBorder(PdfPCell.BOTTOM));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("STOCK", headFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		//cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
		
		table.setHeaderRows(5);


		
		
		
	
		

		// -------------------------------------------------------------------------------------------//
		String motivo = "", sfechahora,snrodoc = "";
		BigDecimal ingresos, salidas, stock, saldoanterior;
		Integer nrodoc = 0;
		saldoanterior = cierremensual == null? new BigDecimal(0): cierremensual.getStockinicial();
		
		//--------------- saldo inicial---------------------//
		cell = new PdfPCell(new Phrase("Saldo anterior", bodyFont));		
		//cell.setBorder(PdfPCell.NO_BORDER);		
		cell.setColspan(12);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(myFormatter.format(saldoanterior) , bodyFont));		
		//cell.setBorder(PdfPCell.NO_BORDER);		
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(2);
		table.addCell(cell);		
		//--------------------------------------------------//

		
		for (Kardex kardex : lst) {
			// PdfPCell cell;
			sfechahora = new UtilitarioFechas().convertirTimestampToString(kardex.getFechahora(), "dd/MM/yyyy HH:MM");
			cell = new PdfPCell(new Phrase(sfechahora, bodyFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);
			
			
		
			

			if (kardex.getTipomovimiento().equals("I")) {
				motivo = kardex.getIng002kardexs().get(0).getIng002().getIng001().getMotivoingreso()
						.getDscmotivoingreso();
				ingresos = kardex.getCantidad();
				salidas = new BigDecimal(0);
				stock = new BigDecimal(saldoanterior.floatValue() + ingresos.floatValue() - salidas.floatValue());
				
				nrodoc = kardex.getIng002kardexs().get(0).getIng002().getIng001().getNrodoc();
				snrodoc = "NI-"+nrodoc.toString();
			} else {
				
				motivo = kardex.getSalida002kardexs().get(0).getSalida002().getSalida001().getMotivosalida()
						.getDscmotivosalida();
				
				ingresos = new BigDecimal(0);
				salidas = kardex.getCantidad();
				stock = new BigDecimal(saldoanterior.floatValue() + ingresos.floatValue() - salidas.floatValue());
				
				nrodoc = kardex.getSalida002kardexs().get(0).getSalida002().getSalida001().getNrodoc(); 
				snrodoc = "NS-"+nrodoc.toString();
			}
			saldoanterior = stock;
			
			cell = new PdfPCell(new Phrase(snrodoc, bodyFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);	

			cell = new PdfPCell(new Phrase(motivo, bodyFont));
			cell.setColspan(4);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			
			
			cell = new PdfPCell(new Phrase( myFormatter.format(ingresos), bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(myFormatter.format(salidas), bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(myFormatter.format(stock), bodyFont));
			cell.setColspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
		}

		document.add(table);

	}

}
