package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catalogo_multiplicar_nivel_educacion database table.
 * 
 */
@Entity
@Table(name="catalogo_multiplicar_nivel_educacion")
@NamedQuery(name="CatalogoMultiplicarNivelEducacion.findAll", query="SELECT c FROM CatalogoMultiplicarNivelEducacion c")
public class CatalogoMultiplicarNivelEducacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_catalogo_multiplicar_nivel_educacion")
	private String idCatalogoMultiplicarNivelEducacion;

	private Integer anno;

	private Integer factor;

	@Column(name="numero_entrega")
	private Integer numeroEntrega;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@ManyToOne
	@JoinColumn(name="id_catalogo_producto_qaliwarma")
	private CatalogoProductoQaliwarma catalogoProductoQaliwarma;

	//bi-directional many-to-one association to NivelEducacion
	@ManyToOne
	@JoinColumn(name="id_nivel_educacion")
	private NivelEducacion nivelEducacion;

	//bi-directional many-to-one association to ProductoPresentacion
	@ManyToOne
	@JoinColumn(name="id_producto_presentacion")
	private ProductoPresentacion productoPresentacion;

	public CatalogoMultiplicarNivelEducacion() {
	}

	public String getIdCatalogoMultiplicarNivelEducacion() {
		return this.idCatalogoMultiplicarNivelEducacion;
	}

	public void setIdCatalogoMultiplicarNivelEducacion(String idCatalogoMultiplicarNivelEducacion) {
		this.idCatalogoMultiplicarNivelEducacion = idCatalogoMultiplicarNivelEducacion;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getFactor() {
		return this.factor;
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	public Integer getNumeroEntrega() {
		return this.numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public CatalogoProductoQaliwarma getCatalogoProductoQaliwarma() {
		return this.catalogoProductoQaliwarma;
	}

	public void setCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		this.catalogoProductoQaliwarma = catalogoProductoQaliwarma;
	}

	public NivelEducacion getNivelEducacion() {
		return this.nivelEducacion;
	}

	public void setNivelEducacion(NivelEducacion nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public ProductoPresentacion getProductoPresentacion() {
		return this.productoPresentacion;
	}

	public void setProductoPresentacion(ProductoPresentacion productoPresentacion) {
		this.productoPresentacion = productoPresentacion;
	}

}