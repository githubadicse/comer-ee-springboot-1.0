package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IProductoPorNumeroEntregaDao;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;

@Service
@Transactional
public class ProductoPorNumeroEntregaService implements IAdicseService<ProductoPorNumeroEntrega, String> {
	
	@Autowired
	private IProductoPorNumeroEntregaDao iProductoPorNumeroEntregaDao;
	
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
	public List<ProductoPorNumeroEntrega> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductoPorNumeroEntrega> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoPorNumeroEntrega grabar(ProductoPorNumeroEntrega entidad) {
		// TODO Auto-generated method stub
		return iProductoPorNumeroEntregaDao.save(entidad);
	}
	
	public void grabarList(List<ProductoPorNumeroEntrega> lista) {
		for(ProductoPorNumeroEntrega row:lista) {
			iProductoPorNumeroEntregaDao.save(row);
		}
	}

	@Override
	public void delete(ProductoPorNumeroEntrega entidad) {
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
	@Transactional(readOnly=true)
	public Optional<ProductoPorNumeroEntrega> findbyid(String id) {
		// TODO Auto-generated method stub
		return iProductoPorNumeroEntregaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductoPorNumeroEntrega> EntityForSpecificatios(ProductoPorNumeroEntrega entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly=true)
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaAnno(Integer anno){
		
		//Sort sort = new Sort(Sort.Direction.ASC,"itemEngrega.dscitem");
		
		return iProductoPorNumeroEntregaDao.getProductoPorNumeroEntregaPorAnno(anno);
	}
	
	@Transactional(readOnly=true)
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnno(
			Integer numeroEntrega,
			Integer anno,
			String idCatalogoProducto){
		
		return iProductoPorNumeroEntregaDao.getProductoPorNumeroEntregaPorNumeroEntregaAndAnno(numeroEntrega, anno, idCatalogoProducto);
	}

	@Transactional(readOnly=true)
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoF(
			Integer numeroEntrega,
			Integer anno
			){
		
		return iProductoPorNumeroEntregaDao.getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoF(numeroEntrega, anno);
	}
	
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoAndItem(
			Integer numeroEntrega,Integer anno,String item
			){
		return iProductoPorNumeroEntregaDao.getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoAndItem(numeroEntrega, anno, item);
		
	}

	
}
