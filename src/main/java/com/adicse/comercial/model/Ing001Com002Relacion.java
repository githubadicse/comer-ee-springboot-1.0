package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ing001_com002_relacion database table.
 * 
 */
@Entity
@Table(name="ing001_com002_relacion")
@NamedQuery(name="Ing001Com002Relacion.findAll", query="SELECT i FROM Ing001Com002Relacion i")
public class Ing001Com002Relacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ing001_com002_relacion")
	private String idIng001Com002Relacion;

	//bi-directional many-to-one association to Com002
	@ManyToOne
	@JoinColumn(name="id_com002")
	private Com002 com002;

	//bi-directional many-to-one association to Ing001
	@ManyToOne
	@JoinColumn(name="id_ing001")
	private Ing001 ing001;

	public Ing001Com002Relacion() {
	}

	public String getIdIng001Com002Relacion() {
		return this.idIng001Com002Relacion;
	}

	public void setIdIng001Com002Relacion(String idIng001Com002Relacion) {
		this.idIng001Com002Relacion = idIng001Com002Relacion;
	}

	public Com002 getCom002() {
		return this.com002;
	}

	public void setCom002(Com002 com002) {
		this.com002 = com002;
	}

	public Ing001 getIng001() {
		return this.ing001;
	}

	public void setIng001(Ing001 ing001) {
		this.ing001 = ing001;
	}

}