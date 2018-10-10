package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the com002 database table.
 * 


/**
 * The persistent class for the com002 database table.
 * 
 */
@Entity
@NamedQuery(name="Com002.findAll", query="SELECT c FROM Com002 c")
public class Com002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idcom002;

	private double cantidad;

	@Column(name="importe_bruto")
	private double importeBruto;

	@Column(name="importe_compra")
	private double importeCompra;

	@Column(name="importe_descuento")
	private double importeDescuento;

	@Column(name="importe_flete")
	private double importeFlete;

	@Column(name="importe_igv")
	private double importeIgv;

	@Column(name="importe_isc")
	private double importeIsc;

	@Column(name="importe_porcentaje_igv")
	private double importePorcentajeIgv;

	@Column(name="importe_total_costo_unitario")
	private double importeTotalCostoUnitario;

	@Column(name="importe_unitario")
	private double importeUnitario;

	@Column(name="importe_valor_compra")
	private double importeValorCompra;

	//bi-directional many-to-one association to Com001
	@ManyToOne
	@JoinColumn(name="idcom001")
	private Com001 com001;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Com002() {
	}

	public String getIdcom002() {
		return this.idcom002;
	}

	public void setIdcom002(String idcom002) {
		this.idcom002 = idcom002;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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

	public double getImporteDescuento() {
		return this.importeDescuento;
	}

	public void setImporteDescuento(double importeDescuento) {
		this.importeDescuento = importeDescuento;
	}

	public double getImporteFlete() {
		return this.importeFlete;
	}

	public void setImporteFlete(double importeFlete) {
		this.importeFlete = importeFlete;
	}

	public double getImporteIgv() {
		return this.importeIgv;
	}

	public void setImporteIgv(double importeIgv) {
		this.importeIgv = importeIgv;
	}

	public double getImporteIsc() {
		return this.importeIsc;
	}

	public void setImporteIsc(double importeIsc) {
		this.importeIsc = importeIsc;
	}

	public double getImportePorcentajeIgv() {
		return this.importePorcentajeIgv;
	}

	public void setImportePorcentajeIgv(double importePorcentajeIgv) {
		this.importePorcentajeIgv = importePorcentajeIgv;
	}

	public double getImporteTotalCostoUnitario() {
		return this.importeTotalCostoUnitario;
	}

	public void setImporteTotalCostoUnitario(double importeTotalCostoUnitario) {
		this.importeTotalCostoUnitario = importeTotalCostoUnitario;
	}

	public double getImporteUnitario() {
		return this.importeUnitario;
	}

	public void setImporteUnitario(double importeUnitario) {
		this.importeUnitario = importeUnitario;
	}

	public double getImporteValorCompra() {
		return this.importeValorCompra;
	}

	public void setImporteValorCompra(double importeValorCompra) {
		this.importeValorCompra = importeValorCompra;
	}

	public Com001 getCom001() {
		return this.com001;
	}

	public void setCom001(Com001 com001) {
		this.com001 = com001;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}