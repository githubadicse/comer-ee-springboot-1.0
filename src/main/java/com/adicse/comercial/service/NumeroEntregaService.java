package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.INumeroEntregaDao;
import com.adicse.comercial.model.NumeroEntrega;

@Service
@Transactional
public class NumeroEntregaService implements IAdicseService<NumeroEntrega, Integer>  {

	@Autowired
	private INumeroEntregaDao iNumeroEntregaDao;
	
	
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
	public List<NumeroEntrega> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NumeroEntrega> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumeroEntrega grabar(NumeroEntrega entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NumeroEntrega entidad) {
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
	public Optional<NumeroEntrega> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iNumeroEntregaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<NumeroEntrega> EntityForSpecificatios(NumeroEntrega entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<NumeroEntrega> getByNumeroEntrega(Integer numeroEntrega){
		return iNumeroEntregaDao.getByNumeroEntrega(numeroEntrega);
	}


}
