package com.adicse.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the catalogo_lote database table.
 * 
 */
@Entity
@Table(name="catalogo_lote")
@NamedQuery(name="CatalogoLote.findAll", query="SELECT c FROM CatalogoLote c")
public class CatalogoLote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_catalogo_lote")
	private String idCatalogoLote;

	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	@JsonFormat (pattern ="dd/MM/yyyy")
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="numero_lote")
	private String numeroLote;

	@Column(name="numero_registro")
	private String numeroRegistro;

	//bi-directional many-to-one association to CatalogoMarca
	@ManyToOne
	@JoinColumn(name="id_catalogo_marca")
	private CatalogoMarca catalogoMarca;

	//bi-directional many-to-one association to MarcaLote
	@OneToMany(mappedBy="catalogoLote")
	private List<MarcaLote> marcaLotes;

	//bi-directional many-to-one association to Piking
	@OneToMany(mappedBy="catalogoLote", cascade={CascadeType.ALL})
	private List<Piking> pikings;

	public CatalogoLote() {
	}

	public String getIdCatalogoLote() {
		return this.idCatalogoLote;
	}

	public void setIdCatalogoLote(String idCatalogoLote) {
		this.idCatalogoLote = idCatalogoLote;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNumeroRegistro() {
		return this.numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public CatalogoMarca getCatalogoMarca() {
		return this.catalogoMarca;
	}

	public void setCatalogoMarca(CatalogoMarca catalogoMarca) {
		this.catalogoMarca = catalogoMarca;
	}

	public List<MarcaLote> getMarcaLotes() {
		return this.marcaLotes;
	}

	public void setMarcaLotes(List<MarcaLote> marcaLotes) {
		this.marcaLotes = marcaLotes;
	}

	public MarcaLote addMarcaLote(MarcaLote marcaLote) {
		getMarcaLotes().add(marcaLote);
		marcaLote.setCatalogoLote(this);

		return marcaLote;
	}

	public MarcaLote removeMarcaLote(MarcaLote marcaLote) {
		getMarcaLotes().remove(marcaLote);
		marcaLote.setCatalogoLote(null);

		return marcaLote;
	}

	public List<Piking> getPikings() {
		return this.pikings;
	}

	public void setPikings(List<Piking> pikings) {
		this.pikings = pikings;
	}

	public Piking addPiking(Piking piking) {
		getPikings().add(piking);
		piking.setCatalogoLote(this);

		return piking;
	}

	public Piking removePiking(Piking piking) {
		getPikings().remove(piking);
		piking.setCatalogoLote(null);

		return piking;
	}

}
