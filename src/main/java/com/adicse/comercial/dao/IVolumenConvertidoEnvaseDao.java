package com.adicse.comercial.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.VolumenConvertidoEnvace;

public interface IVolumenConvertidoEnvaseDao extends CrudRepository<VolumenConvertidoEnvace, String> {

	VolumenConvertidoEnvace saveAndFlush(VolumenConvertidoEnvace entity);
	
	@Query("select p from VolumenConvertidoEnvace p "
			+ "left join p.requerimientoVolumen002Producto  "
			+ " left join p.requerimientoVolumen002Producto.requerimientoVolumen002.requerimientoVolumen001.entregaPorItem.itemEntrega ie"
			+ " left join ie.entregaPorItems entrega"
			+ " where entrega IN :items ")
	public List<VolumenConvertidoEnvace> getVolumenByItem(@Param("items") List<EntregaPorItem> items);
	
	@Modifying
	@Query("delete  from VolumenConvertidoEnvace v where v.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega "
			//+ " and p.requerimientoVolumen002Producto.requerimientoVolumen002.requerimientoVolumen001.anno =:anno "
			)
	public void deleteAll(@Param("numeroEntrega") Integer numeroEntrega);
	
	@Modifying
	@Query("delete  from VolumenConvertidoEnvace v where v.entregaPorItem.idEntregaPorItem in :entregaPorItem "
			//+ " and p.requerimientoVolumen002Producto.requerimientoVolumen002.requerimientoVolumen001.anno =:anno "
			)
	public void deleteByAnnoNumeroEntrega(@Param("entregaPorItem") Set<String> entregaPorItem);
	
	
	@Modifying
	@Query("delete  from VolumenConvertidoEnvace v where v.anno = :anno and v.numeroEntrega = :numeroEntrega "
			)
	public void deleteByAnnoNumeroEntregaFijo(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);	
	
	@Query("select v from VolumenConvertidoEnvace v where v.entregaPorItem.idEntregaPorItem = :idItem "
			+ " and v.catalogoMarca.idCatalogoMarca = :idCatalogoMarca "
			+ " and v.catalogoMarca.productoPresentacion.idProductoPresentacion =:idProductoPresentacion "
			+ " ")
	public List<VolumenConvertidoEnvace> getVolumenByPiking(@Param("idItem") String idItem, @Param("idCatalogoMarca") String idCatalogoMarca, 
			 @Param("idProductoPresentacion") String idProductoPresentacion);
	
	@Query("select v from VolumenConvertidoEnvace v "
			+ " where v.requerimientoVolumen002Producto.productoPorNumeroEntrega.idProductoPorNumeroEntrega =:idProductoPorNumeroEntrega "
			+ " and v.entregaPorItem.itemEntrega.item = :idItem "
			+ " and v.requerimientoVolumen002Producto.requerimientoVolumen002.requerimientoVolumen001.codigomodularIinstitucionEducativa.codigoModular =:codigoModular "
			+ " and v.productoPresentacion.idProductoPresentacion =:idProductoPresentacion ")
	public List<VolumenConvertidoEnvace> getVolumenByIdProductoPorNumeroEntrega(
			@Param("idProductoPorNumeroEntrega") String idProductoPorNumeroEntrega,
			@Param("idItem") String idItem,
			@Param("codigoModular") String codigoModular,
			@Param("idProductoPresentacion") String idProductoPresentacion
			);
	
}
