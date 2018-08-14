package com.adicse.comercial.clases;

import java.math.BigDecimal;

import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;
import com.adicse.comercial.model.RequerimientoVolumen002Producto;

public interface RequerimientoClass {
	
	ProductoPorNumeroEntrega getProductoPorNumeroEntrega();
	
	CatalogoProductoQaliwarma getCatalogoProductoQaliwarma();
	
	
	
	BigDecimal getVolumen();
	
	RequerimientoVolumen002Producto getRequerimientoVolumen002Producto();

	
	
	
	
	
	

}
