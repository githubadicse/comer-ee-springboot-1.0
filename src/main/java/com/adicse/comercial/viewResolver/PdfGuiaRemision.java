package com.adicse.comercial.viewResolver;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.GuiaRemision002;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class PdfGuiaRemision extends AbstractPdfGuiaRemision {

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		DecimalFormat myFormatter = new DecimalFormat("###,##0.000", otherSymbols);

		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		PdfPTable table = new PdfPTable(18);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);

		List<GuiaRemision001> lstGuias = (List<GuiaRemision001>) model.get("data");

		lstGuias.sort((a, b) -> a.getOrdenPorItem().compareTo(b.getOrdenPorItem()));

		String fechaEmision = null, puntoPartida;

		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);

		Integer cntHojas = 1;
		

		for (GuiaRemision001 guiaRemision001 : lstGuias) {

			if (cntHojas > 1) {
				document.newPage();

			}
			cntHojas++;
			// fecha emision
			fechaEmision = formatoFecha.format(guiaRemision001.getFechaEmision());
			posText(fechaEmision, 50, 262, writer, 9);

			// numero de orden de la guia
			String numeroOrden = guiaRemision001.getOrdenPorItem() == null ? ""
					: guiaRemision001.getOrdenPorItem().toString();
			posText(numeroOrden, 200, 262, writer, 9);
			// punto de partida
			puntoPartida = guiaRemision001.getPuntoPartida().getDireccion();
			posText(puntoPartida, 60, 256, writer, 9);

			// serie documento
			// posText(guiaRemision001.getSerie().toString(), 140, 280, writer, 9);

			// numero documento
			// posText(guiaRemision001.getNumero().toString(), 150, 280, writer, 9);

			// ruc del destinatario
			posText(guiaRemision001.getProveedorcliente().getNrodocumento(), 60, 250, writer, 8);

			// nombre razon social
			posText(guiaRemision001.getProveedorcliente().getRazonsocial(), 70, 246, writer, 8);

			// nombre del c.e. codigo modular
			String nombreIIEE = guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getNombreInstitucionEducativa();
			String direcionIIEE = guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getDireccionInstitucionEducativa();
			String ubigeo = guiaRemision001.getRequerimientoVolumen001().getUbigeo().getNombreProvincia() + "-"
					+ guiaRemision001.getRequerimientoVolumen001().getUbigeo().getNombreDistrito();
			posText("II.EE. " + nombreIIEE + " " + direcionIIEE + " " + ubigeo, 60, 240, writer, 8);

			// mas datos
			String numeroEntrega = guiaRemision001.getRequerimientoVolumen001().getEntregaPorItem().getNumeroEntrega()
					.getNumeroEntregaValor().toString();
			String nivelEducacion = guiaRemision001.getRequerimientoVolumen001().getNivelEducacion()
					.getDscNivelEducacion();
			String codigoModular = guiaRemision001.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa()
					.getCodigoModular();
			String sItem = guiaRemision001.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega()
					.getDscitem();
			Integer lenItem = sItem.length();
			Integer len = 0;
			if (lenItem > 13) {
				len = 13;
			} else {
				len = lenItem;
			}

			// Codigo Modular
			posText(" Cod.Mod:" + codigoModular, 180, 243, writer, 9);
			posText(" Nivel :" + nivelEducacion, 180, 235, writer, 9);
			posText("Nro Entrega :" + numeroEntrega + " Item :" + sItem.substring(0, len), 100, 235, writer, 8);

			// DETALLE
			String sProducto = null, sPresentacion = null, sUnidadMedida = null, sUnidadMedidaAbr = null,
					sEnvase = null, sProductoComplemento = null, sLote = null;
			Integer alturaDetalle = 220;
			Integer cantidad = null, factor = null;
			Double nPresentacion = null;
			Double volumen = null, factorVolumenPresentacion = null;
			Double sumVolumen = 0.0;
			Double peso = 0.0;
			Integer itemDetalle = 1;
			String numeroLote = "";
			Double totalPeso = 0.0;
			
	
			
			
			List<GuiaRemision002> lstGuiaRemision002 = guiaRemision001.getGuiaRemision002s();

			lstGuiaRemision002.sort(
					(GuiaRemision002 g1, GuiaRemision002 g2) -> g1.getProductoGrupo().compareTo(g2.getProductoGrupo()));

			// lstGuiaRemision002.sort((GuiaRemision002 g1, GuiaRemision002 g2) ->
			// g1.getMatriz().getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma()
			// .compareTo(g2.getMatriz().getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getDscCatalogoProductoQaliwarma())
			// );

			for (GuiaRemision002 guiaRemision002 : lstGuiaRemision002) {

				if (guiaRemision002.getCantidad() > 0) {

					System.out.println("item Detall : " + itemDetalle);
					if (itemDetalle.equals(4)) {
						System.out.println(" para 4");
					}
					itemDetalle++;
					// volumen = guiaRemision002.

					// sumVolumen = sumVolumen + volumen;

					// ----------------------------producto cantidad
					// ----------------------------------//
					sProducto = guiaRemision002.getProductoGrupo();

					cantidad = guiaRemision002.getCantidad();
					// if(cantidad.equals(0)) {
					// break;
					// }

					numeroLote = "";
					if (guiaRemision002.getVolumenConvertidoEnvace().getRequerimientoVolumen002Producto()
							.getProductoPorNumeroEntrega().getCatalogoProductoQaliwarma().getPrintLote().equals(1))
						numeroLote = " LT:" + guiaRemision002.getNumeroLote() == null ? ""
								: " LT:" + guiaRemision002.getNumeroLote();

					sProductoComplemento = guiaRemision002.getProductoSeleccionado() + " "
							+ guiaRemision002.getMarca().trim() + " " + numeroLote;
					// posText("("+sProducto+") "+sProductoComplemento,20, alturaDetalle, writer,
					// 8);
					posText(sProductoComplemento, 20, alturaDetalle, writer, 7);
					posText(cantidad.toString(), 125, alturaDetalle, writer, 8);

					// ----------------------------producto presentacion
					// ----------------------------------//
					sEnvase = guiaRemision002.getEnvase();

					factorVolumenPresentacion = guiaRemision002.getFactor().doubleValue();

					sUnidadMedidaAbr = guiaRemision002.getUnidadMedidaTrabajo();

					sPresentacion = WordUtils.capitalize(sEnvase.toLowerCase()) + " X "
							+ factorVolumenPresentacion.toString() + " " + sUnidadMedidaAbr;

					posText(sPresentacion, 147, alturaDetalle, writer, 8);

					peso = cantidad * factorVolumenPresentacion;
					totalPeso = totalPeso + peso;
					// si es factor es 1, el volumen sera multiplicado por su peso, si el factor es
					// de volumen osea 1000, el volumen sera dividido entre el peso de la
					// presentacion
					// myFormatter.format(peso)
					posText(myFormatter.format(peso) + " " + sUnidadMedidaAbr, 190, alturaDetalle, writer, 8);
					String rp = StringUtils.repeat(" - ", 70);
					posText(rp, 20, alturaDetalle - 2, writer, 8);

					alturaDetalle = alturaDetalle - 4;
					
				    
				  
					
			        

				}

			}
			alturaDetalle = alturaDetalle - 2;
			posText("Total peso :", 147, alturaDetalle, writer, 8);
			posText(myFormatter.format(totalPeso) + " Kg", 180, alturaDetalle, writer, 8);

			String transportista = "";
			String domicilio = "";
			String chofer = "";
			String rucTransportista = "";
			String placa = "";
			String brevete = "";
			String marcaVehiculo = "";

			if (guiaRemision001.getTransportista() != null) {
				if (guiaRemision001.getTransportista().getProveedorcliente() != null) {

					if (guiaRemision001.getTransportista().getProveedorcliente()
							.getProveedorclientedireccions() != null) {
						domicilio = guiaRemision001.getTransportista().getProveedorcliente()
								.getProveedorclientedireccions().get(0).getDireccion();
					}
					if (guiaRemision001.getTransportista().getProveedorcliente().getRazonsocial() != null) {
						transportista = guiaRemision001.getTransportista().getProveedorcliente().getRazonsocial();
						rucTransportista = guiaRemision001.getTransportista().getProveedorcliente().getNrodocumento();
					}

				}
			}

			if (guiaRemision001.getChofer() != null) {
				chofer = guiaRemision001.getChofer().getNombres();
				brevete = guiaRemision001.getChofer().getNumeroBrevete();

			}
			if (guiaRemision001.getVehiculo() != null) {
				placa = guiaRemision001.getVehiculo().getNumeroPlaca();
				marcaVehiculo = guiaRemision001.getVehiculo().getMarcaVehiculo();
			}

			// String marcaVehiculo = guiaRemision001.getVehiculo().get

			posText(transportista, 50, 60, writer, 8);
			posText(domicilio, 50, 55, writer, 8);
			posText(chofer, 50, 49, writer, 8);

			posText(rucTransportista, 170, 60, writer, 8);
			posText(placa, 170, 55, writer, 8);
			posText(brevete, 170, 49, writer, 8);
			posText(marcaVehiculo, 175, 43, writer, 8);

		}
			 
		//tableDetalle.writeSelectedRows(0, -1, 20,  0 , writer.getDirectContentUnder() );
		
//		  PdfContentByte canvas = writer.getDirectContent();
//	        Rectangle rect = new Rectangle(100, 36, 559, 806);
//	        rect.setBorder(Rectangle.BOX);
//	        rect.setBorderWidth(2);
//	        canvas.rectangle(rect);
//	        
//	        PdfPTable tableDetalle = new PdfPTable(4);
//	        tableDetalle.setWidthPercentage(80);
//			float[] columnWidths = new float[]{10f, 20f, 30f, 10f};
			//tableDetalle.setWidths(columnWidths);
			//tableDetalle.setLockedWidth(true);
	       
//	        tableDetalle.addCell("ejemplo");
//	        tableDetalle.addCell("xx xxxxxxd x x x x  x xxxxxxxxxxxxxxxxxxxxx   xxxxxxxxxxxxxx xxxxxxxxxxxxxxxx xxxxxxxxxxxxxxx xxxxxxxxxxxxx xxxxxxx");
//	        tableDetalle.addCell("xx xxxxxxd x x x x  x xxxxxxxxxxxxxxxxxxxxx   xxxxxxxxxxxxxx xxxxxxxxxxxxxxxx xxxxxxxxxxxxxxx xxxxxxxxxxxxx xxxxxxx");
//	        tableDetalle.addCell("xx xxxxxxd x x x x  x xxxxxxxxxxxxxxxxxxxxx   xxxxxxxxxxxxxx xxxxxxxxxxxxxxxx xxxxxxxxxxxxxxx xxxxxxxxxxxxx xxxxxxx");
//	       
//	        tableDetalle.writeSelectedRows(-1, -1, 102, 500, canvas);
	        
//	        ColumnText ct = new ColumnText(canvas);
//	        ct.addElement(tableDetalle);
//	        ct.setSimpleColumn(10, 10, 80 , 70);
//	        ct.go();  
	        //ct.setSimpleColumn(rect);
	        //document.add(tableDetalle);
		

	}

	@SuppressWarnings("unused")
	private void posText(String text, int x, int y, PdfWriter writer, float tamanoLetra) {
		PdfContentByte cb = writer.getDirectContent();
		BaseFont bf = null;
		try {
			bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		float fontSize = tamanoLetra;
		Font font = new Font();
		font.setSize(tamanoLetra);

		cb.saveState();
		cb.beginText();
		float xp, yp;
		xp = mmToPt(x);
		yp = mmToPt(y);
		cb.moveText(xp, yp);

		cb.setFontAndSize(bf, tamanoLetra);
		cb.showText(text);
		cb.endText();
		cb.restoreState();

	}

	public static float mmToPt(float mm) {
		// 70pt = 25.4mm
		return ((70f * mm) / 25.4f);
	}

}
