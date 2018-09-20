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

import com.adicse.comercial.dao.IProductoDao;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;

@Service
@Transactional
public class ProductoService implements IAdicseService<Producto, Integer>  {
	
	@Autowired
	private IProductoDao iProductoDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Producto> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		Filter _filter = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);
		
		return selectFrom(iProductoDao).leftJoin("stockactuals").where(_filter).findPage(pageable);
	}
	
	public List<Producto> findByParametroLista(String parametro){
		Pageable pageable =  PageRequest.of(0, 4);
		return iProductoDao.findByParametroSoloProducto(parametro, pageable).getContent();
	}
	
	// para el control busca producto PAGINABLE
	public Page<Producto> findByParametroPageable(Integer pagenumber, Integer rows, String parametro){
		Pageable pageable =  PageRequest.of(pagenumber, rows);
		return iProductoDao.findByParametroSoloProducto(parametro, pageable);
	}

	
	@Override
	public List<Producto> getall() {
		// TODO Auto-generated method stub
		return (List<Producto>) iProductoDao.findAll();
	}

	@Override
	public List<Producto> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Producto> findByFilter(Filter filter){
		return selectFrom(iProductoDao).where(filter).findAll(); 
	}

	@Override
	public Producto grabar(Producto entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdproducto() == 0){
			entidad.setIdproducto(iProductoDao.getMax() == null?1:iProductoDao.getMax() + 1 );
		}
		return iProductoDao.save(entidad);
	}

	@Override
	public void delete(Producto entidad) {
		// TODO Auto-generated method stub
		iProductoDao.delete(entidad);
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iProductoDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return iProductoDao.existsById(id);
	}


	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return iProductoDao.count();
	}


	@Override
	public Optional<Producto> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iProductoDao.findById(id);
	}


	@Override
	public Optional<Producto> EntityForSpecificatios(Producto entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Producto getProductoById(Integer idproducto){
		return iProductoDao.getProductoByIdProducto(idproducto);
	}
	
	public Producto getProductoByCodigoBarras(String idproducto){
		return iProductoDao.getProductoByCodigoBarras(idproducto);
	}
	
	public List<Producto> findByDscproductoContainingIgnoreCaseOrderByDscproducto(String dscproducto){
		return iProductoDao.findByDscproductoContainingIgnoreCaseOrderByDscproducto(dscproducto);
	}







}
