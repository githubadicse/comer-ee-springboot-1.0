package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.NumeroEntrega;


public interface INumeroEntregaDao extends CrudRepository<NumeroEntrega, Integer> {
	
	@Query("Select p from NumeroEntrega p where p.numeroEntregaValor =:numeroEntrega")
	List<NumeroEntrega> getByNumeroEntrega(@Param("numeroEntrega") Integer numeroEntrega);

}
