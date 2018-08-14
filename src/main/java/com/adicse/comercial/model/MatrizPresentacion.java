package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the matriz_presentacion database table.
 * 
 */
@Entity
@Table(name="matriz_presentacion")
@NamedQuery(name="MatrizPresentacion.findAll", query="SELECT m FROM MatrizPresentacion m")
public class MatrizPresentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_matriz_presentacion")
	private String idMatrizPresentacion;

	//bi-directional many-to-one association to Matriz
	@ManyToOne
	@JoinColumn(name="id_matriz")
	private Matriz matriz;

	//bi-directional many-to-one association to ProductoPresentacion
	@ManyToOne
	@JoinColumn(name="id_producto_presentacion")
	private ProductoPresentacion productoPresentacion;

	//bi-directional many-to-one association to PresentacionMarca
	@OneToMany(mappedBy="matrizPresentacion")
	private List<PresentacionMarca> presentacionMarcas;

	public MatrizPresentacion() {
	}

	public String getIdMatrizPresentacion() {
		return this.idMatrizPresentacion;
	}

	public void setIdMatrizPresentacion(String idMatrizPresentacion) {
		this.idMatrizPresentacion = idMatrizPresentacion;
	}

	public Matriz getMatriz() {
		return this.matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public ProductoPresentacion getProductoPresentacion() {
		return this.productoPresentacion;
	}

	public void setProductoPresentacion(ProductoPresentacion productoPresentacion) {
		this.productoPresentacion = productoPresentacion;
	}

	public List<PresentacionMarca> getPresentacionMarcas() {
		return this.presentacionMarcas;
	}

	public void setPresentacionMarcas(List<PresentacionMarca> presentacionMarcas) {
		this.presentacionMarcas = presentacionMarcas;
	}

	public PresentacionMarca addPresentacionMarca(PresentacionMarca presentacionMarca) {
		getPresentacionMarcas().add(presentacionMarca);
		presentacionMarca.setMatrizPresentacion(this);

		return presentacionMarca;
	}

	public PresentacionMarca removePresentacionMarca(PresentacionMarca presentacionMarca) {
		getPresentacionMarcas().remove(presentacionMarca);
		presentacionMarca.setMatrizPresentacion(null);

		return presentacionMarca;
	}

}