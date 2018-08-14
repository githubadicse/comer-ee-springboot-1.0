package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Transportista;

public interface ITransportistaDao extends CrudRepository<Transportista, Integer>, PagingAndSortingRepository<Transportista, Integer>, 
JpaSpecificationExecutor<Transportista> {
	
	@Query("SELECT max(p.idTransportista) FROM Transportista p")
	Integer getMax();

}
