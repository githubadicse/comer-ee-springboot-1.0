package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modelo database table.
 * 
 */
@Entity
@NamedQuery(name="Modelo.findAll", query="SELECT m FROM Modelo m")
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idmodelo;

	private String dscmodelo;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="modelo")
	private List<Producto> productos;

	public Modelo() {
	}

	public Integer getIdmodelo() {
		return this.idmodelo;
	}

	public void setIdmodelo(Integer idmodelo) {
		this.idmodelo = idmodelo;
	}

	public String getDscmodelo() {
		return this.dscmodelo;
	}

	public void setDscmodelo(String dscmodelo) {
		this.dscmodelo = dscmodelo;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setModelo(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setModelo(null);

		return producto;
	}

}