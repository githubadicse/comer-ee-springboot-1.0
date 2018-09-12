package com.adicse.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the producto_por_numero_entrega database table.
 * 
 */
@Entity
@Table(name="producto_por_numero_entrega")
@NamedQuery(name="ProductoPorNumeroEntrega.findAll", query="SELECT p FROM ProductoPorNumeroEntrega p")
public class ProductoPorNumeroEntrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_producto_por_numero_entrega")
	private String idProductoPorNumeroEntrega;

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

	@Column(name="dsc_complemento_producto")
	private String dscComplementoProducto;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_elavoracion")
	private Date fechaElavoracion;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_vencimiento1")
	private Date fechaVencimiento1;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_vencimiento2")
	private Date fechaVencimiento2;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_vencimiento3")
	private Date fechaVencimiento3;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_vencimiento4")
	private Date fechaVencimiento4;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
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

	@Column(name="numero_lote")
	private String numeroLote;

	@Column(name="peso_total")
	private BigDecimal pesoTotal;

	@Column(name="presentacion_minima")
	private BigDecimal presentacionMinima;

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

	//bi-directional many-to-one association to CatalogoBonificacion
	@OneToMany(mappedBy="productoPorNumeroEntrega")
	private List<CatalogoBonificacion> catalogoBonificacions;

	//bi-directional many-to-one association to CatalogoMarca
	@OneToMany(mappedBy="productoPorNumeroEntrega")
	private List<CatalogoMarca> catalogoMarcas;

	//bi-directional many-to-one association to Matriz
	@OneToMany(mappedBy="productoPorNumeroEntrega")
	private List<Matriz> matrizs;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@ManyToOne
	@JoinColumn(name="id_catalogo_producto_qaliwarma")
	private CatalogoProductoQaliwarma catalogoProductoQaliwarma;

	//bi-directional many-to-one association to EntregaPorItem
	@ManyToOne
	@JoinColumn(name="id_entregas_por_item")
	private EntregaPorItem entregaPorItem;

	//bi-directional many-to-one association to RequerimientoVolumen002Producto
	@OneToMany(mappedBy="productoPorNumeroEntrega")
	private List<RequerimientoVolumen002Producto> requerimientoVolumen002Productos;

	public ProductoPorNumeroEntrega() {
	}

	public String getIdProductoPorNumeroEntrega() {
		return this.idProductoPorNumeroEntrega;
	}

	public void setIdProductoPorNumeroEntrega(String idProductoPorNumeroEntrega) {
		this.idProductoPorNumeroEntrega = idProductoPorNumeroEntrega;
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

	public String getDscComplementoProducto() {
		return this.dscComplementoProducto;
	}

	public void setDscComplementoProducto(String dscComplementoProducto) {
		this.dscComplementoProducto = dscComplementoProducto;
	}

	public Date getFechaElavoracion() {
		return this.fechaElavoracion;
	}

	public void setFechaElavoracion(Date fechaElavoracion) {
		this.fechaElavoracion = fechaElavoracion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public String getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public BigDecimal getPesoTotal() {
		return this.pesoTotal;
	}

	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public BigDecimal getPresentacionMinima() {
		return this.presentacionMinima;
	}

	public void setPresentacionMinima(BigDecimal presentacionMinima) {
		this.presentacionMinima = presentacionMinima;
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

	public List<CatalogoBonificacion> getCatalogoBonificacions() {
		return this.catalogoBonificacions;
	}

	public void setCatalogoBonificacions(List<CatalogoBonificacion> catalogoBonificacions) {
		this.catalogoBonificacions = catalogoBonificacions;
	}

	public CatalogoBonificacion addCatalogoBonificacion(CatalogoBonificacion catalogoBonificacion) {
		getCatalogoBonificacions().add(catalogoBonificacion);
		catalogoBonificacion.setProductoPorNumeroEntrega(this);

		return catalogoBonificacion;
	}

	public CatalogoBonificacion removeCatalogoBonificacion(CatalogoBonificacion catalogoBonificacion) {
		getCatalogoBonificacions().remove(catalogoBonificacion);
		catalogoBonificacion.setProductoPorNumeroEntrega(null);

		return catalogoBonificacion;
	}

	public List<CatalogoMarca> getCatalogoMarcas() {
		return this.catalogoMarcas;
	}

	public void setCatalogoMarcas(List<CatalogoMarca> catalogoMarcas) {
		this.catalogoMarcas = catalogoMarcas;
	}

	public CatalogoMarca addCatalogoMarca(CatalogoMarca catalogoMarca) {
		getCatalogoMarcas().add(catalogoMarca);
		catalogoMarca.setProductoPorNumeroEntrega(this);

		return catalogoMarca;
	}

	public CatalogoMarca removeCatalogoMarca(CatalogoMarca catalogoMarca) {
		getCatalogoMarcas().remove(catalogoMarca);
		catalogoMarca.setProductoPorNumeroEntrega(null);

		return catalogoMarca;
	}

	public List<Matriz> getMatrizs() {
		return this.matrizs;
	}

	public void setMatrizs(List<Matriz> matrizs) {
		this.matrizs = matrizs;
	}

	public Matriz addMatriz(Matriz matriz) {
		getMatrizs().add(matriz);
		matriz.setProductoPorNumeroEntrega(this);

		return matriz;
	}

	public Matriz removeMatriz(Matriz matriz) {
		getMatrizs().remove(matriz);
		matriz.setProductoPorNumeroEntrega(null);

		return matriz;
	}

	public CatalogoProductoQaliwarma getCatalogoProductoQaliwarma() {
		return this.catalogoProductoQaliwarma;
	}

	public void setCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		this.catalogoProductoQaliwarma = catalogoProductoQaliwarma;
	}

	public EntregaPorItem getEntregaPorItem() {
		return this.entregaPorItem;
	}

	public void setEntregaPorItem(EntregaPorItem entregaPorItem) {
		this.entregaPorItem = entregaPorItem;
	}

	public List<RequerimientoVolumen002Producto> getRequerimientoVolumen002Productos() {
		return this.requerimientoVolumen002Productos;
	}

	public void setRequerimientoVolumen002Productos(List<RequerimientoVolumen002Producto> requerimientoVolumen002Productos) {
		this.requerimientoVolumen002Productos = requerimientoVolumen002Productos;
	}

	public RequerimientoVolumen002Producto addRequerimientoVolumen002Producto(RequerimientoVolumen002Producto requerimientoVolumen002Producto) {
		getRequerimientoVolumen002Productos().add(requerimientoVolumen002Producto);
		requerimientoVolumen002Producto.setProductoPorNumeroEntrega(this);

		return requerimientoVolumen002Producto;
	}

	public RequerimientoVolumen002Producto removeRequerimientoVolumen002Producto(RequerimientoVolumen002Producto requerimientoVolumen002Producto) {
		getRequerimientoVolumen002Productos().remove(requerimientoVolumen002Producto);
		requerimientoVolumen002Producto.setProductoPorNumeroEntrega(null);

		return requerimientoVolumen002Producto;
	}

}
