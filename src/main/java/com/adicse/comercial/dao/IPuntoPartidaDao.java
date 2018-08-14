package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.adicse.comercial.model.PuntoPartida;

public interface IPuntoPartidaDao extends CrudRepository<PuntoPartida, Integer> , PagingAndSortingRepository<PuntoPartida, Integer>, 
JpaSpecificationExecutor<PuntoPartida>{
	
	@Query("SELECT max(p.idPuntoPartida) FROM PuntoPartida p")
	Integer getMax();

}
