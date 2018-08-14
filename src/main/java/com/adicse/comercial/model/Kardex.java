package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the kardex database table.
 * 
 */
@Entity
@NamedQuery(name="Kardex.findAll", query="SELECT k FROM Kardex k")
public class Kardex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idkardex;

	private BigDecimal cantidad;

	private Timestamp fechahora;

	private String nrodoc;

	private String nrolote;

	private Integer nromov;

	private String obs;

	private double preciounitario;

	private String tipomovimiento;

	private double total;

	//bi-directional many-to-one association to Ing002kardex
	@OneToMany(mappedBy="kardex")
	private List<Ing002kardex> ing002kardexs;

	//bi-directional many-to-one association to Periodoalmacen
	@ManyToOne
	@JoinColumn(name="idperiodoalmacen")
	private Periodoalmacen periodoalmacen;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Salida002kardex
	@OneToMany(mappedBy="kardex")
	private List<Salida002kardex> salida002kardexs;

	public Kardex() {
	}

	public String getIdkardex() {
		return this.idkardex;
	}

	public void setIdkardex(String idkardex) {
		this.idkardex = idkardex;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFechahora() {
		return this.fechahora;
	}

	public void setFechahora(Timestamp fechahora) {
		this.fechahora = fechahora;
	}

	public String getNrodoc() {
		return this.nrodoc;
	}

	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}

	public String getNrolote() {
		return this.nrolote;
	}

	public void setNrolote(String nrolote) {
		this.nrolote = nrolote;
	}

	public Integer getNromov() {
		return this.nromov;
	}

	public void setNromov(Integer nromov) {
		this.nromov = nromov;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public double getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public String getTipomovimiento() {
		return this.tipomovimiento;
	}

	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Ing002kardex> getIng002kardexs() {
		return this.ing002kardexs;
	}

	public void setIng002kardexs(List<Ing002kardex> ing002kardexs) {
		this.ing002kardexs = ing002kardexs;
	}

	public Ing002kardex addIng002kardex(Ing002kardex ing002kardex) {
		getIng002kardexs().add(ing002kardex);
		ing002kardex.setKardex(this);

		return ing002kardex;
	}

	public Ing002kardex removeIng002kardex(Ing002kardex ing002kardex) {
		getIng002kardexs().remove(ing002kardex);
		ing002kardex.setKardex(null);

		return ing002kardex;
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

	public List<Salida002kardex> getSalida002kardexs() {
		return this.salida002kardexs;
	}

	public void setSalida002kardexs(List<Salida002kardex> salida002kardexs) {
		this.salida002kardexs = salida002kardexs;
	}

	public Salida002kardex addSalida002kardex(Salida002kardex salida002kardex) {
		getSalida002kardexs().add(salida002kardex);
		salida002kardex.setKardex(this);

		return salida002kardex;
	}

	public Salida002kardex removeSalida002kardex(Salida002kardex salida002kardex) {
		getSalida002kardexs().remove(salida002kardex);
		salida002kardex.setKardex(null);

		return salida002kardex;
	}

}