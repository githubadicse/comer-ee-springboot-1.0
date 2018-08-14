package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.Usuario;






public class UsuarioSpecification implements Specification<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Usuario filter;

	public UsuarioSpecification(Usuario filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdusuario() != null ){
			p.getExpressions().add(cb.equal(root.get("idusuario") , filter.getIdusuario() ));
		}
		
		
		if (StringUtils.isNotBlank(filter.getNomusuario() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscAlmacen")),"%"+ filter.getNomusuario().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
