package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the codigomodular_iinstitucion_educativa database table.
 * 
 */
@Entity
@Table(name="codigomodular_iinstitucion_educativa")
@NamedQuery(name="CodigomodularIinstitucionEducativa.findAll", query="SELECT c FROM CodigomodularIinstitucionEducativa c")
public class CodigomodularIinstitucionEducativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_modular")
	private String codigoModular;

	@Column(name="direccion_institucion_educativa")
	private String direccionInstitucionEducativa;

	private double latitud;

	private double longitud;

	@Column(name="nombre_institucion_educativa")
	private String nombreInstitucionEducativa;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="codigomodularIinstitucionEducativa")
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	public CodigomodularIinstitucionEducativa() {
	}

	public String getCodigoModular() {
		return this.codigoModular;
	}

	public void setCodigoModular(String codigoModular) {
		this.codigoModular = codigoModular;
	}

	public String getDireccionInstitucionEducativa() {
		return this.direccionInstitucionEducativa;
	}

	public void setDireccionInstitucionEducativa(String direccionInstitucionEducativa) {
		this.direccionInstitucionEducativa = direccionInstitucionEducativa;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getNombreInstitucionEducativa() {
		return this.nombreInstitucionEducativa;
	}

	public void setNombreInstitucionEducativa(String nombreInstitucionEducativa) {
		this.nombreInstitucionEducativa = nombreInstitucionEducativa;
	}

	public List<RequerimientoVolumen001> getRequerimientoVolumen001s() {
		return this.requerimientoVolumen001s;
	}

	public void setRequerimientoVolumen001s(List<RequerimientoVolumen001> requerimientoVolumen001s) {
		this.requerimientoVolumen001s = requerimientoVolumen001s;
	}

	public RequerimientoVolumen001 addRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().add(requerimientoVolumen001);
		requerimientoVolumen001.setCodigomodularIinstitucionEducativa(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setCodigomodularIinstitucionEducativa(null);

		return requerimientoVolumen001;
	}

}