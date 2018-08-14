package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Ing001;



public class Ing001Specification implements Specification<Ing001> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Ing001 filter;

	public Ing001Specification(Ing001 filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Ing001> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIding001() != null ){
			p.getExpressions().add(cb.equal(root.get("iding001") , filter.getIding001() ));
		}

		if (StringUtils.isNotBlank(filter.getAlmacen().getDscalmacen() )) {
			p.getExpressions().add(cb.like (cb.lower(root.get("almacen").get("dscalmacen") ),"%" +filter.getAlmacen().getDscalmacen().toLowerCase() + "%" ));
		}	
		
//		if (StringUtils.isNotBlank(filter.getDscproducto() )) {
//			p.getExpressions().add(cb.like(cb.lower(root.get("dscproducto")),"%"+ filter.getDscproducto().toLowerCase() + "%"));
//		}
//	
//		if (StringUtils.isNotBlank(filter.getUnidadmedida().getDscunidadmedida() )) {
//			p.getExpressions().add(cb.equal (cb.lower(root.get("unidadmedida").get("dscunidadmedida") ),filter.getUnidadmedida().getDscunidadmedida().toLowerCase() ));
//		}		
		return p;		
		
	}



}
