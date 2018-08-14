package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the salida002kardex database table.
 * 
 */
@Entity
@NamedQuery(name="Salida002kardex.findAll", query="SELECT s FROM Salida002kardex s")
public class Salida002kardex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idsalida002kardex;

	//bi-directional many-to-one association to Kardex
	@ManyToOne
	@JoinColumn(name="idkardex")
	private Kardex kardex;

	//bi-directional many-to-one association to Salida002
	@ManyToOne
	@JoinColumn(name="idsalida002")
	private Salida002 salida002;

	public Salida002kardex() {
	}

	public String getIdsalida002kardex() {
		return this.idsalida002kardex;
	}

	public void setIdsalida002kardex(String idsalida002kardex) {
		this.idsalida002kardex = idsalida002kardex;
	}

	public Kardex getKardex() {
		return this.kardex;
	}

	public void setKardex(Kardex kardex) {
		this.kardex = kardex;
	}

	public Salida002 getSalida002() {
		return this.salida002;
	}

	public void setSalida002(Salida002 salida002) {
		this.salida002 = salida002;
	}

}