package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.CatalogoLote;

public interface ICatalogoLoteDao extends CrudRepository<CatalogoLote, String> {
	
	@Query("select p from CatalogoLote p where p.catalogoMarca.idCatalogoMarca = :idCatalogoMarca")
	List<CatalogoLote> lstCatalogoLoteByIdCatalogoMarca(@Param("idCatalogoMarca") String idCatalogoMarca);

}
