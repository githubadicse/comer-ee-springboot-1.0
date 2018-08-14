package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.clases.GuiaRemision001Class;
import com.adicse.comercial.clases.GuiasResumenPorNumeroUsuarioAndItemClass;
import com.adicse.comercial.model.GuiaRemision001;

public interface IGuiaRemision001Dao extends CrudRepository<GuiaRemision001, Integer> ,
PagingAndSortingRepository<GuiaRemision001, Integer>, JpaSpecificationExecutor<GuiaRemision001>{
	
	@Query("SELECT max(p.idGuiaRemision001) FROM GuiaRemision001 p")
	Integer getMax();	
	
	@Query("select p from GuiaRemision001 p where p.serie =:serie and p.numero =:numero")
	public GuiaRemision001 getGuiaRemisionPorSerieNumero(@Param("serie") Integer serie,@Param("numero") Integer numero);

	
	@Query("select p from GuiaRemision001 p "
			+ " where p.requerimientoVolumen001.codigomodularIinstitucionEducativa.codigoModular =:codigoModular "
			+ " and p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno "
			+ " and p.requerimientoVolumen001.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega ")
	public GuiaRemision001 getGuiaRemisionPorCodigoModular(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega, @Param("codigoModular") String codigoModular);
	
	
	@Query("select p from GuiaRemision001 p where p.requerimientoVolumen001.entregaPorItem.itemEntrega.item =:idItem")
	public List<GuiaRemision001> getGuiaRemisionPorItem(@Param("idItem") String idItem);
	
	@Query("select p from GuiaRemision001 p "
			+ " where p.requerimientoVolumen001.entregaPorItem.itemEntrega.item =:idItem and "
			+ " p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno "
			+ " and p.requerimientoVolumen001.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega")
	public List<GuiaRemision001> getGuiaRemisionPorItemAndAnnoNumeroEntrega(@Param("idItem") String idItem,@Param("anno") Integer anno,@Param("numeroEntrega") Integer numeroEntrega);
	
	
	@Query("select p from GuiaRemision001 p where p.requerimientoVolumen001.idRequerimientoVolumen001 = :idRequerimientoVolumen001")
	GuiaRemision001 getGuiaRemision001ByIdRequerimientoVolumen001(@Param("idRequerimientoVolumen001") String idRequerimientoVolumen001);
	
	
	
	
	@Query("select p from GuiaRemision001 p "
			+ " where  p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno "
			+ " and p.requerimientoVolumen001.entregaPorItem.itemEntrega.item =:item "
			+ " and p.requerimientoVolumen001.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega ")
	public List<GuiaRemision001> getGuiaRemisionPorAnoNumeroEntregaItem(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega, @Param("item") String codigoModular);

	@Query("select p as guiaRemision001,ie as itemEntrega from GuiaRemision001 p "
			+ " inner join p.requerimientoVolumen001 r on r.idRequerimientoVolumen001 = p.requerimientoVolumen001.idRequerimientoVolumen001 "
			+ " inner join r.entregaPorItem epi on epi.idEntregaPorItem = r.entregaPorItem.idEntregaPorItem "
			+ " inner join epi.itemEntrega ie on ie.item = epi.itemEntrega.item "
			+ " where  p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno "
			+ " and p.requerimientoVolumen001.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega ")
	public List<GuiaRemision001Class> getGuiaRemisionPorAnoNumeroEntrega(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	@Query("select p from GuiaRemision001 p "
			+ " inner join p.requerimientoVolumen001 r on r.idRequerimientoVolumen001 = p.requerimientoVolumen001.idRequerimientoVolumen001 "
			+ " inner join r.entregaPorItem epi on epi.idEntregaPorItem = r.entregaPorItem.idEntregaPorItem "
			+ " inner join epi.itemEntrega ie on ie.item = epi.itemEntrega.item "
			+ " where  p.requerimientoVolumen001.entregaPorItem.itemEntrega.anno =:anno "
			+ " and p.requerimientoVolumen001.entregaPorItem.numeroEntrega.numeroEntregaValor =:numeroEntrega ")
	public List<GuiaRemision001> getGuiaRemisionPorAnoNumeroEntregaV2(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
	
	@Query("select " +
			 " epi as entregaPorItem, ha as horarioAlimentacion,  iEntrega as itemEntrega," +
			" count(g1) as cntIe, sum(r1.numeroUsuarios) as numeroUsuarios" + 
			" from GuiaRemision001 g1" + 
			" inner join g1.requerimientoVolumen001 r1 on g1.requerimientoVolumen001.idRequerimientoVolumen001 = r1.idRequerimientoVolumen001" + 
			" inner join r1.entregaPorItem epi on r1.entregaPorItem.idEntregaPorItem = epi.idEntregaPorItem" + 
			" inner join epi.numeroEntrega nEntrega on nEntrega.idNumeroEntrega = epi.numeroEntrega.idNumeroEntrega" + 
			" inner join r1.nivelEducacion ne on r1.nivelEducacion.idNivelEducacion = ne.idNivelEducacion" + 
			" inner join r1.horarioAlimentacion ha on r1.horarioAlimentacion.idHorarioAlimentacion = ha.idHorarioAlimentacion" + 
			" inner join epi.itemEntrega iEntrega on iEntrega.item = epi.itemEntrega.item " +
			" where nEntrega.numeroEntregaValor = :numeroEntrega " + 
			" and iEntrega.item = :item and r1.flagEstado = 1" + 
			" group by nEntrega,epi,ha,iEntrega, ne.idNivelEducacion, ha.idHorarioAlimentacion")
	public List<GuiasResumenPorNumeroUsuarioAndItemClass> getGuiasResumenPorNumeroUsuariosAndItem(@Param("item") String item,@Param("numeroEntrega") Integer numeroEntrega);
	
	
	
//	@Query("select p from GuiaRemision001 p where p.requerimientoVolumen001  ")
//	public List<GuiaRemision001> getGuiasPendientesRegistroNroFisico(@Param("anno") Integer anno, @Param("numeroEntrega") Integer numeroEntrega);
}
