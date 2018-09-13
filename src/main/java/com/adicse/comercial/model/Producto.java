package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idproducto;

	private Integer activo;

	private String dscproducto;

	private String dscproductocorto;

	private Integer estado;

	private Integer exigelote;

	private Integer exigevencimiento;

	private Integer numeropiezas;

	private BigDecimal peso;

	@Column(name="porcentaje_igv")
	private BigDecimal porcentajeIgv;

	@Column(name="porcentaje_isc")
	private BigDecimal porcentajeIsc;

	private BigDecimal precio1;

	@Column(name="precio1_valor_venta")
	private BigDecimal precio1ValorVenta;

	private BigDecimal precio2;

	@Column(name="precio2_valor_venta")
	private BigDecimal precio2ValorVenta;

	private BigDecimal precio3;

	@Column(name="precio3_valor_venta")
	private BigDecimal precio3ValorVenta;

	private BigDecimal stockminimo;

	@Column(name="valor_igv_precio1")
	private BigDecimal valorIgvPrecio1;

	@Column(name="valor_igv_precio2")
	private BigDecimal valorIgvPrecio2;

	@Column(name="valor_igv_precio3")
	private BigDecimal valorIgvPrecio3;

	@Column(name="valor_isc_precio1")
	private BigDecimal valorIscPrecio1;

	@Column(name="valor_isc_precio2")
	private BigDecimal valorIscPrecio2;

	@Column(name="valor_isc_precio3")
	private BigDecimal valorIscPrecio3;

	//bi-directional many-to-one association to Cierremensual
	@OneToMany(mappedBy="producto")
	private List<Cierremensual> cierremensuals;

	//bi-directional many-to-one association to Codigobarra
	@OneToMany(mappedBy="producto", cascade={CascadeType.ALL})
	private List<Codigobarra> codigobarras;

	//bi-directional many-to-one association to Ing002
	@OneToMany(mappedBy="producto")
	private List<Ing002> ing002s;

	//bi-directional many-to-one association to Kardex
	@OneToMany(mappedBy="producto")
	private List<Kardex> kardexs;

	//bi-directional many-to-one association to CatalogoProductoQaliwarma
	@ManyToOne
	@JoinColumn(name="id_catalogo_producto_qaliwarma")
	private CatalogoProductoQaliwarma catalogoProductoQaliwarma;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Laboratorio
	@ManyToOne
	@JoinColumn(name="idlaboratorio")
	private Laboratorio laboratorio;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="idmarca")
	private Marca marca;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name="idmodelo")
	private Modelo modelo;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="idmoneda")
	private Moneda moneda;

	//bi-directional many-to-one association to Unidadmedida
	@ManyToOne
	@JoinColumn(name="idunidadmedida")
	private Unidadmedida unidadmedida;

	//bi-directional many-to-one association to Salida002
	@OneToMany(mappedBy="producto")
	private List<Salida002> salida002s;

	//bi-directional many-to-one association to Stockactual
	@OneToMany(mappedBy="producto")
	private List<Stockactual> stockactuals;

	public Producto() {
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getDscproducto() {
		return this.dscproducto;
	}

	public void setDscproducto(String dscproducto) {
		this.dscproducto = dscproducto;
	}

	public String getDscproductocorto() {
		return this.dscproductocorto;
	}

	public void setDscproductocorto(String dscproductocorto) {
		this.dscproductocorto = dscproductocorto;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getExigelote() {
		return this.exigelote;
	}

	public void setExigelote(Integer exigelote) {
		this.exigelote = exigelote;
	}

	public Integer getExigevencimiento() {
		return this.exigevencimiento;
	}

	public void setExigevencimiento(Integer exigevencimiento) {
		this.exigevencimiento = exigevencimiento;
	}

	public Integer getNumeropiezas() {
		return this.numeropiezas;
	}

	public void setNumeropiezas(Integer numeropiezas) {
		this.numeropiezas = numeropiezas;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPorcentajeIgv() {
		return this.porcentajeIgv;
	}

	public void setPorcentajeIgv(BigDecimal porcentajeIgv) {
		this.porcentajeIgv = porcentajeIgv;
	}

	public BigDecimal getPorcentajeIsc() {
		return this.porcentajeIsc;
	}

	public void setPorcentajeIsc(BigDecimal porcentajeIsc) {
		this.porcentajeIsc = porcentajeIsc;
	}

	public BigDecimal getPrecio1() {
		return this.precio1;
	}

	public void setPrecio1(BigDecimal precio1) {
		this.precio1 = precio1;
	}

	public BigDecimal getPrecio1ValorVenta() {
		return this.precio1ValorVenta;
	}

	public void setPrecio1ValorVenta(BigDecimal precio1ValorVenta) {
		this.precio1ValorVenta = precio1ValorVenta;
	}

	public BigDecimal getPrecio2() {
		return this.precio2;
	}

	public void setPrecio2(BigDecimal precio2) {
		this.precio2 = precio2;
	}

	public BigDecimal getPrecio2ValorVenta() {
		return this.precio2ValorVenta;
	}

	public void setPrecio2ValorVenta(BigDecimal precio2ValorVenta) {
		this.precio2ValorVenta = precio2ValorVenta;
	}

	public BigDecimal getPrecio3() {
		return this.precio3;
	}

	public void setPrecio3(BigDecimal precio3) {
		this.precio3 = precio3;
	}

	public BigDecimal getPrecio3ValorVenta() {
		return this.precio3ValorVenta;
	}

	public void setPrecio3ValorVenta(BigDecimal precio3ValorVenta) {
		this.precio3ValorVenta = precio3ValorVenta;
	}

	public BigDecimal getStockminimo() {
		return this.stockminimo;
	}

	public void setStockminimo(BigDecimal stockminimo) {
		this.stockminimo = stockminimo;
	}

	public BigDecimal getValorIgvPrecio1() {
		return this.valorIgvPrecio1;
	}

	public void setValorIgvPrecio1(BigDecimal valorIgvPrecio1) {
		this.valorIgvPrecio1 = valorIgvPrecio1;
	}

	public BigDecimal getValorIgvPrecio2() {
		return this.valorIgvPrecio2;
	}

	public void setValorIgvPrecio2(BigDecimal valorIgvPrecio2) {
		this.valorIgvPrecio2 = valorIgvPrecio2;
	}

	public BigDecimal getValorIgvPrecio3() {
		return this.valorIgvPrecio3;
	}

	public void setValorIgvPrecio3(BigDecimal valorIgvPrecio3) {
		this.valorIgvPrecio3 = valorIgvPrecio3;
	}

	public BigDecimal getValorIscPrecio1() {
		return this.valorIscPrecio1;
	}

	public void setValorIscPrecio1(BigDecimal valorIscPrecio1) {
		this.valorIscPrecio1 = valorIscPrecio1;
	}

	public BigDecimal getValorIscPrecio2() {
		return this.valorIscPrecio2;
	}

	public void setValorIscPrecio2(BigDecimal valorIscPrecio2) {
		this.valorIscPrecio2 = valorIscPrecio2;
	}

	public BigDecimal getValorIscPrecio3() {
		return this.valorIscPrecio3;
	}

	public void setValorIscPrecio3(BigDecimal valorIscPrecio3) {
		this.valorIscPrecio3 = valorIscPrecio3;
	}

	public List<Cierremensual> getCierremensuals() {
		return this.cierremensuals;
	}

	public void setCierremensuals(List<Cierremensual> cierremensuals) {
		this.cierremensuals = cierremensuals;
	}

	public Cierremensual addCierremensual(Cierremensual cierremensual) {
		getCierremensuals().add(cierremensual);
		cierremensual.setProducto(this);

		return cierremensual;
	}

	public Cierremensual removeCierremensual(Cierremensual cierremensual) {
		getCierremensuals().remove(cierremensual);
		cierremensual.setProducto(null);

		return cierremensual;
	}

	public List<Codigobarra> getCodigobarras() {
		return this.codigobarras;
	}

	public void setCodigobarras(List<Codigobarra> codigobarras) {
		this.codigobarras = codigobarras;
	}

	public Codigobarra addCodigobarra(Codigobarra codigobarra) {
		getCodigobarras().add(codigobarra);
		codigobarra.setProducto(this);

		return codigobarra;
	}

	public Codigobarra removeCodigobarra(Codigobarra codigobarra) {
		getCodigobarras().remove(codigobarra);
		codigobarra.setProducto(null);

		return codigobarra;
	}

	public List<Ing002> getIng002s() {
		return this.ing002s;
	}

	public void setIng002s(List<Ing002> ing002s) {
		this.ing002s = ing002s;
	}

	public Ing002 addIng002(Ing002 ing002) {
		getIng002s().add(ing002);
		ing002.setProducto(this);

		return ing002;
	}

	public Ing002 removeIng002(Ing002 ing002) {
		getIng002s().remove(ing002);
		ing002.setProducto(null);

		return ing002;
	}

	public List<Kardex> getKardexs() {
		return this.kardexs;
	}

	public void setKardexs(List<Kardex> kardexs) {
		this.kardexs = kardexs;
	}

	public Kardex addKardex(Kardex kardex) {
		getKardexs().add(kardex);
		kardex.setProducto(this);

		return kardex;
	}

	public Kardex removeKardex(Kardex kardex) {
		getKardexs().remove(kardex);
		kardex.setProducto(null);

		return kardex;
	}

	public CatalogoProductoQaliwarma getCatalogoProductoQaliwarma() {
		return this.catalogoProductoQaliwarma;
	}

	public void setCatalogoProductoQaliwarma(CatalogoProductoQaliwarma catalogoProductoQaliwarma) {
		this.catalogoProductoQaliwarma = catalogoProductoQaliwarma;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Laboratorio getLaboratorio() {
		return this.laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public List<Salida002> getSalida002s() {
		return this.salida002s;
	}

	public void setSalida002s(List<Salida002> salida002s) {
		this.salida002s = salida002s;
	}

	public Salida002 addSalida002(Salida002 salida002) {
		getSalida002s().add(salida002);
		salida002.setProducto(this);

		return salida002;
	}

	public Salida002 removeSalida002(Salida002 salida002) {
		getSalida002s().remove(salida002);
		salida002.setProducto(null);

		return salida002;
	}

	public List<Stockactual> getStockactuals() {
		return this.stockactuals;
	}

	public void setStockactuals(List<Stockactual> stockactuals) {
		this.stockactuals = stockactuals;
	}

	public Stockactual addStockactual(Stockactual stockactual) {
		getStockactuals().add(stockactual);
		stockactual.setProducto(this);

		return stockactual;
	}

	public Stockactual removeStockactual(Stockactual stockactual) {
		getStockactuals().remove(stockactual);
		stockactual.setProducto(null);

		return stockactual;
	}

}
