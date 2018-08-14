package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Proveedorclientedireccion;

public interface IProveedorclientedireccionesDao extends CrudRepository<Proveedorclientedireccion, String> {
	
	@Modifying
	@Query("delete From Proveedorclientedireccion c where c.proveedorcliente.idproveedorcliente = :idproveedorcliente")
	void deleteDireccionByIdProveedorCliente(@Param("idproveedorcliente") Integer idproveedorcliente);	

}
