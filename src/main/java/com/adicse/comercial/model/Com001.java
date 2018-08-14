package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the com001 database table.
 * 
 */
@Entity
@NamedQuery(name="Com001.findAll", query="SELECT c FROM Com001 c")
public class Com001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idcom001;

	private Timestamp fecha;

	private Timestamp fechahorasys;

	private Timestamp fechasys;

	private Time hora;

	private Integer idusuario;

	private double igvimporte;

	private double igvporcentaje;

	private double importe;

	private double saldo;

	private double tipocambio;

	private double totpago;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="idmoneda")
	private Moneda moneda;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Com002
	@OneToMany(mappedBy="com001")
	private List<Com002> com002s;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="com001")
	private List<Ing001> ing001s;

	public Com001() {
	}

	public Integer getIdcom001() {
		return this.idcom001;
	}

	public void setIdcom001(Integer idcom001) {
		this.idcom001 = idcom001;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechahorasys() {
		return this.fechahorasys;
	}

	public void setFechahorasys(Timestamp fechahorasys) {
		this.fechahorasys = fechahorasys;
	}

	public Timestamp getFechasys() {
		return this.fechasys;
	}

	public void setFechasys(Timestamp fechasys) {
		this.fechasys = fechasys;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public double getIgvimporte() {
		return this.igvimporte;
	}

	public void setIgvimporte(double igvimporte) {
		this.igvimporte = igvimporte;
	}

	public double getIgvporcentaje() {
		return this.igvporcentaje;
	}

	public void setIgvporcentaje(double igvporcentaje) {
		this.igvporcentaje = igvporcentaje;
	}

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getTipocambio() {
		return this.tipocambio;
	}

	public void setTipocambio(double tipocambio) {
		this.tipocambio = tipocambio;
	}

	public double getTotpago() {
		return this.totpago;
	}

	public void setTotpago(double totpago) {
		this.totpago = totpago;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Proveedorcliente getProveedorcliente() {
		return this.proveedorcliente;
	}

	public void setProveedorcliente(Proveedorcliente proveedorcliente) {
		this.proveedorcliente = proveedorcliente;
	}

	public List<Com002> getCom002s() {
		return this.com002s;
	}

	public void setCom002s(List<Com002> com002s) {
		this.com002s = com002s;
	}

	public Com002 addCom002(Com002 com002) {
		getCom002s().add(com002);
		com002.setCom001(this);

		return com002;
	}

	public Com002 removeCom002(Com002 com002) {
		getCom002s().remove(com002);
		com002.setCom001(null);

		return com002;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setCom001(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setCom001(null);

		return ing001;
	}

}