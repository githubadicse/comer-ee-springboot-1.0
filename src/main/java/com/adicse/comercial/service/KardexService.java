package com.adicse.comercial.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IKardexDao;
import com.adicse.comercial.model.Ing002;
import com.adicse.comercial.model.Ing002kardex;
import com.adicse.comercial.model.Kardex;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Salida002;
import com.adicse.comercial.model.Salida002kardex;
import com.adicse.comercial.utilitarios.Idunico;
import com.adicse.comercial.utilitarios.UtilitarioFechas;


@Service
@Transactional
public class KardexService implements IAdicseService<Kardex, String> {
	
	@Autowired
	private IKardexDao iKardexDao;
	
	
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
	public List<Kardex> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kardex> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kardex grabar(Kardex entidad) {
		// TODO Auto-generated method stub
		return iKardexDao.save(entidad);
	}

	@Override
	public void delete(Kardex entidad) {
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
	public Optional<Kardex> findbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Kardex> EntityForSpecificatios(Kardex entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteKardexByIdKardexIng002(List<Ing002kardex> lstIng002Kardex){
		for(Ing002kardex ing002kardex : lstIng002Kardex){
			try{
			String idkardex = ing002kardex.getKardex().getIdkardex();
			iKardexDao.deleteKardexByIdKardex(idkardex);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
		
	}
	
	public Kardex actualizaKardexIng002(Ing002 ing002){
		Kardex kardex = new Kardex();
		kardex.setIdkardex(new Idunico().getIdunico());
		kardex.setProducto( ing002.getProducto() );
		kardex.setCantidad( ing002.getCantidad());
		String hora = ing002.getIng001().getHora().toString();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		

		//Timestamp ts = Timestamp.valueOf(datePart);
		String fecha = new UtilitarioFechas().convertirDateToString(ing002.getIng001().getFecha());
		String newFecha = fecha + " "+ hora;
		//LocalDateTime dt = LocalDateTime.of(datePart, timePart);
		Date parseDate;
		Timestamp timestamp = null;
		try {
			parseDate = dateFormat.parse(newFecha);
			timestamp = new Timestamp(parseDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		kardex.setFechahora(timestamp);
		kardex.setTipomovimiento("I");
		kardex.setPeriodoalmacen(ing002.getIng001().getPeriodoalmacen());
		
		Integer nromov = iKardexDao.getMax(ing002.getProducto(), ing002.getIng001().getPeriodoalmacen());
		if(nromov == null){
			nromov = 0;
		}
		kardex.setNromov(nromov + 1);
		return kardex;
	}
	
	
	public Kardex actualizaKardexSalida002(Salida002 salida002){
		Kardex kardex = new Kardex();
		kardex.setIdkardex(new Idunico().getIdunico());
		kardex.setProducto( salida002.getProducto() );
		kardex.setCantidad( salida002.getCantidad());
		String hora = salida002.getSalida001().getHora().toString();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		

		//Timestamp ts = Timestamp.valueOf(datePart);
		String fecha = new UtilitarioFechas().convertirDateToString(salida002.getSalida001().getFecha());
		String newFecha = fecha + " "+ hora;
		//LocalDateTime dt = LocalDateTime.of(datePart, timePart);
		Date parseDate;
		Timestamp timestamp = null;
		try {
			parseDate = dateFormat.parse(newFecha);
			timestamp = new Timestamp(parseDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		kardex.setFechahora(timestamp);
		kardex.setTipomovimiento("S");
		kardex.setPeriodoalmacen(salida002.getSalida001().getPeriodoalmacen());
		
		Integer nromov = iKardexDao.getMax(salida002.getProducto(), salida002.getSalida001().getPeriodoalmacen());
		if(nromov == null){
			nromov = 0;
		}
		kardex.setNromov(nromov + 1);
		return kardex;
	}	
	
	public void deleteKardexByIdKardexSalida002(List<Salida002kardex> lstSalida002Kardex){
		for(Salida002kardex salida002kardex : lstSalida002Kardex){
			
			if(salida002kardex != null){
				String idkardex = salida002kardex.getKardex().getIdkardex();
			try{
				iKardexDao.deleteKardexByIdKardex(idkardex);
				
			}catch(Exception ex){
				System.out.println( ex.getMessage());
			}
			}
			
		}
		
		
	}
	
	public List<Kardex> generaKardex(Periodoalmacen periodoalmacen, Producto producto){
		Sort sort = new Sort( Direction.ASC , "fechahora");
		List<Kardex> lstKardex = iKardexDao.getAllByPeriodoalmacenEqualsAndProductoEquals(periodoalmacen, producto, sort);
		
		
		return lstKardex;
		
	}


}
