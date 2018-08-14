package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IRutaDistribucionDao;
import com.adicse.comercial.especification.RutaDistribucionSpecification;
import com.adicse.comercial.model.EmpleadoDistribuidor;
import com.adicse.comercial.model.RutaDistribucion;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.UtilitarioObjectToJSon;


@Service
@Transactional
public class RutaDistribucionService implements IAdicseService<RutaDistribucion, String> {
	
	@Autowired
	private IRutaDistribucionDao iRutaDistribucionDao;

	@Override
	public Page<RutaDistribucion> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RutaDistribucion> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

	
		
		UtilitarioObjectToJSon utilitarioObjectToJSon = new UtilitarioObjectToJSon();
		Integer anno = Integer.parseInt( utilitarioObjectToJSon.getValueOfObject(paramsExtra, "anno"));
		Integer numeroEntrega = Integer.parseInt( utilitarioObjectToJSon.getValueOfObject(paramsExtra, "numeroEntrega"));
		
	
		
		
		EmpleadoDistribuidor empleadoDistribuidor = new EmpleadoDistribuidor();
		
		empleadoDistribuidor.setIdEmpleadoDistribuidor(null);
		empleadoDistribuidor.setNombres(null);
		
		RutaDistribucion rutaDistribucion = new RutaDistribucion();
		rutaDistribucion.setIdRutaDistribucion(null);
		rutaDistribucion.setDscRutaDistribucion(null);
		rutaDistribucion.setNumeroEntrega(numeroEntrega);
		rutaDistribucion.setEmpleadoDistribuidor(empleadoDistribuidor);
		
		
		
		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			rutaDistribucion = (RutaDistribucion) efs.CreateCustomFilter(rutaDistribucion, filter);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		rutaDistribucion.setAnno(anno);
		rutaDistribucion.setNumeroEntrega(numeroEntrega);
		Specification<RutaDistribucion> spec = new RutaDistribucionSpecification(rutaDistribucion);
		
		Page<RutaDistribucion> lista = iRutaDistribucionDao.findAll(spec,pageable);
 

		return lista;
	}

	@Override
	public List<RutaDistribucion> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RutaDistribucion> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RutaDistribucion grabar(RutaDistribucion entidad) {
		// TODO Auto-generated method stub
		return iRutaDistribucionDao.save(entidad);
	}

	@Override
	public void delete(RutaDistribucion entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		iRutaDistribucionDao.deleteById(id);
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RutaDistribucion> findbyid(String id) {
		// TODO Auto-generated method stub
		return iRutaDistribucionDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RutaDistribucion> EntityForSpecificatios(RutaDistribucion entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<RutaDistribucion> getRutaDistribucionByAnnoAndNumeroEntrega(Integer anno,Integer numeroEntrega){
		return iRutaDistribucionDao.getRutaDistribucionByAnnoAndNumeroEntrega(anno, numeroEntrega);
	}


}
