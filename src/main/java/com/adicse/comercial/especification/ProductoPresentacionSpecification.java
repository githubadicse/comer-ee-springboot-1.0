package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.ProductoPresentacion;

public class ProductoPresentacionSpecification implements Specification<ProductoPresentacion>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public Predicate toPredicate(Root<ProductoPresentacion> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		
		p.getExpressions().add(cb.equal(root.get("catalogoProductoQaliwarma").get("productoPresentacions").get("anno") , 2018 ));
		p.getExpressions().add(cb.equal(root.get("catalogoProductoQaliwarma").get("productoPresentacions").get("numeroEntrega") , 1 ));
	

		return p;		
	}	

	

}
