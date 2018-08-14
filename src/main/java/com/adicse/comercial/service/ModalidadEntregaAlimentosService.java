package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IModalidadEntregaAlimentosDao;
import com.adicse.comercial.model.ModalidadEntregaAlimento;

@Service
@Transactional
public class ModalidadEntregaAlimentosService implements IAdicseService<ModalidadEntregaAlimento, Integer> {
	
	@Autowired
	private IModalidadEntregaAlimentosDao iModalidadEntregaAlimentosDao;
	
	
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
	public List<ModalidadEntregaAlimento> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModalidadEntregaAlimento> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModalidadEntregaAlimento grabar(ModalidadEntregaAlimento entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ModalidadEntregaAlimento entidad) {
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
	@Transactional(readOnly=true)
	public Optional<ModalidadEntregaAlimento> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iModalidadEntregaAlimentosDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ModalidadEntregaAlimento> EntityForSpecificatios(ModalidadEntregaAlimento entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
