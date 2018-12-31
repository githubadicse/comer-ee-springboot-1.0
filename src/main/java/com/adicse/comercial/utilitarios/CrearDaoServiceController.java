package com.adicse.comercial.utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CrearDaoServiceController {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Ingrese el nombre de la entidad :");
		String Entidad = reader.readLine();
		
		System.out.print("Ingrese el typo de dato para la clave primaria de la entidad :");
		String Type = reader.readLine()	;
		
		//String Entidad = "Nivel";
		//String Type = "Integer";
		String Package = "com.adicse.comercial";
		
		String rutaBase = "D:\\proyectos\\backend\\comer-ver-2019\\comer-2009-v1\\src\\main\\java\\com\\adicse\\comercial\\";
		String ruta = rutaBase + "utilitarios\\";
		String rutaDao = rutaBase +  "dao\\";
		String rutaService = rutaBase +  "service\\";
		String rutaController = rutaBase +  "controller\\";

		
		//CREANDO EL ARCHIVO DAO
		File fileDao = new File(ruta + "estructuraDao.txt");
		if (fileDao.exists()) {
			
			//Obtenemos la data del archivo en una lista de tipo TextoLine
			List<TextoLine> lstTextoLine = escribirInList(ruta + "estructuraDao.txt");
			
			//Bscamos y reemplazamos , 	
			//String findEntidad, String replaceEntidad, String findType,	String replaceType,	String findEntidadLower,String replaceEntidadLower
			lstTextoLine = buscarAndReplaceInList(lstTextoLine, 
					"package.dao",
					Package +".dao",
					
					"package.service",
					Package +".service",					
					
					"package.model",
					Package + ".model",
					
					"package.controller",
					Package + ".controller",					
					
					
					"package.interfaces",
					Package + ".interfaces",					
					
					"package.specification",
					Package + ".specification",										
					
					"Entidad", 
					Entidad,
					
					"Type", 
					Type,
					
					"entidad", 
					Entidad.toLowerCase());
			
			//Escribitos el archivo en la ruta del Dao
			File fileWriteDao = new File(rutaDao + "I" + Entidad+"Dao.java");
			if(!fileWriteDao.exists()) {
				writeFile(lstTextoLine, fileWriteDao);
				System.out.println("Generacion de Repositorio " + Entidad + " creado con exito");				
			}
			
		}
		
		//CREANDO EL ARCHIVO SERVICE
		File fileService = new File(ruta + "estructuraService.txt");
		if (fileDao.exists()) {
			
			//Obtenemos la data del archivo en una lista de tipo TextoLine
			List<TextoLine> lstTextoLine = escribirInList(ruta + "estructuraService.txt");
			
			//Bscamos y reemplazamos , 	
			//String findEntidad, String replaceEntidad, String findType,	String replaceType,	String findEntidadLower,String replaceEntidadLower
			lstTextoLine = buscarAndReplaceInList(lstTextoLine,
					"package.dao",
					Package +".dao",
					
					
					"package.service",
					Package +".service",
					
					"package.model",
					Package + ".model",
					
					"package.controller",
					Package + ".controller",					
					
					
					"package.interfaces",
					Package + ".interfaces",					
					
					
					"package.specification",
					Package + ".specification",					
					
					"Entidad", 
					Entidad,
					
					"Type", 
					Type,
					
					"entidad", 
					Entidad.toLowerCase());
			
			//Escribitos el archivo en la ruta del Dao
			File fileWriteService = new File(rutaService +  Entidad+"Service.java");
			if(!fileWriteService.exists()) {
				writeFile(lstTextoLine, fileWriteService);
				System.out.println("Generacion de Service " + Entidad + " creado con exito");				
			}
			
		}		
		
		
		//CREANDO EL ARCHIVO CONTROLLER
		File fileController = new File(ruta + "estructuraController.txt");
		if (fileDao.exists()) {
			
			//Obtenemos la data del archivo en una lista de tipo TextoLine
			List<TextoLine> lstTextoLine = escribirInList(ruta + "estructuraController.txt");
			
			//Bscamos y reemplazamos , 	
			//String findEntidad, String replaceEntidad, String findType,	String replaceType,	String findEntidadLower,String replaceEntidadLower
			lstTextoLine = buscarAndReplaceInList(lstTextoLine,
					"package.dao",
					Package +".dao",
					
					
					"package.service",
					Package +".service",
					
					"package.model",
					Package + ".model",
					
					"package.controller",
					Package + ".controller",					
					
					"package.interfaces",
					Package + ".interfaces",					
					
					
					"package.specification",
					Package + ".specification",					
					
					"Entidad", 
					Entidad,
					
					"Type", 
					Type,
					
					"entidad", 
					Entidad.toLowerCase());
			
			//Escribitos el archivo en la ruta del Dao
			File fileWriteService = new File(rutaController +  Entidad+"Controller.java");
			if(!fileWriteService.exists()) {
				writeFile(lstTextoLine, fileWriteService);
				System.out.println("Generacion de Controller " + Entidad + " creado con exito");				
			}
			
		}		
				
		
		
		

	}

	private static List<TextoLine> escribirInList(String sFile) {
		BufferedReader br;
		TextoLine textoLine = null;
		List<TextoLine> lstTextoLine = new ArrayList<>();
		Integer cnt = 1;
		try {
			br = new BufferedReader(new FileReader(sFile));

			String line = "";
			while ((line = br.readLine()) != null) {
				textoLine = new TextoLine();
				textoLine.setNrolinea(cnt);
				
				textoLine.setTexto(line);
				
				lstTextoLine.add(textoLine);
				
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstTextoLine;

	}
	
	
	private static List<TextoLine> buscarAndReplaceInList(List<TextoLine> lstTextoLine,
			String findPackageRepo,
			String replacePackageRepo,
			
			String findPackageService,
			String replacePackageService,			
			
			String findPackageModel,
			String replacePackageModel,
			
			String findPackageController,
			String replacePackageController,			
			
			String findPackageInterfaces,
			String replacePackageInterfaces,			
			
			String findPackageSpecification,
			String replacePackageSpecification,						
			
			String findEntidad,
			String replaceEntidad,
			
			String findType,
			String replaceType,
			
			String findEntidadLower,
			String replaceEntidadLower
			){
		
		

		for(TextoLine row:lstTextoLine) {
			
			String line = row.getTexto();
			
			line = line.replaceAll(findPackageRepo, replacePackageRepo );
			line = line.replaceAll(findPackageService, replacePackageService );
			line = line.replaceAll(findPackageModel, replacePackageModel);
			line = line.replaceAll(findPackageController, replacePackageController);
			line = line.replaceAll(findPackageInterfaces, replacePackageInterfaces);
			line = line.replaceAll(findPackageSpecification, replacePackageSpecification );
			line = line.replaceAll(findEntidad, replaceEntidad );
			line = line.replaceAll(findType, replaceType );
			line = line.replaceAll(findEntidadLower, replaceEntidadLower );
			
			row.setTexto(line);
			
		}
		
		return lstTextoLine;
		
		
	}
	
	private static void writeFile(List<TextoLine> lstTextoLine,File file) {
		FileWriter fw=null;
		try {
			
			fw = new FileWriter(file);
			String line = "";
			for(TextoLine row:lstTextoLine) {
				line = row.getTexto();
				fw.write(line);
				line = "\n";
				fw.write(line);
			}
			
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
