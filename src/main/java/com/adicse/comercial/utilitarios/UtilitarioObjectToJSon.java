package com.adicse.comercial.utilitarios;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

public class UtilitarioObjectToJSon {

	// El parametro paramsExtra es de tipo object, el cual sera convertido a json.
	// el parametro dato es el valor que deberemos buscar.
	// si no se encuentra el valor se devolvera null
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getValueOfObject(Object paramsExtra, String dato) {
		String s2 = "[" + paramsExtra.toString() + "]";

		JsonArray arrayFromString;
		try {

			JsonParser jsonParser = new JsonParser();
			// Convert JSON Array String into JSON Array

			arrayFromString = jsonParser.parse(s2).getAsJsonArray();
			System.out.println(arrayFromString.toString());

		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}

	
		LinkedTreeMap lktm = null;
		LinkedTreeMap<String, Object> propiedadObj = new LinkedTreeMap<>();
		String rtnValue = null;

		Gson googleJson = new Gson();
		
		
		ArrayList javaArrayListFromGSON = googleJson.fromJson(arrayFromString, ArrayList.class);

		for (int j = 0; j < javaArrayListFromGSON.size(); j++) {
			lktm = (LinkedTreeMap) javaArrayListFromGSON.get(j);
			System.out.println(j);

			for (Object key : lktm.keySet()) {
				System.out.println(key);
				if (key.equals(dato)) {
					propiedadObj = (LinkedTreeMap<String, Object>) lktm.get(key.toString());
					String value = null;
					Double dValor = null;
					String sValor = null;
					Integer iValor = null;
					if (propiedadObj.get("value").getClass().getSimpleName().equals("Integer")) {

						iValor= (Integer) propiedadObj.get("value");
						value = iValor.toString();
					}
					if (propiedadObj.get("value").getClass().getSimpleName().equals("Double")) {

						dValor = (Double) propiedadObj.get("value")  ;
						
						value = dValor.toString();
						
						Integer datoAfterDot = value.indexOf(".");
						if(datoAfterDot > 0) {
							String valorAfterDot = value.substring(datoAfterDot + 1);
							if(Integer.parseInt(valorAfterDot) == 0) {
								value = value.substring(0,datoAfterDot);
							}		
						}
					
						
					}
					if (propiedadObj.get("value").getClass().getSimpleName().equals("String")) {

						sValor = (String) propiedadObj.get("value");
						value = sValor;
					}
					
					rtnValue = value;
					break;

				}
			}
		}
	
	
		return rtnValue;
	}

}
