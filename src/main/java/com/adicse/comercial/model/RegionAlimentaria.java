package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the region_alimentaria database table.
 * 
 */
@Entity
@Table(name="region_alimentaria")
@NamedQuery(name="RegionAlimentaria.findAll", query="SELECT r FROM RegionAlimentaria r")
public class RegionAlimentaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_region_alimentaria")
	private String idRegionAlimentaria;

	@Column(name="dsc_region_alimentaria")
	private String dscRegionAlimentaria;

	//bi-directional many-to-one association to CatalogoBonificacion
	@OneToMany(mappedBy="regionAlimentaria", cascade={CascadeType.ALL})
	private List<CatalogoBonificacion> catalogoBonificacions;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="regionAlimentaria")
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	public RegionAlimentaria() {
	}

	public String getIdRegionAlimentaria() {
		return this.idRegionAlimentaria;
	}

	public void setIdRegionAlimentaria(String idRegionAlimentaria) {
		this.idRegionAlimentaria = idRegionAlimentaria;
	}

	public String getDscRegionAlimentaria() {
		return this.dscRegionAlimentaria;
	}

	public void setDscRegionAlimentaria(String dscRegionAlimentaria) {
		this.dscRegionAlimentaria = dscRegionAlimentaria;
	}

	public List<CatalogoBonificacion> getCatalogoBonificacions() {
		return this.catalogoBonificacions;
	}

	public void setCatalogoBonificacions(List<CatalogoBonificacion> catalogoBonificacions) {
		this.catalogoBonificacions = catalogoBonificacions;
	}

	public CatalogoBonificacion addCatalogoBonificacion(CatalogoBonificacion catalogoBonificacion) {
		getCatalogoBonificacions().add(catalogoBonificacion);
		catalogoBonificacion.setRegionAlimentaria(this);

		return catalogoBonificacion;
	}

	public CatalogoBonificacion removeCatalogoBonificacion(CatalogoBonificacion catalogoBonificacion) {
		getCatalogoBonificacions().remove(catalogoBonificacion);
		catalogoBonificacion.setRegionAlimentaria(null);

		return catalogoBonificacion;
	}

	public List<RequerimientoVolumen001> getRequerimientoVolumen001s() {
		return this.requerimientoVolumen001s;
	}

	public void setRequerimientoVolumen001s(List<RequerimientoVolumen001> requerimientoVolumen001s) {
		this.requerimientoVolumen001s = requerimientoVolumen001s;
	}

	public RequerimientoVolumen001 addRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().add(requerimientoVolumen001);
		requerimientoVolumen001.setRegionAlimentaria(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setRegionAlimentaria(null);

		return requerimientoVolumen001;
	}

}
