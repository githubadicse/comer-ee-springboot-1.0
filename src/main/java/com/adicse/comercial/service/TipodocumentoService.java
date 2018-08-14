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

import com.adicse.comercial.dao.ITipodocumentoDao;
import com.adicse.comercial.especification.TipodocumentoSpecification;
import com.adicse.comercial.model.Tipodocumento;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class TipodocumentoService implements IAdicseService<Tipodocumento, Integer> {
	
	@Autowired
	private ITipodocumentoDao iTipodocumentoDao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Tipodocumento> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Tipodocumento entidad = new Tipodocumento();
		entidad.setIdTipoDocumento(null);
		entidad.setDscTipoDocumento(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Tipodocumento) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Tipodocumento> spec = new TipodocumentoSpecification(entidad);
		
		Page<Tipodocumento> lista = iTipodocumentoDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<Tipodocumento> getall() {
		// TODO Auto-generated method stub
		return (List<Tipodocumento>) iTipodocumentoDao.findAll();
	}

	@Override
	public List<Tipodocumento> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipodocumento grabar(Tipodocumento entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Tipodocumento entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iTipodocumentoDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tipodocumento> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iTipodocumentoDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tipodocumento> EntityForSpecificatios(Tipodocumento entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
