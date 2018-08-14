package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IFilialDao;
import com.adicse.comercial.model.Filial;


@Service
@Transactional
public class FilialService implements IAdicseService<Filial, Integer> {
	
	@Autowired
	private IFilialDao iFilialDao;
	
	
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
	public List<Filial> getall() {
		// TODO Auto-generated method stub
		return (List<Filial>) iFilialDao.findAll();
	}

	@Override
	public List<Filial> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filial grabar(Filial entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Filial entidad) {
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
	public Optional<Filial> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Filial> EntityForSpecificatios(Filial entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
