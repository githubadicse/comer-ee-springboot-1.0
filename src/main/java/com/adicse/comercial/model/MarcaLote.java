package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the marca_lote database table.
 * 
 */
@Entity
@Table(name="marca_lote")
@NamedQuery(name="MarcaLote.findAll", query="SELECT m FROM MarcaLote m")
public class MarcaLote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_marca_lote")
	private String idMarcaLote;

	private Integer cantidad;

	private BigDecimal peso;

	//bi-directional many-to-one association to CatalogoLote
	@ManyToOne
	@JoinColumn(name="id_catalogo_lote")
	private CatalogoLote catalogoLote;

	//bi-directional many-to-one association to PresentacionMarca
	@ManyToOne
	@JoinColumn(name="id_presentacion_marca")
	private PresentacionMarca presentacionMarca;

	public MarcaLote() {
	}

	public String getIdMarcaLote() {
		return this.idMarcaLote;
	}

	public void setIdMarcaLote(String idMarcaLote) {
		this.idMarcaLote = idMarcaLote;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public CatalogoLote getCatalogoLote() {
		return this.catalogoLote;
	}

	public void setCatalogoLote(CatalogoLote catalogoLote) {
		this.catalogoLote = catalogoLote;
	}

	public PresentacionMarca getPresentacionMarca() {
		return this.presentacionMarca;
	}

	public void setPresentacionMarca(PresentacionMarca presentacionMarca) {
		this.presentacionMarca = presentacionMarca;
	}

}