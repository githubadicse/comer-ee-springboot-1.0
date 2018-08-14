package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.CatalogoMultiplicarNivelEducacion;

public interface ICatalogoMultiplicarNivelEducacionDao extends CrudRepository<CatalogoMultiplicarNivelEducacion, String> {
	
	@Query("select p from CatalogoMultiplicarNivelEducacion p "
			+ "where p.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma =:idProducto "
			+ "and p.productoPresentacion.idProductoPresentacion=:idProductoPresentacion "
			+ "and p.nivelEducacion.idNivelEducacion =:idNivelEducacion "
			+ "and p.numeroEntrega =:numeroEntrega and p.anno =:anno")
	List<CatalogoMultiplicarNivelEducacion> getCatalogoNivelEducacionByPresentacion(
			@Param("anno") Integer anno,
			@Param("numeroEntrega") Integer numeroEntrega, 
			@Param("idProducto") String idProducto, 
			@Param("idProductoPresentacion") String idProductoPresentacion,
			@Param("idNivelEducacion") Integer idNivelEducacion);

	
	@Query("select p from CatalogoMultiplicarNivelEducacion p "
			+ "where "
			+ " p.numeroEntrega =:numeroEntrega and p.anno =:anno")
	List<CatalogoMultiplicarNivelEducacion> getCatalogoNivelEducacionByPeriodoNumeroEntrega(
			@Param("anno") Integer anno,
			@Param("numeroEntrega") Integer numeroEntrega);
			
}
