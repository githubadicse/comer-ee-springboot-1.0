package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.MontoContratado;






public class MontoContratadoSpecification implements Specification<MontoContratado> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MontoContratado filter;

	public MontoContratadoSpecification(MontoContratado filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<MontoContratado> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
//		
//		if( filter.getIdalmacen() != null ){
//			p.getExpressions().add(cb.equal(root.get("idalmacen") , filter.getIdalmacen() ));
//		}
//		if (StringUtils.isNotBlank(filter.getDscalmacen() )) {
//			p.getExpressions().add(cb.like(cb.lower(root.get("dscAlmacen")),"%"+ filter.getDscalmacen().toLowerCase() + "%"));
//		}
		p.getExpressions().add(cb.equal(root.get("entregaPorItem").get("itemEntrega").get("anno") , filter.getEntregaPorItem().getItemEntrega().getAnno()));
		p.getExpressions().add(cb.equal(root.get("entregaPorItem").get("numeroEntrega").get("numeroEntregaValor") , filter.getEntregaPorItem().getNumeroEntrega().getNumeroEntregaValor() ));

		return p;		
		
	}



}
