package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICatalogoMultiplicarNivelEducacionDao;
import com.adicse.comercial.model.CatalogoMultiplicarNivelEducacion;

@Service
@Transactional
public class CatalogoMultiplicarNivelEducacionService implements IAdicseService<CatalogoMultiplicarNivelEducacion, String> {
	
	@Autowired
	private ICatalogoMultiplicarNivelEducacionDao iCatalogoMultiplicarNivelEducacionDao;

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
	public List<CatalogoMultiplicarNivelEducacion> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogoMultiplicarNivelEducacion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogoMultiplicarNivelEducacion grabar(CatalogoMultiplicarNivelEducacion entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CatalogoMultiplicarNivelEducacion entidad) {
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
	public Optional<CatalogoMultiplicarNivelEducacion> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CatalogoMultiplicarNivelEducacion> EntityForSpecificatios(CatalogoMultiplicarNivelEducacion entidad,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CatalogoMultiplicarNivelEducacion> getCatalogoNivelEducacionByPresentacion(
			Integer anno,
			Integer numeroEntrega, 
			String idProducto, 
			String idProductoPresentacion,
			Integer idNivelEducacion){
		
		return iCatalogoMultiplicarNivelEducacionDao.getCatalogoNivelEducacionByPresentacion(anno, numeroEntrega, idProducto, idProductoPresentacion, idNivelEducacion);
		
	}
	
	public List<CatalogoMultiplicarNivelEducacion> getCatalogoNivelEducacionByPeriodoNumeroEntrega(
			Integer anno,
			Integer numeroEntrega){
		
		return iCatalogoMultiplicarNivelEducacionDao.getCatalogoNivelEducacionByPeriodoNumeroEntrega(anno, numeroEntrega);
		
	}

	
	
	

}
