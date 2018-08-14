package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Almacen;

public interface IAlmacenDao
extends CrudRepository<Almacen, Integer>,
PagingAndSortingRepository<Almacen, Integer>, 
JpaSpecificationExecutor<Almacen>
{
	
	@Query("SELECT max(p.idalmacen) FROM Almacen p")
	Integer getMax();	

}
