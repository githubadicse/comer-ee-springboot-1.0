package com.adicse.comercial.utilitarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgregarRelaciones {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String[][] entitys = {
				{ "Producto", "@OneToMany(mappedBy=\"producto\")", "private List<Codigobarra> codigobarras;",
						"\t@OneToMany(mappedBy=\"producto\", cascade={CascadeType.ALL})" },

				{ "Ing001", "@OneToMany(mappedBy=\"ing001\")", "private List<Ing002> ing002s;",
						"\t@OneToMany(mappedBy=\"ing001\", cascade={CascadeType.ALL})" },
				
				{ "Com002", "@OneToMany(mappedBy=\"com002\")", "private List<Ing001Com002Relacion> ing001Com002Relacions;",
				"\t@OneToMany(mappedBy=\"com002\", cascade={CascadeType.ALL})" },				

				{ "Com001", "@OneToMany(mappedBy=\"com001\")", "private List<Com002> com002s;",
					"\t@OneToMany(mappedBy=\"com001\", cascade={CascadeType.ALL})" },				
				
				{ "Salida001", "@OneToMany(mappedBy=\"salida001\")", "private List<Salida002> salida002s;",
						"\t@OneToMany(mappedBy=\"salida001\", cascade={CascadeType.ALL})" },

				{ "Ven001", "@OneToMany(mappedBy=\"ven001\")", "private List<Ven002> ven002s;",
						"\t@OneToMany(mappedBy=\"ven001\", cascade={CascadeType.ALL})" },
				
				{ "Usuario", "@OneToMany(mappedBy=\"usuario\")", "private List<Usuarioempleado> usuarioempleados;",
				"\t@OneToMany(mappedBy=\"usuario\", cascade={CascadeType.ALL})" },
				
				
				{ "Proveedorcliente", "@OneToMany(mappedBy=\"proveedorcliente\")", "private List<Proveedorclientedireccion> proveedorclientedireccions;",
				"\t@OneToMany(mappedBy=\"proveedorcliente\", cascade={CascadeType.ALL})" },
				
				{ "CatalogoProductoQaliwarma", "@OneToMany(mappedBy=\"catalogoProductoQaliwarma\")", "private List<ProductoPresentacion> productoPresentacions;",
				"\t@OneToMany(mappedBy=\"catalogoProductoQaliwarma\", cascade={CascadeType.ALL})" },
				
				{ "Ubigeo", "@OneToMany(mappedBy=\"ubigeo\")", "private List<RequerimientoVolumen001> requerimientoVolumen001s;",
				"\t@OneToMany(mappedBy=\"ubigeo\", cascade={CascadeType.ALL})" },
				
				{ "RequerimientoVolumen001", "@OneToMany(mappedBy=\"requerimientoVolumen001\")", "private List<RequerimientoVolumen002> requerimientoVolumen002s;",
				"\t@OneToMany(mappedBy=\"requerimientoVolumen001\", cascade={CascadeType.ALL})" },
				
				{ "RequerimientoVolumen002", "@OneToMany(mappedBy=\"requerimientoVolumen002\")", "private List<RequerimientoVolumen002Producto> requerimientoVolumen002Productos;",
				"\t@OneToMany(mappedBy=\"requerimientoVolumen002\", cascade={CascadeType.ALL})" },
				
				{ "RequerimientoVolumen002Producto", "@OneToMany(mappedBy=\"requerimientoVolumen002Producto\")", "private List<VolumenConvertidoEnvace> volumenConvertidoEnvaces;",
				"\t@OneToMany(mappedBy=\"requerimientoVolumen002Producto\", cascade={CascadeType.ALL})" },
				
				
				{ "RegionAlimentaria", "@OneToMany(mappedBy=\"regionAlimentaria\")", "private List<CatalogoBonificacion> catalogoBonificacions;",
				"\t@OneToMany(mappedBy=\"regionAlimentaria\", cascade={CascadeType.ALL})" },
				
				
				{ "GuiaRemision001", "@OneToMany(mappedBy=\"guiaRemision001\")", "private List<GuiaRemision002> guiaRemision002s;",
				"\t@OneToMany(mappedBy=\"guiaRemision001\", cascade={CascadeType.ALL})" },
				
				{ "RutaDistribucion", "@OneToMany(mappedBy=\"rutaDistribucion\")", "private List<RutaDistribucionDetalle> rutaDistribucionDetalles;",
				"\t@OneToMany(mappedBy=\"rutaDistribucion\", cascade={CascadeType.ALL})" },
				
				{ "CatalogoLote", "@OneToMany(mappedBy=\"catalogoLote\")", "private List<Piking> pikings;",
				"\t@OneToMany(mappedBy=\"catalogoLote\", cascade={CascadeType.ALL})" },
				
				{ "Perfil", "@OneToMany(mappedBy=\"perfil\")", "private List<Perfilesdetalle> perfilesdetalles;",
				"\t@OneToMany(mappedBy=\"perfil\", cascade={CascadeType.ALL})" },
				
				{ "Tipodocumento", "@OneToMany(mappedBy=\"tipodocumento\")", "private List<Numerador> numeradors;",
				"\t@OneToMany(mappedBy=\"tipodocumento\", cascade={CascadeType.ALL})" }				
				

		};
		
		//String ruta = "/home/ubuntu/proyectos/spring boot/comercial/src/main/java/com/adicse/comercial/model/";

		String ruta = "D:\\proyectos\\backend\\comer-back-end\\src\\main\\java\\com\\adicse\\comercial\\model\\";
		String archivoFuente = null;
		String archivoTemporal = null;
		String textoBuscar1 = null;
		String textoBuscar2 = null;
		String textoReemplazar = null;

		for (int i = 0; i < entitys.length; i++) {
			archivoFuente = ruta + entitys[i][0] + ".java";
			archivoTemporal = ruta + entitys[i][0] + ".txt";
			textoBuscar1 = entitys[i][1];
			textoBuscar2 = entitys[i][2];
			textoReemplazar = entitys[i][3];

			CrearArchivoTemporal(archivoFuente, archivoTemporal, textoBuscar1, textoBuscar2, textoReemplazar);
			CrearArchivoFuente(archivoTemporal, archivoFuente);
		}
		System.out.println("Fin procedimiento relaciones ...");
		return;

	}

	public static void CrearArchivoTemporal(String archivoRead, String archivoDestination, String textoBuscar1,
			String textoBuscar2, String textoReemplazar) throws IOException {
		File archivoWrite = new File(archivoDestination);
		File archivoLectura = new File(archivoRead);
		FileReader leerArchivo = null;
		leerArchivo = new FileReader(archivoLectura);

		BufferedWriter bw = null;
		if (archivoLectura.exists()) {
			// El fichero ya existe

			BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
			try {

				bw = new BufferedWriter(new FileWriter(archivoWrite));
				bw.write("");

			} catch (IOException e) {
				e.printStackTrace();
			}

			String textoLinea = null;

			Integer cntLine = 1;
			List<TextoLine> lstLines = new ArrayList<TextoLine>();
			while ((textoLinea = memoriaParaLectura.readLine()) != null) {
				TextoLine textoLine = new TextoLine();
				textoLine.setNrolinea(cntLine);
				textoLine.setTexto(textoLinea);
				lstLines.add(textoLine);
				cntLine++;
			}
			String rowTexto = "", rowTexto2 = "";

			for (int i = 0; i < lstLines.size(); i++) {
				TextoLine textoLine = new TextoLine();
				textoLine = (TextoLine) lstLines.get(i);
				rowTexto = textoLine.getTexto();
				rowTexto = rowTexto.replace("\t", "").trim();
				if (rowTexto.equals(textoBuscar1)) {

					// buscamos la siguiente que sela la relacion con la lista.
					rowTexto2 = ((TextoLine) lstLines.get(i + 1)).getTexto();
					rowTexto2 = rowTexto2.replace("\t", "").trim();
					if (rowTexto2.equals(textoBuscar2)) {
						((TextoLine) lstLines.get(i)).setTexto(textoReemplazar);
					}
				}

			}
			for (TextoLine row : lstLines) {
				bw.write(row.getTexto());
				bw.newLine();
			}

			bw.close();
			memoriaParaLectura.close();

		} else {
			// El fichero no existe y hay que crearlo
			System.out.println("El archivo no existe...");
		}
		// archivoLectura.delete();
	}

	public static void CrearArchivoFuente(String archivoRead, String archivoDestination) throws IOException {
		// import org.hibernate.annotations.Type
		File archivoWrite = new File(archivoDestination);
		File archivoLectura = new File(archivoRead);
		FileReader leerArchivo = null;

		leerArchivo = new FileReader(archivoLectura);

		Integer nroLinea = 0;

		BufferedWriter bw = null;

		if (archivoLectura.exists()) {

			BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);
			try {

				bw = new BufferedWriter(new FileWriter(archivoWrite));
				bw.write("");

			} catch (IOException e) {
				e.printStackTrace();
			}
			String textoLinea = null;
			while ((textoLinea = memoriaParaLectura.readLine()) != null) {
				nroLinea++;
				bw.append(textoLinea);
				bw.newLine();

			}
			bw.close();
			memoriaParaLectura.close();

		} else {
			// El fichero no existe y hay que crearlo
			System.out.println("El archivo no existe...");
		}
		archivoLectura.delete();
	}

	public static int BuscarPalabra(String texto, String palabraBuscada) {
		Integer posicionInicial = 0;
		Integer longitud = 0;
		Integer cantidadPalabra = 0;

		for (int i = 0; i < texto.length(); i++) {
			longitud++;
			// if (cadenaPorCaracter[i] == ' ' || i == texto.length() - 1)
			if (i == texto.length() - 1) {
				if (texto.substring(posicionInicial, longitud).trim().toUpperCase()
						.equals(palabraBuscada.trim().toUpperCase())) {
					cantidadPalabra++;
				}
				posicionInicial = i;
			}
		}

		return cantidadPalabra;
	}

}
