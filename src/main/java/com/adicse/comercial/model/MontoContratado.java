package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the monto_contratado database table.
 * 
 */
@Entity
@Table(name="monto_contratado")
@NamedQuery(name="MontoContratado.findAll", query="SELECT m FROM MontoContratado m")
public class MontoContratado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_monto_contratado")
	private Integer idMontoContratado;

	@Temporal(TemporalType.DATE)
	@Column(name="fin_atencion")
	private Date finAtencion;

	@Temporal(TemporalType.DATE)
	@Column(name="inicio_atencion")
	private Date inicioAtencion;

	@Column(name="numero_dias")
	private Integer numeroDias;

	@Column(name="precio_unitario_igv")
	private BigDecimal precioUnitarioIgv;

	@Column(name="precio_unitario_valor")
	private BigDecimal precioUnitarioValor;

	@Column(name="precio_unitario_venta")
	private BigDecimal precioUnitarioVenta;

	//bi-directional many-to-one association to EntregaPorItem
	@ManyToOne
	@JoinColumn(name="id_entrega_por_item")
	private EntregaPorItem entregaPorItem;

	//bi-directional many-to-one association to HorarioAlimentacion
	@ManyToOne
	@JoinColumn(name="id_horario_alimentacion")
	private HorarioAlimentacion horarioAlimentacion;

	//bi-directional many-to-one association to NivelEducacion
	@ManyToOne
	@JoinColumn(name="id_nivel_educacion")
	private NivelEducacion nivelEducacion;

	public MontoContratado() {
	}

	public Integer getIdMontoContratado() {
		return this.idMontoContratado;
	}

	public void setIdMontoContratado(Integer idMontoContratado) {
		this.idMontoContratado = idMontoContratado;
	}

	public Date getFinAtencion() {
		return this.finAtencion;
	}

	public void setFinAtencion(Date finAtencion) {
		this.finAtencion = finAtencion;
	}

	public Date getInicioAtencion() {
		return this.inicioAtencion;
	}

	public void setInicioAtencion(Date inicioAtencion) {
		this.inicioAtencion = inicioAtencion;
	}

	public Integer getNumeroDias() {
		return this.numeroDias;
	}

	public void setNumeroDias(Integer numeroDias) {
		this.numeroDias = numeroDias;
	}

	public BigDecimal getPrecioUnitarioIgv() {
		return this.precioUnitarioIgv;
	}

	public void setPrecioUnitarioIgv(BigDecimal precioUnitarioIgv) {
		this.precioUnitarioIgv = precioUnitarioIgv;
	}

	public BigDecimal getPrecioUnitarioValor() {
		return this.precioUnitarioValor;
	}

	public void setPrecioUnitarioValor(BigDecimal precioUnitarioValor) {
		this.precioUnitarioValor = precioUnitarioValor;
	}

	public BigDecimal getPrecioUnitarioVenta() {
		return this.precioUnitarioVenta;
	}

	public void setPrecioUnitarioVenta(BigDecimal precioUnitarioVenta) {
		this.precioUnitarioVenta = precioUnitarioVenta;
	}

	public EntregaPorItem getEntregaPorItem() {
		return this.entregaPorItem;
	}

	public void setEntregaPorItem(EntregaPorItem entregaPorItem) {
		this.entregaPorItem = entregaPorItem;
	}

	public HorarioAlimentacion getHorarioAlimentacion() {
		return this.horarioAlimentacion;
	}

	public void setHorarioAlimentacion(HorarioAlimentacion horarioAlimentacion) {
		this.horarioAlimentacion = horarioAlimentacion;
	}

	public NivelEducacion getNivelEducacion() {
		return this.nivelEducacion;
	}

	public void setNivelEducacion(NivelEducacion nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

}