package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Perfil;






public class PerfilSpecification implements Specification<Perfil> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Perfil filter;

	public PerfilSpecification(Perfil filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Perfil> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdperfil() != null ){
			p.getExpressions().add(cb.equal(root.get("idperfil") , filter.getIdperfil() ));
		}
		if (StringUtils.isNotBlank(filter.getDscperfil() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscAlmacen")),"%"+ filter.getDscperfil().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
