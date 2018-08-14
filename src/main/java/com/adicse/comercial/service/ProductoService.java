package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.adicse.comercial.dao.IProductoDao;
import com.adicse.comercial.especification.ProductoSpecification;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Unidadmedida;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class ProductoService implements IAdicseService<Producto, Integer>  {
	
	@Autowired
	private IProductoDao iProductoDao;
	
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

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		Producto productofiltro = new Producto();
		productofiltro.setIdproducto(null);
		productofiltro.setDscproducto(null);
		
		Unidadmedida unidadmedida = new Unidadmedida();
		productofiltro.setUnidadmedida(unidadmedida);
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			productofiltro = (Producto) efs.CreateCustomFilter(productofiltro, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Producto> spec = new ProductoSpecification(productofiltro);
		
		Page<Producto> lista = iProductoDao.findAll(spec,pageable);
 

		//
		return lista;
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
	
	public List<Producto> findByDscproductoContainingIgnoreCaseOrderByDscproducto(String dscproducto){
		return iProductoDao.findByDscproductoContainingIgnoreCaseOrderByDscproducto(dscproducto);
	}







}
