package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the matriz database table.
 * 
 */
@Entity
@NamedQuery(name="Matriz.findAll", query="SELECT m FROM Matriz m")
public class Matriz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_matriz")
	private Long idMatriz;

	@Column(name="abr_unidad_medida")
	private String abrUnidadMedida;

	@Column(name="alimento_seleccionado")
	private String alimentoSeleccionado;

	@Column(name="cantidad_lote1")
	private Integer cantidadLote1;

	@Column(name="cantidad_lote2")
	private Integer cantidadLote2;

	@Column(name="cantidad_lote3")
	private Integer cantidadLote3;

	@Column(name="cantidad_lote4")
	private Integer cantidadLote4;

	@Column(name="cantidad_lote5")
	private Integer cantidadLote5;

	@Column(name="dsc_grupo_alimento")
	private String dscGrupoAlimento;

	private BigDecimal factor1;

	private BigDecimal factor2;

	private BigDecimal factor3;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento1")
	private Date fechaVencimiento1;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento2")
	private Date fechaVencimiento2;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento3")
	private Date fechaVencimiento3;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento4")
	private Date fechaVencimiento4;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento5")
	private Date fechaVencimiento5;

	private String lote1;

	private String lote2;

	private String lote3;

	private String lote4;

	private String lote5;

	private String marca1;

	private String marca2;

	private String marca3;

	private String marca4;

	private String marca5;

	private BigDecimal peso;

	private Integer presentacion1;

	private Integer presentacion2;

	private Integer presentacion3;

	@Column(name="registro_sanitario1")
	private String registroSanitario1;

	@Column(name="registro_sanitario2")
	private String registroSanitario2;

	@Column(name="registro_sanitario3")
	private String registroSanitario3;

	@Column(name="registro_sanitario4")
	private String registroSanitario4;

	@Column(name="registro_sanitario5")
	private String registroSanitario5;

	private Integer unds1;

	private Integer unds2;

	private Integer unds3;

	private Integer usuarios;

	private BigDecimal volumen;

	//bi-directional many-to-one association to ProductoPorNumeroEntrega
	@ManyToOne
	@JoinColumn(name="id_producto_por_numero_entrega")
	private ProductoPorNumeroEntrega productoPorNumeroEntrega;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_001")
	private RequerimientoVolumen001 requerimientoVolumen001;

	//bi-directional many-to-one association to MatrizPresentacion
	@OneToMany(mappedBy="matriz")
	private List<MatrizPresentacion> matrizPresentacions;

	public Matriz() {
	}

	public Long getIdMatriz() {
		return this.idMatriz;
	}

	public void setIdMatriz(Long idMatriz) {
		this.idMatriz = idMatriz;
	}

	public String getAbrUnidadMedida() {
		return this.abrUnidadMedida;
	}

	public void setAbrUnidadMedida(String abrUnidadMedida) {
		this.abrUnidadMedida = abrUnidadMedida;
	}

	public String getAlimentoSeleccionado() {
		return this.alimentoSeleccionado;
	}

	public void setAlimentoSeleccionado(String alimentoSeleccionado) {
		this.alimentoSeleccionado = alimentoSeleccionado;
	}

	public Integer getCantidadLote1() {
		return this.cantidadLote1;
	}

	public void setCantidadLote1(Integer cantidadLote1) {
		this.cantidadLote1 = cantidadLote1;
	}

	public Integer getCantidadLote2() {
		return this.cantidadLote2;
	}

	public void setCantidadLote2(Integer cantidadLote2) {
		this.cantidadLote2 = cantidadLote2;
	}

	public Integer getCantidadLote3() {
		return this.cantidadLote3;
	}

	public void setCantidadLote3(Integer cantidadLote3) {
		this.cantidadLote3 = cantidadLote3;
	}

	public Integer getCantidadLote4() {
		return this.cantidadLote4;
	}

	public void setCantidadLote4(Integer cantidadLote4) {
		this.cantidadLote4 = cantidadLote4;
	}

	public Integer getCantidadLote5() {
		return this.cantidadLote5;
	}

	public void setCantidadLote5(Integer cantidadLote5) {
		this.cantidadLote5 = cantidadLote5;
	}

	public String getDscGrupoAlimento() {
		return this.dscGrupoAlimento;
	}

	public void setDscGrupoAlimento(String dscGrupoAlimento) {
		this.dscGrupoAlimento = dscGrupoAlimento;
	}

	public BigDecimal getFactor1() {
		return this.factor1;
	}

	public void setFactor1(BigDecimal factor1) {
		this.factor1 = factor1;
	}

	public BigDecimal getFactor2() {
		return this.factor2;
	}

	public void setFactor2(BigDecimal factor2) {
		this.factor2 = factor2;
	}

	public BigDecimal getFactor3() {
		return this.factor3;
	}

	public void setFactor3(BigDecimal factor3) {
		this.factor3 = factor3;
	}

	public Date getFechaVencimiento1() {
		return this.fechaVencimiento1;
	}

	public void setFechaVencimiento1(Date fechaVencimiento1) {
		this.fechaVencimiento1 = fechaVencimiento1;
	}

	public Date getFechaVencimiento2() {
		return this.fechaVencimiento2;
	}

	public void setFechaVencimiento2(Date fechaVencimiento2) {
		this.fechaVencimiento2 = fechaVencimiento2;
	}

	public Date getFechaVencimiento3() {
		return this.fechaVencimiento3;
	}

	public void setFechaVencimiento3(Date fechaVencimiento3) {
		this.fechaVencimiento3 = fechaVencimiento3;
	}

	public Date getFechaVencimiento4() {
		return this.fechaVencimiento4;
	}

	public void setFechaVencimiento4(Date fechaVencimiento4) {
		this.fechaVencimiento4 = fechaVencimiento4;
	}

	public Date getFechaVencimiento5() {
		return this.fechaVencimiento5;
	}

	public void setFechaVencimiento5(Date fechaVencimiento5) {
		this.fechaVencimiento5 = fechaVencimiento5;
	}

	public String getLote1() {
		return this.lote1;
	}

	public void setLote1(String lote1) {
		this.lote1 = lote1;
	}

	public String getLote2() {
		return this.lote2;
	}

	public void setLote2(String lote2) {
		this.lote2 = lote2;
	}

	public String getLote3() {
		return this.lote3;
	}

	public void setLote3(String lote3) {
		this.lote3 = lote3;
	}

	public String getLote4() {
		return this.lote4;
	}

	public void setLote4(String lote4) {
		this.lote4 = lote4;
	}

	public String getLote5() {
		return this.lote5;
	}

	public void setLote5(String lote5) {
		this.lote5 = lote5;
	}

	public String getMarca1() {
		return this.marca1;
	}

	public void setMarca1(String marca1) {
		this.marca1 = marca1;
	}

	public String getMarca2() {
		return this.marca2;
	}

	public void setMarca2(String marca2) {
		this.marca2 = marca2;
	}

	public String getMarca3() {
		return this.marca3;
	}

	public void setMarca3(String marca3) {
		this.marca3 = marca3;
	}

	public String getMarca4() {
		return this.marca4;
	}

	public void setMarca4(String marca4) {
		this.marca4 = marca4;
	}

	public String getMarca5() {
		return this.marca5;
	}

	public void setMarca5(String marca5) {
		this.marca5 = marca5;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Integer getPresentacion1() {
		return this.presentacion1;
	}

	public void setPresentacion1(Integer presentacion1) {
		this.presentacion1 = presentacion1;
	}

	public Integer getPresentacion2() {
		return this.presentacion2;
	}

	public void setPresentacion2(Integer presentacion2) {
		this.presentacion2 = presentacion2;
	}

	public Integer getPresentacion3() {
		return this.presentacion3;
	}

	public void setPresentacion3(Integer presentacion3) {
		this.presentacion3 = presentacion3;
	}

	public String getRegistroSanitario1() {
		return this.registroSanitario1;
	}

	public void setRegistroSanitario1(String registroSanitario1) {
		this.registroSanitario1 = registroSanitario1;
	}

	public String getRegistroSanitario2() {
		return this.registroSanitario2;
	}

	public void setRegistroSanitario2(String registroSanitario2) {
		this.registroSanitario2 = registroSanitario2;
	}

	public String getRegistroSanitario3() {
		return this.registroSanitario3;
	}

	public void setRegistroSanitario3(String registroSanitario3) {
		this.registroSanitario3 = registroSanitario3;
	}

	public String getRegistroSanitario4() {
		return this.registroSanitario4;
	}

	public void setRegistroSanitario4(String registroSanitario4) {
		this.registroSanitario4 = registroSanitario4;
	}

	public String getRegistroSanitario5() {
		return this.registroSanitario5;
	}

	public void setRegistroSanitario5(String registroSanitario5) {
		this.registroSanitario5 = registroSanitario5;
	}

	public Integer getUnds1() {
		return this.unds1;
	}

	public void setUnds1(Integer unds1) {
		this.unds1 = unds1;
	}

	public Integer getUnds2() {
		return this.unds2;
	}

	public void setUnds2(Integer unds2) {
		this.unds2 = unds2;
	}

	public Integer getUnds3() {
		return this.unds3;
	}

	public void setUnds3(Integer unds3) {
		this.unds3 = unds3;
	}

	public Integer getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Integer usuarios) {
		this.usuarios = usuarios;
	}

	public BigDecimal getVolumen() {
		return this.volumen;
	}

	public void setVolumen(BigDecimal volumen) {
		this.volumen = volumen;
	}

	public ProductoPorNumeroEntrega getProductoPorNumeroEntrega() {
		return this.productoPorNumeroEntrega;
	}

	public void setProductoPorNumeroEntrega(ProductoPorNumeroEntrega productoPorNumeroEntrega) {
		this.productoPorNumeroEntrega = productoPorNumeroEntrega;
	}

	public RequerimientoVolumen001 getRequerimientoVolumen001() {
		return this.requerimientoVolumen001;
	}

	public void setRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		this.requerimientoVolumen001 = requerimientoVolumen001;
	}

	public List<MatrizPresentacion> getMatrizPresentacions() {
		return this.matrizPresentacions;
	}

	public void setMatrizPresentacions(List<MatrizPresentacion> matrizPresentacions) {
		this.matrizPresentacions = matrizPresentacions;
	}

	public MatrizPresentacion addMatrizPresentacion(MatrizPresentacion matrizPresentacion) {
		getMatrizPresentacions().add(matrizPresentacion);
		matrizPresentacion.setMatriz(this);

		return matrizPresentacion;
	}

	public MatrizPresentacion removeMatrizPresentacion(MatrizPresentacion matrizPresentacion) {
		getMatrizPresentacions().remove(matrizPresentacion);
		matrizPresentacion.setMatriz(null);

		return matrizPresentacion;
	}

}