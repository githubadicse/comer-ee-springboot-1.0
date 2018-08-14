package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IRequerimientoVolumen002Dao;
import com.adicse.comercial.model.RequerimientoVolumen002;

@Service
@Transactional
public class RequerimientoVolumen002Service implements IAdicseService<RequerimientoVolumen002, String> {
	
	@Autowired
	private IRequerimientoVolumen002Dao iRequerimientoVolumen002Dao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequerimientoVolumen002> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequerimientoVolumen002> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequerimientoVolumen002 grabar(RequerimientoVolumen002 entidad) {
		// TODO Auto-generated method stub
		return iRequerimientoVolumen002Dao.save(entidad);
	}

	@Override
	public void delete(RequerimientoVolumen002 entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RequerimientoVolumen002> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RequerimientoVolumen002> EntityForSpecificatios(RequerimientoVolumen002 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly=true)
	public List<RequerimientoVolumen002> getRequerimientoVolumen002ByIdR1(
			String idrequerimiento001,
			Integer numeroEntregaValor
			){
		return iRequerimientoVolumen002Dao.getRequerimientoVolumen002ByIdR1(idrequerimiento001, numeroEntregaValor);
		
	}
	
	public void deleteAll(Integer anno,Integer numeroEntrega) {
		iRequerimientoVolumen002Dao.deleteAll(anno,numeroEntrega);
	}



}
