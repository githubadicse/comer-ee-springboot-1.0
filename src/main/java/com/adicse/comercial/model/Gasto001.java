package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the gasto001 database table.
 * 
 */
@Entity
@NamedQuery(name="Gasto001.findAll", query="SELECT g FROM Gasto001 g")
public class Gasto001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idgasto001;

	private Timestamp fecha;

	private Timestamp fechahorasys;

	private String glosa;

	private double igvimporte;

	private double igvporcentaje;

	private double importe;

	private double impuestocuartaimporte;

	private double impuestocuartaporcentaje;

	private String nrodoc;

	private String observacion;

	private String serie;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="idmoneda")
	private Moneda moneda;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Tipodocumento
	@ManyToOne
	@JoinColumn(name="idtipodocumento")
	private Tipodocumento tipodocumento;

	public Gasto001() {
	}

	public Integer getIdgasto001() {
		return this.idgasto001;
	}

	public void setIdgasto001(Integer idgasto001) {
		this.idgasto001 = idgasto001;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechahorasys() {
		return this.fechahorasys;
	}

	public void setFechahorasys(Timestamp fechahorasys) {
		this.fechahorasys = fechahorasys;
	}

	public String getGlosa() {
		return this.glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public double getIgvimporte() {
		return this.igvimporte;
	}

	public void setIgvimporte(double igvimporte) {
		this.igvimporte = igvimporte;
	}

	public double getIgvporcentaje() {
		return this.igvporcentaje;
	}

	public void setIgvporcentaje(double igvporcentaje) {
		this.igvporcentaje = igvporcentaje;
	}

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getImpuestocuartaimporte() {
		return this.impuestocuartaimporte;
	}

	public void setImpuestocuartaimporte(double impuestocuartaimporte) {
		this.impuestocuartaimporte = impuestocuartaimporte;
	}

	public double getImpuestocuartaporcentaje() {
		return this.impuestocuartaporcentaje;
	}

	public void setImpuestocuartaporcentaje(double impuestocuartaporcentaje) {
		this.impuestocuartaporcentaje = impuestocuartaporcentaje;
	}

	public String getNrodoc() {
		return this.nrodoc;
	}

	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

	public Tipodocumento getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

}