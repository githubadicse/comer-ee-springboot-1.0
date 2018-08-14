package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.clases.RequerimientoClass;
import com.adicse.comercial.model.RequerimientoVolumen002Producto;

public interface IRequerimientoVolumen002ProductoDao extends CrudRepository<RequerimientoVolumen002Producto, String>,PagingAndSortingRepository<RequerimientoVolumen002Producto, String>,
JpaSpecificationExecutor<RequerimientoVolumen002Producto> {
	
	//RequerimientoVolumen002Producto saveAndFlush(RequerimientoVolumen002Producto entity);
	
	@Query("select p.productoPorNumeroEntrega as productoPorNumeroEntrega, p.productoPorNumeroEntrega.catalogoProductoQaliwarma as catalogoProductoQaliwarma, "
			+ " p.volumen as volumen, p as requerimientoVolumen002Producto "
			+ " from RequerimientoVolumen002Producto p "
			+ " left join p.productoPorNumeroEntrega left join p.productoPorNumeroEntrega.catalogoProductoQaliwarma "
			
			+ " where p.productoPorNumeroEntrega.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega  "
			+ " and p.productoPorNumeroEntrega.entregaPorItem.itemEntrega.anno =:anno and p.requerimientoVolumen002.requerimientoVolumen001.flagEstado = 1 "
		
			//+ " and p.productoPorNumeroEntrega.catalogoProductoQaliwarma.idCatalogoProductoQaliwarma = 'CEREAL 2-2018'"
			+ " ")
	public List<RequerimientoClass> listarTodo( @Param("anno") Integer anno,@Param("numeroEntrega") Integer numeroEntrega);
	
	@Query("select p from RequerimientoVolumen002Producto p "
			+ " where p.requerimientoVolumen002.idRequerimientoVolumen002 =:idrq002 ")
	public List<RequerimientoVolumen002Producto> getRequerimientoVolumen002ProductoByIdRequerimientoVolumen002(@Param("idrq002") String idrq002);
	
	
	@Modifying
	@Query("delete from RequerimientoVolumen002Producto p where "
			+ "p.requerimientoVolumen002.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno "
			+ " and p.productoPorNumeroEntrega.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega ")
	public void deleteAll(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	

}
