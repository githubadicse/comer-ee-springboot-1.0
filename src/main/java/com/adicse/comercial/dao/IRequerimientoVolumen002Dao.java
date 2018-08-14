package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.RequerimientoVolumen002;

public interface IRequerimientoVolumen002Dao extends CrudRepository<RequerimientoVolumen002, String> {
	
	@Query("Select p from RequerimientoVolumen002 p "
			
			+ " where p.requerimientoVolumen001.idRequerimientoVolumen001=:idrequerimiento001 "
			+ " and p.numeroEntrega.numeroEntregaValor =:numeroEntregaValor")
	public List<RequerimientoVolumen002> getRequerimientoVolumen002ByIdR1(
			@Param("idrequerimiento001") String idrequerimiento001,
			@Param("numeroEntregaValor") Integer numeroEntregaValor
			);
	
	
	@Modifying
	@Query("delete from RequerimientoVolumen002 p where p.numeroEntrega.numeroEntregaValor =:numeroEntrega and p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno")
	public void deleteAll(@Param("anno") Integer anno,@Param("numeroEntrega") Integer numeroEntrega);

}
