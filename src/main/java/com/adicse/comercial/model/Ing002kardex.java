package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ing002kardex database table.
 * 
 */
@Entity
@NamedQuery(name="Ing002kardex.findAll", query="SELECT i FROM Ing002kardex i")
public class Ing002kardex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String iding002kardex;

	//bi-directional many-to-one association to Ing002
	@ManyToOne
	@JoinColumn(name="iding002")
	private Ing002 ing002;

	//bi-directional many-to-one association to Kardex
	@ManyToOne
	@JoinColumn(name="idkardex")
	private Kardex kardex;

	public Ing002kardex() {
	}

	public String getIding002kardex() {
		return this.iding002kardex;
	}

	public void setIding002kardex(String iding002kardex) {
		this.iding002kardex = iding002kardex;
	}

	public Ing002 getIng002() {
		return this.ing002;
	}

	public void setIng002(Ing002 ing002) {
		this.ing002 = ing002;
	}

	public Kardex getKardex() {
		return this.kardex;
	}

	public void setKardex(Kardex kardex) {
		this.kardex = kardex;
	}

}