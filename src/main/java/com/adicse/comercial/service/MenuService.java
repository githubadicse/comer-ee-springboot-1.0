package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IMenuDao;
import com.adicse.comercial.model.Menu;

@Service
@Transactional
public class MenuService implements IAdicseService<Menu, String>  {
	
	@Autowired
	private IMenuDao iMenuDao;
	
	
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
	public List<Menu> getall() {
		// TODO Auto-generated method stub
		return (List<Menu>) iMenuDao.findByOrderByOrdenAsc();
	}

	@Override
	public List<Menu> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu grabar(Menu entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Menu entidad) {
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
	public Optional<Menu> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Menu> EntityForSpecificatios(Menu entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
