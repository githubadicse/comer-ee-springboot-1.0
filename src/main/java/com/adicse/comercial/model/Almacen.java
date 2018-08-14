package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the almacen database table.
 * 
 */
@Entity
@NamedQuery(name="Almacen.findAll", query="SELECT a FROM Almacen a")
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idalmacen;

	private String direccion;

	private String dscalmacen;

	//bi-directional many-to-one association to Cierremensual
	@OneToMany(mappedBy="almacen")
	private List<Cierremensual> cierremensuals;

	//bi-directional many-to-one association to Com001
	@OneToMany(mappedBy="almacen")
	private List<Com001> com001s;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="almacen")
	private List<Ing001> ing001s;

	//bi-directional many-to-one association to Periodoalmacen
	@OneToMany(mappedBy="almacen")
	private List<Periodoalmacen> periodoalmacens;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="almacen")
	private List<Salida001> salida001s;

	//bi-directional many-to-one association to Stockactual
	@OneToMany(mappedBy="almacen")
	private List<Stockactual> stockactuals;

	//bi-directional many-to-one association to Traslado
	@OneToMany(mappedBy="almacen")
	private List<Traslado> traslados;

	//bi-directional many-to-one association to Usuarioalmacen
	@OneToMany(mappedBy="almacen")
	private List<Usuarioalmacen> usuarioalmacens;

	public Almacen() {
	}

	public Integer getIdalmacen() {
		return this.idalmacen;
	}

	public void setIdalmacen(Integer idalmacen) {
		this.idalmacen = idalmacen;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDscalmacen() {
		return this.dscalmacen;
	}

	public void setDscalmacen(String dscalmacen) {
		this.dscalmacen = dscalmacen;
	}

	public List<Cierremensual> getCierremensuals() {
		return this.cierremensuals;
	}

	public void setCierremensuals(List<Cierremensual> cierremensuals) {
		this.cierremensuals = cierremensuals;
	}

	public Cierremensual addCierremensual(Cierremensual cierremensual) {
		getCierremensuals().add(cierremensual);
		cierremensual.setAlmacen(this);

		return cierremensual;
	}

	public Cierremensual removeCierremensual(Cierremensual cierremensual) {
		getCierremensuals().remove(cierremensual);
		cierremensual.setAlmacen(null);

		return cierremensual;
	}

	public List<Com001> getCom001s() {
		return this.com001s;
	}

	public void setCom001s(List<Com001> com001s) {
		this.com001s = com001s;
	}

	public Com001 addCom001(Com001 com001) {
		getCom001s().add(com001);
		com001.setAlmacen(this);

		return com001;
	}

	public Com001 removeCom001(Com001 com001) {
		getCom001s().remove(com001);
		com001.setAlmacen(null);

		return com001;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setAlmacen(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setAlmacen(null);

		return ing001;
	}

	public List<Periodoalmacen> getPeriodoalmacens() {
		return this.periodoalmacens;
	}

	public void setPeriodoalmacens(List<Periodoalmacen> periodoalmacens) {
		this.periodoalmacens = periodoalmacens;
	}

	public Periodoalmacen addPeriodoalmacen(Periodoalmacen periodoalmacen) {
		getPeriodoalmacens().add(periodoalmacen);
		periodoalmacen.setAlmacen(this);

		return periodoalmacen;
	}

	public Periodoalmacen removePeriodoalmacen(Periodoalmacen periodoalmacen) {
		getPeriodoalmacens().remove(periodoalmacen);
		periodoalmacen.setAlmacen(null);

		return periodoalmacen;
	}

	public List<Salida001> getSalida001s() {
		return this.salida001s;
	}

	public void setSalida001s(List<Salida001> salida001s) {
		this.salida001s = salida001s;
	}

	public Salida001 addSalida001(Salida001 salida001) {
		getSalida001s().add(salida001);
		salida001.setAlmacen(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setAlmacen(null);

		return salida001;
	}

	public List<Stockactual> getStockactuals() {
		return this.stockactuals;
	}

	public void setStockactuals(List<Stockactual> stockactuals) {
		this.stockactuals = stockactuals;
	}

	public Stockactual addStockactual(Stockactual stockactual) {
		getStockactuals().add(stockactual);
		stockactual.setAlmacen(this);

		return stockactual;
	}

	public Stockactual removeStockactual(Stockactual stockactual) {
		getStockactuals().remove(stockactual);
		stockactual.setAlmacen(null);

		return stockactual;
	}

	public List<Traslado> getTraslados() {
		return this.traslados;
	}

	public void setTraslados(List<Traslado> traslados) {
		this.traslados = traslados;
	}

	public Traslado addTraslado(Traslado traslado) {
		getTraslados().add(traslado);
		traslado.setAlmacen(this);

		return traslado;
	}

	public Traslado removeTraslado(Traslado traslado) {
		getTraslados().remove(traslado);
		traslado.setAlmacen(null);

		return traslado;
	}

	public List<Usuarioalmacen> getUsuarioalmacens() {
		return this.usuarioalmacens;
	}

	public void setUsuarioalmacens(List<Usuarioalmacen> usuarioalmacens) {
		this.usuarioalmacens = usuarioalmacens;
	}

	public Usuarioalmacen addUsuarioalmacen(Usuarioalmacen usuarioalmacen) {
		getUsuarioalmacens().add(usuarioalmacen);
		usuarioalmacen.setAlmacen(this);

		return usuarioalmacen;
	}

	public Usuarioalmacen removeUsuarioalmacen(Usuarioalmacen usuarioalmacen) {
		getUsuarioalmacens().remove(usuarioalmacen);
		usuarioalmacen.setAlmacen(null);

		return usuarioalmacen;
	}

}