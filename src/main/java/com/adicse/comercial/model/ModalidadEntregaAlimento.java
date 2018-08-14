package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modalidad_entrega_alimentos database table.
 * 
 */
@Entity
@Table(name="modalidad_entrega_alimentos")
@NamedQuery(name="ModalidadEntregaAlimento.findAll", query="SELECT m FROM ModalidadEntregaAlimento m")
public class ModalidadEntregaAlimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_modalidad_entrega_alimentos")
	private Integer idModalidadEntregaAlimentos;

	@Column(name="dsc_modalidad_entrega_alimentos")
	private String dscModalidadEntregaAlimentos;

	//bi-directional many-to-one association to RequerimientoVolumen001
	@OneToMany(mappedBy="modalidadEntregaAlimento")
	private List<RequerimientoVolumen001> requerimientoVolumen001s;

	public ModalidadEntregaAlimento() {
	}

	public Integer getIdModalidadEntregaAlimentos() {
		return this.idModalidadEntregaAlimentos;
	}

	public void setIdModalidadEntregaAlimentos(Integer idModalidadEntregaAlimentos) {
		this.idModalidadEntregaAlimentos = idModalidadEntregaAlimentos;
	}

	public String getDscModalidadEntregaAlimentos() {
		return this.dscModalidadEntregaAlimentos;
	}

	public void setDscModalidadEntregaAlimentos(String dscModalidadEntregaAlimentos) {
		this.dscModalidadEntregaAlimentos = dscModalidadEntregaAlimentos;
	}

	public List<RequerimientoVolumen001> getRequerimientoVolumen001s() {
		return this.requerimientoVolumen001s;
	}

	public void setRequerimientoVolumen001s(List<RequerimientoVolumen001> requerimientoVolumen001s) {
		this.requerimientoVolumen001s = requerimientoVolumen001s;
	}

	public RequerimientoVolumen001 addRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().add(requerimientoVolumen001);
		requerimientoVolumen001.setModalidadEntregaAlimento(this);

		return requerimientoVolumen001;
	}

	public RequerimientoVolumen001 removeRequerimientoVolumen001(RequerimientoVolumen001 requerimientoVolumen001) {
		getRequerimientoVolumen001s().remove(requerimientoVolumen001);
		requerimientoVolumen001.setModalidadEntregaAlimento(null);

		return requerimientoVolumen001;
	}

}