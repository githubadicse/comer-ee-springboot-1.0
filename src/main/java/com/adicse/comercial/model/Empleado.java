package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idempleado;

	private Boolean activo;

	private String direccion;

	private String dni;

	private String email;

	@Column(name="fecha_registro_systema")
	private Timestamp fechaRegistroSystema;

	@Column(name="fecha_registro_systema_modifica")
	private Timestamp fechaRegistroSystemaModifica;

	private Timestamp fechaingreso;

	private Timestamp fechanacimiento;

	private Integer idusuario;

	@Column(name="idusuario_modifica")
	private Integer idusuarioModifica;

	private String nomempleado;

	private String telefono;

	//bi-directional many-to-one association to Aperturapuntoventa
	@OneToMany(mappedBy="empleado")
	private List<Aperturapuntoventa> aperturapuntoventas;

	//bi-directional many-to-one association to Filial
	@ManyToOne
	@JoinColumn(name="id_filial")
	private Filial filial;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="empleado")
	private List<Ing001> ing001s;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="empleado")
	private List<Salida001> salida001s;

	//bi-directional many-to-one association to Usuarioempleado
	@OneToMany(mappedBy="empleado")
	private List<Usuarioempleado> usuarioempleados;

	public Empleado() {
	}

	public Integer getIdempleado() {
		return this.idempleado;
	}

	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFechaRegistroSystema() {
		return this.fechaRegistroSystema;
	}

	public void setFechaRegistroSystema(Timestamp fechaRegistroSystema) {
		this.fechaRegistroSystema = fechaRegistroSystema;
	}

	public Timestamp getFechaRegistroSystemaModifica() {
		return this.fechaRegistroSystemaModifica;
	}

	public void setFechaRegistroSystemaModifica(Timestamp fechaRegistroSystemaModifica) {
		this.fechaRegistroSystemaModifica = fechaRegistroSystemaModifica;
	}

	public Timestamp getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Timestamp fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Timestamp getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Timestamp fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getIdusuarioModifica() {
		return this.idusuarioModifica;
	}

	public void setIdusuarioModifica(Integer idusuarioModifica) {
		this.idusuarioModifica = idusuarioModifica;
	}

	public String getNomempleado() {
		return this.nomempleado;
	}

	public void setNomempleado(String nomempleado) {
		this.nomempleado = nomempleado;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Aperturapuntoventa> getAperturapuntoventas() {
		return this.aperturapuntoventas;
	}

	public void setAperturapuntoventas(List<Aperturapuntoventa> aperturapuntoventas) {
		this.aperturapuntoventas = aperturapuntoventas;
	}

	public Aperturapuntoventa addAperturapuntoventa(Aperturapuntoventa aperturapuntoventa) {
		getAperturapuntoventas().add(aperturapuntoventa);
		aperturapuntoventa.setEmpleado(this);

		return aperturapuntoventa;
	}

	public Aperturapuntoventa removeAperturapuntoventa(Aperturapuntoventa aperturapuntoventa) {
		getAperturapuntoventas().remove(aperturapuntoventa);
		aperturapuntoventa.setEmpleado(null);

		return aperturapuntoventa;
	}

	public Filial getFilial() {
		return this.filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setEmpleado(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setEmpleado(null);

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
		salida001.setEmpleado(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setEmpleado(null);

		return salida001;
	}

	public List<Usuarioempleado> getUsuarioempleados() {
		return this.usuarioempleados;
	}

	public void setUsuarioempleados(List<Usuarioempleado> usuarioempleados) {
		this.usuarioempleados = usuarioempleados;
	}

	public Usuarioempleado addUsuarioempleado(Usuarioempleado usuarioempleado) {
		getUsuarioempleados().add(usuarioempleado);
		usuarioempleado.setEmpleado(this);

		return usuarioempleado;
	}

	public Usuarioempleado removeUsuarioempleado(Usuarioempleado usuarioempleado) {
		getUsuarioempleados().remove(usuarioempleado);
		usuarioempleado.setEmpleado(null);

		return usuarioempleado;
	}

}