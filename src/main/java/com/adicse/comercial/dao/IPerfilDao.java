package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adicse.comercial.model.Perfil;

public interface IPerfilDao extends CrudRepository<Perfil, Integer> ,
PagingAndSortingRepository<Perfil, Integer>, JpaRepository<Perfil, Integer>,
JpaSpecificationExecutor<Perfil>
{
	
	@Query("SELECT max(p.idperfil) FROM Perfil p")
	Integer getMax();
	
}
