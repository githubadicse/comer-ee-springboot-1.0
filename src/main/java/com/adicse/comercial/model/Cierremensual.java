package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the cierremensual database table.
 * 
 */
@Entity
@NamedQuery(name="Cierremensual.findAll", query="SELECT c FROM Cierremensual c")
public class Cierremensual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idcierremensual;

	private BigDecimal ingresos;

	private double preciounitario;

	private BigDecimal salidas;

	private BigDecimal stockfinal;

	private BigDecimal stockinicial;

	private double total;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to Periodoalmacen
	@ManyToOne
	@JoinColumn(name="idperiodoalmacen")
	private Periodoalmacen periodoalmacen;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Cierremensual() {
	}

	public String getIdcierremensual() {
		return this.idcierremensual;
	}

	public void setIdcierremensual(String idcierremensual) {
		this.idcierremensual = idcierremensual;
	}

	public BigDecimal getIngresos() {
		return this.ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public double getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public BigDecimal getSalidas() {
		return this.salidas;
	}

	public void setSalidas(BigDecimal salidas) {
		this.salidas = salidas;
	}

	public BigDecimal getStockfinal() {
		return this.stockfinal;
	}

	public void setStockfinal(BigDecimal stockfinal) {
		this.stockfinal = stockfinal;
	}

	public BigDecimal getStockinicial() {
		return this.stockinicial;
	}

	public void setStockinicial(BigDecimal stockinicial) {
		this.stockinicial = stockinicial;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Periodoalmacen getPeriodoalmacen() {
		return this.periodoalmacen;
	}

	public void setPeriodoalmacen(Periodoalmacen periodoalmacen) {
		this.periodoalmacen = periodoalmacen;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}