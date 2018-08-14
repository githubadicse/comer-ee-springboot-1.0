package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.clases.RequerimientoClass;
import com.adicse.comercial.dao.IRequerimientoVolumen002ProductoDao;
import com.adicse.comercial.model.RequerimientoVolumen002Producto;

@Service
@Transactional
public class RequerimientoVolumen002ProductoService implements IAdicseService<RequerimientoVolumen002Producto, String> {

	EntityManager entityManager;

	@Autowired
	private IRequerimientoVolumen002ProductoDao iRequerimientoVolumen002ProductoDao;
	

	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RequerimientoVolumen002Producto> getall() {
		// TODO Auto-generated method stub

		return (List<RequerimientoVolumen002Producto>) iRequerimientoVolumen002ProductoDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<RequerimientoClass> listarTodo(Integer anno,Integer numeroEntrega) {
		


		List<RequerimientoClass> lst = iRequerimientoVolumen002ProductoDao.listarTodo(anno,numeroEntrega);
//		List<ProductoPresentacion> lstProductoPresentacion = new ArrayList<>();
//		
//		
//		String idCatalogoProducto = null;
//		for (int i = 0; i < lst.size(); i++) {
//			System.out.println("Procesando : " + i);
//			CatalogoProductoQaliwarma catalogoProductoQaliwarma = lst.get(i).getCatalogoProductoQaliwarma();
//			idCatalogoProducto = catalogoProductoQaliwarma.getIdCatalogoProductoQaliwarma();
//			lstProductoPresentacion =iProductoPresentacionDao.getProductoPresentacionByIdProductoAnnoNumeroEntrega(idCatalogoProducto, anno, numeroEntrega);
//			catalogoProductoQaliwarma.setProductoPresentacions(lstProductoPresentacion);
//			catalogoProductoQaliwarma = null;
//			lstProductoPresentacion = null;
//		}
	
		// iRequerimientoVolumen002ProductoDao.flush();
		return lst;
	}

	@Override
	public List<RequerimientoVolumen002Producto> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public RequerimientoVolumen002Producto grabar(RequerimientoVolumen002Producto entidad) {
		// TODO Auto-generated method stub

		entidad = iRequerimientoVolumen002ProductoDao.save(entidad);
		// iRequerimientoVolumen002ProductoDao.flush();
		return entidad;
	}

	@Override
	public void delete(RequerimientoVolumen002Producto entidad) {
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
	public Optional<RequerimientoVolumen002Producto> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RequerimientoVolumen002Producto> EntityForSpecificatios(RequerimientoVolumen002Producto entidad,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List<RequerimientoVolumen002Producto> getRequerimientoVolumen002ProductoByIdRequerimientoVolumen002(
			String idrq002) {
		return iRequerimientoVolumen002ProductoDao
				.getRequerimientoVolumen002ProductoByIdRequerimientoVolumen002(idrq002);
	}

	public void GrabarAll(List<RequerimientoVolumen002Producto> lista) {
		for (RequerimientoVolumen002Producto row : lista) {
			this.grabar(row);
		}
	}
	
	public void deleteAll(Integer anno, Integer numeroEntrega) {
		iRequerimientoVolumen002ProductoDao.deleteAll();
	}



}
