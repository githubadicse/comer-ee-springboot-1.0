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

import com.adicse.comercial.dao.IChoferDao;
import com.adicse.comercial.especification.ChoferSpecification;
import com.adicse.comercial.model.Chofer;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class ChoferService implements IAdicseService<Chofer, Integer> {
	
	@Autowired	
	private IChoferDao iChoferDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Chofer> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		Chofer entidad = new Chofer();
		entidad.setIdChofer(null);
		entidad.setDni(null);
		entidad.setNumeroBrevete(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Chofer) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Chofer> spec = new ChoferSpecification(entidad);
		
		Page<Chofer> lista = iChoferDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<Chofer> getall() {
		// TODO Auto-generated method stub
		return (List<Chofer>) iChoferDao.findAll();
	}

	@Override
	public List<Chofer> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chofer grabar(Chofer entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdChofer() == 0) {
			Integer id = iChoferDao.getMax()== null?1:iChoferDao.getMax()+1;
			entidad.setIdChofer(id);
		}
		return iChoferDao.save(entidad);
	}

	@Override
	public void delete(Chofer entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iChoferDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Chofer> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iChoferDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Chofer> EntityForSpecificatios(Chofer entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
