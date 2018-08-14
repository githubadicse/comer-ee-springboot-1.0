package com.adicse.comercial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.krysalis.barcode.impl.EAN13;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.Codigobarra;
import com.adicse.comercial.service.CodigobarraService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/res/codigobarra")
public class CodigobarraController {

	@Autowired
	private CodigobarraService codigobarraservice;

	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody String sCodigobarra) {
		Map<String, Object> response = new HashMap<String, Object>();

		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Codigobarra codigobarra = null;
		try {
			codigobarra = om.readValue(sCodigobarra, Codigobarra.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;
			// e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.put("success", false);
			response.put("msg", e.getMessage());
			return response;
			// e.printStackTrace();
		}

		try {

			
			codigobarra = codigobarraservice.grabar(codigobarra);
			//String idcodigobarra = codigobarra.getIdcodigobarra();
			//Map<String, Object> obj = new HashMap<>();

			response.put("success", true);
			response.put("msg", "Registro grabado");
			response.put("data", codigobarra);
		} catch (JDBCException e) {
			System.out.println("error 1 :" + e.getMessage());
			SQLException cause = (SQLException) e.getCause();
			// evaluate cause and find out what was the problem
			System.out.println("error 2 :" + cause.getMessage());
			response.put("success", false);
			response.put("msg", cause.getMessage());
		} catch (HibernateException ex) {
			System.out.println("error 3 :" + ex.getMessage());
		}

		return response;

	}

	@ResponseBody
	@RequestMapping("/getAllByProductoIdproducto")
	public Map<String, Object> getAllByProductoIdproducto(@RequestParam("idproducto") Integer idproducto) {
		Map<String, Object> response = new HashMap<String, Object>();

		List<Codigobarra> lst = codigobarraservice.getAllByProductoIdproducto(idproducto);

		response.put("data", lst);

		return response;
	}

	@ResponseBody
	@RequestMapping("/getAllByCodigoEquals")
	public Map<String, Object> getAllByCodigoEquals(@RequestParam("codigo") String codigo) {
		Map<String, Object> response = new HashMap<String, Object>();
		System.out.println("codigo:" +codigo);
		List<Codigobarra> lst = codigobarraservice.getAllByCodigoEquals(codigo);

		response.put("data", lst);

		return response;
	}
	
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping("/generacodigobarra")
	@ResponseBody	
	public Map<String, Object> generarBarCode(@RequestParam("codigo") String codigo) {
		Map<String, Object> response = new HashMap<>();
		
		Integer len = codigo.length();
		Integer dif = (12 - len) ;
		String ldig =  dif.toString();
		@SuppressWarnings("resource")
		Formatter fmt = new Formatter();
		fmt.format("%011d", Integer.parseInt(codigo) );
		codigo = "1" + fmt.toString();
		System.out.println(codigo);
		//String codigo = "100000001202";
		EAN13 generator = new EAN13();
		//UPCEANLogicImpl impl = generator.createLogicImpl();
		//codigo += impl.calcChecksum(codigo);

		//response.put("data", codigo);
		return null;
	}	

}
