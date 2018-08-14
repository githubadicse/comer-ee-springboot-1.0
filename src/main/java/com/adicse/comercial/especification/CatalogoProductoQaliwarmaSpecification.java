package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.CatalogoProductoQaliwarma;



public class CatalogoProductoQaliwarmaSpecification implements Specification<CatalogoProductoQaliwarma> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CatalogoProductoQaliwarma filter;

	public CatalogoProductoQaliwarmaSpecification(CatalogoProductoQaliwarma filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<CatalogoProductoQaliwarma> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdCatalogoProductoQaliwarma() != null ){
			p.getExpressions().add(cb.equal(root.get("idCatalogoProductoQaliwarma") , filter.getIdCatalogoProductoQaliwarma() ));
		}
		if (StringUtils.isNotBlank(filter.getDscCatalogoProductoQaliwarma() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscCatalogoProductoQaliwarma")),"%"+ filter.getDscCatalogoProductoQaliwarma() .toLowerCase() + "%"));
		}
	
		if (StringUtils.isNotBlank(filter.getUnidadmedida().getDscunidadmedida() )) {
			p.getExpressions().add(cb.equal (cb.lower(root.get("unidadmedida").get("dscunidadmedida") ),filter.getUnidadmedida().getDscunidadmedida().toLowerCase() ));
		}		
		if( filter.getAnno() != null) {
			p.getExpressions().add(cb.equal(root.get("anno"), filter.getAnno()));
		}
		return p;		
		
	}



}
