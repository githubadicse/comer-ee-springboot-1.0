package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the requerimiento_volumen_002_producto database table.
 * 
 */
@Entity
@Table(name="requerimiento_volumen_002_producto")
@NamedQuery(name="RequerimientoVolumen002Producto.findAll", query="SELECT r FROM RequerimientoVolumen002Producto r")
public class RequerimientoVolumen002Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_requerimiento_volumen_002_producto")
	private String idRequerimientoVolumen002Producto;

	@Column(name="id_horario_alimentacion_aux")
	private Integer idHorarioAlimentacionAux;

	@Column(name="id_producto_por_numero_entrega_aux")
	private String idProductoPorNumeroEntregaAux;

	@Column(name="id_region_alimentaria_aux")
	private String idRegionAlimentariaAux;

	private BigDecimal volumen;

	@Column(name="volumen_por_usuario")
	private BigDecimal volumenPorUsuario;

	//bi-directional many-to-one association to ProductoPorNumeroEntrega
	@ManyToOne
	@JoinColumn(name="id_producto_por_numero_entrega")
	private ProductoPorNumeroEntrega productoPorNumeroEntrega;

	//bi-directional many-to-one association to RequerimientoVolumen002
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_002")
	private RequerimientoVolumen002 requerimientoVolumen002;

	//bi-directional many-to-one association to VolumenConvertidoEnvace
	@OneToMany(mappedBy="requerimientoVolumen002Producto", cascade={CascadeType.ALL})
	private List<VolumenConvertidoEnvace> volumenConvertidoEnvaces;

	public RequerimientoVolumen002Producto() {
	}

	public String getIdRequerimientoVolumen002Producto() {
		return this.idRequerimientoVolumen002Producto;
	}

	public void setIdRequerimientoVolumen002Producto(String idRequerimientoVolumen002Producto) {
		this.idRequerimientoVolumen002Producto = idRequerimientoVolumen002Producto;
	}

	public Integer getIdHorarioAlimentacionAux() {
		return this.idHorarioAlimentacionAux;
	}

	public void setIdHorarioAlimentacionAux(Integer idHorarioAlimentacionAux) {
		this.idHorarioAlimentacionAux = idHorarioAlimentacionAux;
	}

	public String getIdProductoPorNumeroEntregaAux() {
		return this.idProductoPorNumeroEntregaAux;
	}

	public void setIdProductoPorNumeroEntregaAux(String idProductoPorNumeroEntregaAux) {
		this.idProductoPorNumeroEntregaAux = idProductoPorNumeroEntregaAux;
	}

	public String getIdRegionAlimentariaAux() {
		return this.idRegionAlimentariaAux;
	}

	public void setIdRegionAlimentariaAux(String idRegionAlimentariaAux) {
		this.idRegionAlimentariaAux = idRegionAlimentariaAux;
	}

	public BigDecimal getVolumen() {
		return this.volumen;
	}

	public void setVolumen(BigDecimal volumen) {
		this.volumen = volumen;
	}

	public BigDecimal getVolumenPorUsuario() {
		return this.volumenPorUsuario;
	}

	public void setVolumenPorUsuario(BigDecimal volumenPorUsuario) {
		this.volumenPorUsuario = volumenPorUsuario;
	}

	public ProductoPorNumeroEntrega getProductoPorNumeroEntrega() {
		return this.productoPorNumeroEntrega;
	}

	public void setProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		this.productoPorNumeroEntrega = productoPorNumeroEntrega;
	}

	public RequerimientoVolumen002 getRequerimientoVolumen002() {
		return this.requerimientoVolumen002;
	}

	public void setRequerimientoVolumen002(RequerimientoVolumen002 requerimientoVolumen002) {
		this.requerimientoVolumen002 = requerimientoVolumen002;
	}

	public List<VolumenConvertidoEnvace> getVolumenConvertidoEnvaces() {
		return this.volumenConvertidoEnvaces;
	}

	public void setVolumenConvertidoEnvaces(List<VolumenConvertidoEnvace> volumenConvertidoEnvaces) {
		this.volumenConvertidoEnvaces = volumenConvertidoEnvaces;
	}

	public VolumenConvertidoEnvace addVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().add(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setRequerimientoVolumen002Producto(this);

		return volumenConvertidoEnvace;
	}

	public VolumenConvertidoEnvace removeVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().remove(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setRequerimientoVolumen002Producto(null);

		return volumenConvertidoEnvace;
	}

}
