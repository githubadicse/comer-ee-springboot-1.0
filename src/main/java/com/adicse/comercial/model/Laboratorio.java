package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the laboratorio database table.
 * 
 */
@Entity
@NamedQuery(name="Laboratorio.findAll", query="SELECT l FROM Laboratorio l")
public class Laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idlaboratorio;

	private String dsclaboratorio;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="laboratorio")
	private List<Producto> productos;

	public Laboratorio() {
	}

	public Integer getIdlaboratorio() {
		return this.idlaboratorio;
	}

	public void setIdlaboratorio(Integer idlaboratorio) {
		this.idlaboratorio = idlaboratorio;
	}

	public String getDsclaboratorio() {
		return this.dsclaboratorio;
	}

	public void setDsclaboratorio(String dsclaboratorio) {
		this.dsclaboratorio = dsclaboratorio;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setLaboratorio(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setLaboratorio(null);

		return producto;
	}

}