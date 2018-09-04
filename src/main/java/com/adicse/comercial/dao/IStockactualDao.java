package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Stockactual;


public interface IStockactualDao extends CrudRepository<Stockactual, Integer>, PagingAndSortingRepository<Stockactual, Integer>,
JpaSpecificationExecutor<Stockactual>, JpaRepository<Stockactual, Integer> 
{

}
