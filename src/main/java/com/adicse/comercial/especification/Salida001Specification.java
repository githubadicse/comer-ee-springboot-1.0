package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Salida001;



public class Salida001Specification implements Specification<Salida001> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Salida001 filter;

	public Salida001Specification(Salida001 filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Salida001> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdsalida001() != null ){
			p.getExpressions().add(cb.equal(root.get("idsalida001") , filter.getIdsalida001() ));
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
