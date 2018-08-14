package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IAperturapuntoventaDao;
import com.adicse.comercial.model.Aperturapuntoventa;

@Service
@Transactional
public class AperturapuntoventaService implements IAdicseService<Aperturapuntoventa, Integer> {
	
	@Autowired
	private IAperturapuntoventaDao iAperturapuntoventaDao;

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
	public List<Aperturapuntoventa> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aperturapuntoventa> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aperturapuntoventa grabar(Aperturapuntoventa entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Aperturapuntoventa entidad) {
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
	public Optional<Aperturapuntoventa> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Aperturapuntoventa> EntityForSpecificatios(Aperturapuntoventa entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Aperturapuntoventa getSiExisteAperturaPorEmpleado(Integer idempleado){
		return iAperturapuntoventaDao.getSiExisteAperturaPorEmpleado(idempleado);
	}


}
