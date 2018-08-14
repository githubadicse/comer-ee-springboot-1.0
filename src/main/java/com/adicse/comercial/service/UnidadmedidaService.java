package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IUnidadmedidaDao;
import com.adicse.comercial.especification.UnidadmedidaSpecification;
import com.adicse.comercial.model.Unidadmedida;
import com.adicse.comercial.shared.CustomFilterSpec;


@Service
@Transactional
public class UnidadmedidaService implements IAdicseService<Unidadmedida, Integer> {

	@Autowired
	private IUnidadmedidaDao iUnidadmedidaDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Unidadmedida> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable = PageRequest.of(pagenumber, rows, sort);

		/*
		 * instanciamos una entidad la cual servira de contenedor para realizar
		 * el filtro este evento sera llenado dentro de una funcion que esta en
		 * CustomFilterSpec se le debe pasar dos parametros, uno la entidad que
		 * queremos llenar con los datos del segundo parametro que es un objecto
		 * json que se para en la variable filter
		 */
		Unidadmedida entidad = new Unidadmedida();
		entidad.setIdunidadmedida(null);
		entidad.setDscunidadmedida(null);

		CustomFilterSpec efs = new CustomFilterSpec();
		try {

			entidad = (Unidadmedida) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Specification nos permite agregar implicitamente los where que se
		 * pasaran al evento findAll, Esto sucede en CrudRepository
		 */
		Specification<Unidadmedida> spec = new UnidadmedidaSpecification(entidad);

		Page<Unidadmedida> lista = iUnidadmedidaDao.findAll(spec, pageable);

		//
		return lista;
	}

	@Override
	public List<Unidadmedida> getall() {
		// TODO Auto-generated method stub
		return (List<Unidadmedida>) iUnidadmedidaDao.findAll();
	}

	@Override
	public List<Unidadmedida> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unidadmedida grabar(Unidadmedida entidad) {
		// TODO Auto-generated method stub
		Integer id;
		if(entidad.getIdunidadmedida() == 0) {
			id = iUnidadmedidaDao.getMax() == null?1:iUnidadmedidaDao.getMax() + 1;
			entidad.setIdunidadmedida(id);
		}
		return iUnidadmedidaDao.save(entidad);
	}

	@Override
	public void delete(Unidadmedida entidad) {
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
	public Optional<Unidadmedida> findbyid(Integer id) {
		// TODO Auto-generated method stub
		
		return iUnidadmedidaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Unidadmedida> EntityForSpecificatios(Unidadmedida entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	





}
