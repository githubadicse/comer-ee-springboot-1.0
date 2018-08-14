package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.model.Periodoalmacen;


public interface IPeriodoalmacenDao extends CrudRepository<Periodoalmacen, Integer> ,
PagingAndSortingRepository<Periodoalmacen, Integer>,
JpaSpecificationExecutor<Periodoalmacen>
{
	
	@Query("SELECT max(p.idperiodoalmacen) FROM Periodoalmacen p")
	Integer getMax();
	
	@Query("select p  from Periodoalmacen p where anno=:anno and mes =:mes and estado=:estado and p.almacen.idalmacen = :idalmacen")
	Periodoalmacen getPeriodoAlmacenByEstado(
			@Param("anno") Integer anno,
			@Param("mes") Integer mes,
			@Param("estado") String estado,
			@Param("idalmacen") Integer idalmcen
			);
	
	Periodoalmacen findAllByIniciooperacionesEqualsAndAlmacenIdalmacenEquals (Integer inicio,Integer idalmacen);
	
	Periodoalmacen findAllByMesEqualsAndAnnoEqualsAndAlmacenIdalmacenEquals(Integer mes,Integer anno,Integer idalmacen);
	
	Periodoalmacen findAllByAlmacenEqualsAndEstadoEquals(Almacen almacen, String estado);
	
	List<Periodoalmacen> findAllByAlmacen(Almacen almacen,Sort sort);
	
	Page<Periodoalmacen> findAllByAlmacenIdalmacenEquals (Pageable page, Integer idalmacen);
}
