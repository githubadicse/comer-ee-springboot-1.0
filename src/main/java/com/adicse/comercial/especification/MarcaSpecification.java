package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import com.adicse.comercial.model.Marca;





public class MarcaSpecification implements Specification<Marca> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Marca filter;

	public MarcaSpecification(Marca filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Marca> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdmarca() != null ){
			p.getExpressions().add(cb.equal(root.get("idmarca") , filter.getIdmarca() ));
		}
		if (StringUtils.isNotBlank(filter.getDscmarca() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscmarca")),"%"+ filter.getDscmarca().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
