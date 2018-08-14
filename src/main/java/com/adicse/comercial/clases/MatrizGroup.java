package com.adicse.comercial.clases;

import java.math.BigDecimal;

import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.Matriz;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;

public interface MatrizGroup {
	
	
	Matriz getMatriz();
	ProductoPorNumeroEntrega getProductoPorNumeroEntrega();
	CatalogoProductoQaliwarma getCatalogoProductoQaliwarma();
	
	String getIdProductoPorNumeroEntrega();
	
	String getDscComplementoProducto();
	String getDscCatalogoProductoQaliwarma();
	String getPpne();
	String getLote1();
	String getLote2();
	
	BigDecimal getUnds1();
	BigDecimal getUnds2();
	BigDecimal getUnds3();

}
