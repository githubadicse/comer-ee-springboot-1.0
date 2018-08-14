package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Chofer;






public class ChoferSpecification implements Specification<Chofer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Chofer filter;

	public ChoferSpecification(Chofer filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Chofer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdChofer() != null ){
			p.getExpressions().add(cb.equal(root.get("idChofer") , filter.getIdChofer() ));
		}
		if (StringUtils.isNotBlank(filter.getDni() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dni")),"%"+ filter.getDni().toLowerCase() + "%"));
		}
	
		if (StringUtils.isNotBlank(filter.getNumeroBrevete() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("numeroBrevete")),"%"+ filter.getNumeroBrevete().toLowerCase() + "%"));
		}
		return p;		
		
	}



}
