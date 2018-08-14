package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Tipodocumento;

public interface ITipodocumentoDao 
extends CrudRepository<Tipodocumento, Integer>,
PagingAndSortingRepository<Tipodocumento, Integer>, 
JpaSpecificationExecutor<Tipodocumento>
{
	
	@Query("SELECT max(p.idTipoDocumento) FROM Tipodocumento p")
	Integer getMax();	


}
