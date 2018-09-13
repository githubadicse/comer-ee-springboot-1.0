package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Almacen;


public interface IAlmacenDao
extends CrudRepository<Almacen, Integer>,
PagingAndSortingRepository<Almacen, Integer>, JpaRepository<Almacen,Integer>,
JpaSpecificationExecutor<Almacen>
{
	
	@Query("SELECT max(p.idalmacen) FROM Almacen p")
	Integer getMax();	
	
	@Query("select p from Almacen p where p.filial.idfilial = :idfilial")
	List<Almacen> getAlmacensByIdFilial(@Param("idfilial") Integer idfilial );

}
