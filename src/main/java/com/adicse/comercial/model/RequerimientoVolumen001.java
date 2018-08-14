package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the requerimiento_volumen_001 database table.
 * 
 */
@Entity
@Table(name="requerimiento_volumen_001")
@NamedQuery(name="RequerimientoVolumen001.findAll", query="SELECT r FROM RequerimientoVolumen001 r")
public class RequerimientoVolumen001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_requerimiento_volumen_001")
	private String idRequerimientoVolumen001;

	private Integer anno;

	@Column(name="centro_poblado")
	private String centroPoblado;

	@Column(name="flag_estado")
	private Integer flagEstado;

	@Column(name="numero_usuarios")
	private Integer numeroUsuarios;

	@Column(name="peso_total")
	private BigDecimal pesoTotal;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="requerimientoVolumen001")
	private List<GuiaRemision001> guiaRemision001s;

	//bi-directional many-to-one association to Matriz
	@OneToMany(mappedBy="requerimientoVolumen001")
	private List<Matriz> matrizs;

	//bi-directional many-to-one association to Piking
	@OneToMany(mappedBy="requerimientoVolumen001")
	private List<Piking> pikings;

	//bi-directional many-to-one association to CodigomodularIinstitucionEducativa
	@ManyToOne
	@JoinColumn(name="codigo_modular")
	private CodigomodularIinstitucionEducativa codigomodularIinstitucionEducativa;

	//bi-directional many-to-one association to EntregaPorItem
	@ManyToOne
	@JoinColumn(name="id_entrega_por_item")
	private EntregaPorItem entregaPorItem;

	//bi-directional many-to-one association to HorarioAlimentacion
	@ManyToOne
	@JoinColumn(name="id_horario_alimentacion")
	private HorarioAlimentacion horarioAlimentacion;

	//bi-directional many-to-one association to ModalidadEntregaAlimento
	@ManyToOne
	@JoinColumn(name="id_modalidad_entrega_alimentos")
	private ModalidadEntregaAlimento modalidadEntregaAlimento;

	//bi-directional many-to-one association to NivelEducacion
	@ManyToOne
	@JoinColumn(name="id_nivel_educacion")
	private NivelEducacion nivelEducacion;

	//bi-directional many-to-one association to RegionAlimentaria
	@ManyToOne
	@JoinColumn(name="id_region_alimentaria")
	private RegionAlimentaria regionAlimentaria;

	//bi-directional many-to-one association to Ubigeo
	@ManyToOne
	@JoinColumn(name="idubigeo")
	private Ubigeo ubigeo;

	//bi-directional many-to-one association to RequerimientoVolumen002
	@OneToMany(mappedBy="requerimientoVolumen001", cascade={CascadeType.ALL})
	private List<RequerimientoVolumen002> requerimientoVolumen002s;

	//bi-directional many-to-one association to RutaDistribucionDetalle
	@OneToMany(mappedBy="requerimientoVolumen001")
	private List<RutaDistribucionDetalle> rutaDistribucionDetalles;

	public RequerimientoVolumen001() {
	}

	public String getIdRequerimientoVolumen001() {
		return this.idRequerimientoVolumen001;
	}

	public void setIdRequerimientoVolumen001(String idRequerimientoVolumen001) {
		this.idRequerimientoVolumen001 = idRequerimientoVolumen001;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getCentroPoblado() {
		return this.centroPoblado;
	}

	public void setCentroPoblado(String centroPoblado) {
		this.centroPoblado = centroPoblado;
	}

	public Integer getFlagEstado() {
		return this.flagEstado;
	}

	public void setFlagEstado(Integer flagEstado) {
		this.flagEstado = flagEstado;
	}

	public Integer getNumeroUsuarios() {
		return this.numeroUsuarios;
	}

	public void setNumeroUsuarios(Integer numeroUsuarios) {
		this.numeroUsuarios = numeroUsuarios;
	}

	public BigDecimal getPesoTotal() {
		return this.pesoTotal;
	}

	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public List<GuiaRemision001> getGuiaRemision001s() {
		return this.guiaRemision001s;
	}

	public void setGuiaRemision001s(List<GuiaRemision001> guiaRemision001s) {
		this.guiaRemision001s = guiaRemision001s;
	}

	public GuiaRemision001 addGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().add(guiaRemision001);
		guiaRemision001.setRequerimientoVolumen001(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setRequerimientoVolumen001(null);

		return guiaRemision001;
	}

	public List<Matriz> getMatrizs() {
		return this.matrizs;
	}

	public void setMatrizs(List<Matriz> matrizs) {
		this.matrizs = matrizs;
	}

	public Matriz addMatriz(Matriz matriz) {
		getMatrizs().add(matriz);
		matriz.setRequerimientoVolumen001(this);

		return matriz;
	}

	public Matriz removeMatriz(Matriz matriz) {
		getMatrizs().remove(matriz);
		matriz.setRequerimientoVolumen001(null);

		return matriz;
	}

	public List<Piking> getPikings() {
		return this.pikings;
	}

	public void setPikings(List<Piking> pikings) {
		this.pikings = pikings;
	}

	public Piking addPiking(Piking piking) {
		getPikings().add(piking);
		piking.setRequerimientoVolumen001(this);

		return piking;
	}

	public Piking removePiking(Piking piking) {
		getPikings().remove(piking);
		piking.setRequerimientoVolumen001(null);

		return piking;
	}

	public CodigomodularIinstitucionEducativa getCodigomodularIinstitucionEducativa() {
		return this.codigomodularIinstitucionEducativa;
	}

	public void setCodigomodularIinstitucionEducativa(CodigomodularIinstitucionEducativa codigomodularIinstitucionEducativa) {
		this.codigomodularIinstitucionEducativa = codigomodularIinstitucionEducativa;
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

	public ModalidadEntregaAlimento getModalidadEntregaAlimento() {
		return this.modalidadEntregaAlimento;
	}

	public void setModalidadEntregaAlimento(ModalidadEntregaAlimento modalidadEntregaAlimento) {
		this.modalidadEntregaAlimento = modalidadEntregaAlimento;
	}

	public NivelEducacion getNivelEducacion() {
		return this.nivelEducacion;
	}

	public void setNivelEducacion(NivelEducacion nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public RegionAlimentaria getRegionAlimentaria() {
		return this.regionAlimentaria;
	}

	public void setRegionAlimentaria(RegionAlimentaria regionAlimentaria) {
		this.regionAlimentaria = regionAlimentaria;
	}

	public Ubigeo getUbigeo() {
		return this.ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

	public List<RequerimientoVolumen002> getRequerimientoVolumen002s() {
		return this.requerimientoVolumen002s;
	}

	public void setRequerimientoVolumen002s(List<RequerimientoVolumen002> requerimientoVolumen002s) {
		this.requerimientoVolumen002s = requerimientoVolumen002s;
	}

	public RequerimientoVolumen002 addRequerimientoVolumen002(RequerimientoVolumen002 requerimientoVolumen002) {
		getRequerimientoVolumen002s().add(requerimientoVolumen002);
		requerimientoVolumen002.setRequerimientoVolumen001(this);

		return requerimientoVolumen002;
	}

	public RequerimientoVolumen002 removeRequerimientoVolumen002(RequerimientoVolumen002 requerimientoVolumen002) {
		getRequerimientoVolumen002s().remove(requerimientoVolumen002);
		requerimientoVolumen002.setRequerimientoVolumen001(null);

		return requerimientoVolumen002;
	}

	public List<RutaDistribucionDetalle> getRutaDistribucionDetalles() {
		return this.rutaDistribucionDetalles;
	}

	public void setRutaDistribucionDetalles(List<RutaDistribucionDetalle> rutaDistribucionDetalles) {
		this.rutaDistribucionDetalles = rutaDistribucionDetalles;
	}

	public RutaDistribucionDetalle addRutaDistribucionDetalle(RutaDistribucionDetalle rutaDistribucionDetalle) {
		getRutaDistribucionDetalles().add(rutaDistribucionDetalle);
		rutaDistribucionDetalle.setRequerimientoVolumen001(this);

		return rutaDistribucionDetalle;
	}

	public RutaDistribucionDetalle removeRutaDistribucionDetalle(RutaDistribucionDetalle rutaDistribucionDetalle) {
		getRutaDistribucionDetalles().remove(rutaDistribucionDetalle);
		rutaDistribucionDetalle.setRequerimientoVolumen001(null);

		return rutaDistribucionDetalle;
	}

}
