package com.adicse.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the ing001 database table.
 * 
 */
@Entity
@NamedQuery(name="Ing001.findAll", query="SELECT i FROM Ing001 i")
public class Ing001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer iding001;

	private Integer condicionrelacioncompra;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	private Date fecha;

	private Timestamp fechahorasys;

	private String glosa;

	private Time hora;

	private Integer nrodoc;

	private String nrodocproveedor;

	private String seriedocproveedor;

	//bi-directional many-to-one association to Almacen
	@ManyToOne
	@JoinColumn(name="idalmacen")
	private Almacen almacen;

	//bi-directional many-to-one association to Com001
	@ManyToOne
	@JoinColumn(name="idcom001")
	private Com001 com001;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="idempleado")
	private Empleado empleado;

	//bi-directional many-to-one association to Motivoingreso
	@ManyToOne
	@JoinColumn(name="idmotivoingreso")
	private Motivoingreso motivoingreso;

	//bi-directional many-to-one association to Periodoalmacen
	@ManyToOne
	@JoinColumn(name="idperiodoalmacen")
	private Periodoalmacen periodoalmacen;

	//bi-directional many-to-one association to Proveedorcliente
	@ManyToOne
	@JoinColumn(name="idproveedorcliente")
	private Proveedorcliente proveedorcliente;

	//bi-directional many-to-one association to Tipodocumento
	@ManyToOne
	@JoinColumn(name="id_tipo_documento")
	private Tipodocumento tipodocumento;

	//bi-directional many-to-one association to Traslado
	@ManyToOne
	@JoinColumn(name="idtraslado")
	private Traslado traslado;

	//bi-directional many-to-one association to Ing002
	@OneToMany(mappedBy="ing001", cascade={CascadeType.ALL})
	private List<Ing002> ing002s;

	public Ing001() {
	}

	public Integer getIding001() {
		return this.iding001;
	}

	public void setIding001(Integer iding001) {
		this.iding001 = iding001;
	}

	public Integer getCondicionrelacioncompra() {
		return this.condicionrelacioncompra;
	}

	public void setCondicionrelacioncompra(Integer condicionrelacioncompra) {
		this.condicionrelacioncompra = condicionrelacioncompra;
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

	public String getNrodocproveedor() {
		return this.nrodocproveedor;
	}

	public void setNrodocproveedor(String nrodocproveedor) {
		this.nrodocproveedor = nrodocproveedor;
	}

	public String getSeriedocproveedor() {
		return this.seriedocproveedor;
	}

	public void setSeriedocproveedor(String seriedocproveedor) {
		this.seriedocproveedor = seriedocproveedor;
	}

	public Almacen getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public Com001 getCom001() {
		return this.com001;
	}

	public void setCom001(Com001 com001) {
		this.com001 = com001;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Motivoingreso getMotivoingreso() {
		return this.motivoingreso;
	}

	public void setMotivoingreso(Motivoingreso motivoingreso) {
		this.motivoingreso = motivoingreso;
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

	public Tipodocumento getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Traslado getTraslado() {
		return this.traslado;
	}

	public void setTraslado(Traslado traslado) {
		this.traslado = traslado;
	}

	public List<Ing002> getIng002s() {
		return this.ing002s;
	}

	public void setIng002s(List<Ing002> ing002s) {
		this.ing002s = ing002s;
	}

	public Ing002 addIng002(Ing002 ing002) {
		getIng002s().add(ing002);
		ing002.setIng001(this);

		return ing002;
	}

	public Ing002 removeIng002(Ing002 ing002) {
		getIng002s().remove(ing002);
		ing002.setIng001(null);

		return ing002;
	}

}
