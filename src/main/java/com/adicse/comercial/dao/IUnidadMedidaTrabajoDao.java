package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.UnidadMedidaTrabajo;

public interface IUnidadMedidaTrabajoDao extends CrudRepository<UnidadMedidaTrabajo, Integer>,JpaRepository<UnidadMedidaTrabajo,Integer>, 
PagingAndSortingRepository<UnidadMedidaTrabajo, Integer>, 
JpaSpecificationExecutor<UnidadMedidaTrabajo> {
	
	@Query("SELECT max(p.idUnidadMedidaTrabajo) FROM UnidadMedidaTrabajo p")
	Integer getMax();

}
