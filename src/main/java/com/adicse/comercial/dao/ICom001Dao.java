package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Com001;

public interface ICom001Dao extends CrudRepository<Com001, Long>,JpaRepository<Com001,Long>, 
PagingAndSortingRepository<Com001, Long>, 
JpaSpecificationExecutor<Com001>
{
	@Query("SELECT max(p.idcom001) FROM Com001 p")
	Integer getMax();
}
