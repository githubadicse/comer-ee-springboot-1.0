package com.adicse.comercial.shared;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		   SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		   format.setTimeZone(TimeZone.getTimeZone("UTC"));
	        String date = p.getText();
	        try {
	        	
	            return format.parse(date);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	}

}
