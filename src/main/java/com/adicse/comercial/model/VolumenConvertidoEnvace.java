package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the volumen_convertido_envace database table.
 * 
 */
@Entity
@Table(name="volumen_convertido_envace")
@NamedQuery(name="VolumenConvertidoEnvace.findAll", query="SELECT v FROM VolumenConvertidoEnvace v")
public class VolumenConvertidoEnvace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_volumen_convertido_envace")
	private String idVolumenConvertidoEnvace;

	private Integer anno;

	private Integer bonificacion;

	private Integer cantidad;

	@Column(name="descripcion_final_producto")
	private String descripcionFinalProducto;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_calculo_sys")
	private Date fechaCalculoSys;

	@Column(name="numero_entrega")
	private Integer numeroEntrega;

	private String obs;

	private BigDecimal volumen;

	//bi-directional many-to-one association to GuiaRemision002
	@OneToMany(mappedBy="volumenConvertidoEnvace")
	private List<GuiaRemision002> guiaRemision002s;

	//bi-directional many-to-one association to Piking
	@OneToMany(mappedBy="volumenConvertidoEnvace")
	private List<Piking> pikings;

	//bi-directional many-to-one association to CatalogoMarca
	@ManyToOne
	@JoinColumn(name="id_catalogo_marca")
	private CatalogoMarca catalogoMarca;

	//bi-directional many-to-one association to EntregaPorItem
	@ManyToOne
	@JoinColumn(name="id_entrega_por_item")
	private EntregaPorItem entregaPorItem;

	//bi-directional many-to-one association to ProductoPresentacion
	@ManyToOne
	@JoinColumn(name="id_producto_presentacion")
	private ProductoPresentacion productoPresentacion;

	//bi-directional many-to-one association to RequerimientoVolumen002Producto
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_002_producto")
	private RequerimientoVolumen002Producto requerimientoVolumen002Producto;

	public VolumenConvertidoEnvace() {
	}

	public String getIdVolumenConvertidoEnvace() {
		return this.idVolumenConvertidoEnvace;
	}

	public void setIdVolumenConvertidoEnvace(String idVolumenConvertidoEnvace) {
		this.idVolumenConvertidoEnvace = idVolumenConvertidoEnvace;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getBonificacion() {
		return this.bonificacion;
	}

	public void setBonificacion(Integer bonificacion) {
		this.bonificacion = bonificacion;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcionFinalProducto() {
		return this.descripcionFinalProducto;
	}

	public void setDescripcionFinalProducto(String descripcionFinalProducto) {
		this.descripcionFinalProducto = descripcionFinalProducto;
	}

	public Date getFechaCalculoSys() {
		return this.fechaCalculoSys;
	}

	public void setFechaCalculoSys(Date fechaCalculoSys) {
		this.fechaCalculoSys = fechaCalculoSys;
	}

	public Integer getNumeroEntrega() {
		return this.numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public BigDecimal getVolumen() {
		return this.volumen;
	}

	public void setVolumen(BigDecimal volumen) {
		this.volumen = volumen;
	}

	public List<GuiaRemision002> getGuiaRemision002s() {
		return this.guiaRemision002s;
	}

	public void setGuiaRemision002s(List<GuiaRemision002> guiaRemision002s) {
		this.guiaRemision002s = guiaRemision002s;
	}

	public GuiaRemision002 addGuiaRemision002(GuiaRemision002 guiaRemision002) {
		getGuiaRemision002s().add(guiaRemision002);
		guiaRemision002.setVolumenConvertidoEnvace(this);

		return guiaRemision002;
	}

	public GuiaRemision002 removeGuiaRemision002(GuiaRemision002 guiaRemision002) {
		getGuiaRemision002s().remove(guiaRemision002);
		guiaRemision002.setVolumenConvertidoEnvace(null);

		return guiaRemision002;
	}

	public List<Piking> getPikings() {
		return this.pikings;
	}

	public void setPikings(List<Piking> pikings) {
		this.pikings = pikings;
	}

	public Piking addPiking(Piking piking) {
		getPikings().add(piking);
		piking.setVolumenConvertidoEnvace(this);

		return piking;
	}

	public Piking removePiking(Piking piking) {
		getPikings().remove(piking);
		piking.setVolumenConvertidoEnvace(null);

		return piking;
	}

	public CatalogoMarca getCatalogoMarca() {
		return this.catalogoMarca;
	}

	public void setCatalogoMarca(CatalogoMarca catalogoMarca) {
		this.catalogoMarca = catalogoMarca;
	}

	public EntregaPorItem getEntregaPorItem() {
		return this.entregaPorItem;
	}

	public void setEntregaPorItem(EntregaPorItem entregaPorItem) {
		this.entregaPorItem = entregaPorItem;
	}

	public ProductoPresentacion getProductoPresentacion() {
		return this.productoPresentacion;
	}

	public void setProductoPresentacion(ProductoPresentacion productoPresentacion) {
		this.productoPresentacion = productoPresentacion;
	}

	public RequerimientoVolumen002Producto getRequerimientoVolumen002Producto() {
		return this.requerimientoVolumen002Producto;
	}

	public void setRequerimientoVolumen002Producto(RequerimientoVolumen002Producto requerimientoVolumen002Producto) {
		this.requerimientoVolumen002Producto = requerimientoVolumen002Producto;
	}

}