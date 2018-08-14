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

import com.adicse.comercial.dao.IMontoContratadoDao;
import com.adicse.comercial.especification.MontoContratadoSpecification;
import com.adicse.comercial.model.EntregaPorItem;
import com.adicse.comercial.model.ItemEntrega;
import com.adicse.comercial.model.MontoContratado;
import com.adicse.comercial.model.NumeroEntrega;
import com.adicse.comercial.shared.CustomFilterSpec;
import com.adicse.comercial.utilitarios.UtilitarioObjectToJSon;

@Service
@Transactional
public class MontoContratadoService implements IAdicseService<MontoContratado, Integer> {

	@Autowired
	private IMontoContratadoDao iMontoContratadorDao;
	
	@Override
	public Page<?> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MontoContratado> paginationParmsExtra(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter, Object paramsExtra) {
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);

	
		
		UtilitarioObjectToJSon utilitarioObjectToJSon = new UtilitarioObjectToJSon();
		Integer anno = Integer.parseInt( utilitarioObjectToJSon.getValueOfObject(paramsExtra, "anno"));
		Integer numeroEntrega = Integer.parseInt(utilitarioObjectToJSon.getValueOfObject(paramsExtra, "numeroEntrega"));
		
		ItemEntrega itemEntrega = new ItemEntrega();
		itemEntrega.setDscitem(null);
		itemEntrega.setAnno(anno);
		
		NumeroEntrega numeroEntrega2 = new NumeroEntrega();
		numeroEntrega2.setNumeroEntregaValor(numeroEntrega);
		
		
		EntregaPorItem entregaPorItem = new EntregaPorItem();
		entregaPorItem.setItemEntrega(itemEntrega);
		entregaPorItem.setNumeroEntrega(numeroEntrega2);
		
		MontoContratado montoContratado = new MontoContratado();
		montoContratado.setEntregaPorItem(entregaPorItem);
		
		
		
		CustomFilterSpec efs = new CustomFilterSpec();
		try {
			
			montoContratado = (MontoContratado) efs.CreateCustomFilter(montoContratado, filter);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Specification nos permite agregar implicitamente los where que se pasaran al evento findAll,
		 * Esto sucede en CrudRepository
		 */
		Specification<MontoContratado> spec = new MontoContratadoSpecification(montoContratado);
		
		Page<MontoContratado> lista = iMontoContratadorDao.findAll(spec,pageable);
 

		return lista;
	}

	@Override
	public List<MontoContratado> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MontoContratado> getallbyid(List<?> lst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MontoContratado grabar(MontoContratado entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MontoContratado entidad) {
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
	public Optional<MontoContratado> findbyid(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MontoContratado> EntityForSpecificatios(MontoContratado entidad, Object filter) {
		// TODO Auto-generated method stub
		return null;
	}



}
