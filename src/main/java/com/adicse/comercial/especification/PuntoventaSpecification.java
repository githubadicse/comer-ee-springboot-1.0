package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Puntoventa;






public class PuntoventaSpecification implements Specification<Puntoventa> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Puntoventa filter;

	public PuntoventaSpecification(Puntoventa filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Puntoventa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdpuntoventa() != null ){
			p.getExpressions().add(cb.equal(root.get("idpuntoventa") , filter.getIdpuntoventa() ));
		}
		
		
		if (StringUtils.isNotBlank(filter.getDscpuntoventa() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscpuntoventa")),"%"+ filter.getDscpuntoventa().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
