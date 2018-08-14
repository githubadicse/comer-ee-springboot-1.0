package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Transportista;






public class TransportistaSpecification implements Specification<Transportista> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Transportista filter;

	public TransportistaSpecification(Transportista filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Transportista> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdTransportista() != null ){
			p.getExpressions().add(cb.equal(root.get("idTransportista") , filter.getIdTransportista() ));
		}
		if (StringUtils.isNotBlank(filter.getProveedorcliente().getRazonsocial() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("proveedorcliente").get("razonsocial") ),"%"+ filter.getProveedorcliente().getRazonsocial().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
