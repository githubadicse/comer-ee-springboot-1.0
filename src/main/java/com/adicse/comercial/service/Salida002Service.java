package com.adicse.comercial.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ISalida002Dao;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Salida002;

@Service
@Transactional
public class Salida002Service implements IAdicseService<Salida002, String> {
	
	@Autowired
	private ISalida002Dao iSalida002Dao;
	
	
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
	public List<Salida002> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salida002> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salida002 grabar(Salida002 entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Salida002 entidad) {
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
	public Optional<Salida002> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Salida002> EntityForSpecificatios(Salida002 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteSalida002ByIdIng001(Integer idSalida001){
		iSalida002Dao.deleteSalida002ByIdSalida001(idSalida001);
	}

	public BigDecimal sumByPeriodoProducto(Periodoalmacen periodoalmacen,Producto producto){
		return iSalida002Dao.sumByPeriodoProducto(periodoalmacen, producto);
	}
	
	public List<Salida002> getIng002ByProductoAndPeriodoalmacen(Periodoalmacen periodoalmacen,Producto producto){
		return iSalida002Dao.getSalida002ByProductoAndPeriodoalmacen(producto, periodoalmacen) ;
		
	}



}
