package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Usuarioempleado;

public interface IUsuarioEmpleadoDao extends CrudRepository<Usuarioempleado, Integer> {
	
	
	@Query("SELECT max(p.idusuarioempleado) FROM Usuarioempleado p")
	Integer getMax();
	
	@Query("Select p from Usuarioempleado p where p.usuario.idusuario = :idusuario")
	Usuarioempleado getUsuarioEmpleadoByIdUsuario(@Param("idusuario") Integer idusuario);
	
	
	@Modifying
	@Query("delete from Usuarioempleado c where c.usuario.idusuario = :idusuario")
	void deleteUsuarioEmpleadoByIdusuario(@Param("idusuario") Integer idusuario);	
	

}
