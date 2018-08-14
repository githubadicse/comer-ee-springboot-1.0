package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the periodoalmacen database table.
 * 
 */
@Entity
@NamedQuery(name="Periodoalmacen.findAll", query="SELECT p FROM Periodoalmacen p")
public class Periodoalmacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idperiodoalmacen;

	private Integer anno;

	private String estado;

	private Integer iniciooperaciones;

	private Integer mes;

	//bi-directional many-to-one association to Cierremensual
	@OneToMany(mappedBy="periodoalmacen")
	private List<Cierremensual> cierremensuals;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="periodoalmacen")
	private List<Ing001> ing001s;

	//bi-directional many-to-one association to Kardex
	@OneToMany(mappedBy="periodoalmacen")
	private List<Kardex> kardexs;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="periodoalmacen")
	private List<Salida001> salida001s;

	public Periodoalmacen() {
	}

	public Integer getIdperiodoalmacen() {
		return this.idperiodoalmacen;
	}

	public void setIdperiodoalmacen(Integer idperiodoalmacen) {
		this.idperiodoalmacen = idperiodoalmacen;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIniciooperaciones() {
		return this.iniciooperaciones;
	}

	public void setIniciooperaciones(Integer iniciooperaciones) {
		this.iniciooperaciones = iniciooperaciones;
	}

	public Integer getMes() {
		return this.mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<Cierremensual> getCierremensuals() {
		return this.cierremensuals;
	}

	public void setCierremensuals(List<Cierremensual> cierremensuals) {
		this.cierremensuals = cierremensuals;
	}

	public Cierremensual addCierremensual(Cierremensual cierremensual) {
		getCierremensuals().add(cierremensual);
		cierremensual.setPeriodoalmacen(this);

		return cierremensual;
	}

	public Cierremensual removeCierremensual(Cierremensual cierremensual) {
		getCierremensuals().remove(cierremensual);
		cierremensual.setPeriodoalmacen(null);

		return cierremensual;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setPeriodoalmacen(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setPeriodoalmacen(null);

		return ing001;
	}

	public List<Kardex> getKardexs() {
		return this.kardexs;
	}

	public void setKardexs(List<Kardex> kardexs) {
		this.kardexs = kardexs;
	}

	public Kardex addKardex(Kardex kardex) {
		getKardexs().add(kardex);
		kardex.setPeriodoalmacen(this);

		return kardex;
	}

	public Kardex removeKardex(Kardex kardex) {
		getKardexs().remove(kardex);
		kardex.setPeriodoalmacen(null);

		return kardex;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public List<Salida001> getSalida001s() {
		return this.salida001s;
	}

	public void setSalida001s(List<Salida001> salida001s) {
		this.salida001s = salida001s;
	}

	public Salida001 addSalida001(Salida001 salida001) {
		getSalida001s().add(salida001);
		salida001.setPeriodoalmacen(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setPeriodoalmacen(null);

		return salida001;
	}

}