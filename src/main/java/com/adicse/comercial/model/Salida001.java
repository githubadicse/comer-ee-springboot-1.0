package com.adicse.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the salida001 database table.
 * 
 */
@Entity
@NamedQuery(name="Salida001.findAll", query="SELECT s FROM Salida001 s")
public class Salida001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idsalida001;

	private Integer condicionrelacionventa;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	private Date fecha;

	private Timestamp fechahorasys;

	private String glosa;

	private Time hora;

	private Integer nrodoc;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="idempleado")
	private Empleado empleado;

	//bi-directional many-to-one association to Motivosalida
	@ManyToOne
	@JoinColumn(name="idmotivosalida")
	private Motivosalida motivosalida;

	//bi-directional many-to-one association to Periodoalmacen
	@ManyToOne
	@JoinColumn(name="idperiodoalmacen")
	private Periodoalmacen periodoalmacen;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Traslado
	@ManyToOne
	@JoinColumn(name="idtraslado")
	private Traslado traslado;

	//bi-directional many-to-one association to Ven001
	@ManyToOne
	@JoinColumn(name="idven001")
	private Ven001 ven001;

	//bi-directional many-to-one association to Salida002
	@OneToMany(mappedBy="salida001", cascade={CascadeType.ALL})
	private List<Salida002> salida002s;

	public Salida001() {
	}

	public Integer getIdsalida001() {
		return this.idsalida001;
	}

	public void setIdsalida001(Integer idsalida001) {
		this.idsalida001 = idsalida001;
	}

	public Integer getCondicionrelacionventa() {
		return this.condicionrelacionventa;
	}

	public void setCondicionrelacionventa(Integer condicionrelacionventa) {
		this.condicionrelacionventa = condicionrelacionventa;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechahorasys() {
		return this.fechahorasys;
	}

	public void setFechahorasys(Timestamp fechahorasys) {
		this.fechahorasys = fechahorasys;
	}

	public String getGlosa() {
		return this.glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Integer getNrodoc() {
		return this.nrodoc;
	}

	public void setNrodoc(Integer nrodoc) {
		this.nrodoc = nrodoc;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Motivosalida getMotivosalida() {
		return this.motivosalida;
	}

	public void setMotivosalida(Motivosalida motivosalida) {
		this.motivosalida = motivosalida;
	}

	public Periodoalmacen getPeriodoalmacen() {
		return this.periodoalmacen;
	}

	public void setPeriodoalmacen(Periodoalmacen periodoalmacen) {
		this.periodoalmacen = periodoalmacen;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

	public Traslado getTraslado() {
		return this.traslado;
	}

	public void setTraslado(Traslado traslado) {
		this.traslado = traslado;
	}

	public Ven001 getVen001() {
		return this.ven001;
	}

	public void setVen001(Ven001 ven001) {
		this.ven001 = ven001;
	}

	public List<Salida002> getSalida002s() {
		return this.salida002s;
	}

	public void setSalida002s(List<Salida002> salida002s) {
		this.salida002s = salida002s;
	}

	public Salida002 addSalida002(Salida002 salida002) {
		getSalida002s().add(salida002);
		salida002.setSalida001(this);

		return salida002;
	}

	public Salida002 removeSalida002(Salida002 salida002) {
		getSalida002s().remove(salida002);
		salida002.setSalida001(null);

		return salida002;
	}

}
