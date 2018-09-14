package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Integer>, JpaRepository<Empleado,Integer>, 
PagingAndSortingRepository<Empleado, Integer>,
JpaSpecificationExecutor<Empleado> {
	
	
	Empleado findAllByDni(String dni);
	
	@Query("SELECT max(p.idempleado) FROM Empleado p")
	Integer getMax();
	
//	@Query(nativeQuery = true, value="SELECT e.* FROM bdcomer.Empleado e ")
	@Query(value="SELECT e.* FROM bdcomer.empleado e "
			+ "inner join bdcomer.usuarioempleado ue on e.idempleado = ue.idempleado "
			+ "inner join bdcomer.usuario us on ue.idusuario = us.idusuario where us.idfilial = ?1 "
			+ "order by e.nomempleado", nativeQuery = true)	
	List<Empleado> findByCondicionFilial(Integer condicion);
}
