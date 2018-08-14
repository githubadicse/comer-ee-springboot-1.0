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

import com.adicse.comercial.dao.IPuntoLlegadaDao;
import com.adicse.comercial.especification.PuntoLlegadaSpecification;
import com.adicse.comercial.model.PuntoLlegada;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class PuntoLlegadaService implements IAdicseService<PuntoLlegada, Integer> {
	
	@Autowired
	private IPuntoLlegadaDao iPuntoLlegadaDao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<PuntoLlegada> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		PuntoLlegada entidad = new PuntoLlegada();
		entidad.setIdPuntoLlegada(null);
		entidad.setDireccion(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (PuntoLlegada) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<PuntoLlegada> spec = new PuntoLlegadaSpecification(entidad);
		
		Page<PuntoLlegada> lista = iPuntoLlegadaDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<PuntoLlegada> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PuntoLlegada> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PuntoLlegada grabar(PuntoLlegada entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PuntoLlegada entidad) {
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
	public Optional<PuntoLlegada> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PuntoLlegada> EntityForSpecificatios(PuntoLlegada entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
