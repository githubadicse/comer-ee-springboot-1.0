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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IProveedorclienteDao;
import com.adicse.comercial.dao.IProveedorclientedireccionesDao;
import com.adicse.comercial.model.Proveedorcliente;
import com.adicse.comercial.specification.ConvertObjectToFormatJson;
import com.adicse.comercial.specification.Filter;

@Service
@Transactional
public class ProveedorclienteService implements IAdicseService<Proveedorcliente, Integer> {
	
	@Autowired
	private  IProveedorclienteDao iProveedorclienteDao;
	
	@Autowired
	private IProveedorclientedireccionesDao iProveedorclientedireccionesDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Proveedorcliente> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase().equals("DESC") ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Proveedorcliente> lista = selectFrom(iProveedorclienteDao).where(f).findPage(pageable);
	
		return lista;		
	}

	@Override
	public List<Proveedorcliente> getall() {
		// TODO Auto-generated method stub
		return (List<Proveedorcliente>) iProveedorclienteDao.findAll();
	}

	@Override
	public List<Proveedorcliente> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proveedorcliente grabar(Proveedorcliente entidad) {
		// TODO Auto-generated method stub
		if(entidad.getIdproveedorcliente() == 0) {
			Integer id = iProveedorclienteDao.getMax() == null?1:iProveedorclienteDao.getMax() + 1;
			entidad.setIdproveedorcliente(id);
		}
		entidad = iProveedorclienteDao.save(entidad);
		return entidad;
	}

	@Override
	public void delete(Proveedorcliente entidad) {
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
	public Optional<Proveedorcliente> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iProveedorclienteDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Proveedorcliente> EntityForSpecificatios(Proveedorcliente entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Proveedorcliente> findAllByRazonsocialContainsIgnoreCaseOrderByRazonsocial(String razonsocial){
		return iProveedorclienteDao.findAllByRazonsocialContainsIgnoreCaseOrderByRazonsocial(razonsocial);
	}

	public void deleteDireccionByIdProveedorCliente(Integer idproveedorcliente) {
		iProveedorclientedireccionesDao.deleteDireccionByIdProveedorCliente(idproveedorcliente);
	}
	
	public List<Proveedorcliente> findByFilter(Filter filter){
		return selectFrom(iProveedorclienteDao).where(filter).findAll(); 
	}
	
//	public Proveedorcliente getFindByDocumento(Integer iddocumento, String numdocumento){
//		return iProveedorclientedireccionesDao.getFindByDocumento(iddocumento, numdocumento);
//	}
	
	public List<Proveedorcliente> filterGlobal(@Param("dato") String dato){
		return iProveedorclienteDao.filterGlobal(dato);
	}
}

