package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Integer>,PagingAndSortingRepository<Categoria, Integer>, JpaSpecificationExecutor<Categoria> {
	
	@Query("SELECT max(p.idcategoria) FROM Categoria p")
	Integer getMax();
	
	//@Query("SELECT p FROM Categoria where upper(p.dsccategoria) like upper(%:dsccategoria%)")
	List<Categoria> findByDsccategoriaContainingIgnoreCaseOrderByDsccategoriaAsc(String dsccategoria);

}
