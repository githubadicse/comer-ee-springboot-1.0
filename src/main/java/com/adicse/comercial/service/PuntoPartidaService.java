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

import com.adicse.comercial.dao.IPuntoPartidaDao;
import com.adicse.comercial.especification.PuntoPartidaSpecification;
import com.adicse.comercial.model.PuntoPartida;
import com.adicse.comercial.shared.CustomFilterSpec;


@Service
@Transactional
public class PuntoPartidaService implements IAdicseService<PuntoPartida, Integer> {
	
	@Autowired
	private IPuntoPartidaDao iPuntoPartidaDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<PuntoPartida> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		PuntoPartida entidad = new PuntoPartida();
		entidad.setIdPuntoPartida(null);
		entidad.setDireccion(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (PuntoPartida) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<PuntoPartida> spec = new PuntoPartidaSpecification(entidad);
		
		Page<PuntoPartida> lista = iPuntoPartidaDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<PuntoPartida> getall() {
		// TODO Auto-generated method stub
		return (List<PuntoPartida>) iPuntoPartidaDao.findAll();
	}

	@Override
	public List<PuntoPartida> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PuntoPartida grabar(PuntoPartida entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdPuntoPartida() == 0) {
			Integer idPuntoPartida = iPuntoPartidaDao.getMax()==null?1:iPuntoPartidaDao.getMax()+1;
			entidad.setIdPuntoPartida(idPuntoPartida);
		}
		return iPuntoPartidaDao.save(entidad);
	}

	@Override
	public void delete(PuntoPartida entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iPuntoPartidaDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PuntoPartida> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iPuntoPartidaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PuntoPartida> EntityForSpecificatios(PuntoPartida entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
