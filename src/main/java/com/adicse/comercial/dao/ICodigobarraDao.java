package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Codigobarra;

public interface ICodigobarraDao extends CrudRepository<Codigobarra, Integer>,PagingAndSortingRepository<Codigobarra, Integer>, JpaSpecificationExecutor<Codigobarra> {
	@Query("SELECT max(p.idcodigobarra) FROM Codigobarra p")
	Integer getMax();
	
	List<Codigobarra> getAllByCodigoEquals(String codigo);
	List<Codigobarra> getAllByProductoIdproducto(Integer idproducto);
	
	@Modifying
	@Query("delete From Codigobarra c where c.producto.idproducto = :idproducto")
	void deleteCodigoBarraByCodigoProducto(@Param("idproducto") Integer idproducto);
}
