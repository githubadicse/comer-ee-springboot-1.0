package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.INivelEducacionDao;
import com.adicse.comercial.model.NivelEducacion;

@Service
@Transactional
public class NivelEducacionService implements IAdicseService<NivelEducacion, Integer> {
	
	@Autowired
	private INivelEducacionDao iNivelEducacionDao;
	
	
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
	public List<NivelEducacion> getall() {
		// TODO Auto-generated method stub
		return (List<NivelEducacion>) iNivelEducacionDao.findAll();
	}

	@Override
	public List<NivelEducacion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NivelEducacion grabar(NivelEducacion entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NivelEducacion entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<NivelEducacion> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iNivelEducacionDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<NivelEducacion> EntityForSpecificatios(NivelEducacion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
