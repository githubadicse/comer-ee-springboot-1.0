package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the envase database table.
 * 
 */
@Entity
@NamedQuery(name="Envase.findAll", query="SELECT e FROM Envase e")
public class Envase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_envase")
	private Integer idEnvase;

	@Column(name="dsc_envase")
	private String dscEnvase;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@OneToMany(mappedBy="envase")
	private List<CatalogoProductoQaliwarma> catalogoProductoQaliwarmas;

	public Envase() {
	}

	public Integer getIdEnvase() {
		return this.idEnvase;
	}

	public void setIdEnvase(Integer idEnvase) {
		this.idEnvase = idEnvase;
	}

	public String getDscEnvase() {
		return this.dscEnvase;
	}

	public void setDscEnvase(String dscEnvase) {
		this.dscEnvase = dscEnvase;
	}

	public List<CatalogoProductoQaliwarma> getCatalogoProductoQaliwarmas() {
		return this.catalogoProductoQaliwarmas;
	}

	public void setCatalogoProductoQaliwarmas(List<CatalogoProductoQaliwarma> catalogoProductoQaliwarmas) {
		this.catalogoProductoQaliwarmas = catalogoProductoQaliwarmas;
	}

	public CatalogoProductoQaliwarma addCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		getCatalogoProductoQaliwarmas().add(catalogoProductoQaliwarma);
		catalogoProductoQaliwarma.setEnvase(this);

		return catalogoProductoQaliwarma;
	}

	public CatalogoProductoQaliwarma removeCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		getCatalogoProductoQaliwarmas().remove(catalogoProductoQaliwarma);
		catalogoProductoQaliwarma.setEnvase(null);

		return catalogoProductoQaliwarma;
	}

}