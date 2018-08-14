package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.adicse.comercial.model.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Integer>, PagingAndSortingRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

	
	@Query("SELECT max(p.idusuario) FROM Usuario p")
	Integer getMax();
	
	
	public Usuario findAllByLogin(String login);
}
