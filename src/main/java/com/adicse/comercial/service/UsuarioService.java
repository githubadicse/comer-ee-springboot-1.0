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

import com.adicse.comercial.dao.IUsuarioDao;
import com.adicse.comercial.especification.UsuarioSpecification;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.model.Usuarioempleado;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.Idunico;

@Service
@Transactional
public class UsuarioService implements IAdicseService<Usuario, Integer> {
	
	@Autowired
	private IUsuarioDao iUsuarioDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Usuario> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Usuario entidad = new Usuario();
		entidad.setIdusuario(null);
		entidad.setNomusuario(null);
		entidad.setLogin(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Usuario) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Usuario> spec = new UsuarioSpecification(entidad);
		
		Page<Usuario> lista = iUsuarioDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<Usuario> getall() {
		// TODO Auto-generated method stub
		return (List<Usuario>) iUsuarioDao.findAll();
	}

	@Override
	public List<Usuario> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario grabar(Usuario entidad) {
		// TODO Auto-generated method stub
		Integer nexId = 0;
		
		if(entidad.getIdusuario() == 0){
			nexId = iUsuarioDao.getMax()==null?1: iUsuarioDao.getMax() + 1;
			entidad.setIdusuario(nexId);
		}
		
		if(entidad.getUsuarioempleados() != null ){
			for (Usuarioempleado usuarioempleado : entidad.getUsuarioempleados()) {
				usuarioempleado.setIdusuarioempleado(new Idunico().getIdunico());
			}
		}

		return iUsuarioDao.save(entidad);
	}

	@Override
	public void delete(Usuario entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iUsuarioDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iUsuarioDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario getAllByLogin(String login){
		return iUsuarioDao.findAllByLogin(login);
	}

	@Override
	public Optional<Usuario> EntityForSpecificatios(Usuario entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
