package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IRutaDistribucionDetalleDao;
import com.adicse.comercial.model.RutaDistribucionDetalle;


@Service
@Transactional
public class RutaDistribucionDetalleService implements IAdicseService<RutaDistribucionDetalle, String> {
	
	@Autowired
	private IRutaDistribucionDetalleDao iRutaDistribucionDetalleDao;

	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RutaDistribucionDetalle> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RutaDistribucionDetalle> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RutaDistribucionDetalle grabar(RutaDistribucionDetalle entidad) {
		// TODO Auto-generated method stub
		return iRutaDistribucionDetalleDao.save(entidad);
	}

	@Override
	public void delete(RutaDistribucionDetalle entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		iRutaDistribucionDetalleDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RutaDistribucionDetalle> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RutaDistribucionDetalle> EntityForSpecificatios(RutaDistribucionDetalle entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RutaDistribucionDetalle  getRutaDistribucionByCodigoModularAndNumeroEntregaAndAnno(
			 String codigoModular,
			 Integer numeroEntrega,
			 Integer anno
			) {
		return iRutaDistribucionDetalleDao.getRutaDistribucionByCodigoModularAndNumeroEntregaAndAnno(codigoModular, numeroEntrega, anno);
		
	}


	


}
