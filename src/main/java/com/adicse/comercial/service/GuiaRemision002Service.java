package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IGuiaRemision002Dao;
import com.adicse.comercial.model.GuiaRemision002;

@Service
@Transactional
public class GuiaRemision002Service implements IAdicseService<GuiaRemision002, String> {
	
	@Autowired
	private IGuiaRemision002Dao iGuiaRemision002Dao ;

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
	public List<GuiaRemision002> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuiaRemision002> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiaRemision002 grabar(GuiaRemision002 entidad) {
		// TODO Auto-generated method stub
		return iGuiaRemision002Dao.save(entidad);
	}

	@Override
	public void delete(GuiaRemision002 entidad) {
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
	public Optional<GuiaRemision002> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GuiaRemision002> EntityForSpecificatios(GuiaRemision002 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
