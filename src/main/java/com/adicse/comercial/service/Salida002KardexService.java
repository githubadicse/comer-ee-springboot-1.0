package com.adicse.comercial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ISalida002KardexDao;
import com.adicse.comercial.model.Salida002;
import com.adicse.comercial.model.Salida002kardex;

@Service
@Transactional
public class Salida002KardexService  implements IAdicseService<Salida002kardex, String> {
	
	
	@Autowired
	private ISalida002KardexDao iSalida002KardexDao;

	
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
	public List<Salida002kardex> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salida002kardex> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salida002kardex grabar(Salida002kardex entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Salida002kardex entidad) {
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
	public Optional<Salida002kardex> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Salida002kardex> EntityForSpecificatios(Salida002kardex entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Salida002kardex> findBySalida002In(List<Salida002> lstSal002){
	
		List<Salida002kardex> lst = new ArrayList<>();
		for(Salida002 salida002: lstSal002){
			try{
				Salida002kardex salida002kardex = iSalida002KardexDao.getBySalida002(salida002.getIdsalida002());
				
				lst.add(salida002kardex);				
			}catch(Exception ex){
				System.out.println( ex.getMessage());
			}

			
		}
		return lst;
	}
	
	public void deleteBySalida002KardexInSalida002(List<Salida002> lstSalida002){
		for(Salida002 salida002 : lstSalida002){
			try{
				iSalida002KardexDao.deleteBySalida002KardexInSalida002(salida002.getIdsalida002());
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
	}

	

}
