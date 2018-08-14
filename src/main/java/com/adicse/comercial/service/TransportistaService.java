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

import com.adicse.comercial.dao.ITransportistaDao;
import com.adicse.comercial.especification.TransportistaSpecification;
import com.adicse.comercial.model.Proveedorcliente;
import com.adicse.comercial.model.Transportista;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional
public class TransportistaService implements IAdicseService<Transportista, Integer> {
	
	@Autowired
	private ITransportistaDao iTransportistaDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Transportista> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Transportista entidad = new Transportista();
		entidad.setIdTransportista(null);
		
		Proveedorcliente proveedorcliente = new Proveedorcliente();
		entidad.setProveedorcliente(proveedorcliente);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (Transportista) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<Transportista> spec = new TransportistaSpecification(entidad);
		
		Page<Transportista> lista = iTransportistaDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<Transportista> getall() {
		// TODO Auto-generated method stub
		return (List<Transportista>) iTransportistaDao.findAll();
	}

	@Override
	public List<Transportista> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transportista grabar(Transportista entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdTransportista() == 0) {
			Integer id = iTransportistaDao.getMax()==null?1:iTransportistaDao.getMax() + 1;
			entidad.setIdTransportista(id);
		}
		return iTransportistaDao.save(entidad);
	}

	@Override
	public void delete(Transportista entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		iTransportistaDao.deleteById(id);
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Transportista> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iTransportistaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Transportista> EntityForSpecificatios(Transportista entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
