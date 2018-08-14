package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the transportista database table.
 * 
 */
@Entity
@NamedQuery(name="Transportista.findAll", query="SELECT t FROM Transportista t")
public class Transportista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_transportista")
	private Integer idTransportista;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="transportista")
	private List<EntregaPorItem> entregaPorItems;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="transportista")
	private List<GuiaRemision001> guiaRemision001s;

	//bi-directional many-to-one association to RutaDistribucion
	@OneToMany(mappedBy="transportista")
	private List<RutaDistribucion> rutaDistribucions;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	public Transportista() {
	}

	public Integer getIdTransportista() {
		return this.idTransportista;
	}

	public void setIdTransportista(Integer idTransportista) {
		this.idTransportista = idTransportista;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setTransportista(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setTransportista(null);

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
		guiaRemision001.setTransportista(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setTransportista(null);

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
		rutaDistribucion.setTransportista(this);

		return rutaDistribucion;
	}

	public RutaDistribucion removeRutaDistribucion(RutaDistribucion rutaDistribucion) {
		getRutaDistribucions().remove(rutaDistribucion);
		rutaDistribucion.setTransportista(null);

		return rutaDistribucion;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

}