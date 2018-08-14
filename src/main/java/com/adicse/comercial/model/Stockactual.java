package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stockactual database table.
 * 
 */
@Entity
@NamedQuery(name="Stockactual.findAll", query="SELECT s FROM Stockactual s")
public class Stockactual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idstockactual;

	private BigDecimal stockactual;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Stockactual() {
	}

	public String getIdstockactual() {
		return this.idstockactual;
	}

	public void setIdstockactual(String idstockactual) {
		this.idstockactual = idstockactual;
	}

	public BigDecimal getStockactual() {
		return this.stockactual;
	}

	public void setStockactual(BigDecimal stockactual) {
		this.stockactual = stockactual;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}