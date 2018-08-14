package com.adicse.comercial.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adicse.comercial.model.RutaDistribucionDetalle;

public interface IRutaDistribucionDetalleDao extends CrudRepository<RutaDistribucionDetalle, String> {
	
	@Query("select p from RutaDistribucionDetalle p where p.rutaDistribucion.anno =:anno "
			+ "and p.rutaDistribucion.numeroEntrega =:numeroEntrega "
			+ "and p.requerimientoVolumen001.codigomodularIinstitucionEducativa.codigoModular =:codigoModular ")
	public RutaDistribucionDetalle  getRutaDistribucionByCodigoModularAndNumeroEntregaAndAnno(
			@Param("codigoModular") String codigoModular,
			@Param("numeroEntrega") Integer numeroEntrega,
			@Param("anno") Integer anno
			);
	


}
