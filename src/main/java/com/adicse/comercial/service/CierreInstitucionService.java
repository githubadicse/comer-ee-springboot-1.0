package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICierreInstitucionDao;
import com.adicse.comercial.model.CierreInstitucion;

@Service
@Transactional
public class CierreInstitucionService implements IAdicseService<CierreInstitucion, String> {
	
	@Autowired
	private ICierreInstitucionDao iCierreInstitucionDao;

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
	public List<CierreInstitucion> getall() {
		// TODO Auto-generated method stub
		return (List<CierreInstitucion>) iCierreInstitucionDao.findAll();
	}

	@Override
	public List<CierreInstitucion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CierreInstitucion grabar(CierreInstitucion entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CierreInstitucion entidad) {
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
	public Optional<CierreInstitucion> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CierreInstitucion> EntityForSpecificatios(CierreInstitucion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
