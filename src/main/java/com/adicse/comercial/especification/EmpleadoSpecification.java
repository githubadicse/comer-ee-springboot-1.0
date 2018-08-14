package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Empleado;






public class EmpleadoSpecification implements Specification<Empleado> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Empleado filter;

	public EmpleadoSpecification(Empleado filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Empleado> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdempleado() != null ){
			p.getExpressions().add(cb.equal(root.get("idempleado") , filter.getIdempleado() ));
		}
		if (StringUtils.isNotBlank(filter.getNomempleado() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscEmpleado")),"%"+ filter.getNomempleado().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
