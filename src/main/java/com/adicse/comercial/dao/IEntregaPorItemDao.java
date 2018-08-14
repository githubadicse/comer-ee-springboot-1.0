package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.EntregaPorItem;

public interface IEntregaPorItemDao extends CrudRepository<EntregaPorItem, String> {
	
	@Query("select p from EntregaPorItem p "
			+ "where p.itemEntrega.anno =:anno and p.itemEntrega.item=:item and p.numeroEntrega.numeroEntregaValor =:numeroEntrega")
	EntregaPorItem getEntregaPorItemByAnno(@Param("anno") Integer anno, @Param("item") String item, @Param("numeroEntrega") Integer numeroEntrega);
	
	@Query("select p from EntregaPorItem p "
			+ "where p.itemEntrega.anno =:anno and p.itemEntrega.item=:item and p.numeroEntrega.numeroEntregaValor =:numeroEntrega")
	List<EntregaPorItem> getListEntregaPorItemByAnno(@Param("anno") Integer anno, @Param("item") String item, @Param("numeroEntrega") Integer numeroEntrega);

	@Query("select p from EntregaPorItem p "
			+ " where p.itemEntrega.anno =:anno and  p.numeroEntrega.numeroEntregaValor =:numeroEntrega "
			//+ " and p.itemEntrega.item='AWAJUN' "
			+ " order by p.itemEntrega.item,p.numeroEntrega.numeroEntregaValor")
	List<EntregaPorItem> getListEntregaPorAnnoAndNumeroEntrega(@Param("anno") Integer anno,  @Param("numeroEntrega") Integer numeroEntrega);
	
}


