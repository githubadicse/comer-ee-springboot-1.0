package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the com002 database table.
 * 
 */
@Entity
@NamedQuery(name="Com002.findAll", query="SELECT c FROM Com002 c")
public class Com002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idcom002;

	private double cantidad;

	private Integer idproducto;

	private double preciounitario;

	private double total;

	//bi-directional many-to-one association to Com001
	@ManyToOne
	@JoinColumn(name="idcom001")
	private Com001 com001;

	public Com002() {
	}

	public Integer getIdcom002() {
		return this.idcom002;
	}

	public void setIdcom002(Integer idcom002) {
		this.idcom002 = idcom002;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public double getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Com001 getCom001() {
		return this.com001;
	}

	public void setCom001(Com001 com001) {
		this.com001 = com001;
	}

}