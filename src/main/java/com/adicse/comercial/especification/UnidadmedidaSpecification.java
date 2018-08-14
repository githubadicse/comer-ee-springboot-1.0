package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Unidadmedida;





public class UnidadmedidaSpecification implements Specification<Unidadmedida> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Unidadmedida filter;

	public UnidadmedidaSpecification(Unidadmedida filter) {
		this.filter = filter;
	}	


	@Override
	public Predicate toPredicate(Root<Unidadmedida> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
	Predicate p = cb.conjunction();
		
		if( filter.getIdunidadmedida() != null ){
			p.getExpressions().add(cb.equal(root.get("idunidadmedida") , filter.getIdunidadmedida() ));
		}
		if (StringUtils.isNotBlank(filter.getDscunidadmedida() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscunidadmedida")),"%"+ filter.getDscunidadmedida().toLowerCase() + "%"));
		}
	

		return p;		
	}



}
