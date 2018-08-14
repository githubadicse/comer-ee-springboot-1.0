package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuarioalmacen database table.
 * 
 */
@Entity
@NamedQuery(name="Usuarioalmacen.findAll", query="SELECT u FROM Usuarioalmacen u")
public class Usuarioalmacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idusuarioalmacen;

	private Integer idusuario;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	public Usuarioalmacen() {
	}

	public String getIdusuarioalmacen() {
		return this.idusuarioalmacen;
	}

	public void setIdusuarioalmacen(String idusuarioalmacen) {
		this.idusuarioalmacen = idusuarioalmacen;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

}