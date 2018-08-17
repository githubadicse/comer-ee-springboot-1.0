package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adicse.comercial.model.GuiaRemision001;
import com.adicse.comercial.model.Producto;
import com.adicse.comercial.model.Usuario;
import com.adicse.comercial.model.Usuarioempleado;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Integer>, PagingAndSortingRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

	
	@Query("SELECT max(p.idusuario) FROM Usuario p")
	Integer getMax();
	
	
	public Usuario findAllByLogin(String login);
	
//	@Query("select * from bdcomer.usuario where bdcomer.usuario.idfilial = 1")
//	
//	
//	@Query("Select p from Usuario p where p.usuario.idusuario = :idusuario")
//	Usuarioempleado getUsuarioEmpleadoByIdUsuario(@Param("idusuario") Integer idusuario);
//	
//	@Query("Select p FROM Usuario p where p.idfilial = :idfilial")
//	Producto getProductoByIdProducto(@Param("idfilial") Integer idfilial);
//
//	List<Usuario> findByDscusuarioContainingIgnoreCaseOrderByDscusuario(String dscproducto);
}
