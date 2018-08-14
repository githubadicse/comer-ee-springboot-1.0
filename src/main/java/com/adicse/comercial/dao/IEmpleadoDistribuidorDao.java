package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.EmpleadoDistribuidor;

public interface IEmpleadoDistribuidorDao extends CrudRepository<EmpleadoDistribuidor, String>,PagingAndSortingRepository<EmpleadoDistribuidor, String>, 
JpaSpecificationExecutor<EmpleadoDistribuidor> {

}
