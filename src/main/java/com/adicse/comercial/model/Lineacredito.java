package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the lineacredito database table.
 * 
 */
@Entity
@NamedQuery(name="Lineacredito.findAll", query="SELECT l FROM Lineacredito l")
public class Lineacredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idlineacredito;

	private BigDecimal totalconsumo;

	private BigDecimal totallinea;

	private BigDecimal totalsaldo;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="idmoneda")
	private Moneda moneda;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	public Lineacredito() {
	}

	public Integer getIdlineacredito() {
		return this.idlineacredito;
	}

	public void setIdlineacredito(Integer idlineacredito) {
		this.idlineacredito = idlineacredito;
	}

	public BigDecimal getTotalconsumo() {
		return this.totalconsumo;
	}

	public void setTotalconsumo(BigDecimal totalconsumo) {
		this.totalconsumo = totalconsumo;
	}

	public BigDecimal getTotallinea() {
		return this.totallinea;
	}

	public void setTotallinea(BigDecimal totallinea) {
		this.totallinea = totallinea;
	}

	public BigDecimal getTotalsaldo() {
		return this.totalsaldo;
	}

	public void setTotalsaldo(BigDecimal totalsaldo) {
		this.totalsaldo = totalsaldo;
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

}