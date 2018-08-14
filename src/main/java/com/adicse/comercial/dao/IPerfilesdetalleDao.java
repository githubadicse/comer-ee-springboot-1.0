package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Perfilesdetalle;

public interface IPerfilesdetalleDao extends CrudRepository<Perfilesdetalle, Integer> ,
PagingAndSortingRepository<Perfilesdetalle, Integer>, JpaRepository<Perfilesdetalle, Integer>,
JpaSpecificationExecutor<Perfilesdetalle>
{	
	@Modifying
	@Query("delete From Perfilesdetalle c where c.perfil.idperfil = :id")
	void deletePerfilDetalleByIdPerfil(@Param("id") Integer id);
}
