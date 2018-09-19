package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Integer>, JpaRepository<Empleado,Integer>, 
PagingAndSortingRepository<Empleado, Integer>,
JpaSpecificationExecutor<Empleado> {
	
	
	Empleado findAllByDni(String dni);
	
	@Query("SELECT max(p.idempleado) FROM Empleado p")
	Integer getMax();
	
//	@Query(nativeQuery = true, value="SELECT e.* FROM bdcomer.Empleado e ")
	@Query(value="SELECT p from Empleado p where  p.filial.idfilial =:idfilial ")	
	List<Empleado> findByCondicionFilial(@Param("idfilial") Integer condicion);
}
