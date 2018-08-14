package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidad_medida_trabajo database table.
 * 
 */
@Entity
@Table(name="unidad_medida_trabajo")
@NamedQuery(name="UnidadMedidaTrabajo.findAll", query="SELECT u FROM UnidadMedidaTrabajo u")
public class UnidadMedidaTrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_unidad_medida_trabajo")
	private Integer idUnidadMedidaTrabajo;

	@Column(name="dsc_unidad_medida_trabajo")
	private String dscUnidadMedidaTrabajo;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@OneToMany(mappedBy="unidadMedidaTrabajo")
	private List<CatalogoProductoQaliwarma> catalogoProductoQaliwarmas;

	public UnidadMedidaTrabajo() {
	}

	public Integer getIdUnidadMedidaTrabajo() {
		return this.idUnidadMedidaTrabajo;
	}

	public void setIdUnidadMedidaTrabajo(Integer idUnidadMedidaTrabajo) {
		this.idUnidadMedidaTrabajo = idUnidadMedidaTrabajo;
	}

	public String getDscUnidadMedidaTrabajo() {
		return this.dscUnidadMedidaTrabajo;
	}

	public void setDscUnidadMedidaTrabajo(String dscUnidadMedidaTrabajo) {
		this.dscUnidadMedidaTrabajo = dscUnidadMedidaTrabajo;
	}

	public List<CatalogoProductoQaliwarma> getCatalogoProductoQaliwarmas() {
		return this.catalogoProductoQaliwarmas;
	}

	public void setCatalogoProductoQaliwarmas(List<CatalogoProductoQaliwarma> catalogoProductoQaliwarmas) {
		this.catalogoProductoQaliwarmas = catalogoProductoQaliwarmas;
	}

	public CatalogoProductoQaliwarma addCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		getCatalogoProductoQaliwarmas().add(catalogoProductoQaliwarma);
		catalogoProductoQaliwarma.setUnidadMedidaTrabajo(this);

		return catalogoProductoQaliwarma;
	}

	public CatalogoProductoQaliwarma removeCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		getCatalogoProductoQaliwarmas().remove(catalogoProductoQaliwarma);
		catalogoProductoQaliwarma.setUnidadMedidaTrabajo(null);

		return catalogoProductoQaliwarma;
	}

}