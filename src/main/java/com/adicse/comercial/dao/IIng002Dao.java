package com.adicse.comercial.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;

public interface IIng002Dao extends CrudRepository<Ing002, String> ,
PagingAndSortingRepository<Ing002, String>,
JpaSpecificationExecutor<Ing002>

{
	
	@Modifying
	@Query("delete From Ing002 c where c.ing001.iding001 = :iding001")
	void deleteIng002ByIdIng001(@Param("iding001") Integer iding001);	
	
	@Query("select sum(i2.cantidad) from Ing002 i2 where i2.ing001.periodoalmacen =:periodoalmacen and i2.producto =:producto")
	BigDecimal sumByPeriodoProducto(@Param("periodoalmacen") Periodoalmacen periodoalmacen, 
			@Param("producto")  Producto producto);
	
	@Query("select p from Ing002 p where p.producto = :producto and p.ing001.periodoalmacen = :periodoalmacen")
	List<Ing002> getIng002ByProductoAndPeriodoalmacen(@Param("producto") Producto producto,@Param("periodoalmacen") Periodoalmacen periodoalmacen);

}
