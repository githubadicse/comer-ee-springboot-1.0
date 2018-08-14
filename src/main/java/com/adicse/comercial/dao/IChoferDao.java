package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Chofer;

public interface IChoferDao extends CrudRepository<Chofer, Integer>, PagingAndSortingRepository<Chofer, Integer>, 
JpaSpecificationExecutor<Chofer> {
	
	@Query("SELECT max(p.idChofer) FROM Chofer p")
	Integer getMax();

}
