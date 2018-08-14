package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import com.adicse.comercial.model.Tipodocumento;





public class TipodocumentoSpecification implements Specification<Tipodocumento> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Tipodocumento filter;

	public TipodocumentoSpecification(Tipodocumento filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Tipodocumento> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdTipoDocumento() != null ){
			p.getExpressions().add(cb.equal(root.get("idTipodocumento") , filter.getIdTipoDocumento() ));
		}
		if (StringUtils.isNotBlank(filter.getDscTipoDocumento() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscTipodocumento")),"%"+ filter.getDscTipoDocumento().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
