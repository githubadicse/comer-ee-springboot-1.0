package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.CatalogoMarca;

public interface ICatalogoMarcaDao extends CrudRepository<CatalogoMarca, String> {
	
	
	@Query("select p from CatalogoMarca p where p.productoPorNumeroEntrega.idProductoPorNumeroEntrega =:idProductoPorNumeroEntrega and "
			+ " p.productoPorNumeroEntrega.entregaPorItem.itemEntrega.anno = :anno and p.productoPorNumeroEntrega.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega")
	public List<CatalogoMarca> getCatalogoMarcaByIdProductoPorNumeroEntrega(
			@Param("idProductoPorNumeroEntrega") String idProductoPorNumeroEntrega,
			@Param("anno") Integer anno,
			@Param("numeroEntrega") Integer numeroEntrega
			);
	
	@Query("select p from CatalogoMarca p where p.productoPorNumeroEntrega.idProductoPorNumeroEntrega =:idProductoPorNumeroEntrega and "
			+ " p.productoPresentacion.idProductoPresentacion = :idProductoPresentacion")
	public CatalogoMarca getCatalogoMarcaByIdProductoPorNumeroEntregaIdProductoPresentacion(
			@Param("idProductoPorNumeroEntrega") String idProductoPorNumeroEntrega,
			@Param("idProductoPresentacion") String idProductoPresentacion
			
			);

}
