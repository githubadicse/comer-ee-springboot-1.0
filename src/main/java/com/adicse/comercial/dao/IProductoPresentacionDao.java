package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.ProductoPresentacion;

public interface IProductoPresentacionDao extends CrudRepository<ProductoPresentacion, String> {
	
	@Query("select p from ProductoPresentacion p "
			+ "where p.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma = :id_catalogo_producto_qaliwarma "
			+ " and p.numeroEntrega=:numeroEntrega "
			+ " and p.anno=:anno "
			+ "order by p.cantidadPresentacion asc ")
	List<ProductoPresentacion> getProductoPresentacionByIdProductoAnnoNumeroEntrega(
			@Param("id_catalogo_producto_qaliwarma") String id_catalogo_producto_qaliwarma,
			@Param("anno") Integer anno,
			@Param("numeroEntrega") Integer numeroEntrega);

	
	
}
