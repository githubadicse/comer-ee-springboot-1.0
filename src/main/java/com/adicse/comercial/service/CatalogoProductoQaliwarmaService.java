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

import com.adicse.comercial.dao.ICatalogoProductoQaliwarmaDao;
import com.adicse.comercial.especification.CatalogoProductoQaliwarmaSpecification;
import com.adicse.comercial.model.CatalogoProductoQaliwarma;
import com.adicse.comercial.model.Unidadmedida;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.UtilitarioObjectToJSon;

@Service
@Transactional
public class CatalogoProductoQaliwarmaService implements IAdicseService<CatalogoProductoQaliwarma, String> {

	
	@Autowired
	private ICatalogoProductoQaliwarmaDao iCatalogoProductoQaliwarmaDao;
	
	@Override
	public Page<CatalogoProductoQaliwarma> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter ) {
		return null;
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<CatalogoProductoQaliwarma> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra ) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		/*  
		 * instanciamos una entidad la cual servira de contenedor para realizar el filtro
		 * este evento sera llenado dentro de una funcion que esta en CustomFilterSpec
		 * se le debe pasar dos parametros, uno la entidad que queremos llenar con los datos 
		 * del segundo parametro que es un objecto json que se para en la variable filter  
		 */
		
		UtilitarioObjectToJSon utilitarioObjectToJSon = new UtilitarioObjectToJSon();
		Integer anno = Integer.parseInt( utilitarioObjectToJSon.getValueOfObject(paramsExtra, "anno"));
//		String s = paramsExtra.toString();
//		JSONObject json = null;
//		try{
//			json = new JSONObject(s);
//		}catch(Exception e){
//			System.out.println("error : " + e.getMessage());
//		}
//		
//		JSONArray ajson = new JSONArray();
//		ajson.put(json);
//		JSONObject obj = new JSONObject();
//		for (int i = 0; i < ajson.length(); i++) {
//			obj = ajson.getJSONObject(i);	
//			for (Object key : obj.keySet()) {
//				System.out.println(key);
//			}
//		}
		
		
		CatalogoProductoQaliwarma catalogoProductoQaliwarmaFiltro = new CatalogoProductoQaliwarma();
		catalogoProductoQaliwarmaFiltro.setIdCatalogoProductoQaliwarma(null);
		catalogoProductoQaliwarmaFiltro.setDscCatalogoProductoQaliwarma(null);
		catalogoProductoQaliwarmaFiltro.setAnno(anno);
		
		
		
		Unidadmedida unidadmedida = new Unidadmedida();
		catalogoProductoQaliwarmaFiltro.setUnidadmedida(unidadmedida);
		

		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			catalogoProductoQaliwarmaFiltro = (CatalogoProductoQaliwarma) efs.CreateCustomFilter(catalogoProductoQaliwarmaFiltro, filter);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<CatalogoProductoQaliwarma> spec = new CatalogoProductoQaliwarmaSpecification(catalogoProductoQaliwarmaFiltro);
		
		Page<CatalogoProductoQaliwarma> lista = iCatalogoProductoQaliwarmaDao.findAll(spec,pageable);
 

		//
		return lista;
	}

	@Override
	@Transactional(readOnly=true)
	public List<CatalogoProductoQaliwarma> getall() {
		// TODO Auto-generated method stub
		return (List<CatalogoProductoQaliwarma>) iCatalogoProductoQaliwarmaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<CatalogoProductoQaliwarma> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogoProductoQaliwarma grabar(CatalogoProductoQaliwarma entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CatalogoProductoQaliwarma entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly=true)
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return iCatalogoProductoQaliwarmaDao.existsById(id);
	}
	
	public CatalogoProductoQaliwarma getCatalogoProductoById(String id) {
		return iCatalogoProductoQaliwarmaDao.getCatalogoProductoById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<CatalogoProductoQaliwarma> findbyid(String id) {
		// TODO Auto-generated method stub
		return iCatalogoProductoQaliwarmaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<CatalogoProductoQaliwarma> EntityForSpecificatios(CatalogoProductoQaliwarma entidad,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	List<CatalogoProductoQaliwarma>  getCatalogoProductoQaliwarmaByAnno(Integer anno){
		
		return iCatalogoProductoQaliwarmaDao.getCatalogoProductoQaliwarmaByAnno(anno);
	}


	

}
