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

import com.adicse.comercial.dao.IEmpleadoDao;
import com.adicse.comercial.model.Empleado;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;

@Service
@Transactional
public class EmpleadoService implements IAdicseService<Empleado, Integer> {

	@Autowired
	private IEmpleadoDao iEmpleadoDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}
	
	@Override
	public Page<Empleado> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		Sort sort = new Sort(sortdireccion.toUpperCase().equals("DESC") ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);


		Page<Empleado> lista = selectFrom(iEmpleadoDao).where(f).findPage(pageable);
	
		return lista;
	}

	@Override
	public List<Empleado> getall() {
		// TODO Auto-generated method stub
		return (List<Empleado>) iEmpleadoDao.findAll();
	}

	@Override
	public List<Empleado> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado grabar(Empleado entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdempleado() == 0) {
			Integer id = iEmpleadoDao.getMax() == null?1:iEmpleadoDao.getMax() + 1;
			entidad.setIdempleado(id);				
		}

		
		return iEmpleadoDao.save(entidad) ;
	}

	@Override
	public void delete(Empleado entidad) {
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
	public Optional<Empleado> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iEmpleadoDao.findById(id) ;
	}
	
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Empleado> EntityForSpecificatios(Empleado entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Empleado findAllByDni(String dni){
		return iEmpleadoDao.findAllByDni(dni);
	}
	
	public List<Empleado> findByCondicionFilial(Integer condicion){
		return iEmpleadoDao.findByCondicionFilial(condicion);
	}

	public List<Empleado> findByFilter(Filter filter){
		return selectFrom(iEmpleadoDao).where(filter).findAll(); 
	}

}
