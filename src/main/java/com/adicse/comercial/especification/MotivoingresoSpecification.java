package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import com.adicse.comercial.model.Motivoingreso;





public class MotivoingresoSpecification implements Specification<Motivoingreso> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Motivoingreso filter;

	public MotivoingresoSpecification(Motivoingreso filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Motivoingreso> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdmotivoingreso() != null ){
			p.getExpressions().add(cb.equal(root.get("idMotivoingreso") , filter.getIdmotivoingreso() ));
		}
		if (StringUtils.isNotBlank(filter.getDscmotivoingreso() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscMotivoingreso")),"%"+ filter.getDscmotivoingreso().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
