package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.RutaDistribucion;

public interface IRutaDistribucionDao extends CrudRepository<RutaDistribucion , String>,PagingAndSortingRepository<RutaDistribucion, String>, 
JpaSpecificationExecutor<RutaDistribucion> {
	
	@Query("select p from RutaDistribucion p where p.anno = :anno and p.numeroEntrega =:numeroEntrega")
	List<RutaDistribucion> getRutaDistribucionByAnnoAndNumeroEntrega(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);

}
