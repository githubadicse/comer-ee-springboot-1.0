package com.adicse.comercial.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.RequerimientoVolumen001;


public interface IRequerimientoVolumen001Dao extends CrudRepository<RequerimientoVolumen001, String> ,
PagingAndSortingRepository<RequerimientoVolumen001, String>, 
JpaSpecificationExecutor<RequerimientoVolumen001>{
	
	
	@Query("select p from RequerimientoVolumen001 p where p.anno = :anno and p.entregaPorItem.itemEntrega.item =:item and p.flagEstado = 1")
	List<RequerimientoVolumen001> getReqVol001ByAnnoItem(@Param("anno") Integer anno, @Param("item") String item);
	
	
	@Modifying
	@Query("delete from RequerimientoVolumen001 p where p.entregaPorItem.itemEntrega.anno =:anno and p.entregaPorItem.numeroEntrega.numeroEntregaValor = :numeroEntrega")
	public void deleteAll(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	@Modifying
	@Query(nativeQuery = true,value="delete from bdcomer.requerimiento_volumen_001 p where id_requerimiento_volumen_001 in "
			+ "(select id_requerimiento_volumen_001 from bdcomer.requerimiento_volumen_001 r1 "
			+ " inner join bdcomer.entrega_por_item on r1.id_entrega_por_item = entrega_por_item.id_entrega_por_item"
			+ " inner join bdcomer.numero_entrega on entrega_por_item.id_numero_entrega = numero_entrega.id_numero_entrega"
			+ " inner join bdcomer.item_entrega on entrega_por_item.item = item_entrega.item"
			+ " where  numero_entrega.numero_entrega_valor = :numeroEntrega and item_entrega.anno = :anno"
			+ " )" )
	public void deleteAllNative(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	@Query("select p from RequerimientoVolumen001 p where p.entregaPorItem.itemEntrega.anno = :anno "
			+ "and p.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega "
			+ "and  p.flagEstado = 1 "
			//+ " and p.codigomodularIinstitucionEducativa.codigoModular = '0304097'"
			+ "")
	public List<RequerimientoVolumen001> getReqVol001ByAnnoAndNumeroEntrega(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	
	@Query("select p from RequerimientoVolumen001 p where p.flagEstado = 1 and p.idRequerimientoVolumen001 IN :ids ")
	List<RequerimientoVolumen001> getRequerimientoVolumen001ByIds(@Param("ids") Set<String> ids);
	
	@Query("select p from RequerimientoVolumen001 p "
			+ "where p.flagEstado = 1 and p.codigomodularIinstitucionEducativa.codigoModular = :codigoModular "
			+ " and p.entregaPorItem.itemEntrega.anno = :anno "
			+ " and p.entregaPorItem.numeroEntrega.numeroEntregaValor = :numeroEntrega")
	 RequerimientoVolumen001 getRequerimientoVolumen001ByCodigoModular (@Param("codigoModular") String codigoModular,@Param("anno") Integer anno,@Param("numeroEntrega") Integer numeroEntrega);
	
	@Modifying
	@Query("Update RequerimientoVolumen001 p set p.flagEstado = 0 where p.codigomodularIinstitucionEducativa.codigoModular in :lst")
	public void updateCierre(@Param("lst") Set<String> lst);
}
