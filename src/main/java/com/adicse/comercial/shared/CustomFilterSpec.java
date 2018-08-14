package com.adicse.comercial.shared;

import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomFilterSpec {

	/*
	 * recibe dos parametros una la entidad o tabla, y un objecto tipo json
	 * {campo:{value:dato},{}...} esto debuelve un objeto del tipo de entidad
	 * que se paso pero agregado un registro con su propiedad y el valor a
	 * buscar
	 */
	@SuppressWarnings("rawtypes")
	public Object CreateCustomFilter(Object entity, Object filters)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		//System.out.println("Ingresado para generar  la entidad specificacion para el filtro");
		Field campo = null;

		String s = filters.toString();
		JSONObject json = null;
		try{
			json = new JSONObject(s);
		}catch(Exception e){
			return entity;
		}
		
		JSONArray ajson = new JSONArray();
		ajson.put(json);

		JSONObject obj = new JSONObject();
		JSONObject propiedadObj = new JSONObject();
		String value = null;
		String propiedad = null;
		
		for (int i = 0; i < ajson.length(); i++) {
			obj = ajson.getJSONObject(i);

			for (Object key : obj.keySet()) {

				propiedadObj = (JSONObject) obj.get(key.toString());
				value = (String) propiedadObj.get("value");
				propiedad = key.toString();
				
				
//				for (Field field : entity.getClass().getDeclaredFields()) {
//				    field.setAccessible(true); // You might want to set modifier to public first.
//				    Object valueAux = field.get(entity); 
//				    
//				    if (valueAux != null) {
//				    	
//				        System.out.println(field.getName() + "=" + valueAux);
//				    }
//				}

				if (propiedad.indexOf(".") > 0) {
					String tabla, sucampo;
					String campoAux = propiedad.replace(".", "-");
					String[] parts = campoAux.split("-");
					@SuppressWarnings("unused")
					Field[] properties;
					tabla = parts[0];
					sucampo = parts[1];

					Class entityClass = entity.getClass();
	
					Field f1 = entityClass.getDeclaredField(tabla);
					Field f2 = null;
					Object subEntity = null;
					if(parts.length == 2) {
						f1.setAccessible(true);

						subEntity = f1.get(entity);
						f2 = subEntity.getClass().getDeclaredField(sucampo);
					
						f2.setAccessible(true);
						f2.set(subEntity, value);			
						
					}
					
					
			

				} else {
					campo = entity.getClass().getDeclaredField(propiedad);
					campo.setAccessible(true);
					if (campo.getType().getSimpleName().equals("String")) {
						campo.set(entity, value);
					}
					if (campo.getType().getSimpleName().equals("Integer")) {
						Integer dato = null;
						dato = Integer.parseInt(value);
						campo.set(entity, dato);
					}
				}

			}

		}

		return entity;
	}

}
