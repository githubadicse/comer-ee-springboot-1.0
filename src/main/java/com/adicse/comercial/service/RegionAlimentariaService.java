package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IRegionAlimentariaDao;
import com.adicse.comercial.model.RegionAlimentaria;

@Service
@Transactional
public class RegionAlimentariaService implements IAdicseService<RegionAlimentaria, String> {

	@Autowired
	private IRegionAlimentariaDao iRegionAlimentariaDao;
	
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
	public List<RegionAlimentaria> getall() {
		// TODO Auto-generated method stub
		return (List<RegionAlimentaria>) iRegionAlimentariaDao.findAll();
	}

	@Override
	public List<RegionAlimentaria> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public RegionAlimentaria grabar(RegionAlimentaria entidad) {
		// TODO Auto-generated method stub
		return iRegionAlimentariaDao.save(entidad);
	}
	
	@Transactional(readOnly=false)
	public void grabarList(List<RegionAlimentaria> list) {
		// TODO Auto-generated method stub
		for(RegionAlimentaria row:list) {
			iRegionAlimentariaDao.save(row);
		}
		
	}

	@Override
	public void delete(RegionAlimentaria entidad) {
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
	public Optional<RegionAlimentaria> findbyid(String id) {
		// TODO Auto-generated method stub
		return iRegionAlimentariaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RegionAlimentaria> EntityForSpecificatios(RegionAlimentaria entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
