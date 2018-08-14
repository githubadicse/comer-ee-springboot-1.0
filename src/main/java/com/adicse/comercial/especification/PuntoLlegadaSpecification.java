package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.PuntoLlegada;






public class PuntoLlegadaSpecification implements Specification<PuntoLlegada> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PuntoLlegada filter;

	public PuntoLlegadaSpecification(PuntoLlegada filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<PuntoLlegada> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdPuntoLlegada() != null ){
			p.getExpressions().add(cb.equal(root.get("idPuntoLlegada") , filter.getIdPuntoLlegada() ));
		}
		if (StringUtils.isNotBlank(filter.getDireccion() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("direccion")),"%"+ filter.getDireccion().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
