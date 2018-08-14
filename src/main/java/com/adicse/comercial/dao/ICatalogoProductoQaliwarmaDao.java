package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.CatalogoProductoQaliwarma;

public interface ICatalogoProductoQaliwarmaDao extends CrudRepository<CatalogoProductoQaliwarma, String> 
, PagingAndSortingRepository<CatalogoProductoQaliwarma, String>,
JpaSpecificationExecutor<CatalogoProductoQaliwarma>
{
	
	@Query("select p from CatalogoProductoQaliwarma p where p.idCatalogoProductoQaliwarma = :id")
	CatalogoProductoQaliwarma getCatalogoProductoById(@Param("id") String id);
	
	@Query("select p from CatalogoProductoQaliwarma p where p.anno =:anno")
	List<CatalogoProductoQaliwarma>  getCatalogoProductoQaliwarmaByAnno(@Param("anno") Integer anno);

}
