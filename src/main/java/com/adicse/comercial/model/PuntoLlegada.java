package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the punto_llegada database table.
 * 
 */
@Entity
@Table(name="punto_llegada")
@NamedQuery(name="PuntoLlegada.findAll", query="SELECT p FROM PuntoLlegada p")
public class PuntoLlegada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_punto_llegada")
	private Integer idPuntoLlegada;

	private String direccion;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="puntoLlegada")
	private List<GuiaRemision001> guiaRemision001s;

	public PuntoLlegada() {
	}

	public Integer getIdPuntoLlegada() {
		return this.idPuntoLlegada;
	}

	public void setIdPuntoLlegada(Integer idPuntoLlegada) {
		this.idPuntoLlegada = idPuntoLlegada;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<GuiaRemision001> getGuiaRemision001s() {
		return this.guiaRemision001s;
	}

	public void setGuiaRemision001s(List<GuiaRemision001> guiaRemision001s) {
		this.guiaRemision001s = guiaRemision001s;
	}

	public GuiaRemision001 addGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().add(guiaRemision001);
		guiaRemision001.setPuntoLlegada(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setPuntoLlegada(null);

		return guiaRemision001;
	}

}