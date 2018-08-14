package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Ing001;

public interface IIng001Dao extends CrudRepository<Ing001, Integer>,
PagingAndSortingRepository<Ing001, Integer>,
JpaSpecificationExecutor<Ing001>
{

	@Query("SELECT max(p.nrodoc) FROM Ing001 p")
	Integer getMax();
	
	@Query("SELECT max(p.iding001) FROM Ing001 p")
	Integer getMaxId();	
}
