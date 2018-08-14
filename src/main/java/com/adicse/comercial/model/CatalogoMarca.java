package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogo_marca database table.
 * 
 */
@Entity
@Table(name="catalogo_marca")
@NamedQuery(name="CatalogoMarca.findAll", query="SELECT c FROM CatalogoMarca c")
public class CatalogoMarca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_catalogo_marca")
	private String idCatalogoMarca;

	@Column(name="dsc_catalogo_marca")
	private String dscCatalogoMarca;

	//bi-directional many-to-one association to CatalogoLote
	@OneToMany(mappedBy="catalogoMarca")
	private List<CatalogoLote> catalogoLotes;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="idmarca")
	private Marca marca;

	//bi-directional many-to-one association to ProductoPorNumeroEntrega
	@ManyToOne
	@JoinColumn(name="id_producto_por_numero_entrega")
	private ProductoPorNumeroEntrega productoPorNumeroEntrega;

	//bi-directional many-to-one association to ProductoPresentacion
	@ManyToOne
	@JoinColumn(name="id_producto_presentacion")
	private ProductoPresentacion productoPresentacion;

	//bi-directional many-to-one association to PresentacionMarca
	@OneToMany(mappedBy="catalogoMarca")
	private List<PresentacionMarca> presentacionMarcas;

	//bi-directional many-to-one association to VolumenConvertidoEnvace
	@OneToMany(mappedBy="catalogoMarca")
	private List<VolumenConvertidoEnvace> volumenConvertidoEnvaces;

	public CatalogoMarca() {
	}

	public String getIdCatalogoMarca() {
		return this.idCatalogoMarca;
	}

	public void setIdCatalogoMarca(String idCatalogoMarca) {
		this.idCatalogoMarca = idCatalogoMarca;
	}

	public String getDscCatalogoMarca() {
		return this.dscCatalogoMarca;
	}

	public void setDscCatalogoMarca(String dscCatalogoMarca) {
		this.dscCatalogoMarca = dscCatalogoMarca;
	}

	public List<CatalogoLote> getCatalogoLotes() {
		return this.catalogoLotes;
	}

	public void setCatalogoLotes(List<CatalogoLote> catalogoLotes) {
		this.catalogoLotes = catalogoLotes;
	}

	public CatalogoLote addCatalogoLote(CatalogoLote catalogoLote) {
		getCatalogoLotes().add(catalogoLote);
		catalogoLote.setCatalogoMarca(this);

		return catalogoLote;
	}

	public CatalogoLote removeCatalogoLote(CatalogoLote catalogoLote) {
		getCatalogoLotes().remove(catalogoLote);
		catalogoLote.setCatalogoMarca(null);

		return catalogoLote;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public ProductoPorNumeroEntrega getProductoPorNumeroEntrega() {
		return this.productoPorNumeroEntrega;
	}

	public void setProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		this.productoPorNumeroEntrega = productoPorNumeroEntrega;
	}

	public ProductoPresentacion getProductoPresentacion() {
		return this.productoPresentacion;
	}

	public void setProductoPresentacion(ProductoPresentacion productoPresentacion) {
		this.productoPresentacion = productoPresentacion;
	}

	public List<PresentacionMarca> getPresentacionMarcas() {
		return this.presentacionMarcas;
	}

	public void setPresentacionMarcas(List<PresentacionMarca> presentacionMarcas) {
		this.presentacionMarcas = presentacionMarcas;
	}

	public PresentacionMarca addPresentacionMarca(PresentacionMarca presentacionMarca) {
		getPresentacionMarcas().add(presentacionMarca);
		presentacionMarca.setCatalogoMarca(this);

		return presentacionMarca;
	}

	public PresentacionMarca removePresentacionMarca(PresentacionMarca presentacionMarca) {
		getPresentacionMarcas().remove(presentacionMarca);
		presentacionMarca.setCatalogoMarca(null);

		return presentacionMarca;
	}

	public List<VolumenConvertidoEnvace> getVolumenConvertidoEnvaces() {
		return this.volumenConvertidoEnvaces;
	}

	public void setVolumenConvertidoEnvaces(List<VolumenConvertidoEnvace> volumenConvertidoEnvaces) {
		this.volumenConvertidoEnvaces = volumenConvertidoEnvaces;
	}

	public VolumenConvertidoEnvace addVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().add(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setCatalogoMarca(this);

		return volumenConvertidoEnvace;
	}

	public VolumenConvertidoEnvace removeVolumenConvertidoEnvace(VolumenConvertidoEnvace volumenConvertidoEnvace) {
		getVolumenConvertidoEnvaces().remove(volumenConvertidoEnvace);
		volumenConvertidoEnvace.setCatalogoMarca(null);

		return volumenConvertidoEnvace;
	}

}