package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the producto_presentacion database table.
 * 
 */
@Entity
@Table(name="producto_presentacion")
@NamedQuery(name="ProductoPresentacion.findAll", query="SELECT p FROM ProductoPresentacion p")
public class ProductoPresentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_producto_presentacion")
	private String idProductoPresentacion;

	private Integer anno;

	@Column(name="cantidad_presentacion")
	private Integer cantidadPresentacion;

	@Column(name="dsc_presentacion")
	private String dscPresentacion;

	@Column(name="factor_volumen_presentacion")
	private BigDecimal factorVolumenPresentacion;

	@Column(name="numero_entrega")
	private Integer numeroEntrega;

	//bi-directional many-to-one association to CatalogoMarca
	@OneToMany(mappedBy="productoPresentacion")
	private List<CatalogoMarca> catalogoMarcas;

	//bi-directional many-to-one association to CatalogoMultiplicarNivelEducacion
	@OneToMany(mappedBy="productoPresentacion")
	private List<CatalogoMultiplicarNivelEducacion> catalogoMultiplicarNivelEducacions;

	//bi-directional many-to-one association to MatrizPresentacion
	@OneToMany(mappedBy="productoPresentacion")
	private List<MatrizPresentacion> matrizPresentacions;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@ManyToOne
	@JoinColumn(name="id_catalogo_producto_qaliwarma")
	private CatalogoProductoQaliwarma catalogoProductoQaliwarma;

	//bi-directional many-to-one association to VolumenConvertidoEnvace
	@OneToMany(mappedBy="productoPresentacion")
	private List<VolumenConvertidoEnvace> volumenConvertidoEnvaces;

	public ProductoPresentacion() {
	}

	public String getIdProductoPresentacion() {
		return this.idProductoPresentacion;
	}

	public void setIdProductoPresentacion(String idProductoPresentacion) {
		this.idProductoPresentacion = idProductoPresentacion;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getCantidadPresentacion() {
		return this.cantidadPresentacion;
	}

	public void setCantidadPresentacion(Integer cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}

	public String getDscPresentacion() {
		return this.dscPresentacion;
	}

	public void setDscPresentacion(String dscPresentacion) {
		this.dscPresentacion = dscPresentacion;
	}

	public BigDecimal getFactorVolumenPresentacion() {
		return this.factorVolumenPresentacion;
	}

	public void setFactorVolumenPresentacion(BigDecimal factorVolumenPresentacion) {
		this.factorVolumenPresentacion = factorVolumenPresentacion;
	}

	public Integer getNumeroEntrega() {
		return this.numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public List<CatalogoMarca> getCatalogoMarcas() {
		return this.catalogoMarcas;
	}

	public void setCatalogoMarcas(List<CatalogoMarca> catalogoMarcas) {
		this.catalogoMarcas = catalogoMarcas;
	}

	public CatalogoMarca addCatalogoMarca(CatalogoMarca catalogoMarca) {
		getCatalogoMarcas().add(catalogoMarca);
		catalogoMarca.setProductoPresentacion(this);

		return catalogoMarca;
	}

	public CatalogoMarca removeCatalogoMarca(CatalogoMarca catalogoMarca) {
		getCatalogoMarcas().remove(catalogoMarca);
		catalogoMarca.setProductoPresentacion(null);

		return catalogoMarca;
	}

	public List<CatalogoMultiplicarNivelEducacion> getCatalogoMultiplicarNivelEducacions() {
		return this.catalogoMultiplicarNivelEducacions;
	}

	public void setCatalogoMultiplicarNivelEducacions(List<CatalogoMultiplicarNivelEducacion> catalogoMultiplicarNivelEducacions) {
		this.catalogoMultiplicarNivelEducacions = catalogoMultiplicarNivelEducacions;
	}

	public CatalogoMultiplicarNivelEducacion addCatalogoMultiplicarNivelEducacion(CatalogoMultiplicarNivelEducacion catalogoMultiplicarNivelEducacion) {
		getCatalogoMultiplicarNivelEducacions().add(catalogoMultiplicarNivelEducacion);
		catalogoMultiplicarNivelEducacion.setProductoPresentacion(this);

		return catalogoMultiplicarNivelEducacion;
	}

	public CatalogoMultiplicarNivelEducacion removeCatalogoMultiplicarNivelEducacion(CatalogoMultiplicarNivelEducacion catalogoMultiplicarNivelEducacion) {
		getCatalogoMultiplicarNivelEducacions().remove(catalogoMultiplicarNivelEducacion);
		catalogoMultiplicarNivelEducacion.setProductoPresentacion(null);

		return catalogoMultiplicarNivelEducacion;
	}

	public List<MatrizPresentacion> getMatrizPresentacions() {
		return this.matrizPresentacions;
	}

	public void setMatrizPresentacions(List<MatrizPresentacion> matrizPresentacions) {
		this.matrizPresentacions = matrizPresentacions;
	}

	public MatrizPresentacion addMatrizPresentacion(MatrizPresentacion matrizPresentacion) {
		getMatrizPresentacions().add(matrizPresentacion);
		matrizPresentacion.setProductoPresentacion(this);

		return matrizPresentacion;
	}

	public MatrizPresentacion removeMatrizPresentacion(MatrizPresentacion matrizPresentacion) {
		getMatrizPresentacions().remove(matrizPresentacion);
		matrizPresentacion.setProductoPresentacion(null);

		return matrizPresentacion;
	}

	public CatalogoProductoQaliwarma getCatalogoProductoQaliwarma() {
		return this.catalogoProductoQaliwarma;
	}

	public void setCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		this.catalogoProductoQaliwarma = catalogoProductoQaliwarma;
	}

	public List<VolumenConvertidoEnvace> getVolumenConvertidoEnvaces() {
		return this.volumenConvertidoEnvaces;
	}

	public void setVolumenConvertidoEnvaces(List<VolumenConvertidoEnvace> volumenConvertidoEnvaces) {
		this.volumenConvertidoEnvaces = volumenConvertidoEnvaces;
	}

	public VolumenConvertidoEnvace addVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().add(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setProductoPresentacion(this);

		return volumenConvertidoEnvace;
	}

	public VolumenConvertidoEnvace removeVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().remove(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setProductoPresentacion(null);

		return volumenConvertidoEnvace;
	}

}