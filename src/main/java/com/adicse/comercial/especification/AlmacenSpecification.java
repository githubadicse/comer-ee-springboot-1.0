package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Almacen;






public class AlmacenSpecification implements Specification<Almacen> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Almacen filter;

	public AlmacenSpecification(Almacen filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Almacen> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdalmacen() != null ){
			p.getExpressions().add(cb.equal(root.get("idalmacen") , filter.getIdalmacen() ));
		}
		if (StringUtils.isNotBlank(filter.getDscalmacen() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscAlmacen")),"%"+ filter.getDscalmacen().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
