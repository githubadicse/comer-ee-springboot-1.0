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

import com.adicse.comercial.dao.IAlmacenDao;
import com.adicse.comercial.especification.AlmacenSpecification;
import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class AlmacenService implements IAdicseService<Almacen, Integer> {
	
	@Autowired
	private IAlmacenDao iAlmacenDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Almacen> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		Almacen entidad = new Almacen();
		entidad.setIdalmacen(null);
		entidad.setDscalmacen(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Almacen) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Almacen> spec = new AlmacenSpecification(entidad);
		
		Page<Almacen> lista = iAlmacenDao.findAll(spec,pageable);
 

		//
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
