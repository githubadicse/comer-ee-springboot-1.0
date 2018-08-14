package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedorclientedireccion database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedorclientedireccion.findAll", query="SELECT p FROM Proveedorclientedireccion p")
public class Proveedorclientedireccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idproveedorclientedireccion;

	private String direccion;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="proveedorclientedireccion")
	private List<Ven001> ven001s;

	public Proveedorclientedireccion() {
	}

	public String getIdproveedorclientedireccion() {
		return this.idproveedorclientedireccion;
	}

	public void setIdproveedorclientedireccion(String idproveedorclientedireccion) {
		this.idproveedorclientedireccion = idproveedorclientedireccion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setProveedorclientedireccion(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setProveedorclientedireccion(null);

		return ven001;
	}

}