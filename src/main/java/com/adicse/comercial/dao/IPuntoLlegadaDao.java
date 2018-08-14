package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.PuntoLlegada;

public interface IPuntoLlegadaDao extends CrudRepository<PuntoLlegada, Integer>, PagingAndSortingRepository<PuntoLlegada, Integer>, 
JpaSpecificationExecutor<PuntoLlegada> {
	
	@Query("SELECT max(p.idPuntoLlegada) FROM PuntoLlegada p")
	Integer getMax();

}
