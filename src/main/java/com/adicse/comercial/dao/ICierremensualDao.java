package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adicse.comercial.model.Cierremensual;
import com.adicse.comercial.model.Periodoalmacen;
import com.adicse.comercial.model.Producto;

public interface ICierremensualDao  extends CrudRepository<Cierremensual, Integer> {
	
	Cierremensual findAllByPeriodoalmacenAndProducto (Periodoalmacen periodoalmacen, Producto producto);
	
	List<Cierremensual> findAllByPeriodoalmacen(Periodoalmacen periodoalmacen);

}
