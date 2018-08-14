package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the guia_remision_002 database table.
 * 
 */
@Entity
@Table(name="guia_remision_002")
@NamedQuery(name="GuiaRemision002.findAll", query="SELECT g FROM GuiaRemision002 g")
public class GuiaRemision002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_guia_remision_002")
	private String idGuiaRemision002;

	private Integer cantidad;

	private String envase;

	private BigDecimal factor;

	@Column(name="flag_over_lote")
	private Boolean flagOverLote;

	private String marca;

	@Column(name="numero_lote")
	private String numeroLote;

	@Column(name="peso_total")
	private BigDecimal pesoTotal;

	@Column(name="producto_grupo")
	private String productoGrupo;

	@Column(name="producto_seleccionado")
	private String productoSeleccionado;

	@Column(name="unidad_medida_trabajo")
	private String unidadMedidaTrabajo;

	//bi-directional many-to-one association to GuiaRemision001
	@ManyToOne
	@JoinColumn(name="id_guia_remision_001")
	private GuiaRemision001 guiaRemision001;

	//bi-directional many-to-one association to VolumenConvertidoEnvace
	@ManyToOne
	@JoinColumn(name="id_volumen_convertido_envace")
	private VolumenConvertidoEnvace volumenConvertidoEnvace;

	public GuiaRemision002() {
	}

	public String getIdGuiaRemision002() {
		return this.idGuiaRemision002;
	}

	public void setIdGuiaRemision002(String idGuiaRemision002) {
		this.idGuiaRemision002 = idGuiaRemision002;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getEnvase() {
		return this.envase;
	}

	public void setEnvase(String envase) {
		this.envase = envase;
	}

	public BigDecimal getFactor() {
		return this.factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public Boolean getFlagOverLote() {
		return this.flagOverLote;
	}

	public void setFlagOverLote(Boolean flagOverLote) {
		this.flagOverLote = flagOverLote;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public BigDecimal getPesoTotal() {
		return this.pesoTotal;
	}

	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public String getProductoGrupo() {
		return this.productoGrupo;
	}

	public void setProductoGrupo(String productoGrupo) {
		this.productoGrupo = productoGrupo;
	}

	public String getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

	public void setProductoSeleccionado(String productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public String getUnidadMedidaTrabajo() {
		return this.unidadMedidaTrabajo;
	}

	public void setUnidadMedidaTrabajo(String unidadMedidaTrabajo) {
		this.unidadMedidaTrabajo = unidadMedidaTrabajo;
	}

	public GuiaRemision001 getGuiaRemision001() {
		return this.guiaRemision001;
	}

	public void setGuiaRemision001(GuiaRemision001 guiaRemision001) {
		this.guiaRemision001 = guiaRemision001;
	}

	public VolumenConvertidoEnvace getVolumenConvertidoEnvace() {
		return this.volumenConvertidoEnvace;
	}

	public void setVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		this.volumenConvertidoEnvace = volumenConvertidoEnvace;
	}

}