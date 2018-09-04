package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IMedioPagoDao;
import com.adicse.comercial.model.MedioPago;



@Service
@Transactional
public class MedioPagoService implements IAdicseService<MedioPago, Integer>{

	@Autowired
	private IMedioPagoDao iMedioPagoDao;
	
	
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
	public List<MedioPago> getall() {
		// TODO Auto-generated method stub
		return (List<MedioPago>) iMedioPagoDao.findAll();		
	}

	@Override
	public List<MedioPago> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedioPago grabar(MedioPago entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MedioPago entidad) {
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
	public Optional<MedioPago> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MedioPago> EntityForSpecificatios(MedioPago entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
