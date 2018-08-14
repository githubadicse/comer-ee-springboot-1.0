package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motivosalida database table.
 * 
 */
@Entity
@NamedQuery(name="Motivosalida.findAll", query="SELECT m FROM Motivosalida m")
public class Motivosalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idmotivosalida;

	private String dscmotivosalida;

	private Integer flagtraslado;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="motivosalida")
	private List<Salida001> salida001s;

	public Motivosalida() {
	}

	public Integer getIdmotivosalida() {
		return this.idmotivosalida;
	}

	public void setIdmotivosalida(Integer idmotivosalida) {
		this.idmotivosalida = idmotivosalida;
	}

	public String getDscmotivosalida() {
		return this.dscmotivosalida;
	}

	public void setDscmotivosalida(String dscmotivosalida) {
		this.dscmotivosalida = dscmotivosalida;
	}

	public Integer getFlagtraslado() {
		return this.flagtraslado;
	}

	public void setFlagtraslado(Integer flagtraslado) {
		this.flagtraslado = flagtraslado;
	}

	public List<Salida001> getSalida001s() {
		return this.salida001s;
	}

	public void setSalida001s(List<Salida001> salida001s) {
		this.salida001s = salida001s;
	}

	public Salida001 addSalida001(Salida001 salida001) {
		getSalida001s().add(salida001);
		salida001.setMotivosalida(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setMotivosalida(null);

		return salida001;
	}

}