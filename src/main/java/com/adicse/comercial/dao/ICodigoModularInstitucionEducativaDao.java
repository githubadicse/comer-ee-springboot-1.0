package com.adicse.comercial.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.CodigomodularIinstitucionEducativa;

public interface ICodigoModularInstitucionEducativaDao extends CrudRepository<CodigomodularIinstitucionEducativa, String> {

	@Query("Select p from CodigomodularIinstitucionEducativa p where p.codigoModular in :lst")
	List<CodigomodularIinstitucionEducativa> getCodigoModularInstitucionEducativaByIds(@Param("lst") Set<String> lst);
	
}
