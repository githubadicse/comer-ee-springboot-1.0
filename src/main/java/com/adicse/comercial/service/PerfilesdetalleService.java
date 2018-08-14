package com.adicse.comercial.service;
import static com.adicse.comercial.specification.SpecificationBuilder.selectFrom;
import com.adicse.comercial.specification.Filter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IPerfilesdetalleDao;
import com.adicse.comercial.model.Perfilesdetalle;
import com.adicse.comercial.utilitarios.Idunico;

@Service
@Transactional
public class PerfilesdetalleService implements IAdicseService<Perfilesdetalle, Integer> {

	@Autowired
	private IPerfilesdetalleDao iPerfilesdetalleDao;
			
	private Idunico idUnico;
	
	@Override
	public Perfilesdetalle grabar(Perfilesdetalle entidad) {
		if ( entidad.getIdsysperfilesdetalle() == null ) { 
			String id =  idUnico.getIdunico();
			entidad.setIdsysperfilesdetalle(id);
		}
		
		iPerfilesdetalleDao.save(entidad);
		return entidad;
	}
	
	public List<Perfilesdetalle> findByFilter(Filter filter){
		return selectFrom(iPerfilesdetalleDao).where(filter).findAll(); 
	}
	
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
	public List<Perfilesdetalle> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfilesdetalle> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Perfilesdetalle entidad) {
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
	public Optional<Perfilesdetalle> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Perfilesdetalle> EntityForSpecificatios(Perfilesdetalle entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
