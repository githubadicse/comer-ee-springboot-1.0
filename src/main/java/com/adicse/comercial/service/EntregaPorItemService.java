package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IEntregaPorItemDao;
import com.adicse.comercial.model.EntregaPorItem;


@Service
@Transactional
public class EntregaPorItemService implements IAdicseService<EntregaPorItem, String> {
	
	@Autowired
	private IEntregaPorItemDao iEntregaPorItemDao;
	
	
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
	public List<EntregaPorItem> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntregaPorItem> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntregaPorItem grabar(EntregaPorItem entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(EntregaPorItem entidad) {
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
	public Optional<EntregaPorItem> findbyid(String id) {
		// TODO Auto-generated method stub
		return iEntregaPorItemDao.findById(id) ;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EntregaPorItem> EntityForSpecificatios(EntregaPorItem entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly=true)
	public EntregaPorItem getEntregaPorItemByAnno(Integer anno, String item, Integer numeroEntrega) {
		return iEntregaPorItemDao.getEntregaPorItemByAnno(anno, item, numeroEntrega);
	}
	
	@Transactional(readOnly=true)
	public List<EntregaPorItem> getListEntregaPorItemByAnno(Integer anno, String item, Integer numeroEntrega) {
		return iEntregaPorItemDao.getListEntregaPorItemByAnno(anno, item, numeroEntrega);
	}
	
	public List<EntregaPorItem> getListEntregaPorAnnoAndNumeroEntrega(Integer anno, Integer numeroEntrega){
		return iEntregaPorItemDao.getListEntregaPorAnnoAndNumeroEntrega(anno, numeroEntrega);
	}


	

}
