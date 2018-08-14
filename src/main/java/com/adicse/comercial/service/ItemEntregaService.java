package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IItemEntregaDao;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.model.ProductoPorNumeroEntrega;
import com.adicse.comercial.model.ProductoPresentacion;

@Service
@Transactional
public class ItemEntregaService implements IAdicseService<ItemEntrega, String> {
	
	@Autowired
	private IItemEntregaDao iItemEntregaDao;
	
//	@Autowired
//	private IProductoPorNumeroEntregaDao iProductoPorNumeroEntregaDao;
	
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
	@Transactional(readOnly=true)
	public List<ItemEntrega> getall() {
		// TODO Auto-generated method stub
		
		return (List<ItemEntrega>) iItemEntregaDao.getAll();// findAll();
	}

	@Override
	public List<ItemEntrega> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemEntrega grabar(ItemEntrega entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ItemEntrega entidad) {
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
	public Optional<ItemEntrega> findbyid(String id) {
		// TODO Auto-generated method stub
		return iItemEntregaDao.findById(id) ;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ItemEntrega> EntityForSpecificatios(ItemEntrega entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List<ItemEntrega> getItemByAnno(Integer anno){
		
		List<ItemEntrega> lstItemEntrega = (List<ItemEntrega>) iItemEntregaDao.findAll();
		
	
		for(ItemEntrega itemEntrega: lstItemEntrega) {
			//String sItemEntrega = itemEntrega.getItem();

			for(EntregaPorItem entregaPorItem:itemEntrega.getEntregaPorItems()) {
				entregaPorItem.setItemEntrega(null);
				
				for(ProductoPorNumeroEntrega  productoPorNumeroEntrega : entregaPorItem.getProductoPorNumeroEntregas()) {
					productoPorNumeroEntrega.setEntregaPorItem(null);
					
					for(ProductoPresentacion productoPresentacion:productoPorNumeroEntrega.getCatalogoProductoQaliwarma().getProductoPresentacions()) {
						productoPresentacion.setCatalogoProductoQaliwarma(null);
					}
				}
				
				
				
				
			}
			
			
		}
		
		
		return lstItemEntrega;
	}
	
	public List<ItemEntrega> getItemsEntrega(Integer numeroEntrega, Integer anno){
		@SuppressWarnings({ "deprecation", "unused" })
		Pageable topTen = new PageRequest(0, 10);	
		return iItemEntregaDao.getItemsEntrega(numeroEntrega, anno) ;
	}
	
	public ItemEntrega getItemByItem(String item, Integer anno){
		
		return iItemEntregaDao.getItemByItem(item,anno);
		
	}
	
	public List<ItemEntrega> getAllByAno( Integer anno){
		return iItemEntregaDao.getAllByAno(anno);
	}


}
