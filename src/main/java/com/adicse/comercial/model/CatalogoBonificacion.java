package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catalogo_bonificacion database table.
 * 
 */
@Entity
@Table(name="catalogo_bonificacion")
@NamedQuery(name="CatalogoBonificacion.findAll", query="SELECT c FROM CatalogoBonificacion c")
public class CatalogoBonificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_catalogo_bonificacion")
	private String idCatalogoBonificacion;

	@Column(name="id_producto_presentacion")
	private String idProductoPresentacion;

	@Column(name="numero_de_preparacion_por_entrega")
	private Integer numeroDePreparacionPorEntrega;

	//bi-directional many-to-one association to HorarioAlimentacion
	@ManyToOne
	@JoinColumn(name="id_horario_alimentacion")
	private HorarioAlimentacion horarioAlimentacion;

	//bi-directional many-to-one association to ProductoPorNumeroEntrega
	@ManyToOne
	@JoinColumn(name="id_producto_por_numero_entrega")
	private ProductoPorNumeroEntrega productoPorNumeroEntrega;

	//bi-directional many-to-one association to RegionAlimentaria
	@ManyToOne
	@JoinColumn(name="id_region_alimentaria")
	private RegionAlimentaria regionAlimentaria;

	public CatalogoBonificacion() {
	}

	public String getIdCatalogoBonificacion() {
		return this.idCatalogoBonificacion;
	}

	public void setIdCatalogoBonificacion(String idCatalogoBonificacion) {
		this.idCatalogoBonificacion = idCatalogoBonificacion;
	}

	public String getIdProductoPresentacion() {
		return this.idProductoPresentacion;
	}

	public void setIdProductoPresentacion(String idProductoPresentacion) {
		this.idProductoPresentacion = idProductoPresentacion;
	}

	public Integer getNumeroDePreparacionPorEntrega() {
		return this.numeroDePreparacionPorEntrega;
	}

	public void setNumeroDePreparacionPorEntrega(Integer numeroDePreparacionPorEntrega) {
		this.numeroDePreparacionPorEntrega = numeroDePreparacionPorEntrega;
	}

	public HorarioAlimentacion getHorarioAlimentacion() {
		return this.horarioAlimentacion;
	}

	public void setHorarioAlimentacion(HorarioAlimentacion horarioAlimentacion) {
		this.horarioAlimentacion = horarioAlimentacion;
	}

	public ProductoPorNumeroEntrega getProductoPorNumeroEntrega() {
		return this.productoPorNumeroEntrega;
	}

	public void setProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		this.productoPorNumeroEntrega = productoPorNumeroEntrega;
	}

	public RegionAlimentaria getRegionAlimentaria() {
		return this.regionAlimentaria;
	}

	public void setRegionAlimentaria(RegionAlimentaria regionAlimentaria) {
		this.regionAlimentaria = regionAlimentaria;
	}

}