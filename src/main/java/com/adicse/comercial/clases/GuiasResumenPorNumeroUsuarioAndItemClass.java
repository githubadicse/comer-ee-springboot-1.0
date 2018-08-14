package com.adicse.comercial.clases;

import java.math.BigDecimal;
import java.util.List;

import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.HorarioAlimentacion;
import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.model.NumeroEntrega;

public interface GuiasResumenPorNumeroUsuarioAndItemClass {
	
	public NumeroEntrega getNumeroEntrega();
	public EntregaPorItem getEntregaPorItem();
	public HorarioAlimentacion getHorarioAlimentacion();
	
	public ItemEntrega getItemEntrega();
	public Integer getCntIe();
	public BigDecimal getNumeroUsuarios();
	public GuiaRemision001 getGuiaRemision001();
	
	public List<GuiasResumenPorNumeroUsuarioAndItemClass> getLista();

}
