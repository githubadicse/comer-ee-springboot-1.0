package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the chofer database table.
 * 
 */
@Entity
@NamedQuery(name="Chofer.findAll", query="SELECT c FROM Chofer c")
public class Chofer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_chofer")
	private Integer idChofer;

	private String dni;

	private String nombres;

	@Column(name="numero_brevete")
	private String numeroBrevete;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="chofer")
	private List<EntregaPorItem> entregaPorItems;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="chofer")
	private List<GuiaRemision001> guiaRemision001s;

	//bi-directional many-to-one association to RutaDistribucion
	@OneToMany(mappedBy="chofer")
	private List<RutaDistribucion> rutaDistribucions;

	public Chofer() {
	}

	public Integer getIdChofer() {
		return this.idChofer;
	}

	public void setIdChofer(Integer idChofer) {
		this.idChofer = idChofer;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroBrevete() {
		return this.numeroBrevete;
	}

	public void setNumeroBrevete(String numeroBrevete) {
		this.numeroBrevete = numeroBrevete;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setChofer(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setChofer(null);

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
		guiaRemision001.setChofer(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setChofer(null);

		return guiaRemision001;
	}

	public List<RutaDistribucion> getRutaDistribucions() {
		return this.rutaDistribucions;
	}

	public void setRutaDistribucions(List<RutaDistribucion> rutaDistribucions) {
		this.rutaDistribucions = rutaDistribucions;
	}

	public RutaDistribucion addRutaDistribucion(RutaDistribucion rutaDistribucion) {
		getRutaDistribucions().add(rutaDistribucion);
		rutaDistribucion.setChofer(this);

		return rutaDistribucion;
	}

	public RutaDistribucion removeRutaDistribucion(RutaDistribucion rutaDistribucion) {
		getRutaDistribucions().remove(rutaDistribucion);
		rutaDistribucion.setChofer(null);

		return rutaDistribucion;
	}

}