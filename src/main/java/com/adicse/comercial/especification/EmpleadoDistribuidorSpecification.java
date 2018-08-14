package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import com.adicse.comercial.model.EmpleadoDistribuidor;






public class EmpleadoDistribuidorSpecification implements Specification<EmpleadoDistribuidor> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EmpleadoDistribuidor filter;

	public EmpleadoDistribuidorSpecification(EmpleadoDistribuidor filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<EmpleadoDistribuidor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdEmpleadoDistribuidor() != null ){
			p.getExpressions().add(cb.equal(root.get("idEmpleadoDistribuidor") , filter.getIdEmpleadoDistribuidor() ));
		}
		if (StringUtils.isNotBlank(filter.getNombres() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("nombres")),"%"+ filter.getNombres() .toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
