package com.adicse.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the guia_remision_001 database table.
 * 
 */
@Entity
@Table(name="guia_remision_001")
@NamedQuery(name="GuiaRemision001.findAll", query="SELECT g FROM GuiaRemision001 g")
public class GuiaRemision001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_guia_remision_001")
	private Integer idGuiaRemision001;

	private String estado;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_emision")
	private Date fechaEmision;

	private Integer numero;

	@Column(name="numero_fisico")
	private String numeroFisico;

	@Column(name="orden_por_item")
	private Integer ordenPorItem;

	private Integer serie;

	@Column(name="suma_peso_toal")
	private BigDecimal sumaPesoToal;

	//bi-directional many-to-one association to Chofer
	@ManyToOne
	@JoinColumn(name="id_chofer")
	private Chofer chofer;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to PuntoLlegada
	@ManyToOne
	@JoinColumn(name="id_punto_llegada")
	private PuntoLlegada puntoLlegada;

	//bi-directional many-to-one association to PuntoPartida
	@ManyToOne
	@JoinColumn(name="id_punto_partida")
	private PuntoPartida puntoPartida;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_001")
	private RequerimientoVolumen001 requerimientoVolumen001;

	//bi-directional many-to-one association to Transportista
	@ManyToOne
	@JoinColumn(name="id_transportista")
	private Transportista transportista;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="id_vehiculo")
	private Vehiculo vehiculo;

	//bi-directional many-to-one association to GuiaRemision002
	@OneToMany(mappedBy="guiaRemision001", cascade={CascadeType.ALL})
	private List<GuiaRemision002> guiaRemision002s;

	public GuiaRemision001() {
	}

	public Integer getIdGuiaRemision001() {
		return this.idGuiaRemision001;
	}

	public void setIdGuiaRemision001(Integer idGuiaRemision001) {
		this.idGuiaRemision001 = idGuiaRemision001;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNumeroFisico() {
		return this.numeroFisico;
	}

	public void setNumeroFisico(String numeroFisico) {
		this.numeroFisico = numeroFisico;
	}

	public Integer getOrdenPorItem() {
		return this.ordenPorItem;
	}

	public void setOrdenPorItem(Integer ordenPorItem) {
		this.ordenPorItem = ordenPorItem;
	}

	public Integer getSerie() {
		return this.serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public BigDecimal getSumaPesoToal() {
		return this.sumaPesoToal;
	}

	public void setSumaPesoToal(BigDecimal sumaPesoToal) {
		this.sumaPesoToal = sumaPesoToal;
	}

	public Chofer getChofer() {
		return this.chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

	public PuntoLlegada getPuntoLlegada() {
		return this.puntoLlegada;
	}

	public void setPuntoLlegada(PuntoLlegada puntoLlegada) {
		this.puntoLlegada = puntoLlegada;
	}

	public PuntoPartida getPuntoPartida() {
		return this.puntoPartida;
	}

	public void setPuntoPartida(PuntoPartida puntoPartida) {
		this.puntoPartida = puntoPartida;
	}

	public RequerimientoVolumen001 getRequerimientoVolumen001() {
		return this.requerimientoVolumen001;
	}

	public void setRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		this.requerimientoVolumen001 = requerimientoVolumen001;
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

	public List<GuiaRemision002> getGuiaRemision002s() {
		return this.guiaRemision002s;
	}

	public void setGuiaRemision002s(List<GuiaRemision002> guiaRemision002s) {
		this.guiaRemision002s = guiaRemision002s;
	}

	public GuiaRemision002 addGuiaRemision002(GuiaRemision002 guiaRemision002) {
		getGuiaRemision002s().add(guiaRemision002);
		guiaRemision002.setGuiaRemision001(this);

		return guiaRemision002;
	}

	public GuiaRemision002 removeGuiaRemision002(GuiaRemision002 guiaRemision002) {
		getGuiaRemision002s().remove(guiaRemision002);
		guiaRemision002.setGuiaRemision001(null);

		return guiaRemision002;
	}

}
