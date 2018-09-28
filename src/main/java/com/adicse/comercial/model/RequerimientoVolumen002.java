package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the requerimiento_volumen_002 database table.
 * 
 */
@Entity
@Table(name="requerimiento_volumen_002")
@NamedQuery(name="RequerimientoVolumen002.findAll", query="SELECT r FROM RequerimientoVolumen002 r")
public class RequerimientoVolumen002 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_requerimiento_volumen_002")
	private String idRequerimientoVolumen002;

	@Column(name="numero_usuarios")
	private Integer numeroUsuarios;

	//bi-directional many-to-one association to NumeroEntrega
	@ManyToOne
	@JoinColumn(name="id_numero_entrega")
	private NumeroEntrega numeroEntrega;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@ManyToOne
	@JoinColumn(name="id_requerimiento_volumen_001")
	private RequerimientoVolumen001 requerimientoVolumen001;

	//bi-directional many-to-one association to RequerimientoVolumen002Producto
	@OneToMany(mappedBy="requerimientoVolumen002", cascade={CascadeType.ALL})
	private List<RequerimientoVolumen002Producto> requerimientoVolumen002Productos;

	public RequerimientoVolumen002() {
	}

	public String getIdRequerimientoVolumen002() {
		return this.idRequerimientoVolumen002;
	}

	public void setIdRequerimientoVolumen002(String idRequerimientoVolumen002) {
		this.idRequerimientoVolumen002 = idRequerimientoVolumen002;
	}

	public Integer getNumeroUsuarios() {
		return this.numeroUsuarios;
	}

	public void setNumeroUsuarios(Integer numeroUsuarios) {
		this.numeroUsuarios = numeroUsuarios;
	}

	public NumeroEntrega getNumeroEntrega() {
		return this.numeroEntrega;
	}

	public void setNumeroEntrega(NumeroEntrega numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public RequerimientoVolumen001 getRequerimientoVolumen001() {
		return this.requerimientoVolumen001;
	}

	public void setRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		this.requerimientoVolumen001 = requerimientoVolumen001;
	}

	public List<RequerimientoVolumen002Producto> getRequerimientoVolumen002Productos() {
		return this.requerimientoVolumen002Productos;
	}

	public void setRequerimientoVolumen002Productos(List<RequerimientoVolumen002Producto> requerimientoVolumen002Productos) {
		this.requerimientoVolumen002Productos = requerimientoVolumen002Productos;
	}

	public RequerimientoVolumen002Producto addRequerimientoVolumen002Producto(RequerimientoVolumen002Producto requerimientoVolumen002Producto) {
		getRequerimientoVolumen002Productos().add(requerimientoVolumen002Producto);
		requerimientoVolumen002Producto.setRequerimientoVolumen002(this);

		return requerimientoVolumen002Producto;
	}

	public RequerimientoVolumen002Producto removeRequerimientoVolumen002Producto(RequerimientoVolumen002Producto requerimientoVolumen002Producto) {
		getRequerimientoVolumen002Productos().remove(requerimientoVolumen002Producto);
		requerimientoVolumen002Producto.setRequerimientoVolumen002(null);

		return requerimientoVolumen002Producto;
	}

}
