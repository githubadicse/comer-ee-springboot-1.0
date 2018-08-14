package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the numero_entrega database table.
 * 
 */
@Entity
@Table(name="numero_entrega")
@NamedQuery(name="NumeroEntrega.findAll", query="SELECT n FROM NumeroEntrega n")
public class NumeroEntrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_numero_entrega")
	private Integer idNumeroEntrega;

	@Column(name="dsc_numero_entrega")
	private String dscNumeroEntrega;

	@Column(name="numero_entrega_valor")
	private Integer numeroEntregaValor;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="numeroEntrega")
	private List<EntregaPorItem> entregaPorItems;

	//bi-directional many-to-one association to RequerimientoVolumen002
	@OneToMany(mappedBy="numeroEntrega")
	private List<RequerimientoVolumen002> requerimientoVolumen002s;

	public NumeroEntrega() {
	}

	public Integer getIdNumeroEntrega() {
		return this.idNumeroEntrega;
	}

	public void setIdNumeroEntrega(Integer idNumeroEntrega) {
		this.idNumeroEntrega = idNumeroEntrega;
	}

	public String getDscNumeroEntrega() {
		return this.dscNumeroEntrega;
	}

	public void setDscNumeroEntrega(String dscNumeroEntrega) {
		this.dscNumeroEntrega = dscNumeroEntrega;
	}

	public Integer getNumeroEntregaValor() {
		return this.numeroEntregaValor;
	}

	public void setNumeroEntregaValor(Integer numeroEntregaValor) {
		this.numeroEntregaValor = numeroEntregaValor;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setNumeroEntrega(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setNumeroEntrega(null);

		return entregaPorItem;
	}

	public List<RequerimientoVolumen002> getRequerimientoVolumen002s() {
		return this.requerimientoVolumen002s;
	}

	public void setRequerimientoVolumen002s(List<RequerimientoVolumen002> requerimientoVolumen002s) {
		this.requerimientoVolumen002s = requerimientoVolumen002s;
	}

	public RequerimientoVolumen002 addRequerimientoVolumen002(RequerimientoVolumen002 requerimientoVolumen002) {
		getRequerimientoVolumen002s().add(requerimientoVolumen002);
		requerimientoVolumen002.setNumeroEntrega(this);

		return requerimientoVolumen002;
	}

	public RequerimientoVolumen002 removeRequerimientoVolumen002(RequerimientoVolumen002 requerimientoVolumen002) {
		getRequerimientoVolumen002s().remove(requerimientoVolumen002);
		requerimientoVolumen002.setNumeroEntrega(null);

		return requerimientoVolumen002;
	}

}