package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IHorarioAlimentacionDao;
import com.adicse.comercial.model.HorarioAlimentacion;

@Service
@Transactional
public class HorarioAlimentacionService implements IAdicseService<HorarioAlimentacion, Integer> {

	@Autowired
	private IHorarioAlimentacionDao iHorarioAlimentacionDao;
	
	
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
	public List<HorarioAlimentacion> getall() {
		// TODO Auto-generated method stub
		return (List<HorarioAlimentacion>) iHorarioAlimentacionDao.findAll();
	}

	@Override
	public List<HorarioAlimentacion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HorarioAlimentacion grabar(HorarioAlimentacion entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(HorarioAlimentacion entidad) {
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
	public Optional<HorarioAlimentacion> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iHorarioAlimentacionDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HorarioAlimentacion> EntityForSpecificatios(HorarioAlimentacion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
