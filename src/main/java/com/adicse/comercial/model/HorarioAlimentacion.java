package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the horario_alimentacion database table.
 * 
 */
@Entity
@Table(name="horario_alimentacion")
@NamedQuery(name="HorarioAlimentacion.findAll", query="SELECT h FROM HorarioAlimentacion h")
public class HorarioAlimentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_horario_alimentacion")
	private Integer idHorarioAlimentacion;

	@Column(name="dsc_horario_alimentacion")
	private String dscHorarioAlimentacion;

	//bi-directional many-to-one association to CatalogoBonificacion
	@OneToMany(mappedBy="horarioAlimentacion")
	private List<CatalogoBonificacion> catalogoBonificacions;

	//bi-directional many-to-one association to MontoContratado
	@OneToMany(mappedBy="horarioAlimentacion")
	private List<MontoContratado> montoContratados;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="horarioAlimentacion")
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	public HorarioAlimentacion() {
	}

	public Integer getIdHorarioAlimentacion() {
		return this.idHorarioAlimentacion;
	}

	public void setIdHorarioAlimentacion(Integer idHorarioAlimentacion) {
		this.idHorarioAlimentacion = idHorarioAlimentacion;
	}

	public String getDscHorarioAlimentacion() {
		return this.dscHorarioAlimentacion;
	}

	public void setDscHorarioAlimentacion(String dscHorarioAlimentacion) {
		this.dscHorarioAlimentacion = dscHorarioAlimentacion;
	}

	public List<CatalogoBonificacion> getCatalogoBonificacions() {
		return this.catalogoBonificacions;
	}

	public void setCatalogoBonificacions(List<CatalogoBonificacion> catalogoBonificacions) {
		this.catalogoBonificacions = catalogoBonificacions;
	}

	public CatalogoBonificacion addCatalogoBonificacion(CatalogoBonificacion catalogoBonificacion) {
		getCatalogoBonificacions().add(catalogoBonificacion);
		catalogoBonificacion.setHorarioAlimentacion(this);

		return catalogoBonificacion;
	}

	public CatalogoBonificacion removeCatalogoBonificacion(CatalogoBonificacion catalogoBonificacion) {
		getCatalogoBonificacions().remove(catalogoBonificacion);
		catalogoBonificacion.setHorarioAlimentacion(null);

		return catalogoBonificacion;
	}

	public List<MontoContratado> getMontoContratados() {
		return this.montoContratados;
	}

	public void setMontoContratados(List<MontoContratado> montoContratados) {
		this.montoContratados = montoContratados;
	}

	public MontoContratado addMontoContratado(MontoContratado montoContratado) {
		getMontoContratados().add(montoContratado);
		montoContratado.setHorarioAlimentacion(this);

		return montoContratado;
	}

	public MontoContratado removeMontoContratado(MontoContratado montoContratado) {
		getMontoContratados().remove(montoContratado);
		montoContratado.setHorarioAlimentacion(null);

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
		requerimientoVolumen001.setHorarioAlimentacion(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setHorarioAlimentacion(null);

		return requerimientoVolumen001;
	}

}