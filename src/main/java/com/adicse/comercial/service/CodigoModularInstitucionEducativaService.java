package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICodigoModularInstitucionEducativaDao;
import com.adicse.comercial.model.CodigomodularIinstitucionEducativa;

@Service
@Transactional
public class CodigoModularInstitucionEducativaService implements IAdicseService<CodigomodularIinstitucionEducativa, String> {
	
	@Autowired
	private ICodigoModularInstitucionEducativaDao iCodigoModularInstitucionEducativaDao;

	
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
	public List<CodigomodularIinstitucionEducativa> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CodigomodularIinstitucionEducativa> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodigomodularIinstitucionEducativa grabar(CodigomodularIinstitucionEducativa entidad) {
		// TODO Auto-generated method stub
		return iCodigoModularInstitucionEducativaDao.save(entidad);
	}

	@Override
	public void delete(CodigomodularIinstitucionEducativa entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return iCodigoModularInstitucionEducativaDao.existsById(id);
	}

	@Override
	public Optional<CodigomodularIinstitucionEducativa> findbyid(String id) {
		// TODO Auto-generated method stub
		return iCodigoModularInstitucionEducativaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CodigomodularIinstitucionEducativa> EntityForSpecificatios(
			CodigomodularIinstitucionEducativa entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CodigomodularIinstitucionEducativa> getCodigoModularInstitucionEducativaByIds(Set<String> lst) {
		return iCodigoModularInstitucionEducativaDao.getCodigoModularInstitucionEducativaByIds(lst);
	}



}
