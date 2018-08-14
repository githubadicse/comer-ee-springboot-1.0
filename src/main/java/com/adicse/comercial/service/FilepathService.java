package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IFilepathDao;
import com.adicse.comercial.model.Filepath;


@Service
@Transactional
public class FilepathService implements IAdicseService<Filepath, Integer>  {
	
	@Autowired
	private IFilepathDao iFilapathDao;
	
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
	public List<Filepath> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filepath> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filepath grabar(Filepath entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Filepath entidad) {
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
	public Optional<Filepath> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iFilapathDao.findById(id) ;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Filepath> EntityForSpecificatios(Filepath entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
