package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modalidad_venta database table.
 * 
 */
@Entity
@Table(name="modalidad_venta")
@NamedQuery(name="ModalidadVenta.findAll", query="SELECT m FROM ModalidadVenta m")
public class ModalidadVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_modalidad_venta")
	private Integer idModalidadVenta;

	@Column(name="dsc_modalidad_venta")
	private String dscModalidadVenta;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="modalidadVenta")
	private List<Ven001> ven001s;

	public ModalidadVenta() {
	}

	public Integer getIdModalidadVenta() {
		return this.idModalidadVenta;
	}

	public void setIdModalidadVenta(Integer idModalidadVenta) {
		this.idModalidadVenta = idModalidadVenta;
	}

	public String getDscModalidadVenta() {
		return this.dscModalidadVenta;
	}

	public void setDscModalidadVenta(String dscModalidadVenta) {
		this.dscModalidadVenta = dscModalidadVenta;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setModalidadVenta(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setModalidadVenta(null);

		return ven001;
	}

}