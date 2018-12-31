package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Envase;

public interface IEnvaseDao extends CrudRepository<Envase, Integer>,JpaRepository<Envase,Integer>, 
PagingAndSortingRepository<Envase, Integer>, 
JpaSpecificationExecutor<Envase> {
	
	@Query("SELECT max(p.idEnvase) FROM Envase p")
	Integer getMax();

}
