package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.MedioPago;

public interface IMedioPagoDao extends CrudRepository<MedioPago, Integer>,
PagingAndSortingRepository<MedioPago, Integer>, 
JpaSpecificationExecutor<MedioPago>, JpaRepository<MedioPago, Integer>{

}
