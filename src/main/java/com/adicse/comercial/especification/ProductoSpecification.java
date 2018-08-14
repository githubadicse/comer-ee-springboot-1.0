package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Producto;



public class ProductoSpecification implements Specification<Producto> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Producto filter;

	public ProductoSpecification(Producto filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdproducto() != null ){
			p.getExpressions().add(cb.equal(root.get("idproducto") , filter.getIdproducto() ));
		}
		if (StringUtils.isNotBlank(filter.getDscproducto() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscproducto")),"%"+ filter.getDscproducto().toLowerCase() + "%"));
		}
	
		if (StringUtils.isNotBlank(filter.getUnidadmedida().getDscunidadmedida() )) {
			p.getExpressions().add(cb.equal (cb.lower(root.get("unidadmedida").get("dscunidadmedida") ),filter.getUnidadmedida().getDscunidadmedida().toLowerCase() ));
		}		
		return p;		
		
	}



}
