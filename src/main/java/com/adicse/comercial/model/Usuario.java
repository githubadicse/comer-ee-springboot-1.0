package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idusuario;

	private Integer activo;

	private String clave;

	private String dni;

	private String login;

	private String nomusuario;

	private Integer status;

	//bi-directional many-to-one association to Rol
	@OneToMany(mappedBy="usuario")
	private List<Rol> rols;

	//bi-directional many-to-one association to Filial
	@ManyToOne
	@JoinColumn(name="idfilial")
	private Filial filial;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="idperfil")
	private Perfil perfil;

	//bi-directional many-to-one association to Usuarioempleado
	@OneToMany(mappedBy="usuario", cascade={CascadeType.ALL})
	private List<Usuarioempleado> usuarioempleados;

	public Usuario() {
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomusuario() {
		return this.nomusuario;
	}

	public void setNomusuario(String nomusuario) {
		this.nomusuario = nomusuario;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Rol addRol(Rol rol) {
		getRols().add(rol);
		rol.setUsuario(this);

		return rol;
	}

	public Rol removeRol(Rol rol) {
		getRols().remove(rol);
		rol.setUsuario(null);

		return rol;
	}

	public Filial getFilial() {
		return this.filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Usuarioempleado> getUsuarioempleados() {
		return this.usuarioempleados;
	}

	public void setUsuarioempleados(List<Usuarioempleado> usuarioempleados) {
		this.usuarioempleados = usuarioempleados;
	}

	public Usuarioempleado addUsuarioempleado(Usuarioempleado usuarioempleado) {
		getUsuarioempleados().add(usuarioempleado);
		usuarioempleado.setUsuario(this);

		return usuarioempleado;
	}

	public Usuarioempleado removeUsuarioempleado(Usuarioempleado usuarioempleado) {
		getUsuarioempleados().remove(usuarioempleado);
		usuarioempleado.setUsuario(null);

		return usuarioempleado;
	}

}
