package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modalidad_compra_venta database table.
 * 
 */
@Entity
@Table(name="modalidad_compra_venta")
@NamedQuery(name="ModalidadCompraVenta.findAll", query="SELECT m FROM ModalidadCompraVenta m")
public class ModalidadCompraVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_modalidad_compra_venta")
	private Integer idModalidadCompraVenta;

	@Column(name="dsc_modalidad_compra_venta")
	private String dscModalidadCompraVenta;

	//bi-directional many-to-one association to Com001
	@OneToMany(mappedBy="modalidadCompraVenta")
	private List<Com001> com001s;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="modalidadCompraVenta")
	private List<Ven001> ven001s;

	public ModalidadCompraVenta() {
	}

	public Integer getIdModalidadCompraVenta() {
		return this.idModalidadCompraVenta;
	}

	public void setIdModalidadCompraVenta(Integer idModalidadCompraVenta) {
		this.idModalidadCompraVenta = idModalidadCompraVenta;
	}

	public String getDscModalidadCompraVenta() {
		return this.dscModalidadCompraVenta;
	}

	public void setDscModalidadCompraVenta(String dscModalidadCompraVenta) {
		this.dscModalidadCompraVenta = dscModalidadCompraVenta;
	}

	public List<Com001> getCom001s() {
		return this.com001s;
	}

	public void setCom001s(List<Com001> com001s) {
		this.com001s = com001s;
	}

	public Com001 addCom001(Com001 com001) {
		getCom001s().add(com001);
		com001.setModalidadCompraVenta(this);

		return com001;
	}

	public Com001 removeCom001(Com001 com001) {
		getCom001s().remove(com001);
		com001.setModalidadCompraVenta(null);

		return com001;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setModalidadCompraVenta(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setModalidadCompraVenta(null);

		return ven001;
	}

}