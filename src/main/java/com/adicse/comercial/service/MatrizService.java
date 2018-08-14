package com.adicse.comercial.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.clases.MatrizGroup;
import com.adicse.comercial.dao.IMatrizDao;
import com.adicse.comercial.model.Matriz;

@Service
@Transactional
public class MatrizService implements IAdicseService<Matriz, Integer> {
	
	@Autowired
	private IMatrizDao iMatrizDao;

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
	public List<Matriz> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matriz> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matriz grabar(Matriz entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Matriz entidad) {
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
	public Optional<Matriz> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Matriz> EntityForSpecificatios(Matriz entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void grabarList(List<Matriz> lista) {
		iMatrizDao.saveAll(lista);
	}
	
	public List<Matriz> getMatrizByAnnoAndNumeroEntrega(Integer anno, Integer numeroEntrega){
		return iMatrizDao.getMatrizByAnnoAndNumeroEntrega(anno, numeroEntrega);
	}
	
	
	public BigDecimal getSumaPesoByIdRequerimientoVolumen001( String idRequerimientoVolumen001) {
		return iMatrizDao.getSumaPesoByIdRequerimientoVolumen001(idRequerimientoVolumen001);
	}
	
	public List<MatrizGroup> getMatrizByIdsRequerimientoVolumen001( Set<String> ids){
		
		return iMatrizDao.getMatrizByIdsRequerimientoVolumen001(ids);
		
	}
	
	public List<Matriz> getMatrizByIdsRequerimientoVolumen001V2(Set<String> ids){
		return iMatrizDao.getMatrizByIdsRequerimientoVolumen001V2(ids);
		
	}

	
}
