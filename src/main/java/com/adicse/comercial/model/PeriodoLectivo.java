package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the periodo_lectivo database table.
 * 
 */
@Entity
@Table(name="periodo_lectivo")
@NamedQuery(name="PeriodoLectivo.findAll", query="SELECT p FROM PeriodoLectivo p")
public class PeriodoLectivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_periodo_lectivo")
	private Integer idPeriodoLectivo;

	private Integer anno;

	@Column(name="numero_entrega")
	private Integer numeroEntrega;

	public PeriodoLectivo() {
	}

	public Integer getIdPeriodoLectivo() {
		return this.idPeriodoLectivo;
	}

	public void setIdPeriodoLectivo(Integer idPeriodoLectivo) {
		this.idPeriodoLectivo = idPeriodoLectivo;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getNumeroEntrega() {
		return this.numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

}