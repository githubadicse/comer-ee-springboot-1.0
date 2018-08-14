package com.adicse.comercial.clases;

import java.util.List;

public class TreeNodo {
	
	String idmenu;
	String label;
	String icon;
	Integer leaf;
	String routerLink;
	
	List<TreeNodo> items;
	
	
	
	public TreeNodo() {
		//lstTreeNodo.add(new TreeNodo());
		//System.out.println(" Constructor de TreeNodo ... ");
		
	}

	public String getLabel() {
		return label;
	}

	
	
	public String getRouterlink() {
		return routerLink;
	}

	public void setRouterlink(String routerlink) {
		this.routerLink = routerlink;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(String idmenu) {
		this.idmenu = idmenu;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<TreeNodo> getLstTreeNodo() {
		return items;
	}

	public void setLstTreeNodo(List<TreeNodo> items) {
		this.items = items;
	}
	
	
		
}
