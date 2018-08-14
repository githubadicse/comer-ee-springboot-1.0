package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ven002 database table.
 * 
 */
@Entity
@NamedQuery(name="Ven002.findAll", query="SELECT v FROM Ven002 v")
public class Ven002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idven002;

	private BigDecimal cantidad;

	@Column(name="descuento_unitario")
	private BigDecimal descuentoUnitario;

	private Integer idproducto;

	@Column(name="igv_porcentaje")
	private BigDecimal igvPorcentaje;

	@Column(name="igv_valor_unitario")
	private BigDecimal igvValorUnitario;

	@Column(name="isc_porcentaje")
	private BigDecimal iscPorcentaje;

	@Column(name="isc_valor_unitario")
	private BigDecimal iscValorUnitario;

	@Column(name="item_sub_total_igv")
	private BigDecimal itemSubTotalIgv;

	@Column(name="item_sub_total_isc")
	private BigDecimal itemSubTotalIsc;

	@Column(name="item_sub_total_valor_venta")
	private BigDecimal itemSubTotalValorVenta;

	@Column(name="item_total_venta")
	private BigDecimal itemTotalVenta;

	@Column(name="precio_unitario_venta")
	private BigDecimal precioUnitarioVenta;

	@Column(name="valor_bruto_unitario")
	private BigDecimal valorBrutoUnitario;

	@Column(name="valor_venta_unitario")
	private BigDecimal valorVentaUnitario;

	//bi-directional many-to-one association to Ven001
	@ManyToOne
	@JoinColumn(name="idven001")
	private Ven001 ven001;

	public Ven002() {
	}

	public String getIdven002() {
		return this.idven002;
	}

	public void setIdven002(String idven002) {
		this.idven002 = idven002;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getDescuentoUnitario() {
		return this.descuentoUnitario;
	}

	public void setDescuentoUnitario(BigDecimal descuentoUnitario) {
		this.descuentoUnitario = descuentoUnitario;
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public BigDecimal getIgvPorcentaje() {
		return this.igvPorcentaje;
	}

	public void setIgvPorcentaje(BigDecimal igvPorcentaje) {
		this.igvPorcentaje = igvPorcentaje;
	}

	public BigDecimal getIgvValorUnitario() {
		return this.igvValorUnitario;
	}

	public void setIgvValorUnitario(BigDecimal igvValorUnitario) {
		this.igvValorUnitario = igvValorUnitario;
	}

	public BigDecimal getIscPorcentaje() {
		return this.iscPorcentaje;
	}

	public void setIscPorcentaje(BigDecimal iscPorcentaje) {
		this.iscPorcentaje = iscPorcentaje;
	}

	public BigDecimal getIscValorUnitario() {
		return this.iscValorUnitario;
	}

	public void setIscValorUnitario(BigDecimal iscValorUnitario) {
		this.iscValorUnitario = iscValorUnitario;
	}

	public BigDecimal getItemSubTotalIgv() {
		return this.itemSubTotalIgv;
	}

	public void setItemSubTotalIgv(BigDecimal itemSubTotalIgv) {
		this.itemSubTotalIgv = itemSubTotalIgv;
	}

	public BigDecimal getItemSubTotalIsc() {
		return this.itemSubTotalIsc;
	}

	public void setItemSubTotalIsc(BigDecimal itemSubTotalIsc) {
		this.itemSubTotalIsc = itemSubTotalIsc;
	}

	public BigDecimal getItemSubTotalValorVenta() {
		return this.itemSubTotalValorVenta;
	}

	public void setItemSubTotalValorVenta(BigDecimal itemSubTotalValorVenta) {
		this.itemSubTotalValorVenta = itemSubTotalValorVenta;
	}

	public BigDecimal getItemTotalVenta() {
		return this.itemTotalVenta;
	}

	public void setItemTotalVenta(BigDecimal itemTotalVenta) {
		this.itemTotalVenta = itemTotalVenta;
	}

	public BigDecimal getPrecioUnitarioVenta() {
		return this.precioUnitarioVenta;
	}

	public void setPrecioUnitarioVenta(BigDecimal precioUnitarioVenta) {
		this.precioUnitarioVenta = precioUnitarioVenta;
	}

	public BigDecimal getValorBrutoUnitario() {
		return this.valorBrutoUnitario;
	}

	public void setValorBrutoUnitario(BigDecimal valorBrutoUnitario) {
		this.valorBrutoUnitario = valorBrutoUnitario;
	}

	public BigDecimal getValorVentaUnitario() {
		return this.valorVentaUnitario;
	}

	public void setValorVentaUnitario(BigDecimal valorVentaUnitario) {
		this.valorVentaUnitario = valorVentaUnitario;
	}

	public Ven001 getVen001() {
		return this.ven001;
	}

	public void setVen001(Ven001 ven001) {
		this.ven001 = ven001;
	}

}