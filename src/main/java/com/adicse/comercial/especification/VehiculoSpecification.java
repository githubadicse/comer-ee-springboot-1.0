package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Vehiculo;






public class VehiculoSpecification implements Specification<Vehiculo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Vehiculo filter;

	public VehiculoSpecification(Vehiculo filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Vehiculo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdVehiculo() != null ){
			p.getExpressions().add(cb.equal(root.get("idVehiculo") , filter.getIdVehiculo() ));
		}
		if (StringUtils.isNotBlank(filter.getNumeroPlaca() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("numeroPlaca")),"%"+ filter.getNumeroPlaca().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
