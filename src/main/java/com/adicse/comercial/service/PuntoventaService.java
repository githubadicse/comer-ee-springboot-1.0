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

import com.adicse.comercial.dao.IPuntoventaDao;
import com.adicse.comercial.especification.PuntoventaSpecification;
import com.adicse.comercial.model.Puntoventa;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class PuntoventaService implements IAdicseService<Puntoventa, Integer> {

	
	@Autowired
	private IPuntoventaDao iPuntoventaDao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}
	
	@Override
	public Page<Puntoventa> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Puntoventa entidad = new Puntoventa();
		entidad.setIdpuntoventa(null);
		entidad.setDscpuntoventa(null);
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Puntoventa) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Puntoventa> spec = new PuntoventaSpecification(entidad);
		
		Page<Puntoventa> lista = iPuntoventaDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<Puntoventa> getall() {
		// TODO Auto-generated method stub
		return (List<Puntoventa>) iPuntoventaDao.findAll() ;
	}

	@Override
	public List<Puntoventa> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puntoventa grabar(Puntoventa entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdpuntoventa() == 0){
			Integer id = iPuntoventaDao.getMax()== null?1:iPuntoventaDao.getMax() + 1;
			entidad.setIdpuntoventa(id);
		}
		return iPuntoventaDao.save(entidad);
	}

	@Override
	public void delete(Puntoventa entidad) {
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
	public Optional<Puntoventa> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return  iPuntoventaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Puntoventa> EntityForSpecificatios(Puntoventa entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Puntoventa getPuntoVentaByMac(List<String> macs){
		return iPuntoventaDao.getPuntoVentaByMac(macs);
	}



}
