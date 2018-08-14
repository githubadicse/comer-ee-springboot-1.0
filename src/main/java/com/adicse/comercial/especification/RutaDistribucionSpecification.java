package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.RutaDistribucion;






public class RutaDistribucionSpecification implements Specification<RutaDistribucion> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final RutaDistribucion filter;

	public RutaDistribucionSpecification(RutaDistribucion filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<RutaDistribucion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getIdRutaDistribucion() != null ){
			p.getExpressions().add(cb.equal(root.get("idRutaDistribucion") , filter.getIdRutaDistribucion() ));
		}
		
		if( filter.getAnno() != null ){
			p.getExpressions().add(cb.equal(root.get("anno") , filter.getAnno() ));
		}
		
		if( filter.getNumeroEntrega() != null ){
			p.getExpressions().add(cb.equal(root.get("numeroEntrega") , filter.getNumeroEntrega() ));
		}
		
		if (StringUtils.isNotBlank(filter.getDscRutaDistribucion() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("dscRutaDistribucion")),"%"+ filter.getDscRutaDistribucion().toLowerCase() + "%"));
		}
		
		if (StringUtils.isNotBlank(filter.getEmpleadoDistribuidor().getNombres() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("empleadoDistribuidor").get("nombres")  ),"%"+ filter.getEmpleadoDistribuidor().getNombres().toLowerCase() + "%"));
		}
	

		return p;		
		
	}



}
