package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidadmedida database table.
 * 
 */
@Entity
@NamedQuery(name="Unidadmedida.findAll", query="SELECT u FROM Unidadmedida u")
public class Unidadmedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idunidadmedida;

	private String abrunidadmedida;

	private String dscunidadmedida;

	private Integer factor;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@OneToMany(mappedBy="unidadmedida")
	private List<CatalogoProductoQaliwarma> catalogoProductoQaliwarmas;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="unidadmedida")
	private List<Producto> productos;

	public Unidadmedida() {
	}

	public Integer getIdunidadmedida() {
		return this.idunidadmedida;
	}

	public void setIdunidadmedida(Integer idunidadmedida) {
		this.idunidadmedida = idunidadmedida;
	}

	public String getAbrunidadmedida() {
		return this.abrunidadmedida;
	}

	public void setAbrunidadmedida(String abrunidadmedida) {
		this.abrunidadmedida = abrunidadmedida;
	}

	public String getDscunidadmedida() {
		return this.dscunidadmedida;
	}

	public void setDscunidadmedida(String dscunidadmedida) {
		this.dscunidadmedida = dscunidadmedida;
	}

	public Integer getFactor() {
		return this.factor;
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	public List<CatalogoProductoQaliwarma> getCatalogoProductoQaliwarmas() {
		return this.catalogoProductoQaliwarmas;
	}

	public void setCatalogoProductoQaliwarmas(List<CatalogoProductoQaliwarma> catalogoProductoQaliwarmas) {
		this.catalogoProductoQaliwarmas = catalogoProductoQaliwarmas;
	}

	public CatalogoProductoQaliwarma addCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		getCatalogoProductoQaliwarmas().add(catalogoProductoQaliwarma);
		catalogoProductoQaliwarma.setUnidadmedida(this);

		return catalogoProductoQaliwarma;
	}

	public CatalogoProductoQaliwarma removeCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		getCatalogoProductoQaliwarmas().remove(catalogoProductoQaliwarma);
		catalogoProductoQaliwarma.setUnidadmedida(null);

		return catalogoProductoQaliwarma;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setUnidadmedida(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUnidadmedida(null);

		return producto;
	}

}