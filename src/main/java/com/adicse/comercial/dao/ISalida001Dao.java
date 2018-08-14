package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Salida001;

public interface ISalida001Dao extends CrudRepository<Salida001, Integer>
,
PagingAndSortingRepository<Salida001, Integer>,
JpaSpecificationExecutor<Salida001>
{
	@Query("SELECT max(p.nrodoc) FROM Salida001 p")
	Integer getMax();
	
	@Query("SELECT max(p.idsalida001) FROM Salida001 p")
	Integer getMaxId();	

}
