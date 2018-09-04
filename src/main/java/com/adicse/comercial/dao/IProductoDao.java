package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Producto;

public interface IProductoDao extends CrudRepository<Producto, Integer>, PagingAndSortingRepository<Producto, Integer>,
		JpaSpecificationExecutor<Producto>, JpaRepository<Producto, Integer>

{

	@Query("SELECT max(p.idproducto) FROM Producto p")
	Integer getMax();

	@Query("Select p FROM Producto p where p.idproducto = :idproducto")
	Producto getProductoByIdProducto(@Param("idproducto") Integer idproducto);

	List<Producto> findByDscproductoContainingIgnoreCaseOrderByDscproducto(String dscproducto);
	
	//Producto findByCatalogoProductoQaliwarmaEquals(CatalogoProductoQaliwarma catalogo);
	
	@Query("Select p FROM Producto p inner join p.codigobarras c on c.producto.idproducto = p.idproducto where c.codigo = :codigobarras")
	Producto getProductoByCodigoBarras(@Param("codigobarras") String codigobarras);
	
	@Query("Select p From Producto p inner join p.stockactuals s on s.producto.idproducto = p.idproducto")
	List<Producto> findProductoAndStock();
}
