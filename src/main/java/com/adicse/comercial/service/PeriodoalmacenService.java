package com.adicse.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adicse.comercial.dao.IPeriodoalmacenDao;
import com.adicse.comercial.model.Almacen;
import com.adicse.comercial.model.Periodoalmacen;

@Service
@Transactional
public class PeriodoalmacenService implements IAdicseService<Periodoalmacen, Integer> {

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
	public List<Periodoalmacen> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Periodoalmacen> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Periodoalmacen grabar(Periodoalmacen entidad) {
		// TODO Auto-generated method stub
		Integer nexId = 0;
		if(entidad.getIdperiodoalmacen() == 0){
			nexId = iPeriodoalmacenDao.getMax() + 1;
			entidad.setIdperiodoalmacen(nexId);
		}
		return iPeriodoalmacenDao.save(entidad);
	}

	@Override
	public void delete(Periodoalmacen entidad) {
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
	public Optional<Periodoalmacen> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return iPeriodoalmacenDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Periodoalmacen> EntityForSpecificatios(Periodoalmacen entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Periodoalmacen getPeriodoAlmacenByEstado(Integer anno,Integer mes, String estado, Integer idalmacen){
		return iPeriodoalmacenDao.getPeriodoAlmacenByEstado(anno, mes, estado, idalmacen);
	}
	
	public Periodoalmacen findAllByIniciooperacionesEqualsAndAlmacenIdalmacenEquals(Integer inicio,Integer idalmacen){
		return iPeriodoalmacenDao.findAllByIniciooperacionesEqualsAndAlmacenIdalmacenEquals(inicio,idalmacen);
	}
	
	
	public Periodoalmacen findAllByMesEqualsAndAnnoEqualsAndAlmacenIdalmacenEquals(Integer mes,Integer anno,Integer idalmacen){
		return iPeriodoalmacenDao.findAllByMesEqualsAndAnnoEqualsAndAlmacenIdalmacenEquals(mes,anno,idalmacen);
	}
	
	public Periodoalmacen findAllByAlmacenEqualsAndEstadoEquals(Almacen almacen,String estado)
	{
		return iPeriodoalmacenDao.findAllByAlmacenEqualsAndEstadoEquals(almacen, estado);
	}
	
	public List<Periodoalmacen> findAllByAlmacen(Almacen almacen){
		Sort sort = new Sort( Direction.ASC , "anno");
		sort.and(new Sort(Direction.ASC , "mes"));
		return iPeriodoalmacenDao.findAllByAlmacen(almacen, sort);
	}
	
	public Page<Periodoalmacen> paginationByAlmacen(Integer pagenumber, Integer rows, Integer idalmacen) {
		// TODO Auto-generated method stub
		
		Sort sort = new Sort( Direction.ASC , "anno");
		sort.and(new Sort(Direction.ASC , "mes"));
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

		//Almacen almacen = iAlmacenDao.findById(idalmacen).get() ;
		
		
		Page<Periodoalmacen> lista = iPeriodoalmacenDao.findAllByAlmacenIdalmacenEquals(pageable, idalmacen) ;
 

		
		return lista;
	}


}
