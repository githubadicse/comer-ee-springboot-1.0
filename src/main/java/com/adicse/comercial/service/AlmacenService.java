package com.adicse.comercial.service;

import static com.adicse.comercial.specification.SpecificationBuilder.selectFrom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IAlmacenDao;
import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;

@Service
@Transactional
public class AlmacenService implements IAdicseService<Almacen, Integer> {
	
	@Autowired
	private IAlmacenDao iAlmacenDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Almacen> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase().equals("DESC") ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Almacen> lista = selectFrom(iAlmacenDao).where(f).findPage(pageable);

		return lista;
	}

	@Override
	public List<Almacen> getall() {
		// TODO Auto-generated method stub
		return (List<Almacen>) iAlmacenDao.findAll() ;
	}

	@Override
	public List<Almacen> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Almacen grabar(Almacen entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdalmacen() == 0) {
			Integer id = iAlmacenDao.getMax() == null?1:iAlmacenDao.getMax() + 1;
			entidad.setIdalmacen(id);
		}
		
		iAlmacenDao.save(entidad);
		return entidad;
	}

	@Override
	public void delete(Almacen entidad) {
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
	public Optional<Almacen> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iAlmacenDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Almacen> EntityForSpecificatios(Almacen entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Almacen> getAlmacensByIdFilial(Integer idfilial ){
		return iAlmacenDao.getAlmacensByIdFilial(idfilial);
	}

	

}
