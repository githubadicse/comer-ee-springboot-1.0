package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Salida002;
import com.adicse.comercial.model.Salida002kardex;

public interface ISalida002KardexDao extends CrudRepository<Salida002kardex, String> {
	
	List<Salida002kardex> findBySalida002In(List<Salida002> lstIng002);
	
	@Modifying
	@Query("delete from Salida002kardex p where p.salida002.idsalida002 = :idsalida002")
	void deleteBySalida002KardexInSalida002(@Param("idsalida002") String idsalida002);
	
	@Query("select p from Salida002kardex p where p.salida002.idsalida002 = :idsalida002")
	public Salida002kardex getBySalida002 (@Param("idsalida002") String idsalida002);

}
