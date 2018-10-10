package com.adicse.comercial.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The persistent class for the com001 database table.
 * 
 */
@Entity
@NamedQuery(name="Com001.findAll", query="SELECT c FROM Com001 c")
public class Com001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idcom001;

	@Column(name="documento_numero")
	private String documentoNumero;

	@Column(name="documento_serie")
	private String documentoSerie;

	@JsonFormat (pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Column(name="fecha_registro_systema")
	@JsonFormat (pattern ="dd/MM/yyyy hh:mm:ss")
	private Timestamp fechaRegistroSystema;

	@Column(name="fecha_registro_systema_modifica")
	@JsonFormat (pattern ="dd/MM/yyyy hh:mm:ss")
	private Timestamp fechaRegistroSystemaModifica;

	@JsonFormat (pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="flag_genera_from_nota_ingreso")
	private Boolean flagGeneraFromNotaIngreso;

	@Column(name="id_usuario_crea")
	private Integer idUsuarioCrea;

	@Column(name="id_usuario_modifica")
	private Integer idUsuarioModifica;

	@Column(name="importe_bruto")
	private double importeBruto;

	@Column(name="importe_compra")
	private double importeCompra;

	@Column(name="importe_igv")
	private double importeIgv;

	@Column(name="importe_igv_porcentaje")
	private double importeIgvPorcentaje;

	@Column(name="importe_isc")
	private double importeIsc;

	@Column(name="importe_pagos")
	private double importePagos;

	@Column(name="tipo_cambio")
	private double tipoCambio;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to ModalidadCompraVenta
	@ManyToOne
	@JoinColumn(name="id_modalidad_compra_venta")
	private ModalidadCompraVenta modalidadCompraVenta;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="id_moneda")
	private Moneda moneda;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Tipodocumento
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private Tipodocumento tipodocumento;

	//bi-directional many-to-one association to Com002
	@OneToMany(mappedBy="com001", cascade={CascadeType.ALL})
	private List<Com002> com002s;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="com001")
	private List<Ing001> ing001s;

	public Com001() {
	}

	public Long getIdcom001() {
		return this.idcom001;
	}

	public void setIdcom001(Long idcom001) {
		this.idcom001 = idcom001;
	}

	public String getDocumentoNumero() {
		return this.documentoNumero;
	}

	public void setDocumentoNumero(String documentoNumero) {
		this.documentoNumero = documentoNumero;
	}

	public String getDocumentoSerie() {
		return this.documentoSerie;
	}

	public void setDocumentoSerie(String documentoSerie) {
		this.documentoSerie = documentoSerie;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Timestamp getFechaRegistroSystema() {
		return this.fechaRegistroSystema;
	}

	public void setFechaRegistroSystema(Timestamp fechaRegistroSystema) {
		this.fechaRegistroSystema = fechaRegistroSystema;
	}

	public Timestamp getFechaRegistroSystemaModifica() {
		return this.fechaRegistroSystemaModifica;
	}

	public void setFechaRegistroSystemaModifica(Timestamp fechaRegistroSystemaModifica) {
		this.fechaRegistroSystemaModifica = fechaRegistroSystemaModifica;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Boolean getFlagGeneraFromNotaIngreso() {
		return this.flagGeneraFromNotaIngreso;
	}

	public void setFlagGeneraFromNotaIngreso(Boolean flagGeneraFromNotaIngreso) {
		this.flagGeneraFromNotaIngreso = flagGeneraFromNotaIngreso;
	}

	public Integer getIdUsuarioCrea() {
		return this.idUsuarioCrea;
	}

	public void setIdUsuarioCrea(Integer idUsuarioCrea) {
		this.idUsuarioCrea = idUsuarioCrea;
	}

	public Integer getIdUsuarioModifica() {
		return this.idUsuarioModifica;
	}

	public void setIdUsuarioModifica(Integer idUsuarioModifica) {
		this.idUsuarioModifica = idUsuarioModifica;
	}

	public double getImporteBruto() {
		return this.importeBruto;
	}

	public void setImporteBruto(double importeBruto) {
		this.importeBruto = importeBruto;
	}

	public double getImporteCompra() {
		return this.importeCompra;
	}

	public void setImporteCompra(double importeCompra) {
		this.importeCompra = importeCompra;
	}

	public double getImporteIgv() {
		return this.importeIgv;
	}

	public void setImporteIgv(double importeIgv) {
		this.importeIgv = importeIgv;
	}

	public double getImporteIgvPorcentaje() {
		return this.importeIgvPorcentaje;
	}

	public void setImporteIgvPorcentaje(double importeIgvPorcentaje) {
		this.importeIgvPorcentaje = importeIgvPorcentaje;
	}

	public double getImporteIsc() {
		return this.importeIsc;
	}

	public void setImporteIsc(double importeIsc) {
		this.importeIsc = importeIsc;
	}

	public double getImportePagos() {
		return this.importePagos;
	}

	public void setImportePagos(double importePagos) {
		this.importePagos = importePagos;
	}

	public double getTipoCambio() {
		return this.tipoCambio;
	}

	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public ModalidadCompraVenta getModalidadCompraVenta() {
		return this.modalidadCompraVenta;
	}

	public void setModalidadCompraVenta(ModalidadCompraVenta modalidadCompraVenta) {
		this.modalidadCompraVenta = modalidadCompraVenta;
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

	public List<Com002> getCom002s() {
		return this.com002s;
	}

	public void setCom002s(List<Com002> com002s) {
		this.com002s = com002s;
	}

	public Com002 addCom002(Com002 com002) {
		getCom002s().add(com002);
		com002.setCom001(this);

		return com002;
	}

	public Com002 removeCom002(Com002 com002) {
		getCom002s().remove(com002);
		com002.setCom001(null);

		return com002;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setCom001(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setCom001(null);

		return ing001;
	}

}
