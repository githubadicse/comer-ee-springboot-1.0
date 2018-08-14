package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.ProductoPorNumeroEntrega;

public interface IProductoPorNumeroEntregaDao extends CrudRepository<ProductoPorNumeroEntrega, String> {

	@Query("select p from ProductoPorNumeroEntrega p where p.entregaPorItem.itemEntrega.anno = :anno "
			+ "order by p.entregaPorItem.itemEntrega.dscitem, p.entregaPorItem.numeroEntrega.numeroEntregaValor, p.catalogoProductoQaliwarma.dscCatalogoProductoQaliwarma")
	List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorAnno(@Param("anno") Integer anno);
	
	
	
	@Query("select p from ProductoPorNumeroEntrega p "
			+ "where p.entregaPorItem.itemEntrega.item =:itemEntrega "
			+ "and p.entregaPorItem.itemEntrega.anno =:anno")
	List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorItemAnno(@Param("itemEntrega") String itemEntrega,@Param("anno") Integer anno);
	
	@Query("select p from ProductoPorNumeroEntrega p "
			+ "where  p.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega "
			+ " and p.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma =:idCatalogoProducto "
			+ " and p.entregaPorItem.itemEntrega.anno=:anno ")
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnno(
			@Param("numeroEntrega") Integer numeroEntrega,
			@Param("anno") Integer anno,
			@Param("idCatalogoProducto") String idCatalogoProducto);
	
	@Query("select p from ProductoPorNumeroEntrega p "
			+ "where  p.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega "			
			+ " and p.entregaPorItem.itemEntrega.anno=:anno ")
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoF(
			@Param("numeroEntrega") Integer numeroEntrega,
			@Param("anno") Integer anno);
	
	
	@Query("select p from ProductoPorNumeroEntrega p "
			+ "where  p.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega "			
			+ " and p.entregaPorItem.itemEntrega.anno=:anno and p.entregaPorItem.itemEntrega.item = :item ")
	public List<ProductoPorNumeroEntrega> getProductoPorNumeroEntregaPorNumeroEntregaAndAnnoAndItem(
			@Param("numeroEntrega") Integer numeroEntrega,
			@Param("anno") Integer anno,
			@Param("item") String item
			);
			
}


