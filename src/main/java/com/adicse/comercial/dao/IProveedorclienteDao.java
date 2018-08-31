package com.adicse.comercial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.Proveedorcliente;

public interface IProveedorclienteDao extends CrudRepository<Proveedorcliente, Integer>
,PagingAndSortingRepository<Proveedorcliente, Integer>
,JpaSpecificationExecutor<Proveedorcliente>,JpaRepository<Proveedorcliente, Integer>


{
	
	@Query("SELECT max(p.idproveedorcliente) FROM Proveedorcliente p")
	Integer getMax();	
	
	List<Proveedorcliente> findAllByRazonsocialContainsIgnoreCaseOrderByRazonsocial(String razonsocial);
	
	@Query("SELECT p from Proveedorcliente p where p.razonsocial like %:dato% ")
	List<Proveedorcliente> filterGlobal(@Param("dato") String dato);
	
//	@Query("Select p FROM Proveedorcliente p inner join p.proveedorclientedireccions c on c.proveedorclientedireccions.idproveedorcliente = p.idproveedorcliente where p.documentoidentificacion.iddocumentoidentificacion = :iddocumento and p.nrodocumento: numdocumento")
//	Proveedorcliente getFindByDocumento(@Param("iddocumento") Integer iddocumento, @Param("numdocumento") String numdocumento);
}
