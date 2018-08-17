package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Numerador;

public interface INumeradorDao  
extends CrudRepository<Numerador, Integer>,
PagingAndSortingRepository<Numerador, Integer>, 
JpaSpecificationExecutor<Numerador>
{
	
	@Query("SELECT max(p.idNumerador) FROM Numerador p")
	Integer getMax();
	
	@Modifying
	@Query("delete From Numerador c where c.tipodocumento.idTipoDocumento= :id")
	void deleteNumeradorByIdTipoDocumento(@Param("id") Integer id);


}
