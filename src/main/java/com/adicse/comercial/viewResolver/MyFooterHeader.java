package com.adicse.comercial.viewResolver;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class MyFooterHeader extends PdfPageEventHelper {

	Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);

	
	public void onStartPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		Phrase header = new Phrase("ENCABEZADO DE PAGINA", ffont);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,
				(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
	}
	
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		Phrase footer = new Phrase("Pag " + document.getPageNumber() , ffont);

		
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
				(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
	}
	
}
