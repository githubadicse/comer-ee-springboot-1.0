package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the punto_partida database table.
 * 
 */
@Entity
@Table(name="punto_partida")
@NamedQuery(name="PuntoPartida.findAll", query="SELECT p FROM PuntoPartida p")
public class PuntoPartida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_punto_partida")
	private Integer idPuntoPartida;

	private String direccion;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="puntoPartida")
	private List<EntregaPorItem> entregaPorItems;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="puntoPartida")
	private List<GuiaRemision001> guiaRemision001s;

	public PuntoPartida() {
	}

	public Integer getIdPuntoPartida() {
		return this.idPuntoPartida;
	}

	public void setIdPuntoPartida(Integer idPuntoPartida) {
		this.idPuntoPartida = idPuntoPartida;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setPuntoPartida(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setPuntoPartida(null);

		return entregaPorItem;
	}

	public List<GuiaRemision001> getGuiaRemision001s() {
		return this.guiaRemision001s;
	}

	public void setGuiaRemision001s(List<GuiaRemision001> guiaRemision001s) {
		this.guiaRemision001s = guiaRemision001s;
	}

	public GuiaRemision001 addGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().add(guiaRemision001);
		guiaRemision001.setPuntoPartida(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setPuntoPartida(null);

		return guiaRemision001;
	}

}