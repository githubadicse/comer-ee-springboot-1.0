package com.adicse.comercial.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Puntoventa;

public interface IPuntoventaDao extends CrudRepository<Puntoventa, Integer>,
PagingAndSortingRepository<Puntoventa, Integer>, 
JpaSpecificationExecutor<Puntoventa> {

	
	@Query("SELECT max(p.idpuntoventa) FROM Puntoventa p")
	Integer getMax();	
	
	@Query("Select p From Puntoventa p where lower(p.mac) in :macs")
	Puntoventa getPuntoVentaByMac(@Param("macs") List<String> mac);
}
