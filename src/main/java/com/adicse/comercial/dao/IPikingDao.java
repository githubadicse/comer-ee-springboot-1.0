package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Piking;

public interface IPikingDao extends CrudRepository<Piking, String> {

	@Query("select p from Piking p where p.volumenConvertidoEnvace.idVolumenConvertidoEnvace = :idVolumenConvertidoEnvace ")
	public Piking getPikingByIdVolumenConvertidoEnvace(@Param("idVolumenConvertidoEnvace") String idVolumenConvertidoEnvace);
	
	@Query("select p from Piking p where p.catalogoLote.idCatalogoLote =:idCatalogoLote")
	public List<Piking> getPikingByIdCatalogoLote(@Param("idCatalogoLote") String idCatalogoLote);
	
	@Modifying
	@Query("delete from Piking p where p.catalogoLote.idCatalogoLote = :idCatalogoLote")
	public void deleteByIdCatalogoLote(@Param("idCatalogoLote") String idCatalogoLote);
}
