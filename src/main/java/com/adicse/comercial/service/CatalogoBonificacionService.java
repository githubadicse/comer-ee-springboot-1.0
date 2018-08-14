package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICatalogoBonificacionDao;
import com.adicse.comercial.model.CatalogoBonificacion;


@Service
@Transactional(readOnly=true)
public class CatalogoBonificacionService implements IAdicseService<CatalogoBonificacion, String> {
	
	@Autowired
	private ICatalogoBonificacionDao iCatalogoBonificacionDao;
	
	
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
	public List<CatalogoBonificacion> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogoBonificacion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public CatalogoBonificacion grabar(CatalogoBonificacion entidad) {
		// TODO Auto-generated method stub
		return iCatalogoBonificacionDao.save(entidad);
	}

	@Override
	public void delete(CatalogoBonificacion entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CatalogoBonificacion> findbyid(String id) {
		// TODO Auto-generated method stub
		return iCatalogoBonificacionDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CatalogoBonificacion> EntityForSpecificatios(CatalogoBonificacion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CatalogoBonificacion> getCatalogoBonificacionByProductoHorarioRegio(
			String idProducto
			,
			String idRegion,
			Integer numeroEntrega,
			Integer idHorarioAlimentacion
			) {
		
		return iCatalogoBonificacionDao.getCatalogoBonificacionByProductoHorarioRegio(idProducto
				, idRegion
				, numeroEntrega
				, idHorarioAlimentacion
				);
		
	}

	

}
