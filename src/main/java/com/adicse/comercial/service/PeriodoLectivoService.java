package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IPeriodoLectivoDao;
import com.adicse.comercial.model.PeriodoLectivo;


@Service
@Transactional
public class PeriodoLectivoService implements IAdicseService<PeriodoLectivo, Integer> {
	
	@Autowired
	private IPeriodoLectivoDao iPeriodoLectivoDao; 

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
	public List<PeriodoLectivo> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PeriodoLectivo> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeriodoLectivo grabar(PeriodoLectivo entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PeriodoLectivo entidad) {
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
	public Optional<PeriodoLectivo> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iPeriodoLectivoDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PeriodoLectivo> EntityForSpecificatios(PeriodoLectivo entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
