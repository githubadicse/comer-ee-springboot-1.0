package com.adicse.comercial.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IIng002Dao;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;

@Service
@Transactional
public class Ing002Service implements IAdicseService<Ing002, String> {
	
	@Autowired
	private IIng002Dao iIng002Dao;
	
	
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
	public List<Ing002> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ing002> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ing002 grabar(Ing002 entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Ing002 entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ing002> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ing002> EntityForSpecificatios(Ing002 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteIng002ByIdIng001(Integer iding001){
		iIng002Dao.deleteIng002ByIdIng001(iding001);
	}

	public BigDecimal sumByPeriodoProducto(Periodoalmacen periodoalmacen,Producto producto){
		return iIng002Dao.sumByPeriodoProducto(periodoalmacen, producto);
	}
	
	public List<Ing002> getIng002ByProductoAndPeriodoalmacen(Periodoalmacen periodoalmacen,Producto producto){
		return iIng002Dao.getIng002ByProductoAndPeriodoalmacen(producto, periodoalmacen) ;
		
	}


}
