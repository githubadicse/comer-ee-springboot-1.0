package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motivoingreso database table.
 * 
 */
@Entity
@NamedQuery(name="Motivoingreso.findAll", query="SELECT m FROM Motivoingreso m")
public class Motivoingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idmotivoingreso;

	private String dscmotivoingreso;

	private Integer flagtraslado;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="motivoingreso")
	private List<Ing001> ing001s;

	public Motivoingreso() {
	}

	public Integer getIdmotivoingreso() {
		return this.idmotivoingreso;
	}

	public void setIdmotivoingreso(Integer idmotivoingreso) {
		this.idmotivoingreso = idmotivoingreso;
	}

	public String getDscmotivoingreso() {
		return this.dscmotivoingreso;
	}

	public void setDscmotivoingreso(String dscmotivoingreso) {
		this.dscmotivoingreso = dscmotivoingreso;
	}

	public Integer getFlagtraslado() {
		return this.flagtraslado;
	}

	public void setFlagtraslado(Integer flagtraslado) {
		this.flagtraslado = flagtraslado;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setMotivoingreso(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setMotivoingreso(null);

		return ing001;
	}

}