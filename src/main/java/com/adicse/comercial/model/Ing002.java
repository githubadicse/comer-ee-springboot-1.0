package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ing002 database table.
 * 
 */
@Entity
@NamedQuery(name="Ing002.findAll", query="SELECT i FROM Ing002 i")
public class Ing002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String iding002;

	private BigDecimal cantidad;

	private Timestamp fechavencimiento;

	private Integer item;

	private String nrolote;

	private BigDecimal peso;

	private BigDecimal pesototal;

	//bi-directional many-to-one association to Ing001
	@ManyToOne
	@JoinColumn(name="iding001")
	private Ing001 ing001;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Ing002kardex
	@OneToMany(mappedBy="ing002")
	private List<Ing002kardex> ing002kardexs;

	public Ing002() {
	}

	public String getIding002() {
		return this.iding002;
	}

	public void setIding002(String iding002) {
		this.iding002 = iding002;
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

	public Ing001 getIng001() {
		return this.ing001;
	}

	public void setIng001(Ing001 ing001) {
		this.ing001 = ing001;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Ing002kardex> getIng002kardexs() {
		return this.ing002kardexs;
	}

	public void setIng002kardexs(List<Ing002kardex> ing002kardexs) {
		this.ing002kardexs = ing002kardexs;
	}

	public Ing002kardex addIng002kardex(Ing002kardex ing002kardex) {
		getIng002kardexs().add(ing002kardex);
		ing002kardex.setIng002(this);

		return ing002kardex;
	}

	public Ing002kardex removeIng002kardex(Ing002kardex ing002kardex) {
		getIng002kardexs().remove(ing002kardex);
		ing002kardex.setIng002(null);

		return ing002kardex;
	}

}