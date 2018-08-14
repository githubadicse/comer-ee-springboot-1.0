package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.adicse.comercial.dao.IMarcaDao;
import com.adicse.comercial.especification.MarcaSpecification;
import com.adicse.comercial.model.Marca;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class MarcaService implements IAdicseService<Marca, Integer> {
	
	@Autowired
	private IMarcaDao iMarcaDao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Marca> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Marca entidad = new Marca();
		entidad.setIdmarca(null);
		entidad.setDscmarca(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Marca) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Marca> spec = new MarcaSpecification(entidad);
		
		Page<Marca> lista = iMarcaDao.findAll(spec,pageable);
 

		//
		return lista;

	}

	@Override
	public List<Marca> getall() {
		// TODO Auto-generated method stub
		return (List<Marca>) iMarcaDao.findAll();
	}

	@Override
	public List<Marca> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Marca grabar(Marca entidad) {
		// TODO Auto-generated method stub
		Integer id = 0;
		if(entidad.getIdmarca() == 0){
			if(iMarcaDao.getMax() != null) {
				entidad.setIdmarca(iMarcaDao.getMax() + 1 );
			}else {
				entidad.setIdmarca(id + 1 );
			}
			
		}
		return iMarcaDao.save(entidad);
	}

	@Override
	public void delete(Marca entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iMarcaDao.deleteById(id); 
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Marca> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iMarcaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Marca> EntityForSpecificatios(Marca entidad, Object filter) {
		// TODO Auto-generated method stub

		return null;
	}
	
	public List<Marca> findByDscmarcaContainingIgnoreCaseOrderByDscmarcaAsc(String dscmarca){
		return iMarcaDao.findByDscmarcaContainingIgnoreCaseOrderByDscmarcaAsc (dscmarca);
	}

	
	


}
