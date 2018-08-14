package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the salida002 database table.
 * 
 */
@Entity
@NamedQuery(name="Salida002.findAll", query="SELECT s FROM Salida002 s")
public class Salida002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idsalida002;

	private BigDecimal cantidad;

	private Timestamp fechavencimiento;

	private Integer item;

	private String nrolote;

	private BigDecimal peso;

	private BigDecimal pesototal;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Salida001
	@ManyToOne
	@JoinColumn(name="idsalida001")
	private Salida001 salida001;

	//bi-directional many-to-one association to Salida002kardex
	@OneToMany(mappedBy="salida002")
	private List<Salida002kardex> salida002kardexs;

	public Salida002() {
	}

	public String getIdsalida002() {
		return this.idsalida002;
	}

	public void setIdsalida002(String idsalida002) {
		this.idsalida002 = idsalida002;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFechavencimiento() {
		return this.fechavencimiento;
	}

	public void setFechavencimiento(Timestamp fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}

	public Integer getItem() {
		return this.item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getNrolote() {
		return this.nrolote;
	}

	public void setNrolote(String nrolote) {
		this.nrolote = nrolote;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPesototal() {
		return this.pesototal;
	}

	public void setPesototal(BigDecimal pesototal) {
		this.pesototal = pesototal;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Salida001 getSalida001() {
		return this.salida001;
	}

	public void setSalida001(Salida001 salida001) {
		this.salida001 = salida001;
	}

	public List<Salida002kardex> getSalida002kardexs() {
		return this.salida002kardexs;
	}

	public void setSalida002kardexs(List<Salida002kardex> salida002kardexs) {
		this.salida002kardexs = salida002kardexs;
	}

	public Salida002kardex addSalida002kardex(Salida002kardex salida002kardex) {
		getSalida002kardexs().add(salida002kardex);
		salida002kardex.setSalida002(this);

		return salida002kardex;
	}

	public Salida002kardex removeSalida002kardex(Salida002kardex salida002kardex) {
		getSalida002kardexs().remove(salida002kardex);
		salida002kardex.setSalida002(null);

		return salida002kardex;
	}

}