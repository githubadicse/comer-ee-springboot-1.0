package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Marca;

public interface IMarcaDao extends CrudRepository<Marca, Integer>, PagingAndSortingRepository<Marca, Integer>, JpaSpecificationExecutor<Marca> {
	
	@Query("SELECT max(p.idmarca) FROM Marca p")
	Integer getMax();	
	
	List<Marca> findByDscmarcaContainingIgnoreCaseOrderByDscmarcaAsc(String dscmarca);

}
