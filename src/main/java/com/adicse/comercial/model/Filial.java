package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the filial database table.
 * 
 */
@Entity
@NamedQuery(name="Filial.findAll", query="SELECT f FROM Filial f")
public class Filial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idfilial;

	private String direccion;

	private String dnirepresentantelegal;

	private String dscfilial;

	private String razonsocial;

	private String representantelegal;

	private String ruc;

	private String telefonos;

	//bi-directional many-to-one association to Almacen
	@OneToMany(mappedBy="filial")
	private List<Almacen> almacens;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="filial")
	private List<Empleado> empleados;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="filial")
	private List<Usuario> usuarios;

	public Filial() {
	}

	public Integer getIdfilial() {
		return this.idfilial;
	}

	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDnirepresentantelegal() {
		return this.dnirepresentantelegal;
	}

	public void setDnirepresentantelegal(String dnirepresentantelegal) {
		this.dnirepresentantelegal = dnirepresentantelegal;
	}

	public String getDscfilial() {
		return this.dscfilial;
	}

	public void setDscfilial(String dscfilial) {
		this.dscfilial = dscfilial;
	}

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getRepresentantelegal() {
		return this.representantelegal;
	}

	public void setRepresentantelegal(String representantelegal) {
		this.representantelegal = representantelegal;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public List<Almacen> getAlmacens() {
		return this.almacens;
	}

	public void setAlmacens(List<Almacen> almacens) {
		this.almacens = almacens;
	}

	public Almacen addAlmacen(Almacen almacen) {
		getAlmacens().add(almacen);
		almacen.setFilial(this);

		return almacen;
	}

	public Almacen removeAlmacen(Almacen almacen) {
		getAlmacens().remove(almacen);
		almacen.setFilial(null);

		return almacen;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setFilial(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setFilial(null);

		return empleado;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setFilial(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setFilial(null);

		return usuario;
	}

}