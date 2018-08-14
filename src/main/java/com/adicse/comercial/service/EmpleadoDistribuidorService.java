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

import com.adicse.comercial.dao.IEmpleadoDistribuidorDao;
import com.adicse.comercial.especification.EmpleadoDistribuidorSpecification;
import com.adicse.comercial.model.EmpleadoDistribuidor;
import com.adicse.comercial.shared.CustomFilterSpec;


@Service
@Transactional
public class EmpleadoDistribuidorService  implements IAdicseService<EmpleadoDistribuidor, String> {
	
	@Autowired
	private IEmpleadoDistribuidorDao iEmpleadoDistribuidorDao; 

	@Override
	public Page<EmpleadoDistribuidor> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		EmpleadoDistribuidor entidad = new EmpleadoDistribuidor();
		entidad.setIdEmpleadoDistribuidor(null);
		entidad.setIdEmpleadoDistribuidor(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (EmpleadoDistribuidor) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<EmpleadoDistribuidor> spec = new EmpleadoDistribuidorSpecification(entidad);
		
		Page<EmpleadoDistribuidor> lista = iEmpleadoDistribuidorDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpleadoDistribuidor> getall() {
		// TODO Auto-generated method stub
		return (List<EmpleadoDistribuidor>) iEmpleadoDistribuidorDao.findAll();
	}

	@Override
	public List<EmpleadoDistribuidor> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpleadoDistribuidor grabar(EmpleadoDistribuidor entidad) {
		// TODO Auto-generated method stub
		return iEmpleadoDistribuidorDao.save(entidad) ;
	}

	@Override
	public void delete(EmpleadoDistribuidor entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		iEmpleadoDistribuidorDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EmpleadoDistribuidor> findbyid(String id) {
		// TODO Auto-generated method stub
		return iEmpleadoDistribuidorDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EmpleadoDistribuidor> EntityForSpecificatios(EmpleadoDistribuidor entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}


}
