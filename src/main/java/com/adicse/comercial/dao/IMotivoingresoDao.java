package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Motivoingreso;

public interface IMotivoingresoDao extends CrudRepository<Motivoingreso, Integer>,
		PagingAndSortingRepository<Motivoingreso, Integer>, JpaSpecificationExecutor<Motivoingreso> {

	@Query("SELECT max(p.idmotivoingreso) FROM Motivoingreso p")
	Integer getMax();

}
