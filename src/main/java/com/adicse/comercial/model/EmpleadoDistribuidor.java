package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empleado_distribuidor database table.
 * 
 */
@Entity
@Table(name="empleado_distribuidor")
@NamedQuery(name="EmpleadoDistribuidor.findAll", query="SELECT e FROM EmpleadoDistribuidor e")
public class EmpleadoDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_empleado_distribuidor")
	private String idEmpleadoDistribuidor;

	@Column(name="celular_asignado")
	private String celularAsignado;

	private String dni;

	private String email;

	private String imei;

	private String nombres;

	private String telefonos;

	//bi-directional many-to-one association to RutaDistribucion
	@OneToMany(mappedBy="empleadoDistribuidor")
	private List<RutaDistribucion> rutaDistribucions;

	public EmpleadoDistribuidor() {
	}

	public String getIdEmpleadoDistribuidor() {
		return this.idEmpleadoDistribuidor;
	}

	public void setIdEmpleadoDistribuidor(String idEmpleadoDistribuidor) {
		this.idEmpleadoDistribuidor = idEmpleadoDistribuidor;
	}

	public String getCelularAsignado() {
		return this.celularAsignado;
	}

	public void setCelularAsignado(String celularAsignado) {
		this.celularAsignado = celularAsignado;
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

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public List<RutaDistribucion> getRutaDistribucions() {
		return this.rutaDistribucions;
	}

	public void setRutaDistribucions(List<RutaDistribucion> rutaDistribucions) {
		this.rutaDistribucions = rutaDistribucions;
	}

	public RutaDistribucion addRutaDistribucion(RutaDistribucion rutaDistribucion) {
		getRutaDistribucions().add(rutaDistribucion);
		rutaDistribucion.setEmpleadoDistribuidor(this);

		return rutaDistribucion;
	}

	public RutaDistribucion removeRutaDistribucion(RutaDistribucion rutaDistribucion) {
		getRutaDistribucions().remove(rutaDistribucion);
		rutaDistribucion.setEmpleadoDistribuidor(null);

		return rutaDistribucion;
	}

}