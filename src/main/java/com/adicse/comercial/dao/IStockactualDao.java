package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Stockactual;


public interface IStockactualDao extends CrudRepository<Stockactual, Integer>, PagingAndSortingRepository<Stockactual, Integer>,
JpaSpecificationExecutor<Stockactual>, JpaRepository<Stockactual, Integer> 
{
		
	@Query("Select s FROM Stockactual s inner join s.producto p "
			+ "inner join p.codigobarras c on c.producto.idproducto = p.idproducto "
			+ "where s.almacen.idalmacen = ?2 and (lower(CONCAT(p.dscproducto, p.categoria.dsccategoria, p.marca.dscmarca, c.codigo)) LIKE lower(concat('%',?1,'%'))) "
			+ "group by s.idstockactual")	
	public List<Stockactual> findByParametro(String parametro, Integer idalmacen, Pageable pageable);
	
}
