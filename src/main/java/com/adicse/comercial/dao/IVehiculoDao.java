package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Vehiculo;

public interface IVehiculoDao extends CrudRepository<Vehiculo, Integer>,JpaRepository<Vehiculo,Integer>, PagingAndSortingRepository<Vehiculo, Integer>, 
JpaSpecificationExecutor<Vehiculo> {
	
	@Query("SELECT max(p.idVehiculo) FROM Vehiculo p")
	Integer getMax();

}
