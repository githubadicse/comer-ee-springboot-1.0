package com.adicse.comercial.especification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.adicse.comercial.model.RequerimientoVolumen001;






public class RequerimientoVolumen001Specification implements Specification<RequerimientoVolumen001> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final RequerimientoVolumen001 filter;
	private final String codigosModulares;
	private String array[];
	@SuppressWarnings("unused")
	private List<String> lst;

	public RequerimientoVolumen001Specification(RequerimientoVolumen001 filter, String codigosModulares) {
		this.filter = filter;
		this.codigosModulares = codigosModulares;
		
		if(this.codigosModulares.length() > 0) {
			this.array = this.codigosModulares.split(";");
			for (int i = 0; i < array.length; i++) {
				array[i] = array[i].trim();
			}
			
	
		}else {
			//this.array = {"x"};
		}
		
	}	

	@Override
	public Predicate toPredicate(Root<RequerimientoVolumen001> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.conjunction() ;	
		if( filter.getIdRequerimientoVolumen001() != null ){
			p.getExpressions().add(cb.equal(root.get("idRequerimientoVolumen001") , filter.getIdRequerimientoVolumen001() ));
		}
		if (StringUtils.isNotBlank(filter.getCentroPoblado() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("centroPoblado")),"%"+ filter.getCentroPoblado().toLowerCase() + "%"));
		}
	
	
		if (StringUtils.isNotBlank(filter.getUbigeo().getNombreDepartamento() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("ubigeo").get("nombreDepartamento") ),"%"+ filter.getUbigeo().getNombreDepartamento().toLowerCase() + "%"));
		}	
		
		if (StringUtils.isNotBlank(filter.getUbigeo().getNombreProvincia() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("ubigeo").get("nombreProvincia") ),"%"+ filter.getUbigeo().getNombreProvincia().toLowerCase() + "%"));
		}	
		
		if (StringUtils.isNotBlank(filter.getUbigeo().getNombreDistrito() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("ubigeo").get("nombreDistrito") ),"%"+ filter.getUbigeo().getNombreDistrito().toLowerCase() + "%"));
		}	
		
		if (StringUtils.isNotBlank(filter.getCodigomodularIinstitucionEducativa().getCodigoModular() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("codigomodularIinstitucionEducativa").get("codigoModular") ),"%"+ filter.getCodigomodularIinstitucionEducativa().getCodigoModular().toLowerCase() + "%"));
		}	
		
		if (StringUtils.isNotBlank(filter.getCodigomodularIinstitucionEducativa().getNombreInstitucionEducativa() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("codigomodularIinstitucionEducativa").get("nombreInstitucionEducativa") ),"%"+ filter.getCodigomodularIinstitucionEducativa().getNombreInstitucionEducativa().toLowerCase() + "%"));
		}	
		if (StringUtils.isNotBlank(filter.getCodigomodularIinstitucionEducativa().getDireccionInstitucionEducativa() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("codigomodularIinstitucionEducativa").get("direccionInstitucionEducativa") ),"%"+ filter.getCodigomodularIinstitucionEducativa().getDireccionInstitucionEducativa().toLowerCase() + "%"));
		}	
		
		if (StringUtils.isNotBlank(filter.getNivelEducacion().getDscNivelEducacion())) {
			p.getExpressions().add(cb.like(cb.lower(root.get("nivelEducacion").get("dscNivelEducacion") ),"%"+ filter.getNivelEducacion().getDscNivelEducacion().toLowerCase() + "%"));
		}	
		
		if (StringUtils.isNotBlank(filter.getRegionAlimentaria().getDscRegionAlimentaria())) {
			p.getExpressions().add(cb.like(cb.lower(root.get("regionAlimentaria").get("dscRegionAlimentaria") ),"%"+ filter.getRegionAlimentaria().getDscRegionAlimentaria().toLowerCase() + "%"));
		}
		
		if (StringUtils.isNotBlank(filter.getHorarioAlimentacion().getDscHorarioAlimentacion() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("horarioAlimentacion").get("dscHorarioAlimentacion") ),"%"+ filter.getHorarioAlimentacion().getDscHorarioAlimentacion().toLowerCase() + "%"));
		}
		if (StringUtils.isNotBlank(filter.getEntregaPorItem().getItemEntrega().getDscitem() )) {
			p.getExpressions().add(cb.like(cb.lower(root.get("entregaPorItem").get("itemEntrega").get("dscitem") ),"%"+ filter.getEntregaPorItem().getItemEntrega().getDscitem().toLowerCase() + "%"));
			
		}
		
		if (StringUtils.isNotBlank(filter.getEntregaPorItem().getItemEntrega().getItem() )) {
			p.getExpressions().add(cb.equal(cb.lower(root.get("entregaPorItem").get("itemEntrega").get("item") ),filter.getEntregaPorItem().getItemEntrega().getItem().toLowerCase() ));
			
		}
		
		Collection<Predicate> predicates = new ArrayList<>();
		if(array != null) {
			for (int i = 0; i < this.array.length; i++) {
				String codigo = this.array[i];
				Predicate pp = cb.equal(  root.get("codigomodularIinstitucionEducativa").get("codigoModular") , codigo );
				predicates.add(pp);
				
				
				
			}	
			//Predicate or = cb.or(predicates.toArray(new Predicate[predicates.size()]));
			p.getExpressions().add(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		}
		
		
		p.getExpressions().add(cb.equal(root.get("entregaPorItem").get("itemEntrega").get("anno") , filter.getEntregaPorItem().getItemEntrega().getAnno() ) );
		
		p.getExpressions().add(cb.equal(root.get("entregaPorItem").get("numeroEntrega").get("numeroEntregaValor") , filter.getEntregaPorItem().getNumeroEntrega().getNumeroEntregaValor()) );
		
		return p;		
		
	}



}
