package com.adicse.comercial.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IRequerimientoVolumen001Dao;
import com.adicse.comercial.especification.RequerimientoVolumen001Specification;
import com.adicse.comercial.model.CodigomodularIinstitucionEducativa;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.HorarioAlimentacion;
import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.model.NivelEducacion;
import com.adicse.comercial.model.NumeroEntrega;
import com.adicse.comercial.model.RegionAlimentaria;
import com.adicse.comercial.model.RequerimientoVolumen001;
import com.adicse.comercial.model.Ubigeo;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.UtilitarioObjectToJSon;

@Service
@Transactional
public class RequerimientoVolumen001Service implements IAdicseService<RequerimientoVolumen001, String> {

	@Autowired
	private IRequerimientoVolumen001Dao iRequerimientoVolumen001Dao;
	
	
	@Override
	public Page<RequerimientoVolumen001> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

	
		
		UtilitarioObjectToJSon utilitarioObjectToJSon = new UtilitarioObjectToJSon();
		Integer anno = Integer.parseInt( utilitarioObjectToJSon.getValueOfObject(paramsExtra, "anno"));
		Integer numeroEntrega = Integer.parseInt( utilitarioObjectToJSon.getValueOfObject(paramsExtra, "numeroEntrega"));
		String idItem = utilitarioObjectToJSon.getValueOfObject(paramsExtra, "item");
		String codigosModulares = utilitarioObjectToJSon.getValueOfObject(paramsExtra, "codigosModulares");
		
		if(codigosModulares == null) {
			codigosModulares = "";
		}
		
		try {
			codigosModulares = URLDecoder.decode( codigosModulares,"UTF-8");
			if(idItem!= null) {
				idItem = URLDecoder.decode( idItem,"UTF-8");
				
			}else {
				idItem = "";
			}
			
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String itemDsc = "";
		if(!filter.equals("undefined") ){
			itemDsc = utilitarioObjectToJSon.getValueOfObject(filter, "entregaPorItem.itemEntrega.dscitem");
		}
		
		
	
		
		RequerimientoVolumen001 requerimientoVolumen001 = new RequerimientoVolumen001();
		requerimientoVolumen001.setIdRequerimientoVolumen001(null);
		requerimientoVolumen001.setCentroPoblado(null);
		
		Ubigeo ubigeo = new Ubigeo();
		ubigeo.setIdubigeo(null);
		ubigeo.setNombreDepartamento(null);
		ubigeo.setNombreProvincia(null);
		ubigeo.setNombreDistrito(null);
		
		requerimientoVolumen001.setUbigeo(ubigeo);
		
		RegionAlimentaria regionAlimentaria = new RegionAlimentaria();
		regionAlimentaria.setIdRegionAlimentaria(null);
		regionAlimentaria.setDscRegionAlimentaria(null);
		
		requerimientoVolumen001.setRegionAlimentaria(regionAlimentaria);
		
		CodigomodularIinstitucionEducativa codigomodularIinstitucionEducativa = new CodigomodularIinstitucionEducativa();
		codigomodularIinstitucionEducativa.setCodigoModular(null);
		codigomodularIinstitucionEducativa.setNombreInstitucionEducativa(null);
		codigomodularIinstitucionEducativa.setDireccionInstitucionEducativa(null);
		
		requerimientoVolumen001.setCodigomodularIinstitucionEducativa(codigomodularIinstitucionEducativa);
		
		NivelEducacion nivelEducacion = new NivelEducacion();
		nivelEducacion.setIdNivelEducacion(null);
		nivelEducacion.setDscNivelEducacion(null);
		
		requerimientoVolumen001.setNivelEducacion(nivelEducacion);
		
		
		HorarioAlimentacion horarioAlimentacion = new HorarioAlimentacion();
		horarioAlimentacion.setIdHorarioAlimentacion(null);
		horarioAlimentacion.setDscHorarioAlimentacion(null);
		
		requerimientoVolumen001.setHorarioAlimentacion(horarioAlimentacion);
		
		
		
		ItemEntrega itemEntrega = new ItemEntrega();
		itemEntrega.setDscitem(itemDsc);
		itemEntrega.setItem(idItem);
		
		NumeroEntrega numeroEntregaE = new NumeroEntrega();
		
		EntregaPorItem entregaPorItem = new EntregaPorItem();
		entregaPorItem.setItemEntrega(itemEntrega);
		entregaPorItem.setNumeroEntrega(numeroEntregaE);
		
		requerimientoVolumen001.setEntregaPorItem(entregaPorItem);
		//EmpleadoDistribuidor empleadoDistribuidor = new EmpleadoDistribuidor();
		//rutaDistribucion.setEmpleadoDistribuidor(null);
		

		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			requerimientoVolumen001 = (RequerimientoVolumen001) efs.CreateCustomFilter(requerimientoVolumen001, filter);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		requerimientoVolumen001.getEntregaPorItem().getItemEntrega().setAnno(anno);
		requerimientoVolumen001.getEntregaPorItem().getNumeroEntrega().setNumeroEntregaValor(numeroEntrega);
		Specification<RequerimientoVolumen001> spec = new RequerimientoVolumen001Specification(requerimientoVolumen001,codigosModulares);
		
		Page<RequerimientoVolumen001> lista = iRequerimientoVolumen001Dao.findAll(spec,pageable);
 

		return lista;
	}
	
	@PersistenceContext
    private EntityManager entityManager;

	
	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequerimientoVolumen001> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequerimientoVolumen001> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public RequerimientoVolumen001 grabar(RequerimientoVolumen001 entidad) {
		// TODO Auto-generated method stub
		RequerimientoVolumen001 requerimientoVolumen001 = iRequerimientoVolumen001Dao.save(entidad);
		//entityManager.clear();
		return requerimientoVolumen001;
	}

	@Override
	public void delete(RequerimientoVolumen001 entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<RequerimientoVolumen001> findbyid(String id) {
		// TODO Auto-generated method stub
		return iRequerimientoVolumen001Dao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RequerimientoVolumen001> EntityForSpecificatios(RequerimientoVolumen001 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly=true)
	public List<RequerimientoVolumen001> getReqVol001ByAnnoItem(Integer anno, String item){
		return iRequerimientoVolumen001Dao.getReqVol001ByAnnoItem(anno, item);
	}
	
	@Transactional(readOnly=false)
	public void grabarList(List<RequerimientoVolumen001> lista) {
		
		for(RequerimientoVolumen001 row : lista) {
			iRequerimientoVolumen001Dao.save(row);
		}
	}

	public void deleteAll(Integer anno, Integer numeroEntrega) {
		//List<RequerimientoVolumen001> lst = this.getReqVol001ByAnnoAndNumeroEntrega(anno, numeroEntrega);
		//iRequerimientoVolumen001Dao.deleteAll(lst);
		//lst = null;
		iRequerimientoVolumen001Dao.deleteAllNative(anno, numeroEntrega);
	}

	public List<RequerimientoVolumen001> getReqVol001ByAnnoAndNumeroEntrega(Integer anno, Integer numeroEntrega){
		return iRequerimientoVolumen001Dao.getReqVol001ByAnnoAndNumeroEntrega(anno, numeroEntrega);
	}
	
	public List<RequerimientoVolumen001> getRequerimientoVolumen001ByIds(Set<String> ids){
		return iRequerimientoVolumen001Dao.getRequerimientoVolumen001ByIds(ids);
		
	}
	
	public RequerimientoVolumen001 getRequerimientoVolumen001ByCodigoModular (String codigoModular,Integer anno,Integer numeroEntrega) {
		return iRequerimientoVolumen001Dao.getRequerimientoVolumen001ByCodigoModular(codigoModular, anno, numeroEntrega);
	}
	
	public void updateCierre(@Param("lst") Set<String> lst) {
		iRequerimientoVolumen001Dao.updateCierre(lst);
	}

	
	
	

}
