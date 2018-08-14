package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_educacion database table.
 * 
 */
@Entity
@Table(name="nivel_educacion")
@NamedQuery(name="NivelEducacion.findAll", query="SELECT n FROM NivelEducacion n")
public class NivelEducacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_nivel_educacion")
	private Integer idNivelEducacion;

	@Column(name="dsc_nivel_educacion")
	private String dscNivelEducacion;

	//bi-directional many-to-one association to CatalogoMultiplicarNivelEducacion
	@OneToMany(mappedBy="nivelEducacion")
	private List<CatalogoMultiplicarNivelEducacion> catalogoMultiplicarNivelEducacions;

	//bi-directional many-to-one association to MontoContratado
	@OneToMany(mappedBy="nivelEducacion")
	private List<MontoContratado> montoContratados;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="nivelEducacion")
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	public NivelEducacion() {
	}

	public Integer getIdNivelEducacion() {
		return this.idNivelEducacion;
	}

	public void setIdNivelEducacion(Integer idNivelEducacion) {
		this.idNivelEducacion = idNivelEducacion;
	}

	public String getDscNivelEducacion() {
		return this.dscNivelEducacion;
	}

	public void setDscNivelEducacion(String dscNivelEducacion) {
		this.dscNivelEducacion = dscNivelEducacion;
	}

	public List<CatalogoMultiplicarNivelEducacion> getCatalogoMultiplicarNivelEducacions() {
		return this.catalogoMultiplicarNivelEducacions;
	}

	public void setCatalogoMultiplicarNivelEducacions(List<CatalogoMultiplicarNivelEducacion> catalogoMultiplicarNivelEducacions) {
		this.catalogoMultiplicarNivelEducacions = catalogoMultiplicarNivelEducacions;
	}

	public CatalogoMultiplicarNivelEducacion addCatalogoMultiplicarNivelEducacion(CatalogoMultiplicarNivelEducacion catalogoMultiplicarNivelEducacion) {
		getCatalogoMultiplicarNivelEducacions().add(catalogoMultiplicarNivelEducacion);
		catalogoMultiplicarNivelEducacion.setNivelEducacion(this);

		return catalogoMultiplicarNivelEducacion;
	}

	public CatalogoMultiplicarNivelEducacion removeCatalogoMultiplicarNivelEducacion(CatalogoMultiplicarNivelEducacion catalogoMultiplicarNivelEducacion) {
		getCatalogoMultiplicarNivelEducacions().remove(catalogoMultiplicarNivelEducacion);
		catalogoMultiplicarNivelEducacion.setNivelEducacion(null);

		return catalogoMultiplicarNivelEducacion;
	}

	public List<MontoContratado> getMontoContratados() {
		return this.montoContratados;
	}

	public void setMontoContratados(List<MontoContratado> montoContratados) {
		this.montoContratados = montoContratados;
	}

	public MontoContratado addMontoContratado(MontoContratado montoContratado) {
		getMontoContratados().add(montoContratado);
		montoContratado.setNivelEducacion(this);

		return montoContratado;
	}

	public MontoContratado removeMontoContratado(MontoContratado montoContratado) {
		getMontoContratados().remove(montoContratado);
		montoContratado.setNivelEducacion(null);

		return montoContratado;
	}

	public List<RequerimientoVolumen001> getRequerimientoVolumen001s() {
		return this.requerimientoVolumen001s;
	}

	public void setRequerimientoVolumen001s(List<RequerimientoVolumen001> requerimientoVolumen001s) {
		this.requerimientoVolumen001s = requerimientoVolumen001s;
	}

	public RequerimientoVolumen001 addRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().add(requerimientoVolumen001);
		requerimientoVolumen001.setNivelEducacion(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setNivelEducacion(null);

		return requerimientoVolumen001;
	}

}