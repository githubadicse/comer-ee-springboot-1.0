package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_vehiculo")
	private Integer idVehiculo;

	@Column(name="marca_vehiculo")
	private String marcaVehiculo;

	@Column(name="numero_placa")
	private String numeroPlaca;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="vehiculo")
	private List<EntregaPorItem> entregaPorItems;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="vehiculo")
	private List<GuiaRemision001> guiaRemision001s;

	//bi-directional many-to-one association to RutaDistribucion
	@OneToMany(mappedBy="vehiculo")
	private List<RutaDistribucion> rutaDistribucions;

	public Vehiculo() {
	}

	public Integer getIdVehiculo() {
		return this.idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getMarcaVehiculo() {
		return this.marcaVehiculo;
	}

	public void setMarcaVehiculo(String marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}

	public String getNumeroPlaca() {
		return this.numeroPlaca;
	}

	public void setNumeroPlaca(String numeroPlaca) {
		this.numeroPlaca = numeroPlaca;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setVehiculo(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setVehiculo(null);

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
		guiaRemision001.setVehiculo(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setVehiculo(null);

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
		rutaDistribucion.setVehiculo(this);

		return rutaDistribucion;
	}

	public RutaDistribucion removeRutaDistribucion(RutaDistribucion rutaDistribucion) {
		getRutaDistribucions().remove(rutaDistribucion);
		rutaDistribucion.setVehiculo(null);

		return rutaDistribucion;
	}

}