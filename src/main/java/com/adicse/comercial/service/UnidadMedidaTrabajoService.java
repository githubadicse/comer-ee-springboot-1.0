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

import com.adicse.comercial.dao.IUnidadMedidaTrabajoDao;
import com.adicse.comercial.model.UnidadMedidaTrabajo;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;


@Service
@Transactional
public class UnidadMedidaTrabajoService implements IAdicseService<UnidadMedidaTrabajo, Integer> {
	
	@Autowired
	private IUnidadMedidaTrabajoDao iUnidadMedidaTrabajoDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 
	

	
	
	@Override
	public Page<UnidadMedidaTrabajo> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<UnidadMedidaTrabajo> lista = selectFrom(iUnidadMedidaTrabajoDao).where(f).findPage(pageable);
	
 

		return lista;
	}

	@Override
	public List<UnidadMedidaTrabajo> getall() {
		// TODO Auto-generated method stub
		return (List<UnidadMedidaTrabajo>) iUnidadMedidaTrabajoDao.findAll();
	}

	@Override
	public List<UnidadMedidaTrabajo> getallbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnidadMedidaTrabajo create(UnidadMedidaTrabajo unidadmedidatrabajo) {
		// TODO Auto-generated method stub
		if(unidadmedidatrabajo.getIdUnidadMedidaTrabajo() == 0) {
			Integer id = iUnidadMedidaTrabajoDao.getMax()==null?1:iUnidadMedidaTrabajoDao.getMax()+1;
			unidadmedidatrabajo.setIdUnidadMedidaTrabajo(id);
		}
		return iUnidadMedidaTrabajoDao.save(unidadmedidatrabajo);
	}

	@Override
	public UnidadMedidaTrabajo update(UnidadMedidaTrabajo unidadmedidatrabajo) {
		// TODO Auto-generated method stub
		UnidadMedidaTrabajo unidadmedidatrabajoUpdate = iUnidadMedidaTrabajoDao.findById(unidadmedidatrabajo.getIdUnidadMedidaTrabajo()).get();

		BeanUtils.copyProperties(unidadmedidatrabajo, unidadmedidatrabajoUpdate);
		return iUnidadMedidaTrabajoDao.save(unidadmedidatrabajoUpdate);
		
	}

	@Override
	public void delete(UnidadMedidaTrabajo unidadmedidatrabajo) {
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
	public Optional<UnidadMedidaTrabajo> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iUnidadMedidaTrabajoDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}


}
