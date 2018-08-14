package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the piking database table.
 * 
 */
@Entity
@NamedQuery(name="Piking.findAll", query="SELECT p FROM Piking p")
public class Piking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_piking")
	private String idPiking;

	private Integer cantidad;

	//bi-directional many-to-one association to CatalogoLote
	@ManyToOne
	@JoinColumn(name="id_catalogo_lote")
	private CatalogoLote catalogoLote;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_001")
	private RequerimientoVolumen001 requerimientoVolumen001;

	//bi-directional many-to-one association to VolumenConvertidoEnvace
	@ManyToOne
	@JoinColumn(name="id_volumen_convertido_envace")
	private VolumenConvertidoEnvace volumenConvertidoEnvace;

	public Piking() {
	}

	public String getIdPiking() {
		return this.idPiking;
	}

	public void setIdPiking(String idPiking) {
		this.idPiking = idPiking;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public CatalogoLote getCatalogoLote() {
		return this.catalogoLote;
	}

	public void setCatalogoLote(CatalogoLote catalogoLote) {
		this.catalogoLote = catalogoLote;
	}

	public RequerimientoVolumen001 getRequerimientoVolumen001() {
		return this.requerimientoVolumen001;
	}

	public void setRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		this.requerimientoVolumen001 = requerimientoVolumen001;
	}

	public VolumenConvertidoEnvace getVolumenConvertidoEnvace() {
		return this.volumenConvertidoEnvace;
	}

	public void setVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		this.volumenConvertidoEnvace = volumenConvertidoEnvace;
	}

}