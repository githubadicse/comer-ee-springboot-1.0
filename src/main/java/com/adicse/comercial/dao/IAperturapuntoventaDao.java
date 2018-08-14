package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Aperturapuntoventa;

public interface IAperturapuntoventaDao extends CrudRepository<Aperturapuntoventa, Integer> {
	
	@Query("SELECT max(p.idaperturapuntoventa) FROM Aperturapuntoventa p")
	Integer getMax();	
	
	@Query("Select p from Aperturapuntoventa p where  p.empleado.idempleado = :idempleado ")
	Aperturapuntoventa getSiExisteAperturaPorEmpleado(@Param("idempleado") Integer idempleado);
	
}
