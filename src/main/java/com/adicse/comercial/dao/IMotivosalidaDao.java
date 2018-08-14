package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Motivosalida;

public interface IMotivosalidaDao extends CrudRepository<Motivosalida, Integer>,
		PagingAndSortingRepository<Motivosalida, Integer>, JpaSpecificationExecutor<Motivosalida> {

	@Query("SELECT max(p.idmotivosalida) FROM Motivosalida p")
	Integer getMax();

}
