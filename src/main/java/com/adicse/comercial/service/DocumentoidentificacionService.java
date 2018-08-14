package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IDocumentoidentificacionDao;
import com.adicse.comercial.model.Documentoidentificacion;


@Service
@Transactional
public class DocumentoidentificacionService implements IAdicseService<Documentoidentificacion,Integer> {

	@Autowired
	private IDocumentoidentificacionDao iDocumentoidentificacionDao;
	
	
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
	public List<Documentoidentificacion> getall() {
		// TODO Auto-generated method stub
		return (List<Documentoidentificacion>) iDocumentoidentificacionDao.findAll();
	
	}

	@Override
	public List<Documentoidentificacion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documentoidentificacion grabar(Documentoidentificacion entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Documentoidentificacion entidad) {
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
	public Optional<Documentoidentificacion> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Documentoidentificacion> EntityForSpecificatios(Documentoidentificacion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
