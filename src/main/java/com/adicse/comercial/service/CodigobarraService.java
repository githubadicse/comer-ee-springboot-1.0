package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICodigobarraDao;
import com.adicse.comercial.model.Codigobarra;

@Service
@Transactional
public class CodigobarraService implements IAdicseService<Codigobarra, Integer> {
	
	@Autowired
	private ICodigobarraDao iCodigobarraDao;
	
//	@Autowired
//	private EntityManager em;

	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}
	
	
	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Codigobarra> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Codigobarra> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Codigobarra grabar(Codigobarra entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Codigobarra entidad) {
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
	public Optional<Codigobarra> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Codigobarra> EntityForSpecificatios(Codigobarra entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Codigobarra> getAllByCodigoEquals(String codigo){
		return iCodigobarraDao.getAllByCodigoEquals(codigo);
	}
	
	public List<Codigobarra> getAllByProductoIdproducto(Integer idproducto){
		return iCodigobarraDao.getAllByProductoIdproducto(idproducto);
	}
	
	public void deleteAllByCodigoproducto(Integer idproducto){
		
		iCodigobarraDao.deleteCodigoBarraByCodigoProducto(idproducto);
	}



	


}
