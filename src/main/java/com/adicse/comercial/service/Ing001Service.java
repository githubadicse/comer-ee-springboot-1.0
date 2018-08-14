package com.adicse.comercial.service;

import java.sql.Timestamp;
import java.util.Date;
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

import com.adicse.comercial.dao.ICierremensualDao;
import com.adicse.comercial.dao.IIng001Dao;
import com.adicse.comercial.dao.IIng002KardexDao;
import com.adicse.comercial.dao.IKardexDao;
import com.adicse.comercial.especification.Ing001Specification;
import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.model.Cierremensual;
import com.adicse.comercial.model.Ing001;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Ing002kardex;
import com.adicse.comercial.model.Kardex;
import com.adicse.comercial.model.Proveedorcliente;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.Idunico;

@Service
@Transactional
public class Ing001Service implements IAdicseService<Ing001, Integer> {

	@Autowired
	private IIng001Dao iIng001Dao;
	
	@Autowired
	private ICierremensualDao iCierremensualDao;
	
	@Autowired
	private CierremensualService  cierremensualService;
		
	@Autowired
	private IKardexDao iKardexDao;
	
	@Autowired
	private KardexService kardexService;
	
	@Autowired
	private IIng002KardexDao iIng002KardexDao;
	
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Ing001> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable = PageRequest.of(pagenumber, rows, sort);

		/*
		 * instanciamos una entidad la cual servira de contenedor para realizar
		 * el filtro este evento sera llenado dentro de una funcion que esta en
		 * CustomFilterSpec se le debe pasar dos parametros, uno la entidad que
		 * queremos llenar con los datos del segundo parametro que es un objecto
		 * json que se para en la variable filter
		 */
		Ing001 ing001filtro = new Ing001();
		ing001filtro.setIding001(null);
		ing001filtro.setNrodoc(null);
		
		
		Almacen almacen = new Almacen();
		ing001filtro.setAlmacen(almacen);

		Proveedorcliente proveedorcliente = new Proveedorcliente();
		ing001filtro.setProveedorcliente(proveedorcliente);

		CustomFilterSpec efs = new CustomFilterSpec();
		try {

			ing001filtro = (Ing001) efs.CreateCustomFilter(ing001filtro, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Specification nos permite agregar implicitamente los where que se
		 * pasaran al evento findAll, Esto sucede en CrudRepository
		 */
		Specification<Ing001> spec = new Ing001Specification(ing001filtro);

		Page<Ing001> lista = iIng001Dao.findAll(spec, pageable);

		//
		return lista;
	}

	@Override
	public List<Ing001> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ing001> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ing001 grabar(Ing001 entidad) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Timestamp tm = new Timestamp(date.getTime());
		entidad.setFechahorasys(tm);
		Integer next = null;
		if (entidad.getIding001() == 0) {
			next = iIng001Dao.getMax() == null ? 0 : iIng001Dao.getMax();
			entidad.setNrodoc(next + 1);
		}
		if (entidad.getIding001() == 0) {
			next = iIng001Dao.getMaxId() == null ? 0 : iIng001Dao.getMaxId();
			entidad.setIding001(next + 1);
		}
		Ing001 ing001 = iIng001Dao.save(entidad);
		
		//grabamos cierre de periodo y kardex
		
		for(Ing002 ing002 : ing001.getIng002s()){
			
			Cierremensual cierremensual = cierremensualService.actualizaCierremensualIng002(ing002);
			iCierremensualDao.save(cierremensual);
	
			Kardex kardex = kardexService.actualizaKardexIng002(ing002);
			iKardexDao.save(kardex);
			
			Ing002kardex ing002kardex = new Ing002kardex();
			ing002kardex.setIding002kardex(new Idunico().getIdunico() );
			ing002kardex.setIng002(ing002);
			ing002kardex.setKardex(kardex);
			
			iIng002KardexDao.save(ing002kardex);
			
		}
		
		return ing001;
	}

	@Override
	public void delete(Ing001 entidad) {
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
	public Optional<Ing001> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iIng001Dao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ing001> EntityForSpecificatios(Ing001 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
