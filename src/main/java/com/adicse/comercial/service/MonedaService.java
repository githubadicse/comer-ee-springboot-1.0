package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IMonedaDao;
import com.adicse.comercial.model.Moneda;


@Service
@Transactional
public class MonedaService implements IAdicseService<Moneda, Integer> {
	
	@Autowired
	private IMonedaDao iMonedaDao;

	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Moneda> getall() {
		// TODO Auto-generated method stub
		return (List<Moneda>) iMonedaDao.findAll();
	}

	@Override
	public List<Moneda> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Moneda grabar(Moneda entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Moneda entidad) {
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
	public Optional<Moneda> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Moneda> EntityForSpecificatios(Moneda entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
