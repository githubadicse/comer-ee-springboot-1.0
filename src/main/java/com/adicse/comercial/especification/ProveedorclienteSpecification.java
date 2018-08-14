package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Proveedorcliente;



public class ProveedorclienteSpecification implements Specification<Proveedorcliente> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Proveedorcliente filter;

	public ProveedorclienteSpecification(Proveedorcliente filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Proveedorcliente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdproveedorcliente() != null ){
			p.getExpressions().add(cb.equal(root.get("idproveedorcliente") , filter.getIdproveedorcliente() ));
		}
		if (StringUtils.isNotBlank(filter.getRazonsocial() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("razonsocial")),"%"+ filter.getRazonsocial().toLowerCase() + "%"));
		}
	
		if (filter.getDocumentoidentificacion().getIddocumentoidentificacion() != null ) {
			p.getExpressions().add(cb.equal (cb.lower(root.get("documentoidentificacion").get("iddocumentoidentificacion") ),filter.getDocumentoidentificacion().getIddocumentoidentificacion() ));
		}	
		if (StringUtils.isNotBlank(filter.getNrodocumento() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("nrodocumento")),"%"+ filter.getNrodocumento().toLowerCase() + "%"));
		}		
		return p;		
		
	}



}
