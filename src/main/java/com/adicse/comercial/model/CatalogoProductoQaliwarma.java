package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogo_producto_qaliwarma database table.
 * 
 */
@Entity
@Table(name="catalogo_producto_qaliwarma")
@NamedQuery(name="CatalogoProductoQaliwarma.findAll", query="SELECT c FROM CatalogoProductoQaliwarma c")
public class CatalogoProductoQaliwarma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_catalogo_producto_qaliwarma")
	private String idCatalogoProductoQaliwarma;

	private Integer anno;

	@Column(name="dsc_catalogo_producto_qaliwarma")
	private String dscCatalogoProductoQaliwarma;

	private Boolean entrega1;

	private Boolean entrega10;

	private Boolean entrega11;

	private Boolean entrega12;

	private Boolean entrega2;

	private Boolean entrega3;

	private Boolean entrega4;

	private Boolean entrega5;

	private Boolean entrega6;

	private Boolean entrega7;

	private Boolean entrega8;

	private Boolean entrega9;

	@Column(name="print_lote")
	private Integer printLote;

	//bi-directional many-to-one association to CatalogoMultiplicarNivelEducacion
	@OneToMany(mappedBy="catalogoProductoQaliwarma")
	private List<CatalogoMultiplicarNivelEducacion> catalogoMultiplicarNivelEducacions;

	//bi-directional many-to-one association to Envase
	@ManyToOne
	@JoinColumn(name="id_envase")
	private Envase envase;

	//bi-directional many-to-one association to UnidadMedidaTrabajo
	@ManyToOne
	@JoinColumn(name="id_unidad_medida_trabajo")
	private UnidadMedidaTrabajo unidadMedidaTrabajo;

	//bi-directional many-to-one association to Unidadmedida
	@ManyToOne
	@JoinColumn(name="idunidadmedida")
	private Unidadmedida unidadmedida;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="catalogoProductoQaliwarma")
	private List<Producto> productos;

	//bi-directional many-to-one association to ProductoPorNumeroEntrega
	@OneToMany(mappedBy="catalogoProductoQaliwarma")
	private List<ProductoPorNumeroEntrega> productoPorNumeroEntregas;

	//bi-directional many-to-one association to ProductoPresentacion
	@OneToMany(mappedBy="catalogoProductoQaliwarma", cascade={CascadeType.ALL})
	private List<ProductoPresentacion> productoPresentacions;

	public CatalogoProductoQaliwarma() {
	}

	public String getIdCatalogoProductoQaliwarma() {
		return this.idCatalogoProductoQaliwarma;
	}

	public void setIdCatalogoProductoQaliwarma(String idCatalogoProductoQaliwarma) {
		this.idCatalogoProductoQaliwarma = idCatalogoProductoQaliwarma;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getDscCatalogoProductoQaliwarma() {
		return this.dscCatalogoProductoQaliwarma;
	}

	public void setDscCatalogoProductoQaliwarma(String dscCatalogoProductoQaliwarma) {
		this.dscCatalogoProductoQaliwarma = dscCatalogoProductoQaliwarma;
	}

	public Boolean getEntrega1() {
		return this.entrega1;
	}

	public void setEntrega1(Boolean entrega1) {
		this.entrega1 = entrega1;
	}

	public Boolean getEntrega10() {
		return this.entrega10;
	}

	public void setEntrega10(Boolean entrega10) {
		this.entrega10 = entrega10;
	}

	public Boolean getEntrega11() {
		return this.entrega11;
	}

	public void setEntrega11(Boolean entrega11) {
		this.entrega11 = entrega11;
	}

	public Boolean getEntrega12() {
		return this.entrega12;
	}

	public void setEntrega12(Boolean entrega12) {
		this.entrega12 = entrega12;
	}

	public Boolean getEntrega2() {
		return this.entrega2;
	}

	public void setEntrega2(Boolean entrega2) {
		this.entrega2 = entrega2;
	}

	public Boolean getEntrega3() {
		return this.entrega3;
	}

	public void setEntrega3(Boolean entrega3) {
		this.entrega3 = entrega3;
	}

	public Boolean getEntrega4() {
		return this.entrega4;
	}

	public void setEntrega4(Boolean entrega4) {
		this.entrega4 = entrega4;
	}

	public Boolean getEntrega5() {
		return this.entrega5;
	}

	public void setEntrega5(Boolean entrega5) {
		this.entrega5 = entrega5;
	}

	public Boolean getEntrega6() {
		return this.entrega6;
	}

	public void setEntrega6(Boolean entrega6) {
		this.entrega6 = entrega6;
	}

	public Boolean getEntrega7() {
		return this.entrega7;
	}

	public void setEntrega7(Boolean entrega7) {
		this.entrega7 = entrega7;
	}

	public Boolean getEntrega8() {
		return this.entrega8;
	}

	public void setEntrega8(Boolean entrega8) {
		this.entrega8 = entrega8;
	}

	public Boolean getEntrega9() {
		return this.entrega9;
	}

	public void setEntrega9(Boolean entrega9) {
		this.entrega9 = entrega9;
	}

	public Integer getPrintLote() {
		return this.printLote;
	}

	public void setPrintLote(Integer printLote) {
		this.printLote = printLote;
	}

	public List<CatalogoMultiplicarNivelEducacion> getCatalogoMultiplicarNivelEducacions() {
		return this.catalogoMultiplicarNivelEducacions;
	}

	public void setCatalogoMultiplicarNivelEducacions(List<CatalogoMultiplicarNivelEducacion> catalogoMultiplicarNivelEducacions) {
		this.catalogoMultiplicarNivelEducacions = catalogoMultiplicarNivelEducacions;
	}

	public CatalogoMultiplicarNivelEducacion addCatalogoMultiplicarNivelEducacion(CatalogoMultiplicarNivelEducacion catalogoMultiplicarNivelEducacion) {
		getCatalogoMultiplicarNivelEducacions().add(catalogoMultiplicarNivelEducacion);
		catalogoMultiplicarNivelEducacion.setCatalogoProductoQaliwarma(this);

		return catalogoMultiplicarNivelEducacion;
	}

	public CatalogoMultiplicarNivelEducacion removeCatalogoMultiplicarNivelEducacion(CatalogoMultiplicarNivelEducacion catalogoMultiplicarNivelEducacion) {
		getCatalogoMultiplicarNivelEducacions().remove(catalogoMultiplicarNivelEducacion);
		catalogoMultiplicarNivelEducacion.setCatalogoProductoQaliwarma(null);

		return catalogoMultiplicarNivelEducacion;
	}

	public Envase getEnvase() {
		return this.envase;
	}

	public void setEnvase(Envase envase) {
		this.envase = envase;
	}

	public UnidadMedidaTrabajo getUnidadMedidaTrabajo() {
		return this.unidadMedidaTrabajo;
	}

	public void setUnidadMedidaTrabajo(UnidadMedidaTrabajo unidadMedidaTrabajo) {
		this.unidadMedidaTrabajo = unidadMedidaTrabajo;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCatalogoProductoQaliwarma(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCatalogoProductoQaliwarma(null);

		return producto;
	}

	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregas() {
		return this.productoPorNumeroEntregas;
	}

	public void setProductoPorNumeroEntregas(List<ProductoPorNumeroEntrega> productoPorNumeroEntregas) {
		this.productoPorNumeroEntregas = productoPorNumeroEntregas;
	}

	public ProductoPorNumeroEntrega addProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		getProductoPorNumeroEntregas().add(productoPorNumeroEntrega);
		productoPorNumeroEntrega.setCatalogoProductoQaliwarma(this);

		return productoPorNumeroEntrega;
	}

	public ProductoPorNumeroEntrega removeProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		getProductoPorNumeroEntregas().remove(productoPorNumeroEntrega);
		productoPorNumeroEntrega.setCatalogoProductoQaliwarma(null);

		return productoPorNumeroEntrega;
	}

	public List<ProductoPresentacion> getProductoPresentacions() {
		return this.productoPresentacions;
	}

	public void setProductoPresentacions(List<ProductoPresentacion> productoPresentacions) {
		this.productoPresentacions = productoPresentacions;
	}

	public ProductoPresentacion addProductoPresentacion(ProductoPresentacion productoPresentacion) {
		getProductoPresentacions().add(productoPresentacion);
		productoPresentacion.setCatalogoProductoQaliwarma(this);

		return productoPresentacion;
	}

	public ProductoPresentacion removeProductoPresentacion(ProductoPresentacion productoPresentacion) {
		getProductoPresentacions().remove(productoPresentacion);
		productoPresentacion.setCatalogoProductoQaliwarma(null);

		return productoPresentacion;
	}

}
