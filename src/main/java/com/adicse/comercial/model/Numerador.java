package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the numerador database table.
 * 
 */
@Entity
@NamedQuery(name="Numerador.findAll", query="SELECT n FROM Numerador n")
public class Numerador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_numerador")
	private Integer idNumerador;

	private Integer serie;

	@Column(name="ultimo_emitido")
	private Integer ultimoEmitido;

	//bi-directional many-to-one association to Tipodocumento
	@ManyToOne
	@JoinColumn(name="idtipodocumento")
	private Tipodocumento tipodocumento;

	public Numerador() {
	}

	public Integer getIdNumerador() {
		return this.idNumerador;
	}

	public void setIdNumerador(Integer idNumerador) {
		this.idNumerador = idNumerador;
	}

	public Integer getSerie() {
		return this.serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getUltimoEmitido() {
		return this.ultimoEmitido;
	}

	public void setUltimoEmitido(Integer ultimoEmitido) {
		this.ultimoEmitido = ultimoEmitido;
	}

	public Tipodocumento getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

}