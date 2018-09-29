package com.adicse.comercial.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat (pattern ="dd/MM/yyyy hh:mm:ss")
	private Timestamp fechaRegistroSystema;

	@Column(name="fecha_registro_systema_modifica")
	@JsonFormat (pattern ="dd/MM/yyyy hh:mm:ss")
	private Timestamp fechaRegistroSystemaModifica;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	private Date fechaingreso;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	private Date fechanacimiento;

	@Column(name="idusuario_crea")
	private Integer idusuarioCrea;

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

	public Date getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Integer getIdusuarioCrea() {
		return this.idusuarioCrea;
	}

	public void setIdusuarioCrea(Integer idusuarioCrea) {
		this.idusuarioCrea = idusuarioCrea;
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

}
