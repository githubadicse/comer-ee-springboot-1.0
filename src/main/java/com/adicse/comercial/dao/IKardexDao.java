package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Kardex;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;

public interface IKardexDao extends CrudRepository<Kardex, String>,
PagingAndSortingRepository<Kardex, String>, 
JpaSpecificationExecutor<Kardex>
{

	@Query("SELECT max(p.nromov) FROM Kardex p where p.producto = :producto and p.periodoalmacen =:periodoalmacen")
	public Integer getMax(@Param("producto") Producto producto,@Param("periodoalmacen") Periodoalmacen periodoalmacen);
	
	@Modifying
	@Query("delete from Kardex p where p.idkardex  =:idkardex")
	public void deleteKardexByIdKardex(@Param("idkardex") String idkardex);
	
	//gel all kardex por periodo y almacen y producto
	public List<Kardex> getAllByPeriodoalmacenEqualsAndProductoEquals (Periodoalmacen periodoalmacen,Producto producto, Sort sort);
	

	

}
