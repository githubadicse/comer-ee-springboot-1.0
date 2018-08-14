package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the moneda database table.
 * 
 */
@Entity
@NamedQuery(name="Moneda.findAll", query="SELECT m FROM Moneda m")
public class Moneda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_moneda")
	private Integer idMoneda;

	@Column(name="dsc_moneda")
	private String dscMoneda;

	//bi-directional many-to-one association to Com001
	@OneToMany(mappedBy="moneda")
	private List<Com001> com001s;

	//bi-directional many-to-one association to Gasto001
	@OneToMany(mappedBy="moneda")
	private List<Gasto001> gasto001s;

	//bi-directional many-to-one association to Lineacredito
	@OneToMany(mappedBy="moneda")
	private List<Lineacredito> lineacreditos;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="moneda")
	private List<Producto> productos;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="moneda")
	private List<Ven001> ven001s;

	public Moneda() {
	}

	public Integer getIdMoneda() {
		return this.idMoneda;
	}

	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}

	public String getDscMoneda() {
		return this.dscMoneda;
	}

	public void setDscMoneda(String dscMoneda) {
		this.dscMoneda = dscMoneda;
	}

	public List<Com001> getCom001s() {
		return this.com001s;
	}

	public void setCom001s(List<Com001> com001s) {
		this.com001s = com001s;
	}

	public Com001 addCom001(Com001 com001) {
		getCom001s().add(com001);
		com001.setMoneda(this);

		return com001;
	}

	public Com001 removeCom001(Com001 com001) {
		getCom001s().remove(com001);
		com001.setMoneda(null);

		return com001;
	}

	public List<Gasto001> getGasto001s() {
		return this.gasto001s;
	}

	public void setGasto001s(List<Gasto001> gasto001s) {
		this.gasto001s = gasto001s;
	}

	public Gasto001 addGasto001(Gasto001 gasto001) {
		getGasto001s().add(gasto001);
		gasto001.setMoneda(this);

		return gasto001;
	}

	public Gasto001 removeGasto001(Gasto001 gasto001) {
		getGasto001s().remove(gasto001);
		gasto001.setMoneda(null);

		return gasto001;
	}

	public List<Lineacredito> getLineacreditos() {
		return this.lineacreditos;
	}

	public void setLineacreditos(List<Lineacredito> lineacreditos) {
		this.lineacreditos = lineacreditos;
	}

	public Lineacredito addLineacredito(Lineacredito lineacredito) {
		getLineacreditos().add(lineacredito);
		lineacredito.setMoneda(this);

		return lineacredito;
	}

	public Lineacredito removeLineacredito(Lineacredito lineacredito) {
		getLineacreditos().remove(lineacredito);
		lineacredito.setMoneda(null);

		return lineacredito;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setMoneda(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setMoneda(null);

		return producto;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setMoneda(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setMoneda(null);

		return ven001;
	}

}