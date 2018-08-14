package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the item_entrega database table.
 * 
 */
@Entity
@Table(name="item_entrega")
@NamedQuery(name="ItemEntrega.findAll", query="SELECT i FROM ItemEntrega i")
public class ItemEntrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String item;

	private Integer anno;

	private String dscitem;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="itemEntrega")
	private List<EntregaPorItem> entregaPorItems;

	public ItemEntrega() {
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getDscitem() {
		return this.dscitem;
	}

	public void setDscitem(String dscitem) {
		this.dscitem = dscitem;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setItemEntrega(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setItemEntrega(null);

		return entregaPorItem;
	}

}