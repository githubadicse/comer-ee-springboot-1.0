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

import com.adicse.comercial.dao.IMotivosalidaDao;
import com.adicse.comercial.especification.MotivosalidaSpecification;
import com.adicse.comercial.model.Motivosalida;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class MotivosalidaService implements IAdicseService<Motivosalida, Integer> {
	
	@Autowired
	private IMotivosalidaDao iMotivosalidaDao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Motivosalida> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		Motivosalida entidad = new Motivosalida();
		entidad.setIdmotivosalida(null);
		entidad.setDscmotivosalida(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Motivosalida) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Motivosalida> spec = new MotivosalidaSpecification(entidad);
		
		Page<Motivosalida> lista = iMotivosalidaDao.findAll(spec,pageable);
 

		//
		return  lista;
	}

	@Override
	public List<Motivosalida> getall() {
		// TODO Auto-generated method stub
		return (List<Motivosalida>) iMotivosalidaDao.findAll();
	}

	@Override
	public List<Motivosalida> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Motivosalida grabar(Motivosalida entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Motivosalida entidad) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iMotivosalidaDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Motivosalida> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iMotivosalidaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Motivosalida> EntityForSpecificatios(Motivosalida entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
