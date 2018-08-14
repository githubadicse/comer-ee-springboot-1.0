package com.adicse.comercial.service;

import java.math.BigDecimal;
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
import com.adicse.comercial.dao.IKardexDao;
import com.adicse.comercial.dao.ISalida001Dao;
import com.adicse.comercial.dao.ISalida002Dao;
import com.adicse.comercial.dao.ISalida002KardexDao;
import com.adicse.comercial.especification.Salida001Specification;
import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.model.Cierremensual;
import com.adicse.comercial.model.Kardex;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Proveedorcliente;
import com.adicse.comercial.model.Salida001;
import com.adicse.comercial.model.Salida002;
import com.adicse.comercial.model.Salida002kardex;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.Idunico;

@Service
@Transactional
public class Salida001Service implements IAdicseService<Salida001, Integer> {
	
	@Autowired
	private ISalida001Dao iSalida001Dao;
	
	@Autowired
	private ISalida002Dao iSalida002Dao;
	
	
	@Autowired
	private CierremensualService cierremensualService;
	
	@Autowired
	private ICierremensualDao iCierremensualDao;
	
	@Autowired
	private KardexService kardexService;
	
	@Autowired
	private IKardexDao iKardexDao;
	
	@Autowired
	private ISalida002KardexDao iSalida002KardexDao;
	
	@Override
	public Page<?> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		return null;
	}

	@Override
	public Page<Salida001> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
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
		Salida001 salida001filtro = new Salida001();
		salida001filtro.setIdsalida001(null);
		salida001filtro.setNrodoc(null);
				
		Almacen almacen = new Almacen();
		salida001filtro.setAlmacen(almacen);

		Proveedorcliente proveedorcliente = new Proveedorcliente();
		salida001filtro.setProveedorcliente(proveedorcliente);

		CustomFilterSpec efs = new CustomFilterSpec();
		try {

			salida001filtro = (Salida001) efs.CreateCustomFilter(salida001filtro, filter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Specification nos permite agregar implicitamente los where que se
		 * pasaran al evento findAll, Esto sucede en CrudRepository
		 */
		Specification<Salida001> spec = new Salida001Specification(salida001filtro);

		Page<Salida001> lista = iSalida001Dao.findAll(spec, pageable);

		//
		return lista;
	}

	@Override
	public List<Salida001> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salida001> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salida001 grabar(Salida001 entidad) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Timestamp tm = new Timestamp(date.getTime());
		entidad.setFechahorasys(tm);
		Integer next = null;
		if (entidad.getIdsalida001() == 0) {
			next = iSalida001Dao.getMax() == null ? 0 : iSalida001Dao.getMax();
			entidad .setNrodoc(next + 1);
		}
		if (entidad.getIdsalida001() == 0) {
			next = iSalida001Dao.getMaxId() == null ? 0 : iSalida001Dao.getMaxId();
			entidad.setIdsalida001(next + 1);
		}
		Salida001 salida001 = iSalida001Dao.save(entidad);
		
		//grabamos cierre de periodo y kardex
		
		for(Salida002 salida002 : salida001.getSalida002s()){
			
			Cierremensual cierremensual = cierremensualService.actualizaCierremensualSalida002(salida002);
			iCierremensualDao.save(cierremensual);
	
			Kardex kardex = kardexService.actualizaKardexSalida002(salida002);
			iKardexDao.save(kardex);
			
			Salida002kardex salida002kardex = new Salida002kardex();
			salida002kardex.setIdsalida002kardex(new Idunico().getIdunico() );
			salida002kardex.setSalida002(salida002);
			salida002kardex.setKardex(kardex);
			
			iSalida002KardexDao.save(salida002kardex);
			
		}
		
		return salida001;
	}

	@Override
	public void delete(Salida001 entidad) {
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
	public Optional<Salida001> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iSalida001Dao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Salida001> EntityForSpecificatios(Salida001 entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteSalida002ByIdSalida001(Integer idSalida001){
		iSalida002Dao.deleteSalida002ByIdSalida001(idSalida001);
	}

	public BigDecimal sumByPeriodoProducto(Periodoalmacen periodoalmacen,Producto producto){
		return iSalida002Dao.sumByPeriodoProducto(periodoalmacen, producto);
	}


	
	

}
