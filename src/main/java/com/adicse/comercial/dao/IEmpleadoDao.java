package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Integer>, PagingAndSortingRepository<Empleado, Integer>,
JpaSpecificationExecutor<Empleado> {
	
	
	Empleado findAllByDni(String dni);

}
