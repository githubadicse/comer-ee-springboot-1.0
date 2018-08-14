package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICatalogoLoteDao;
import com.adicse.comercial.model.CatalogoLote;

@Service
@Transactional
public class CatalogoLoteService implements IAdicseService<CatalogoLote, String> {
	
	@Autowired
	private ICatalogoLoteDao iCatalogoLoteDao; 

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
	public List<CatalogoLote> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogoLote> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogoLote grabar(CatalogoLote entidad) {
		// TODO Auto-generated method stub
		return iCatalogoLoteDao.save(entidad);
	}

	@Override
	public void delete(CatalogoLote entidad) {
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
	public Optional<CatalogoLote> findbyid(String id) {
		// TODO Auto-generated method stub
		return iCatalogoLoteDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CatalogoLote> EntityForSpecificatios(CatalogoLote entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CatalogoLote> lstCatalogoLoteByIdCatalogoMarca(String idCatalogoMarca){
		return iCatalogoLoteDao.lstCatalogoLoteByIdCatalogoMarca(idCatalogoMarca);
	}


	

}
