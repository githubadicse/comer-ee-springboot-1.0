package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ven001 database table.
 * 
 */
@Entity
@NamedQuery(name="Ven001.findAll", query="SELECT v FROM Ven001 v")
public class Ven001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idven001;

	@Column(name="descuentos_globales")
	private BigDecimal descuentosGlobales;

	@Column(name="fecha_emision")
	private Timestamp fechaEmision;

	@Column(name="importe_total_venta")
	private BigDecimal importeTotalVenta;

	@Column(name="\"numeroDocumento\"")
	private String numeroDocumento;

	@Column(name="\"serieDocumento\"")
	private String serieDocumento;

	@Column(name="sumatoria_igv")
	private BigDecimal sumatoriaIgv;

	@Column(name="sumatoria_isc")
	private BigDecimal sumatoriaIsc;

	@Column(name="sumatoria_otros_cargos")
	private BigDecimal sumatoriaOtrosCargos;

	@Column(name="sumatoria_otros_tributos")
	private BigDecimal sumatoriaOtrosTributos;

	@Column(name="total_descuentos")
	private BigDecimal totalDescuentos;

	@Column(name="total_valor_venta_exoneradas")
	private BigDecimal totalValorVentaExoneradas;

	@Column(name="total_valor_venta_grabadas")
	private BigDecimal totalValorVentaGrabadas;

	@Column(name="total_valor_venta_inafectas")
	private BigDecimal totalValorVentaInafectas;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="ven001")
	private List<Salida001> salida001s;

	//bi-directional many-to-one association to Aperturapuntoventa
	@ManyToOne
	@JoinColumn(name="idaperturapuntoventa")
	private Aperturapuntoventa aperturapuntoventa;

	//bi-directional many-to-one association to ModalidadVenta
	@ManyToOne
	@JoinColumn(name="id_modalidad_venta")
	private ModalidadVenta modalidadVenta;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="id_moneda")
	private Moneda moneda;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Proveedorclientedireccion
	@ManyToOne
	@JoinColumn(name="idproveedorclientedireccion")
	private Proveedorclientedireccion proveedorclientedireccion;

	//bi-directional many-to-one association to Tipodocumento
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private Tipodocumento tipodocumento;

	//bi-directional many-to-one association to Ven002
	@OneToMany(mappedBy="ven001", cascade={CascadeType.ALL})
	private List<Ven002> ven002s;

	public Ven001() {
	}

	public Integer getIdven001() {
		return this.idven001;
	}

	public void setIdven001(Integer idven001) {
		this.idven001 = idven001;
	}

	public BigDecimal getDescuentosGlobales() {
		return this.descuentosGlobales;
	}

	public void setDescuentosGlobales(BigDecimal descuentosGlobales) {
		this.descuentosGlobales = descuentosGlobales;
	}

	public Timestamp getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public BigDecimal getImporteTotalVenta() {
		return this.importeTotalVenta;
	}

	public void setImporteTotalVenta(BigDecimal importeTotalVenta) {
		this.importeTotalVenta = importeTotalVenta;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getSerieDocumento() {
		return this.serieDocumento;
	}

	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}

	public BigDecimal getSumatoriaIgv() {
		return this.sumatoriaIgv;
	}

	public void setSumatoriaIgv(BigDecimal sumatoriaIgv) {
		this.sumatoriaIgv = sumatoriaIgv;
	}

	public BigDecimal getSumatoriaIsc() {
		return this.sumatoriaIsc;
	}

	public void setSumatoriaIsc(BigDecimal sumatoriaIsc) {
		this.sumatoriaIsc = sumatoriaIsc;
	}

	public BigDecimal getSumatoriaOtrosCargos() {
		return this.sumatoriaOtrosCargos;
	}

	public void setSumatoriaOtrosCargos(BigDecimal sumatoriaOtrosCargos) {
		this.sumatoriaOtrosCargos = sumatoriaOtrosCargos;
	}

	public BigDecimal getSumatoriaOtrosTributos() {
		return this.sumatoriaOtrosTributos;
	}

	public void setSumatoriaOtrosTributos(BigDecimal sumatoriaOtrosTributos) {
		this.sumatoriaOtrosTributos = sumatoriaOtrosTributos;
	}

	public BigDecimal getTotalDescuentos() {
		return this.totalDescuentos;
	}

	public void setTotalDescuentos(BigDecimal totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public BigDecimal getTotalValorVentaExoneradas() {
		return this.totalValorVentaExoneradas;
	}

	public void setTotalValorVentaExoneradas(BigDecimal totalValorVentaExoneradas) {
		this.totalValorVentaExoneradas = totalValorVentaExoneradas;
	}

	public BigDecimal getTotalValorVentaGrabadas() {
		return this.totalValorVentaGrabadas;
	}

	public void setTotalValorVentaGrabadas(BigDecimal totalValorVentaGrabadas) {
		this.totalValorVentaGrabadas = totalValorVentaGrabadas;
	}

	public BigDecimal getTotalValorVentaInafectas() {
		return this.totalValorVentaInafectas;
	}

	public void setTotalValorVentaInafectas(BigDecimal totalValorVentaInafectas) {
		this.totalValorVentaInafectas = totalValorVentaInafectas;
	}

	public List<Salida001> getSalida001s() {
		return this.salida001s;
	}

	public void setSalida001s(List<Salida001> salida001s) {
		this.salida001s = salida001s;
	}

	public Salida001 addSalida001(Salida001 salida001) {
		getSalida001s().add(salida001);
		salida001.setVen001(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setVen001(null);

		return salida001;
	}

	public Aperturapuntoventa getAperturapuntoventa() {
		return this.aperturapuntoventa;
	}

	public void setAperturapuntoventa(Aperturapuntoventa aperturapuntoventa) {
		this.aperturapuntoventa = aperturapuntoventa;
	}

	public ModalidadVenta getModalidadVenta() {
		return this.modalidadVenta;
	}

	public void setModalidadVenta(ModalidadVenta modalidadVenta) {
		this.modalidadVenta = modalidadVenta;
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

	public Proveedorclientedireccion getProveedorclientedireccion() {
		return this.proveedorclientedireccion;
	}

	public void setProveedorclientedireccion(Proveedorclientedireccion proveedorclientedireccion) {
		this.proveedorclientedireccion = proveedorclientedireccion;
	}

	public Tipodocumento getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public List<Ven002> getVen002s() {
		return this.ven002s;
	}

	public void setVen002s(List<Ven002> ven002s) {
		this.ven002s = ven002s;
	}

	public Ven002 addVen002(Ven002 ven002) {
		getVen002s().add(ven002);
		ven002.setVen001(this);

		return ven002;
	}

	public Ven002 removeVen002(Ven002 ven002) {
		getVen002s().remove(ven002);
		ven002.setVen001(null);

		return ven002;
	}

}
