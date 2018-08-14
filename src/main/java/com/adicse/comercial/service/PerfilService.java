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

import com.adicse.comercial.dao.IPerfilDao;
import com.adicse.comercial.dao.IPerfilesdetalleDao;
import com.adicse.comercial.especification.PerfilSpecification;
import com.adicse.comercial.model.Perfil;
import com.adicse.comercial.shared.CustomFilterSpec;


@Service
@Transactional
public class PerfilService implements IAdicseService<Perfil, Integer> {
	
	@Autowired
	private IPerfilDao iPerfilDao;	
	
	@Autowired
	private IPerfilesdetalleDao iPerfilesdetalleDao;	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Perfil> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Perfil perfilfiltro = new Perfil();
		perfilfiltro.setIdperfil(null);
		perfilfiltro.setDscperfil(null);
		
	

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			perfilfiltro = (Perfil) efs.CreateCustomFilter(perfilfiltro, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Perfil> spec = new PerfilSpecification(perfilfiltro);
		
		Page<Perfil> lista = iPerfilDao.findAll(spec,pageable);
 

		//
		return lista;
	}
		
	@Override
	public List<Perfil> getall() {
		// TODO Auto-generated method stub
		return (List<Perfil>) iPerfilDao.findAll();
	}

	@Override
	public List<Perfil> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return (List<Perfil>) iPerfilDao.findAll();
	}

	@Override
	public Perfil grabar(Perfil entidad) {
		// TODO Auto-generated method stub		
		if ( entidad.getIdperfil() == 0 ) { 
			Integer IdMax = iPerfilDao.getMax() == null ? 1 : iPerfilDao.getMax() + 1 ; 
			entidad.setIdperfil(IdMax);
		}
		
		iPerfilDao.save(entidad);
		return entidad;
	}
	
	public Perfil findById(Integer id) {
		return iPerfilDao.findById(id).get();
	}
	
	public void deletePerfilesdetalleByIdPerfil(Integer idperfil) {
		iPerfilesdetalleDao.deletePerfilDetalleByIdPerfil(idperfil);
	}

	@Override
	public void delete(Perfil entidad) {		
	}

	@Override
	public void deletebyid(Integer id) {
		iPerfilDao.deleteById(id);
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Perfil> findbyid(Integer id) {
		return iPerfilDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Perfil> EntityForSpecificatios(Perfil entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
