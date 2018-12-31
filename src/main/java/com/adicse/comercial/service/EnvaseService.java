package com.adicse.comercial.service;



import static com.adicse.comercial.specification.SpecificationBuilder.selectFrom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adicse.comercial.interfaces.IAdicseService;

import com.adicse.comercial.dao.IEnvaseDao;
import com.adicse.comercial.model.Envase;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;




@Service
@Transactional
public class EnvaseService implements IAdicseService<Envase, Integer> {
	
	@Autowired
	private IEnvaseDao iEnvaseDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 
	

	
	
	@Override
	public Page<Envase> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Envase> lista = selectFrom(iEnvaseDao).where(f).findPage(pageable);
	
 

		return lista;
	}

	@Override
	public List<Envase> getall() {
		// TODO Auto-generated method stub
		return (List<Envase>) iEnvaseDao.findAll();
	}

	@Override
	public List<Envase> getallbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envase create(Envase envase) {
		// TODO Auto-generated method stub
		if(envase.getIdEnvase() == 0) {
			Integer id = iEnvaseDao.getMax()==null?1:iEnvaseDao.getMax()+1;
			envase.setIdEnvase(id);
		}
		return iEnvaseDao.save(envase);
	}

	@Override
	public Envase update(Envase envase) {
		// TODO Auto-generated method stub
		Envase envaseUpdate = iEnvaseDao.findById(envase.getIdEnvase()).get();

		BeanUtils.copyProperties(envase, envaseUpdate);
		return iEnvaseDao.save(envaseUpdate);
		
	}

	@Override
	public void delete(Envase envase) {
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
	public Optional<Envase> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iEnvaseDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
