package com.adicse.comercial.dao;

import java.math.BigDecimal;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.clases.MatrizGroup;
import com.adicse.comercial.model.Matriz;

public interface IMatrizDao extends CrudRepository<Matriz, Integer> {
	
	
	@Query("select p from Matriz p where p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno and "
			+ " p.requerimientoVolumen001.entregaPorItem.numeroEntrega.numeroEntregaValor = :numeroEntrega "
			+ " order by "
			+ " p.requerimientoVolumen001.entregaPorItem.itemEntrega.item, "
			//+ " p.requerimientoVolumen001.ubigeo.nombreDistrito, "
			+ " p.requerimientoVolumen001.codigomodularIinstitucionEducativa.codigoModular, "
			+ " p.productoPorNumeroEntrega.catalogoProductoQaliwarma.dscCatalogoProductoQaliwarma ")
	List<Matriz> getMatrizByAnnoAndNumeroEntrega(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	
	@Query("Select sum(p.peso) from Matriz p where p.requerimientoVolumen001.idRequerimientoVolumen001 = :idRequerimientoVolumen001") 
	BigDecimal getSumaPesoByIdRequerimientoVolumen001(@Param("idRequerimientoVolumen001") String idRequerimientoVolumen001);
	
	@Query("Select "
			+ " ppne.dscComplementoProducto as dscComplementoProducto, "
			+ " c.dscCatalogoProductoQaliwarma as dscCatalogoProductoQaliwarma, sum(p.unds1) as unds1, sum(p.unds2) as unds2, sum(p.unds3) as unds3, "
			+ " ppne.lote1 as lote1,"
			+ " ppne.lote2 as lote2 "
			+ " from Matriz p "
			
			+ " inner join p.requerimientoVolumen001 rv on p.requerimientoVolumen001.idRequerimientoVolumen001 = rv.idRequerimientoVolumen001 "
			+ " inner join p.productoPorNumeroEntrega ppne on p.productoPorNumeroEntrega.idProductoPorNumeroEntrega = ppne.idProductoPorNumeroEntrega "
			+ " inner join ppne.catalogoProductoQaliwarma c on ppne.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma = c.idCatalogoProductoQaliwarma "
			+ " where p.requerimientoVolumen001.idRequerimientoVolumen001 IN :ids and rv.entregaPorItem.numeroEntrega.numeroEntregaValor = 2 "
//			+ " order by "  
//			+ " p.requerimientoVolumen001.entregaPorItem.itemEntrega.item, " 
//			+ " p.requerimientoVolumen001.ubigeo.nombreDistrito, " 
//			+ " p.requerimientoVolumen001.codigomodularIinstitucionEducativa.codigoModular, " 
//			+ " p.productoPorNumeroEntrega.catalogoProductoQaliwarma.dscCatalogoProductoQaliwarma "
			+ " group by ppne.dscComplementoProducto,c.dscCatalogoProductoQaliwarma,ppne.lote1,ppne.lote2 "
			)
	List<MatrizGroup> getMatrizByIdsRequerimientoVolumen001(@Param("ids") Set<String> ids);
	
	
	@Query("select p from Matriz p where p.requerimientoVolumen001.idRequerimientoVolumen001 IN :ids ")
	List<Matriz> getMatrizByIdsRequerimientoVolumen001V2(@Param("ids") Set<String> ids);
	

}
