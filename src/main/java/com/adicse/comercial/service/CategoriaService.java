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

import com.adicse.comercial.dao.ICategoriaDao;
import com.adicse.comercial.especification.CategoriaSpecification;
import com.adicse.comercial.model.Categoria;
import com.adicse.comercial.shared.CustomFilterSpec;


@Service
@Transactional
public class CategoriaService implements IAdicseService<Categoria, Integer> {
	
	
	@Autowired
	private ICategoriaDao iCategoriaDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Categoria> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Categoria entidad = new Categoria();
		entidad.setIdcategoria(null);
		entidad.setDsccategoria(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Categoria) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Categoria> spec = new CategoriaSpecification(entidad);
		
		Page<Categoria> lista = iCategoriaDao.findAll(spec,pageable);
 

		//
		return lista;

	}

	@Override
	public List<Categoria> getall() {
		// TODO Auto-generated method stub
		return (List<Categoria>) iCategoriaDao.findAll();
	}

	@Override
	public List<Categoria> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria grabar(Categoria entidad) {
		// TODO Auto-generated method stub
		Integer nexId = 0;
		if(entidad.getIdcategoria() == 0){
			nexId = iCategoriaDao.getMax()==null?1:iCategoriaDao.getMax() + 1;
			entidad.setIdcategoria(nexId);
		}
		return iCategoriaDao.save(entidad);
	}

	@Override
	public void delete(Categoria entidad) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iCategoriaDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return iCategoriaDao.existsById(id);
	}

	@Override
	public Optional<Categoria> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iCategoriaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return iCategoriaDao.count();
	}




	public List<Categoria> findByDsccategoriaContainingIgnoreCaseOrderByDsccategoriaAsc(String dsccategoria){
		return iCategoriaDao.findByDsccategoriaContainingIgnoreCaseOrderByDsccategoriaAsc (dsccategoria);
	}

	@Override
	public Optional<Categoria> EntityForSpecificatios(Categoria entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
