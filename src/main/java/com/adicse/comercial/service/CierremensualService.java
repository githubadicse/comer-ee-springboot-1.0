package com.adicse.comercial.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.ICierremensualDao;
import com.adicse.comercial.dao.IIng002Dao;
import com.adicse.comercial.dao.IPeriodoalmacenDao;
import com.adicse.comercial.dao.ISalida002Dao;
import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.model.Cierremensual;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Salida002;
import com.adicse.comercial.utilitarios.Idunico;
import com.adicse.comercial.utilitarios.UtilitarioNexPeriodo;

@Service
@Transactional
public class CierremensualService implements IAdicseService<Cierremensual, Integer> {

	
	
	@Autowired
	private ICierremensualDao iCierremensualDao;
	
	@Autowired
	private IIng002Dao iIng002Dao;
	
	@Autowired
	private ISalida002Dao iSalida002Dao;
	
	@Autowired
	private IPeriodoalmacenDao iPeriodoalmacenDao;
	
	
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
	public List<Cierremensual> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cierremensual> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cierremensual grabar(Cierremensual entidad) {
		// TODO Auto-generated method stub
		
		return iCierremensualDao.save(entidad);
	}

	@Override
	public void delete(Cierremensual entidad) {
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
	public Optional<Cierremensual> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cierremensual> EntityForSpecificatios(Cierremensual entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cierremensual findAllByPeriodoalmacen(Periodoalmacen periodoalmacen, Producto producto)
	{
		return iCierremensualDao.findAllByPeriodoalmacenAndProducto(periodoalmacen,producto);
	}
	
	public Cierremensual actualizaCierremensualIng002(Ing002 ing002){
		BigDecimal cantidad = ing002.getCantidad();
		Producto producto = ing002.getProducto();
		Almacen almacen = ing002.getIng001().getAlmacen();
		Periodoalmacen periodoalmacen = ing002.getIng001().getPeriodoalmacen();
		
		//buscamos si existe en cierre de periodo para obtener el stock inicial.
		BigDecimal stockinicial = new BigDecimal(0);
		BigDecimal ingresos = new BigDecimal(0);
		BigDecimal salidas = new BigDecimal(0);
		BigDecimal stockfinal = new BigDecimal(0);
		
		//OJO ACA SE DEBE FILTRAR POR PERIODOALMACEN Y PRODUCTO. FALTAL PRODUCTO
		Cierremensual cierremensual = iCierremensualDao.findAllByPeriodoalmacenAndProducto(periodoalmacen, producto);
		if(cierremensual != null){
			stockinicial = cierremensual.getStockinicial();
			ingresos = iIng002Dao.sumByPeriodoProducto(periodoalmacen, producto);
			salidas = cierremensual.getSalidas() ;
			stockfinal = cierremensual.getStockfinal();
			
			if(stockinicial == null) {
				stockinicial = new BigDecimal(0);
			}
			if(ingresos == null){
				ingresos = new BigDecimal(0);
			}
			if(salidas == null){
				salidas = new BigDecimal(0);
			}		
			
			cierremensual.setIngresos( ingresos );
			cierremensual.setSalidas(salidas);
			cierremensual.setStockfinal( new BigDecimal( stockinicial.floatValue() + ingresos.floatValue() -  salidas.floatValue()) );
			
				
		}else{
			cierremensual = new Cierremensual();
			String idcierremensual = new Idunico().getIdunico();
			cierremensual.setIdcierremensual(idcierremensual);
			cierremensual.setProducto(producto);
			cierremensual.setAlmacen(almacen);
			cierremensual.setPeriodoalmacen(periodoalmacen);
			cierremensual.setStockinicial( stockinicial );
			cierremensual.setIngresos( new BigDecimal( ingresos.floatValue() + cantidad.floatValue()) );
			cierremensual.setSalidas(salidas);
			cierremensual.setStockfinal( new BigDecimal(stockfinal.floatValue() + cantidad.floatValue()) );
			
			
			
		}	
		return cierremensual;
	}
//
	public Cierremensual actualizaCierremensualSalida002(Salida002 salida002){
		BigDecimal cantidad = salida002.getCantidad();
		Producto producto = salida002.getProducto();
		Almacen almacen = salida002.getSalida001().getAlmacen();
		Periodoalmacen periodoalmacen = salida002.getSalida001().getPeriodoalmacen();
		
		//buscamos si existe en cierre de periodo para obtener el stock inicial.
		BigDecimal stockinicial = new BigDecimal(0);
		BigDecimal ingresos = new BigDecimal(0);
		BigDecimal salidas = new BigDecimal(0);
		BigDecimal stockfinal = new BigDecimal(0);
		
		//OJO ACA SE DEBE FILTRAR POR PERIODOALMACEN Y PRODUCTO. FALTAL PRODUCTO
		Cierremensual cierremensual = iCierremensualDao.findAllByPeriodoalmacenAndProducto(periodoalmacen, producto);
		if(cierremensual != null){
			stockinicial = cierremensual.getStockinicial();
			ingresos = cierremensual.getIngresos();
			salidas = iSalida002Dao.sumByPeriodoProducto(periodoalmacen, producto);
			
			stockfinal = cierremensual.getStockfinal();
			
			cierremensual.setIngresos( ingresos );
			cierremensual.setSalidas(salidas);
			
			if(stockinicial == null) {
				stockinicial = new BigDecimal(0);
			}
			if(ingresos == null){
				ingresos = new BigDecimal(0);
			}
			if(salidas == null){
				salidas = new BigDecimal(0);
			}
			cierremensual.setStockfinal( new BigDecimal( stockinicial.floatValue() + ingresos.floatValue() -  salidas.floatValue()) );
			
				
		}else{
			cierremensual = new Cierremensual();
			String idcierremensual = new Idunico().getIdunico();
			cierremensual.setIdcierremensual(idcierremensual);
			cierremensual.setProducto(producto);
			cierremensual.setAlmacen(almacen);
			cierremensual.setPeriodoalmacen(periodoalmacen);
			cierremensual.setStockinicial( stockinicial );
			cierremensual.setIngresos( ingresos );
			salidas = new BigDecimal( salidas.floatValue() + cantidad.floatValue());
			cierremensual.setSalidas(salidas);
			cierremensual.setStockfinal( new BigDecimal(stockfinal.floatValue() - cantidad.floatValue()) );
			
			
			
		}	
		return cierremensual;
	}
	
	public Map<String,Object> procesoCierremensual(Periodoalmacen periodoalmacen){
		
		Map<String,Object> response = new HashMap<String, Object>();
		
		List<Cierremensual> lst = iCierremensualDao.findAllByPeriodoalmacen(periodoalmacen);
		BigDecimal stockinicial = new BigDecimal(0);
		BigDecimal ingresos = new BigDecimal(0);
		BigDecimal salidas = new BigDecimal(0);
		BigDecimal stockfinal = new BigDecimal(0);		
		
		Boolean success = true;
		
		for(Cierremensual cierremensual: lst){
			try{
			Producto producto = cierremensual.getProducto();
			stockinicial = cierremensual.getStockinicial();
			
			if(producto.getIdproducto() == 29742){
				System.out.println("producto buscado ..................");
				
			}
			
			ingresos = iIng002Dao.sumByPeriodoProducto(periodoalmacen, producto);		
			salidas = iSalida002Dao.sumByPeriodoProducto(periodoalmacen, producto);
			cierremensual.setIngresos(ingresos);
			
			salidas = cierremensual.getSalidas() ;
			
			if(stockinicial == null){
				stockinicial = new BigDecimal(0);
			}
			
			if(ingresos == null){
				ingresos = new BigDecimal(0);
			}
			
			if(salidas == null){
				salidas = new BigDecimal(0);
			}
			
			stockfinal = new BigDecimal( stockinicial.floatValue() + ingresos.floatValue() - salidas.floatValue()); 		
			cierremensual.setStockfinal(stockfinal);
			
			
			iCierremensualDao.save(cierremensual);			
			}catch(Exception ex){
				success = false;
				response.put("success", success);
				response.put("msg", ex.getMessage());
				break;
			}
	
		}
		
		//Actualizamos el periodo almace y creamos el nuevo periodo.
		periodoalmacen.setEstado("C");
		iPeriodoalmacenDao.save(periodoalmacen);
		
		Periodoalmacen periodoalmacenNew = null;
		Integer mes, anno, idalmacen;
		
		mes = new UtilitarioNexPeriodo().getNexMes(periodoalmacen.getMes());
		anno = new UtilitarioNexPeriodo().getNexAno(periodoalmacen.getMes(), periodoalmacen.getAnno() );
		idalmacen = periodoalmacen.getAlmacen().getIdalmacen();
		
		periodoalmacenNew = iPeriodoalmacenDao.findAllByMesEqualsAndAnnoEqualsAndAlmacenIdalmacenEquals(mes, anno, idalmacen);
		if(periodoalmacenNew == null){
			periodoalmacenNew = new Periodoalmacen();
			Integer nex = iPeriodoalmacenDao.getMax();
			if(nex == null){
				nex = 0;
			}
			nex++;
			periodoalmacenNew.setIdperiodoalmacen(nex);
		}
		

		periodoalmacenNew.setMes(new UtilitarioNexPeriodo().getNexMes(periodoalmacen.getMes()));
		periodoalmacenNew.setAnno(new UtilitarioNexPeriodo().getNexAno(periodoalmacen.getMes(), periodoalmacen.getAnno() ));
		periodoalmacenNew.setEstado("A");
		periodoalmacenNew.setIniciooperaciones(0);
		periodoalmacenNew.setAlmacen(periodoalmacen.getAlmacen());
		
		iPeriodoalmacenDao.save(periodoalmacenNew);
		
		Cierremensual cierremensualNew = null;
		for(Cierremensual cierremensual:lst){
			
				
			cierremensualNew = new Cierremensual();
			
			cierremensualNew.setIdcierremensual(new Idunico().getIdunico());
			cierremensualNew.setProducto(cierremensual.getProducto());
			cierremensualNew.setAlmacen(cierremensual.getAlmacen());
			cierremensualNew.setStockinicial(cierremensual.getStockfinal());
			cierremensualNew.setPeriodoalmacen(periodoalmacenNew);
			
			iCierremensualDao.save(cierremensualNew);		
			
		}
		
		response.put("success", success);
		
		
		return response;
	}


}
