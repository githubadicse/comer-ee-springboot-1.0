package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.CatalogoBonificacion;

public interface ICatalogoBonificacionDao extends CrudRepository<CatalogoBonificacion, String> {

	
	
//	@Query("select p from CatalogoBonificacion p "
//			+ " where p.productoPorNumeroEntrega.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma=:idProducto "
//			+ " and  p.productoPorNumeroEntrega.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega"
//			+ " and p.horarioAlimentacion.idHorarioAlimentacion =:idHorarioAtencion "
//			+ " and p.regionAlimentaria.idRegionAlimentaria =:idRegion")
//	public CatalogoBonificacion getCatalogoBonificacionByProductoHorarioRegio(
//			@Param("idProducto") String idProducto,
//			@Param("idRegion") String idRegion,
//			@Param("numeroEntrega") Integer numeroEntrega,
//			@Param("idHorarioAtencion") Integer idHorarioAtencion
//			);
	
	
	@Query("select p from CatalogoBonificacion p "
			+ "where p.productoPorNumeroEntrega.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma=:idProducto "
			+ " and p.regionAlimentaria.idRegionAlimentaria =:idRegion "
			+ " and p.productoPorNumeroEntrega.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega "
			+ " and p.horarioAlimentacion.idHorarioAlimentacion =:idHorarioAlimentacion")
	public List<CatalogoBonificacion> getCatalogoBonificacionByProductoHorarioRegio(
			@Param("idProducto") String idProducto,
			@Param("idRegion") String idRegion,
			@Param("numeroEntrega") Integer numeroEntrega,
			@Param("idHorarioAlimentacion") Integer idHorarioAlimentacion
			);
	
	
	
	
}
