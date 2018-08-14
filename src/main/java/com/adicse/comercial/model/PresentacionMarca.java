package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the presentacion_marca database table.
 * 
 */
@Entity
@Table(name="presentacion_marca")
@NamedQuery(name="PresentacionMarca.findAll", query="SELECT p FROM PresentacionMarca p")
public class PresentacionMarca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_presentacion_marca")
	private String idPresentacionMarca;

	//bi-directional many-to-one association to MarcaLote
	@OneToMany(mappedBy="presentacionMarca")
	private List<MarcaLote> marcaLotes;

	//bi-directional many-to-one association to CatalogoMarca
	@ManyToOne
	@JoinColumn(name="id_catalogo_marca")
	private CatalogoMarca catalogoMarca;

	//bi-directional many-to-one association to MatrizPresentacion
	@ManyToOne
	@JoinColumn(name="id_matriz_presentacion")
	private MatrizPresentacion matrizPresentacion;

	public PresentacionMarca() {
	}

	public String getIdPresentacionMarca() {
		return this.idPresentacionMarca;
	}

	public void setIdPresentacionMarca(String idPresentacionMarca) {
		this.idPresentacionMarca = idPresentacionMarca;
	}

	public List<MarcaLote> getMarcaLotes() {
		return this.marcaLotes;
	}

	public void setMarcaLotes(List<MarcaLote> marcaLotes) {
		this.marcaLotes = marcaLotes;
	}

	public MarcaLote addMarcaLote(MarcaLote marcaLote) {
		getMarcaLotes().add(marcaLote);
		marcaLote.setPresentacionMarca(this);

		return marcaLote;
	}

	public MarcaLote removeMarcaLote(MarcaLote marcaLote) {
		getMarcaLotes().remove(marcaLote);
		marcaLote.setPresentacionMarca(null);

		return marcaLote;
	}

	public CatalogoMarca getCatalogoMarca() {
		return this.catalogoMarca;
	}

	public void setCatalogoMarca(CatalogoMarca catalogoMarca) {
		this.catalogoMarca = catalogoMarca;
	}

	public MatrizPresentacion getMatrizPresentacion() {
		return this.matrizPresentacion;
	}

	public void setMatrizPresentacion(MatrizPresentacion matrizPresentacion) {
		this.matrizPresentacion = matrizPresentacion;
	}

}