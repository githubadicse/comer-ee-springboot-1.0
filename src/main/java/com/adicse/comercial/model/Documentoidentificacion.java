package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the documentoidentificacion database table.
 * 
 */
@Entity
@NamedQuery(name="Documentoidentificacion.findAll", query="SELECT d FROM Documentoidentificacion d")
public class Documentoidentificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer iddocumentoidentificacion;

	private String dscdocumentoidentificacion;

	private Integer longitud;

	//bi-directional many-to-one association to Proveedorcliente
	@OneToMany(mappedBy="documentoidentificacion")
	private List<Proveedorcliente> proveedorclientes;

	public Documentoidentificacion() {
	}

	public Integer getIddocumentoidentificacion() {
		return this.iddocumentoidentificacion;
	}

	public void setIddocumentoidentificacion(Integer iddocumentoidentificacion) {
		this.iddocumentoidentificacion = iddocumentoidentificacion;
	}

	public String getDscdocumentoidentificacion() {
		return this.dscdocumentoidentificacion;
	}

	public void setDscdocumentoidentificacion(String dscdocumentoidentificacion) {
		this.dscdocumentoidentificacion = dscdocumentoidentificacion;
	}

	public Integer getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}

	public List<Proveedorcliente> getProveedorclientes() {
		return this.proveedorclientes;
	}

	public void setProveedorclientes(List<Proveedorcliente> proveedorclientes) {
		this.proveedorclientes = proveedorclientes;
	}

	public Proveedorcliente addProveedorcliente(Proveedorcliente proveedorcliente) {
		getProveedorclientes().add(proveedorcliente);
		proveedorcliente.setDocumentoidentificacion(this);

		return proveedorcliente;
	}

	public Proveedorcliente removeProveedorcliente(Proveedorcliente proveedorcliente) {
		getProveedorclientes().remove(proveedorcliente);
		proveedorcliente.setDocumentoidentificacion(null);

		return proveedorcliente;
	}

}