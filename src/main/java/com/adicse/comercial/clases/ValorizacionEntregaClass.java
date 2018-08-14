package com.adicse.comercial.clases;

import java.io.Serializable;
import java.util.List;

public class ValorizacionEntregaClass implements Serializable {
	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<GuiasResumenPorNumeroUsuarioAndItemClass> listaValorizacion;
	
	public String nombreItem;
	
	

	public String getNombreItem() {
		return nombreItem;
	}

	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

	public List<GuiasResumenPorNumeroUsuarioAndItemClass> getListaValorizacion() {
		return listaValorizacion;
	}

	public void setListaValorizacion(List<GuiasResumenPorNumeroUsuarioAndItemClass> listaValorizacion) {
		this.listaValorizacion = listaValorizacion;
	}
	
		public ValorizacionEntregaClass() {
		
	}
	

}
