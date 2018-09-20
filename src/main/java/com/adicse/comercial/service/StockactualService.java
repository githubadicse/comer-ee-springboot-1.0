package com.adicse.comercial.service;

import static com.adicse.comercial.specification.SpecificationBuilder.selectFrom;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.adicse.comercial.dao.IStockactualDao;
import com.adicse.comercial.model.Stockactual;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;


@Service
@Transactional
public class StockactualService implements IAdicseService<Stockactual, Integer> {

	
	@Autowired
	private IStockactualDao iStockactualDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson;
	
	@Override
	public Page<Stockactual> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		Filter _filter = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);
		
		return selectFrom(iStockactualDao).where(_filter).findPage(pageable);

	}
	
	//para el control busca producto LISTA
	public List<Stockactual> findByParametro(String parametro, Integer idalmacen){
		Pageable pageable =  PageRequest.of(0, 4);
		return iStockactualDao.findByParametro(parametro, idalmacen, pageable).getContent();
	}
	
	// para el control busca producto PAGINABLE
	public Page<Stockactual> findByParametroPageable(Integer pagenumber, Integer rows, String parametro, Integer idalmacen){
		Pageable pageable =  PageRequest.of(pagenumber, rows);
		return iStockactualDao.findByParametro(parametro, idalmacen, pageable);
	}
	
//	//para el control busca producto LISTA
//	public List<Stockactual> findByParametroSoloProducto(String parametro){
//		Pageable pageable =  PageRequest.of(0, 4);
//		return iStockactualDao.findByParametroSoloProducto(parametro, pageable).getContent();
//	}
//		
//	// para el control busca producto PAGINABLE
//	public Page<Stockactual> findByParametroPageableSoloProducto(Integer pagenumber, Integer rows, String parametro){
//		Pageable pageable =  PageRequest.of(pagenumber, rows);
//		return iStockactualDao.findByParametroSoloProducto(parametro, pageable);
//	}

	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stockactual> getall() {
		return (List<Stockactual>) iStockactualDao.findAll();
	}

	@Override
	public List<Stockactual> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stockactual grabar(Stockactual entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Stockactual entidad) {
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
	public Optional<Stockactual> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Stockactual> EntityForSpecificatios(Stockactual entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
