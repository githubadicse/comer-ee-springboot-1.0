package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the aperturapuntoventa database table.
 * 
 */
@Entity
@NamedQuery(name="Aperturapuntoventa.findAll", query="SELECT a FROM Aperturapuntoventa a")
public class Aperturapuntoventa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idaperturapuntoventa;

	private String estado;

	private Timestamp fechahora;

	private Integer idusuario;

	private BigDecimal saldoinicial;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="idempleado")
	private Empleado empleado;

	//bi-directional many-to-one association to Puntoventa
	@ManyToOne
	@JoinColumn(name="idpos")
	private Puntoventa puntoventa;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="aperturapuntoventa")
	private List<Ven001> ven001s;

	public Aperturapuntoventa() {
	}

	public Integer getIdaperturapuntoventa() {
		return this.idaperturapuntoventa;
	}

	public void setIdaperturapuntoventa(Integer idaperturapuntoventa) {
		this.idaperturapuntoventa = idaperturapuntoventa;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechahora() {
		return this.fechahora;
	}

	public void setFechahora(Timestamp fechahora) {
		this.fechahora = fechahora;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public BigDecimal getSaldoinicial() {
		return this.saldoinicial;
	}

	public void setSaldoinicial(BigDecimal saldoinicial) {
		this.saldoinicial = saldoinicial;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Puntoventa getPuntoventa() {
		return this.puntoventa;
	}

	public void setPuntoventa(Puntoventa puntoventa) {
		this.puntoventa = puntoventa;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setAperturapuntoventa(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setAperturapuntoventa(null);

		return ven001;
	}

}