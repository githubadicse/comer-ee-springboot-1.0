package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.adicse.comercial.shared.SqlTimeDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;


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

	@JsonFormat (pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="fecha_registro_systema")
	@JsonFormat (pattern ="dd/MM/yyyy hh:mm:ss")
	private Timestamp fechaRegistroSystema;

	@Column(name="fecha_registro_systema_modifica")
	@JsonFormat (pattern ="dd/MM/yyyy hh:mm:ss")
	private Timestamp fechaRegistroSystemaModifica;

	private String glosa;

	@JsonDeserialize(using=SqlTimeDeserializer.class)
	private Time hora;

	@Column(name="idusuario_crea")
	private Integer idusuarioCrea;

	@Column(name="idusuario_modifica")
	private Integer idusuarioModifica;

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

	//bi-directional many-to-one association to Ing001Com002Relacion
	@OneToMany(mappedBy="ing001")
	private List<Ing001Com002Relacion> ing001Com002Relacions;

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

	public Timestamp getFechaRegistroSystema() {
		return this.fechaRegistroSystema;
	}

	public void setFechaRegistroSystema(Timestamp fechaRegistroSystema) {
		this.fechaRegistroSystema = fechaRegistroSystema;
	}

	public Timestamp getFechaRegistroSystemaModifica() {
		return this.fechaRegistroSystemaModifica;
	}

	public void setFechaRegistroSystemaModifica(Timestamp fechaRegistroSystemaModifica) {
		this.fechaRegistroSystemaModifica = fechaRegistroSystemaModifica;
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

	public Integer getIdusuarioCrea() {
		return this.idusuarioCrea;
	}

	public void setIdusuarioCrea(Integer idusuarioCrea) {
		this.idusuarioCrea = idusuarioCrea;
	}

	public Integer getIdusuarioModifica() {
		return this.idusuarioModifica;
	}

	public void setIdusuarioModifica(Integer idusuarioModifica) {
		this.idusuarioModifica = idusuarioModifica;
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

	public List<Ing001Com002Relacion> getIng001Com002Relacions() {
		return this.ing001Com002Relacions;
	}

	public void setIng001Com002Relacions(List<Ing001Com002Relacion> ing001Com002Relacions) {
		this.ing001Com002Relacions = ing001Com002Relacions;
	}

	public Ing001Com002Relacion addIng001Com002Relacion(Ing001Com002Relacion ing001Com002Relacion) {
		getIng001Com002Relacions().add(ing001Com002Relacion);
		ing001Com002Relacion.setIng001(this);

		return ing001Com002Relacion;
	}

	public Ing001Com002Relacion removeIng001Com002Relacion(Ing001Com002Relacion ing001Com002Relacion) {
		getIng001Com002Relacions().remove(ing001Com002Relacion);
		ing001Com002Relacion.setIng001(null);

		return ing001Com002Relacion;
	}

}
