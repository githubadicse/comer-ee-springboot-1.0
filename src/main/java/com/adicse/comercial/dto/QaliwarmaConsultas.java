package com.adicse.comercial.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.service.GuiaRemision001Service;

@Service
@Transactional
public class QaliwarmaConsultas {
	
	@SuppressWarnings("unused")
	@Autowired
	private GuiaRemision001Service guiaRemision001Service;
	
	public List<GuiaRemision001> getListaGuiasRemisionByAnnoNumeroEntrega(Integer anno,Integer numeroEntrega){
		
		
		return null;
	}

}
