package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IModalidadCompraVenta;
import com.adicse.comercial.model.ModalidadCompraVenta;


@Service
@Transactional
public class ModalidadCompraVentaService implements IAdicseService<ModalidadCompraVenta, Integer> {
	
	
	@Autowired
	private IModalidadCompraVenta iModalidadCompraVenta;

	@Override
	public Page<ModalidadCompraVenta> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ModalidadCompraVenta> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModalidadCompraVenta> getall() {
		// TODO Auto-generated method stub
		return (List<ModalidadCompraVenta>) iModalidadCompraVenta.findAll();
	}

	@Override
	public List<ModalidadCompraVenta> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModalidadCompraVenta grabar(ModalidadCompraVenta entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ModalidadCompraVenta entidad) {
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
	public Optional<ModalidadCompraVenta> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ModalidadCompraVenta> EntityForSpecificatios(ModalidadCompraVenta entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
