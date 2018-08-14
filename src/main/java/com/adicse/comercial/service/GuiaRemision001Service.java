package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.clases.GuiaRemision001Class;
import com.adicse.comercial.clases.GuiasResumenPorNumeroUsuarioAndItemClass;
import com.adicse.comercial.dao.IGuiaRemision001Dao;
import com.adicse.comercial.especification.GuiaRemision001Specification;
import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.shared.CustomFilterSpec;

@Service
@Transactional(readOnly=true)
public class GuiaRemision001Service implements IAdicseService<GuiaRemision001, Integer> {
	
	@Autowired
	private IGuiaRemision001Dao iGuiaRemision001Dao; 
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<GuiaRemision001> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		GuiaRemision001 entidad = new GuiaRemision001();
		entidad.setSerie(null);
		entidad.setNumero(null);
		entidad.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().setCodigoModular(null);
		entidad.getRequerimientoVolumen001().getCodigomodularIinstitucionEducativa().setNombreInstitucionEducativa(null);
		entidad.getRequerimientoVolumen001().getEntregaPorItem().getItemEntrega().setItem(null);
		
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			entidad = (GuiaRemision001) efs.CreateCustomFilter(entidad, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<GuiaRemision001> spec = new GuiaRemision001Specification(entidad);
		
		Page<GuiaRemision001> lista = iGuiaRemision001Dao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	public List<GuiaRemision001> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuiaRemision001> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public GuiaRemision001 grabar(GuiaRemision001 entidad) {
		// TODO Auto-generated method stub
		return iGuiaRemision001Dao.save(entidad);
	}

	@Override
	public void delete(GuiaRemision001 entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GuiaRemision001> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iGuiaRemision001Dao.findById(id) ;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GuiaRemision001> EntityForSpecificatios(GuiaRemision001 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GuiaRemision001 getGuiaRemisionPorSerieNumero(Integer serie,Integer numero) {
		return iGuiaRemision001Dao.getGuiaRemisionPorSerieNumero(serie, numero);
	}
	
	
	public GuiaRemision001 getGuiaRemisionPorCodigoModular(Integer anno, Integer numeroEntrega,String codigoModular){
		return iGuiaRemision001Dao.getGuiaRemisionPorCodigoModular(anno,numeroEntrega,codigoModular);
	}
	
	
	
	public List<GuiaRemision001> getGuiaRemisionPorItem(String idItem){
		return iGuiaRemision001Dao.getGuiaRemisionPorItem(idItem);
	}

	public Integer getMax() {
		return iGuiaRemision001Dao.getMax()==null?1:iGuiaRemision001Dao.getMax() + 1;
	}
	
	public List<GuiaRemision001> getGuiaRemisionPorAnoNumeroEntregaItem(Integer anno, Integer numeroEntrega, String item){
		return iGuiaRemision001Dao.getGuiaRemisionPorAnoNumeroEntregaItem(anno, numeroEntrega, item);
	}
	
	
	public List<GuiaRemision001> getGuiaRemisionPorItemAndAnnoNumeroEntrega(String idItem,Integer anno,Integer numeroEntrega){
		return  iGuiaRemision001Dao.getGuiaRemisionPorItemAndAnnoNumeroEntrega(idItem, anno, numeroEntrega);
	}
	
	public List<GuiaRemision001Class> getGuiaRemisionPorAnoNumeroEntrega(Integer anno,Integer numeroEntrega){
		return  iGuiaRemision001Dao.getGuiaRemisionPorAnoNumeroEntrega(anno, numeroEntrega);
	}
	
	public List<GuiasResumenPorNumeroUsuarioAndItemClass> getGuiasResumenPorNumeroUsuariosAndItem(String item,Integer numeroEntrega){
		return iGuiaRemision001Dao.getGuiasResumenPorNumeroUsuariosAndItem(item, numeroEntrega);
	}
	
	public GuiaRemision001 getGuiaRemision001ByIdRequerimientoVolumen001(String idRequerimientoVolumen001) {
		return iGuiaRemision001Dao.getGuiaRemision001ByIdRequerimientoVolumen001(idRequerimientoVolumen001);
		
	}
	
	public List<GuiaRemision001> getGuiaRemisionPorAnoNumeroEntregaV2(Integer anno, Integer numeroEntrega){
		return iGuiaRemision001Dao.getGuiaRemisionPorAnoNumeroEntregaV2(anno, numeroEntrega);
	}


}
