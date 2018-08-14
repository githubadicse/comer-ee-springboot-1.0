package com.adicse.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the entrega_por_item database table.
 * 
 */
@Entity
@Table(name="entrega_por_item")
@NamedQuery(name="EntregaPorItem.findAll", query="SELECT e FROM EntregaPorItem e")
public class EntregaPorItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_entrega_por_item")
	private String idEntregaPorItem;

	private Boolean cerrado;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fin_atencion")
	private Date finAtencion;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="inicio_atencion")
	private Date inicioAtencion;

	//bi-directional many-to-one association to Chofer
	@ManyToOne
	@JoinColumn(name="id_chofer")
	private Chofer chofer;

	//bi-directional many-to-one association to ItemEntrega
	@ManyToOne
	@JoinColumn(name="item")
	private ItemEntrega itemEntrega;

	//bi-directional many-to-one association to NumeroEntrega
	@ManyToOne
	@JoinColumn(name="id_numero_entrega")
	private NumeroEntrega numeroEntrega;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to PuntoPartida
	@ManyToOne
	@JoinColumn(name="id_punto_partida")
	private PuntoPartida puntoPartida;

	//bi-directional many-to-one association to Transportista
	@ManyToOne
	@JoinColumn(name="id_transportista")
	private Transportista transportista;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	//bi-directional many-to-one association to MontoContratado
	@OneToMany(mappedBy="entregaPorItem")
	private List<MontoContratado> montoContratados;

	//bi-directional many-to-one association to ProductoPorNumeroEntrega
	@OneToMany(mappedBy="entregaPorItem")
	private List<ProductoPorNumeroEntrega> productoPorNumeroEntregas;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="entregaPorItem")
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	//bi-directional many-to-one association to VolumenConvertidoEnvace
	@OneToMany(mappedBy="entregaPorItem")
	private List<VolumenConvertidoEnvace> volumenConvertidoEnvaces;

	public EntregaPorItem() {
	}

	public String getIdEntregaPorItem() {
		return this.idEntregaPorItem;
	}

	public void setIdEntregaPorItem(String idEntregaPorItem) {
		this.idEntregaPorItem = idEntregaPorItem;
	}

	public Boolean getCerrado() {
		return this.cerrado;
	}

	public void setCerrado(Boolean cerrado) {
		this.cerrado = cerrado;
	}

	public Date getFinAtencion() {
		return this.finAtencion;
	}

	public void setFinAtencion(Date finAtencion) {
		this.finAtencion = finAtencion;
	}

	public Date getInicioAtencion() {
		return this.inicioAtencion;
	}

	public void setInicioAtencion(Date inicioAtencion) {
		this.inicioAtencion = inicioAtencion;
	}

	public Chofer getChofer() {
		return this.chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public ItemEntrega getItemEntrega() {
		return this.itemEntrega;
	}

	public void setItemEntrega(ItemEntrega itemEntrega) {
		this.itemEntrega = itemEntrega;
	}

	public NumeroEntrega getNumeroEntrega() {
		return this.numeroEntrega;
	}

	public void setNumeroEntrega(NumeroEntrega numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

	public PuntoPartida getPuntoPartida() {
		return this.puntoPartida;
	}

	public void setPuntoPartida(PuntoPartida puntoPartida) {
		this.puntoPartida = puntoPartida;
	}

	public Transportista getTransportista() {
		return this.transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<MontoContratado> getMontoContratados() {
		return this.montoContratados;
	}

	public void setMontoContratados(List<MontoContratado> montoContratados) {
		this.montoContratados = montoContratados;
	}

	public MontoContratado addMontoContratado(MontoContratado montoContratado) {
		getMontoContratados().add(montoContratado);
		montoContratado.setEntregaPorItem(this);

		return montoContratado;
	}

	public MontoContratado removeMontoContratado(MontoContratado montoContratado) {
		getMontoContratados().remove(montoContratado);
		montoContratado.setEntregaPorItem(null);

		return montoContratado;
	}

	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregas() {
		return this.productoPorNumeroEntregas;
	}

	public void setProductoPorNumeroEntregas(List<ProductoPorNumeroEntrega> productoPorNumeroEntregas) {
		this.productoPorNumeroEntregas = productoPorNumeroEntregas;
	}

	public ProductoPorNumeroEntrega addProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		getProductoPorNumeroEntregas().add(productoPorNumeroEntrega);
		productoPorNumeroEntrega.setEntregaPorItem(this);

		return productoPorNumeroEntrega;
	}

	public ProductoPorNumeroEntrega removeProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		getProductoPorNumeroEntregas().remove(productoPorNumeroEntrega);
		productoPorNumeroEntrega.setEntregaPorItem(null);

		return productoPorNumeroEntrega;
	}

	public List<RequerimientoVolumen001> getRequerimientoVolumen001s() {
		return this.requerimientoVolumen001s;
	}

	public void setRequerimientoVolumen001s(List<RequerimientoVolumen001> requerimientoVolumen001s) {
		this.requerimientoVolumen001s = requerimientoVolumen001s;
	}

	public RequerimientoVolumen001 addRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().add(requerimientoVolumen001);
		requerimientoVolumen001.setEntregaPorItem(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setEntregaPorItem(null);

		return requerimientoVolumen001;
	}

	public List<VolumenConvertidoEnvace> getVolumenConvertidoEnvaces() {
		return this.volumenConvertidoEnvaces;
	}

	public void setVolumenConvertidoEnvaces(List<VolumenConvertidoEnvace> volumenConvertidoEnvaces) {
		this.volumenConvertidoEnvaces = volumenConvertidoEnvaces;
	}

	public VolumenConvertidoEnvace addVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().add(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setEntregaPorItem(this);

		return volumenConvertidoEnvace;
	}

	public VolumenConvertidoEnvace removeVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().remove(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setEntregaPorItem(null);

		return volumenConvertidoEnvace;
	}

}
