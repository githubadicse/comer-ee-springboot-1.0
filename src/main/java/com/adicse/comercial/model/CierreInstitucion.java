package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cierre_institucion database table.
 * 
 */
@Entity
@Table(name="cierre_institucion")
@NamedQuery(name="CierreInstitucion.findAll", query="SELECT c FROM CierreInstitucion c")
public class CierreInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_modular")
	private String codigoModular;

	private Integer anno;

	@Column(name="fin_entrega")
	private Integer finEntrega;

	@Column(name="inicio_entrega")
	private Integer inicioEntrega;

	public CierreInstitucion() {
	}

	public String getCodigoModular() {
		return this.codigoModular;
	}

	public void setCodigoModular(String codigoModular) {
		this.codigoModular = codigoModular;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getFinEntrega() {
		return this.finEntrega;
	}

	public void setFinEntrega(Integer finEntrega) {
		this.finEntrega = finEntrega;
	}

	public Integer getInicioEntrega() {
		return this.inicioEntrega;
	}

	public void setInicioEntrega(Integer inicioEntrega) {
		this.inicioEntrega = inicioEntrega;
	}

}