package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.adicse.comercial.model.MontoContratado;

public interface IMontoContratadoDao extends CrudRepository<MontoContratado, Integer>, 
PagingAndSortingRepository<MontoContratado, Integer> ,
JpaSpecificationExecutor<MontoContratado>{

}
