package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idmenu;

	private String icon;

	private String label;

	private Integer leaf;

	private String name;

	private String obs;

	private Integer orden;

	private String routerlink;

	private String state;

	private String type;

	public Menu() {
	}

	public String getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(String idmenu) {
		this.idmenu = idmenu;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getRouterlink() {
		return this.routerlink;
	}

	public void setRouterlink(String routerlink) {
		this.routerlink = routerlink;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}