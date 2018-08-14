package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuarioempleado database table.
 * 
 */
@Entity
@NamedQuery(name="Usuarioempleado.findAll", query="SELECT u FROM Usuarioempleado u")
public class Usuarioempleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idusuarioempleado;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="idempleado")
	private Empleado empleado;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Usuarioempleado() {
	}

	public String getIdusuarioempleado() {
		return this.idusuarioempleado;
	}

	public void setIdusuarioempleado(String idusuarioempleado) {
		this.idusuarioempleado = idusuarioempleado;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}