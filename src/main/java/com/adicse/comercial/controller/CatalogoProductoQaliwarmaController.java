package com.adicse.comercial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.service.CatalogoProductoQaliwarmaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
@RestController
@RequestMapping(value = "/res/catalogoproductoqaliwarma")
public class CatalogoProductoQaliwarmaController {
	
	@Autowired
	private CatalogoProductoQaliwarmaService catalogoProductoQaliwarmaService;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows, @RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn, @RequestParam("filters") Object filter, @RequestParam("paramsExtra") Object paramsExtra) {


		System.out.println("pagenumber :" + pagenumber);
		System.out.println("rows :" + rows);
		Page<CatalogoProductoQaliwarma> page = catalogoProductoQaliwarmaService.paginationParmsExtra(pagenumber, rows, sortdireccion, sortcolumn, filter,paramsExtra);

		List<CatalogoProductoQaliwarma> lst = page.getContent();


		Map<String, Object> response = new HashMap<String, Object>();

		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		return response;
	}
	
	

	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody String sProducto) {
		Map<String, Object> response = new HashMap<String, Object>();
		System.out.println("Grabando ingreso almacen ....");
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		CatalogoProductoQaliwarma producto = null;
		
		try {
			producto = om.readValue(sProducto, CatalogoProductoQaliwarma.class);
			// eliminamos el detalle de presentacion
			// actualizamos el detalle de presentacion
			//Integer i = 0;
			
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
			e.printStackTrace();
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

			
			producto = catalogoProductoQaliwarmaService.grabar(producto);
			String idproducto = producto.getIdCatalogoProductoQaliwarma();
		

			response.put("success", true);
			response.put("msg", "Registro grabado");
			response.put("idproducto", idproducto);
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

	@RequestMapping("/getall")
	@ResponseBody
	public List<CatalogoProductoQaliwarma> getAll() {
		List<CatalogoProductoQaliwarma> lst = catalogoProductoQaliwarmaService.getall();
		lst.sort((a,b)-> a.getDscCatalogoProductoQaliwarma().compareTo(b.getDscCatalogoProductoQaliwarma()) );
		
		return lst;
	}

	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String, Object> findone(@RequestParam("id") String idproducto) {

		CatalogoProductoQaliwarma producto = catalogoProductoQaliwarmaService.findbyid(idproducto).get();

//		for (ProductoPresentacion row :producto.getProductoPresentacions()) {
//			row.setCatalogoProductoQaliwarma(null);
//		}
		
	

		Map<String, Object> response = new HashMap<>();
		response.put("data", producto);
		return response;
	}

}
