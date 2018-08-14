package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IVolumenConvertidoEnvaseDao;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.VolumenConvertidoEnvace;


@Service
@Transactional
public class VolumenConvertidoEnvaseService implements IAdicseService<VolumenConvertidoEnvace, String> {

	@Autowired
	private IVolumenConvertidoEnvaseDao iVolumenConvertidoEnvaseDao;
	
	
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
	public List<VolumenConvertidoEnvace> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VolumenConvertidoEnvace> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VolumenConvertidoEnvace grabar(VolumenConvertidoEnvace entidad) {
		// TODO Auto-generated method stub
		return iVolumenConvertidoEnvaseDao.save(entidad);
	}

	@Override
	public void delete(VolumenConvertidoEnvace entidad) {
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
	public Optional<VolumenConvertidoEnvace> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VolumenConvertidoEnvace> EntityForSpecificatios(VolumenConvertidoEnvace entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<VolumenConvertidoEnvace> getVolumenByItem(List<EntregaPorItem> lstEntregaPorItem){
		return iVolumenConvertidoEnvaseDao.getVolumenByItem(lstEntregaPorItem);
	}
	
	public void deleteAll(Integer anno,Integer numeroEntrega) {
		iVolumenConvertidoEnvaseDao.deleteAll();
		
	}
	
	
	public void deleteByAnnoNumeroEntrega(Set<String> entregaPorItem) {
	
		iVolumenConvertidoEnvaseDao.deleteByAnnoNumeroEntrega(entregaPorItem);
	}
	
	public List<VolumenConvertidoEnvace> getVolumenByPiking(String idItem,String idCatalogoMarca,String idProductoPresentacion){
		return iVolumenConvertidoEnvaseDao.getVolumenByPiking(idItem, idCatalogoMarca, idProductoPresentacion);
	}
	
	public List<VolumenConvertidoEnvace> getVolumenByIdProductoPorNumeroEntrega(String idProductoPorNumeroEntrega, String idItem, String codigoModular,String idProductoPresentacion){
		return iVolumenConvertidoEnvaseDao.getVolumenByIdProductoPorNumeroEntrega(idProductoPorNumeroEntrega,idItem,codigoModular,idProductoPresentacion);
	}

	public void deleteByAnnoNumeroEntregaFijo(Integer anno, Integer numeroEntrega) {
		iVolumenConvertidoEnvaseDao.deleteByAnnoNumeroEntregaFijo(anno, numeroEntrega);
	}
	

}
