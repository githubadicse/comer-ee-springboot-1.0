package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IUsuarioEmpleadoDao;
import com.adicse.comercial.model.Usuarioempleado;


@Service
@Transactional
public class UsuarioEmpleadoService implements IAdicseService<Usuarioempleado, Integer> {
	
	@Autowired
	private IUsuarioEmpleadoDao iUsuarioEmpleadoDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuarioempleado> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuarioempleado> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarioempleado grabar(Usuarioempleado entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Usuarioempleado entidad) {
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
	public Optional<Usuarioempleado> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuarioempleado> EntityForSpecificatios(Usuarioempleado entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuarioempleado getUsuarioEmpleadoByIdUsuario(Integer idusuario){
		return iUsuarioEmpleadoDao.getUsuarioEmpleadoByIdUsuario(idusuario);
	}
	
	public void deleteUsuarioEmpleadoByIdusuario(Integer idusuario){
		iUsuarioEmpleadoDao.deleteUsuarioEmpleadoByIdusuario(idusuario);
	}



}
