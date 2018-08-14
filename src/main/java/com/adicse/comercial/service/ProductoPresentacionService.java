package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IProductoPresentacionDao;
import com.adicse.comercial.model.ProductoPresentacion;

@Service
@Transactional
public class ProductoPresentacionService implements IAdicseService<ProductoPresentacion, String> {
	
	@Autowired
	private IProductoPresentacionDao iProductoPresentacionDao;
	
	
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
	public List<ProductoPresentacion> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductoPresentacion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoPresentacion grabar(ProductoPresentacion entidad) {
		// TODO Auto-generated method stub
		return iProductoPresentacionDao.save(entidad);
	}

	@Override
	public void delete(ProductoPresentacion entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		iProductoPresentacionDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductoPresentacion> findbyid(String id) {
		// TODO Auto-generated method stub
		return iProductoPresentacionDao.findById(id) ;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductoPresentacion> EntityForSpecificatios(ProductoPresentacion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ProductoPresentacion> getProductoPresentacionByIdProductoAnnoNumeroEntrega(String id_catalogo_producto_qaliwarma, Integer anno, Integer numeroEntrega){
		
		return iProductoPresentacionDao.getProductoPresentacionByIdProductoAnnoNumeroEntrega(id_catalogo_producto_qaliwarma, anno, numeroEntrega);
		
	}



}
