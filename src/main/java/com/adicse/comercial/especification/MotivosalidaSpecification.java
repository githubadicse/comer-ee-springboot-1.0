package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import com.adicse.comercial.model.Motivosalida;





public class MotivosalidaSpecification implements Specification<Motivosalida> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Motivosalida filter;

	public MotivosalidaSpecification(Motivosalida filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Motivosalida> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdmotivosalida() != null ){
			p.getExpressions().add(cb.equal(root.get("idMotivosalida") , filter.getIdmotivosalida() ));
		}
		if (StringUtils.isNotBlank(filter.getDscmotivosalida() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscMotivosalida")),"%"+ filter.getDscmotivosalida().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
