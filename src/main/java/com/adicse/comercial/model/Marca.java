package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idmarca;

	private String dscmarca;

	//bi-directional many-to-one association to CatalogoMarca
	@OneToMany(mappedBy="marca")
	private List<CatalogoMarca> catalogoMarcas;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="marca")
	private List<Producto> productos;

	public Marca() {
	}

	public Integer getIdmarca() {
		return this.idmarca;
	}

	public void setIdmarca(Integer idmarca) {
		this.idmarca = idmarca;
	}

	public String getDscmarca() {
		return this.dscmarca;
	}

	public void setDscmarca(String dscmarca) {
		this.dscmarca = dscmarca;
	}

	public List<CatalogoMarca> getCatalogoMarcas() {
		return this.catalogoMarcas;
	}

	public void setCatalogoMarcas(List<CatalogoMarca> catalogoMarcas) {
		this.catalogoMarcas = catalogoMarcas;
	}

	public CatalogoMarca addCatalogoMarca(CatalogoMarca catalogoMarca) {
		getCatalogoMarcas().add(catalogoMarca);
		catalogoMarca.setMarca(this);

		return catalogoMarca;
	}

	public CatalogoMarca removeCatalogoMarca(CatalogoMarca catalogoMarca) {
		getCatalogoMarcas().remove(catalogoMarca);
		catalogoMarca.setMarca(null);

		return catalogoMarca;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setMarca(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setMarca(null);

		return producto;
	}

}