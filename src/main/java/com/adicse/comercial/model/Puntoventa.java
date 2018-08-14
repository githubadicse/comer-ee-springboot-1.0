package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the puntoventa database table.
 * 
 */
@Entity
@NamedQuery(name="Puntoventa.findAll", query="SELECT p FROM Puntoventa p")
public class Puntoventa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idpuntoventa;

	private String dscpuntoventa;

	private String mac;

	//bi-directional many-to-one association to Aperturapuntoventa
	@OneToMany(mappedBy="puntoventa")
	private List<Aperturapuntoventa> aperturapuntoventas;

	public Puntoventa() {
	}

	public Integer getIdpuntoventa() {
		return this.idpuntoventa;
	}

	public void setIdpuntoventa(Integer idpuntoventa) {
		this.idpuntoventa = idpuntoventa;
	}

	public String getDscpuntoventa() {
		return this.dscpuntoventa;
	}

	public void setDscpuntoventa(String dscpuntoventa) {
		this.dscpuntoventa = dscpuntoventa;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<Aperturapuntoventa> getAperturapuntoventas() {
		return this.aperturapuntoventas;
	}

	public void setAperturapuntoventas(List<Aperturapuntoventa> aperturapuntoventas) {
		this.aperturapuntoventas = aperturapuntoventas;
	}

	public Aperturapuntoventa addAperturapuntoventa(Aperturapuntoventa aperturapuntoventa) {
		getAperturapuntoventas().add(aperturapuntoventa);
		aperturapuntoventa.setPuntoventa(this);

		return aperturapuntoventa;
	}

	public Aperturapuntoventa removeAperturapuntoventa(Aperturapuntoventa aperturapuntoventa) {
		getAperturapuntoventas().remove(aperturapuntoventa);
		aperturapuntoventa.setPuntoventa(null);

		return aperturapuntoventa;
	}

}