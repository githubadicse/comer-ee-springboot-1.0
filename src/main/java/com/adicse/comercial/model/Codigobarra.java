package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the codigobarra database table.
 * 
 */
@Entity
@NamedQuery(name="Codigobarra.findAll", query="SELECT c FROM Codigobarra c")
public class Codigobarra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idcodigobarra;

	private String codigo;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Codigobarra() {
	}

	public String getIdcodigobarra() {
		return this.idcodigobarra;
	}

	public void setIdcodigobarra(String idcodigobarra) {
		this.idcodigobarra = idcodigobarra;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}