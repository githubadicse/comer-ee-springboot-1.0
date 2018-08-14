package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the traslado database table.
 * 
 */
@Entity
@NamedQuery(name="Traslado.findAll", query="SELECT t FROM Traslado t")
public class Traslado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idtraslado;

	private Integer estado;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="traslado")
	private List<Ing001> ing001s;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="traslado")
	private List<Salida001> salida001s;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	public Traslado() {
	}

	public Integer getIdtraslado() {
		return this.idtraslado;
	}

	public void setIdtraslado(Integer idtraslado) {
		this.idtraslado = idtraslado;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setTraslado(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setTraslado(null);

		return ing001;
	}

	public List<Salida001> getSalida001s() {
		return this.salida001s;
	}

	public void setSalida001s(List<Salida001> salida001s) {
		this.salida001s = salida001s;
	}

	public Salida001 addSalida001(Salida001 salida001) {
		getSalida001s().add(salida001);
		salida001.setTraslado(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setTraslado(null);

		return salida001;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

}