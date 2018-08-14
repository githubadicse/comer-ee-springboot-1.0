package com.adicse.comercial.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.GuiaRemision001;





public class GuiaRemision001Specification implements Specification<GuiaRemision001> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GuiaRemision001 filter;

	public GuiaRemision001Specification(GuiaRemision001 filter) {
		this.filter = filter;
	}	

	@Override
	public Predicate toPredicate(Root<GuiaRemision001> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction();
		
		if( filter.getSerie() != null ){
			p.getExpressions().add(cb.equal(root.get("serie") , filter.getSerie() ));
		}
		
		if( filter.getNumero() != null ){
			p.getExpressions().add(cb.equal(root.get("numero") , filter.getNumero() ));
		}
	
		if (StringUtils.isNotBlank(filter.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular() )) {
			p.getExpressions().add(cb.equal (cb.lower(root.get("requerimientoVolumen001").get("codigomodularIinstitucionEducativa").get("codigoModular") ),
					filter.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getCodigoModular().toLowerCase() ));
		}	
	
		if (StringUtils.isNotBlank(filter.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getNombreInstitucionEducativa() )) {
			p.getExpressions().add(cb.equal (cb.lower(root.get("requerimientoVolumen001").get("codigomodularIinstitucionEducativa").get("nombreInstitucionEducativa") ),
					filter.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().getNombreInstitucionEducativa().toLowerCase() ));
		}	
		if (StringUtils.isNotBlank(filter.getRequerimientoVolumen001().getEntregaPorItem(). getItemEntrega().getItem() )) {
			p.getExpressions().add(cb.equal (cb.lower(root.get("requerimientoVolumen001").get("itemEntrega").get("item") ),
					filter.getRequerimientoVolumen001().getEntregaPorItem(). getItemEntrega().getItem().toLowerCase() ));
		}	
		return p;		
		
	}



}
