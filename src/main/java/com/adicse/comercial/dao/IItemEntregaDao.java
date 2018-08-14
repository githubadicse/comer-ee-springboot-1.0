package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.ItemEntrega;



public interface IItemEntregaDao extends CrudRepository<ItemEntrega, String> {
	

	@Query("Select p from ItemEntrega p "
			+ " inner join p.entregaPorItems e "
			+ " inner join e.requerimientoVolumen001s r1 "
			+ " inner join r1.requerimientoVolumen002s r2 "
			+ " inner join r2.requerimientoVolumen002Productos r2p"
			
			
			+ " inner join e.numeroEntrega ns "
			
			+ " where p.anno =:anno and ns.numeroEntregaValor = :numeroEntrega "
	
			+ " and r2.numeroEntrega.numeroEntregaValor =:numeroEntrega "
			//+ " and r1.codigomodularIinstitucionEducativa.codigoModular = '0274118'"
			+ " order by r1.codigomodularIinstitucionEducativa")
	public List<ItemEntrega> getItemsEntrega(@Param("numeroEntrega") Integer numeroEntrega, @Param("anno") Integer anno) ;
	
	
	@Query("select p from ItemEntrega p "
			+ "where p.anno =:anno"
			+ "")
	public List<ItemEntrega> getAll();
	
	@Query("select p from ItemEntrega p "
			+ "where p.anno =:anno"
			+ "")
	public List<ItemEntrega> getAllByAno( @Param("anno") Integer anno);
	
	@Query("select p from ItemEntrega p "
			+ "where p.item =:item and p.anno =:anno"
			+ "")
	public ItemEntrega getItemByItem(@Param("item") String item, @Param("anno") Integer anno);
	
	
	
}
