package com.adicse.comercial.utilitarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;



public class AgregarJsonFormatTimeStamp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String[][] entitys = {				
				{"Ing001"},
				{"Com001"},
				{"Usuario"},
				{"Salida001"},
				{"GuiaRemision001"},
				{"CatalogoLote"},
				{"ProductoPorNumeroEntrega"},
				{"EntregaPorItem"}		,
				{"RutaDistribucion"},
				{"Empleado"}
		};
		
		
		String ruta = "D:\\proyectos\\backend\\comer-back-end\\src\\main\\java\\com\\adicse\\comercial\\model\\";
		//String ruta = "/home/ubuntu/proyectos/spring boot/comercial/src/main/java/com/adicse/comercial/model/";

		String archivoFuente = null;
		String archivoTemporal = null;
	
		
		for(int i=0; i < entitys.length ; i++){
			archivoFuente = ruta + entitys[i][0] + ".java";
			archivoTemporal = ruta + entitys[i][0] + ".txt";
	
			
			CrearArchivoTemporal(archivoFuente,archivoTemporal);
			CrearArchivoFuente(archivoTemporal,archivoFuente);			
		}
		System.out.println("Fin procedimiento agregar formato TimeStamp ...");
		return ;		

	}
	
	
	public static void CrearArchivoTemporal(String archivoRead,
			String archivoDestination 
			) throws IOException{
		File archivoWrite = new File(archivoDestination);
		File archivoLectura = new File(archivoRead);
		FileReader leerArchivo = null;		
		Boolean flagRegistro=true;
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
//				textoLine.setNrolinea(cntLine);
//				textoLine.setTexto(textoLinea);
//				lstLines.add(textoLine);
//				cntLine++;
				
				if(cntLine > 2 && flagRegistro){
					
					if(textoLinea.trim().length() == 0) {
						textoLinea = "import com.fasterxml.jackson.annotation.JsonFormat;";
						textoLine = new TextoLine();
						textoLine.setNrolinea(cntLine);
						textoLine.setTexto(textoLinea);					
						lstLines.add(textoLine);
						cntLine++;				
						
						flagRegistro = false;
						
					}
					

				}
				//textoLinea.trim().contains("private Timestamp");
				if(textoLinea.trim().contains("private Timestamp")){
					
					String textoContenido = "\t"+textoLinea.trim();
					
					textoLinea = "\t@JsonFormat (pattern =\"dd/MM/yyyy hh:mm:ss\")";
					textoLine = new TextoLine();
					textoLine.setNrolinea(cntLine);
					textoLine.setTexto(textoLinea);		
					lstLines.add(textoLine);
					
					cntLine++;
					textoLine = new TextoLine();
					textoLine.setNrolinea(cntLine);
					textoLine.setTexto(textoContenido);
					lstLines.add(textoLine);

				}else {
					textoLine = new TextoLine();
					textoLine.setNrolinea(cntLine);
					textoLine.setTexto(textoLinea);
					lstLines.add(textoLine);
					cntLine++;
					
				}
			}

			for(TextoLine row : lstLines){
				bw.write(row.getTexto());
				bw.newLine();
			}		

			bw.close();
			memoriaParaLectura.close();

		} else {
			// El fichero no existe y hay que crearlo
			System.out.println("El archivo no existe...");
		}		
		//archivoLectura.delete();
	}
	
	
	public static void CrearArchivoFuente(
			String archivoRead,
			String archivoDestination
			) throws IOException{
		//import org.hibernate.annotations.Type
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
			String textoLinea = null, textoLineaAnt = "X";
			while ((textoLinea = memoriaParaLectura.readLine()) != null) {
				
				if(!textoLinea.trim().equals(textoLineaAnt.trim())){
					nroLinea++;
					bw.append(textoLinea);
					bw.newLine();				
				}
	
				
				textoLineaAnt = textoLinea;

			}
			bw.close();
			memoriaParaLectura.close();

		} else {
			// El fichero no existe y hay que crearlo
			System.out.println("El archivo no existe...");
		}	
		archivoLectura.delete();
	}	


}



