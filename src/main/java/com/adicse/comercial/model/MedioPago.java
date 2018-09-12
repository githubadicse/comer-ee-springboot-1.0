package com.adicse.comercial.model;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the medio_pago database table.
 * 
 */
@Entity
@Table(name="medio_pago")
@NamedQuery(name="MedioPago.findAll", query="SELECT m FROM MedioPago m")
public class MedioPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_medio_pago")
	private Integer idMedioPago;

	@Column(name="codigo_sunat")
	private String codigoSunat;

	@Column(name="dsc_medio_pago")
	private String dscMedioPago;

	public MedioPago() {
	}

	public Integer getIdMedioPago() {
		return this.idMedioPago;
	}

	public void setIdMedioPago(Integer idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public String getCodigoSunat() {
		return this.codigoSunat;
	}

	public void setCodigoSunat(String codigoSunat) {
		this.codigoSunat = codigoSunat;
	}

	public String getDscMedioPago() {
		return this.dscMedioPago;
	}

	public void setDscMedioPago(String dscMedioPago) {
		this.dscMedioPago = dscMedioPago;
	}

}
