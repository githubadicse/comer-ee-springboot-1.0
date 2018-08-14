package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICatalogoMarcaDao;
import com.adicse.comercial.model.CatalogoMarca;

@Service
@Transactional
public class CatalogoMarcaService implements IAdicseService<CatalogoMarca, String> {
	
	@Autowired
	private ICatalogoMarcaDao iCatalogoMarcaDao;

	
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
	public List<CatalogoMarca> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogoMarca> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//@Transactional(readOnly=false)
	public CatalogoMarca grabar(CatalogoMarca entidad) {
		// TODO Auto-generated method stub
		return iCatalogoMarcaDao.save(entidad);
	}

	@Override
	public void delete(CatalogoMarca entidad) {
		// TODO Auto-generated method stub
		iCatalogoMarcaDao.delete(entidad);
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		iCatalogoMarcaDao.deleteById(id) ;
		System.out.println("Eliminando CatalogoMarca : " + id);
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CatalogoMarca> findbyid(String id) {
		// TODO Auto-generated method stub
		return iCatalogoMarcaDao.findById(id);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CatalogoMarca> EntityForSpecificatios(CatalogoMarca entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CatalogoMarca> getCatalogoMarcaByIdProductoPorNumeroEntrega(String idProductoPorNumeroEntrega,Integer anno, Integer numeroEntrega) {
		
		
		return iCatalogoMarcaDao.getCatalogoMarcaByIdProductoPorNumeroEntrega(idProductoPorNumeroEntrega,anno,numeroEntrega);
		
	}
	
	public CatalogoMarca getCatalogoMarcaByIdProductoPorNumeroEntregaIdProductoPresentacion(
			String idProductoPorNumeroEntrega,
			String idProductoPresentacion
			
			){
		return iCatalogoMarcaDao.getCatalogoMarcaByIdProductoPorNumeroEntregaIdProductoPresentacion(idProductoPorNumeroEntrega, idProductoPresentacion);
		
	}
	

}
