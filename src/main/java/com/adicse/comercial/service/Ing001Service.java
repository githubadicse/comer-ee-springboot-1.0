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

import com.adicse.comercial.dao.IIng001Dao;
import com.adicse.comercial.model.Ing001;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;

@Service
@Transactional
public class Ing001Service implements IAdicseService<Ing001, Integer> {

	@Autowired
	private IIng001Dao iIng001Dao;
	

	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Ing001> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase().equals("DESC") ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Ing001> lista = selectFrom(iIng001Dao).where(f).findPage(pageable);
	
 

		return lista;
	}
	
	public Page<Ing001> paginacion(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Filter f) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase().equals("DESC") ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
			
		Page<Ing001> lista = selectFrom(iIng001Dao).where(f).findPage(pageable);

		return lista;
	}
	
//	public Page<Ing001> findListByParametro(Integer idalmacen, Integer pagenumber, Integer rows, String parametro){
//		Pageable pageable =  PageRequest.of(pagenumber, rows);
//		String _parametro = parametro.toLowerCase();
//		return iIng001Dao.findListByParametro(idalmacen, _parametro, pageable);
//	}

	@Override
	public List<Ing001> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ing001> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ing001 grabar(Ing001 entidad) {
		// TODO Auto-generated method stub
	
		Integer next = null;
		if (entidad.getIding001() == 0) {
			next = iIng001Dao.getMax() == null ? 0 : iIng001Dao.getMax();
			entidad.setNrodoc(next + 1);
		}
		if (entidad.getIding001() == 0) {
			next = iIng001Dao.getMaxId() == null ? 0 : iIng001Dao.getMaxId();
			entidad.setIding001(next + 1);
		}
		Ing001 ing001 = iIng001Dao.save(entidad);
		

		
		return ing001;
	}

	@Override
	public void delete(Ing001 entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iIng001Dao.deleteById(id);
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ing001> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iIng001Dao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ing001> EntityForSpecificatios(Ing001 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
