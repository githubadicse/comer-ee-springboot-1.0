package com.adicse.comercial.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Salida002;

public interface ISalida002Dao extends CrudRepository<Salida002, String>,
PagingAndSortingRepository<Salida002, String>,
JpaSpecificationExecutor<Salida002>
{
	
	@Modifying
	@Query("delete From Salida002 c where c.salida001.idsalida001 = :idsalida001")
	void deleteSalida002ByIdSalida001(@Param("idsalida001") Integer idsalida001);	
	
	@Query("select sum(s2.cantidad) from Salida002 s2 where s2.salida001.periodoalmacen =:periodoalmacen and s2.producto =:producto")
	BigDecimal sumByPeriodoProducto(@Param("periodoalmacen") Periodoalmacen periodoalmacen, 
			@Param("producto")  Producto producto);

	
	@Query("select p from Salida002 p where p.producto = :producto and p.salida001.periodoalmacen = :periodoalmacen")
	List<Salida002> getSalida002ByProductoAndPeriodoalmacen(@Param("producto") Producto producto,@Param("periodoalmacen") Periodoalmacen periodoalmacen);

}
