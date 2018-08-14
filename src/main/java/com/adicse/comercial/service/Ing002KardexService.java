package com.adicse.comercial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IIng002KardexDao;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Ing002kardex;

@Service
@Transactional
public class Ing002KardexService implements IAdicseService<Ing002kardex, String> {

	@Autowired
	private IIng002KardexDao iIng002KardexDao;
	
	
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
	public List<Ing002kardex> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ing002kardex> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ing002kardex grabar(Ing002kardex entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Ing002kardex entidad) {
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
	public Optional<Ing002kardex> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ing002kardex> EntityForSpecificatios(Ing002kardex entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@ReadOnlyProperty
	public List<Ing002kardex> findByIng002In(List<Ing002> lstIng002) {

		List<Ing002kardex> lst = new ArrayList<>();
		for (Ing002 ing002 : lstIng002) {
			try {
				Ing002kardex salida002kardex = iIng002KardexDao.getByIng002(ing002.getIding002());

				lst.add(salida002kardex);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		return lst;

	}

	public void deleteByIng002KardexInIng002(List<Ing002> lstIng002) {
		for (Ing002 ing002 : lstIng002) {
			try {
				iIng002KardexDao.deleteByIng002KardexInIng002(ing002);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	}



}
