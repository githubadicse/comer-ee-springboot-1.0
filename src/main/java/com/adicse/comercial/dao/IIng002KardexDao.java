package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Ing002kardex;

public interface IIng002KardexDao extends CrudRepository<Ing002kardex, String> {

	
	List<Ing002kardex> findByIng002In(List<Ing002> lstIng002);
	
	@Modifying
	@Query("delete from Ing002kardex p where p.ing002 = :ing002")
	void deleteByIng002KardexInIng002(@Param("ing002") Ing002 ing002);
	
	
	@Query("select p from Ing002kardex p where p.ing002.iding002 = :iding002")
	public Ing002kardex getByIng002 (@Param("iding002") String iding002);
}
