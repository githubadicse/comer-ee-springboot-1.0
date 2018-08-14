package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IPikingDao;
import com.adicse.comercial.model.Piking;

@Service
@Transactional
public class PikingService implements IAdicseService<Piking, String> {
	
	@Autowired
	private IPikingDao iPikingDao;

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
	public List<Piking> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Piking> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piking grabar(Piking entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Piking entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		iPikingDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Piking> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Piking> EntityForSpecificatios(Piking entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteAll(List<Piking> lst) {
		iPikingDao.deleteAll(lst);
	}
	
	public Piking getPikingByIdVolumenConvertidoEnvace(String idVolumenConvertidoEnvace) {
		return iPikingDao.getPikingByIdVolumenConvertidoEnvace(idVolumenConvertidoEnvace);
	}
	
	public List<Piking> getPikingByIdCatalogoLote(String idCatalogoLote){
		return iPikingDao.getPikingByIdCatalogoLote(idCatalogoLote);
		
	}
	
	public void deleteByIdCatalogoLote(String idCatalogoLote){
		 iPikingDao.deleteByIdCatalogoLote(idCatalogoLote);
		
	}



}
