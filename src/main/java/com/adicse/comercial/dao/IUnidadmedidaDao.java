package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Unidadmedida;

public interface IUnidadmedidaDao extends CrudRepository<Unidadmedida, Integer>,
		PagingAndSortingRepository<Unidadmedida, Integer>, JpaSpecificationExecutor<Unidadmedida> {

	@Query("SELECT max(p.idunidadmedida) FROM Unidadmedida p")
	Integer getMax();
	

}
