package com.adicse.comercial.service;

import static com.adicse.comercial.specification.SpecificationBuilder.selectFrom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICom001Dao;
import com.adicse.comercial.model.Com001;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;


@Service
@Transactional
public class Com001Service implements IAdicseService<Com001, Long> {

	
	@Autowired
	private ICom001Dao iCom001Dao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 
	
	
	@Override
	public Page<Com001> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Com001> lista = selectFrom(iCom001Dao).where(f).findPage(pageable);
	
 

		return lista;
	}

	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Com001> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Com001> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Com001 grabar(Com001 entidad) {
		// TODO Auto-generated method stub
		if (entidad.getIdcom001() == 0) {
			Integer next = iCom001Dao.getMax();
			
			next = next == null ? 1 : next + 1; 
						
			Long l = Long.valueOf(next); 
			
			entidad.setIdcom001(l);
		}
				
		
		return iCom001Dao.save(entidad);
	}

	@Override
	public void delete(Com001 entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Long id) {
		// TODO Auto-generated method stub
		iCom001Dao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Com001> findbyid(Long id) {
		// TODO Auto-generated method stub
		return iCom001Dao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Com001> EntityForSpecificatios(Com001 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
