package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ubigeo database table.
 * 
 */
@Entity
@NamedQuery(name="Ubigeo.findAll", query="SELECT u FROM Ubigeo u")
public class Ubigeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idubigeo;

	@Column(name="nombre_departamento")
	private String nombreDepartamento;

	@Column(name="nombre_distrito")
	private String nombreDistrito;

	@Column(name="nombre_provincia")
	private String nombreProvincia;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="ubigeo", cascade={CascadeType.ALL})
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	public Ubigeo() {
	}

	public String getIdubigeo() {
		return this.idubigeo;
	}

	public void setIdubigeo(String idubigeo) {
		this.idubigeo = idubigeo;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getNombreDistrito() {
		return this.nombreDistrito;
	}

	public void setNombreDistrito(String nombreDistrito) {
		this.nombreDistrito = nombreDistrito;
	}

	public String getNombreProvincia() {
		return this.nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public List<RequerimientoVolumen001> getRequerimientoVolumen001s() {
		return this.requerimientoVolumen001s;
	}

	public void setRequerimientoVolumen001s(List<RequerimientoVolumen001> requerimientoVolumen001s) {
		this.requerimientoVolumen001s = requerimientoVolumen001s;
	}

	public RequerimientoVolumen001 addRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().add(requerimientoVolumen001);
		requerimientoVolumen001.setUbigeo(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setUbigeo(null);

		return requerimientoVolumen001;
	}

}
