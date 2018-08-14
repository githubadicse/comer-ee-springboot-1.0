package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ruta_distribucion_detalle database table.
 * 
 */
@Entity
@Table(name="ruta_distribucion_detalle")
@NamedQuery(name="RutaDistribucionDetalle.findAll", query="SELECT r FROM RutaDistribucionDetalle r")
public class RutaDistribucionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ruta_distribucion_detalle")
	private String idRutaDistribucionDetalle;

	private Integer orden;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_001")
	private RequerimientoVolumen001 requerimientoVolumen001;

	//bi-directional many-to-one association to RutaDistribucion
	@ManyToOne
	@JoinColumn(name="id_ruta_distribucion")
	private RutaDistribucion rutaDistribucion;

	public RutaDistribucionDetalle() {
	}

	public String getIdRutaDistribucionDetalle() {
		return this.idRutaDistribucionDetalle;
	}

	public void setIdRutaDistribucionDetalle(String idRutaDistribucionDetalle) {
		this.idRutaDistribucionDetalle = idRutaDistribucionDetalle;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public RequerimientoVolumen001 getRequerimientoVolumen001() {
		return this.requerimientoVolumen001;
	}

	public void setRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		this.requerimientoVolumen001 = requerimientoVolumen001;
	}

	public RutaDistribucion getRutaDistribucion() {
		return this.rutaDistribucion;
	}

	public void setRutaDistribucion(RutaDistribucion rutaDistribucion) {
		this.rutaDistribucion = rutaDistribucion;
	}

}