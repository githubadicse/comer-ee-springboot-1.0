package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipodocumento database table.
 * 
 */
@Entity
@NamedQuery(name="Tipodocumento.findAll", query="SELECT t FROM Tipodocumento t")
public class Tipodocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_documento")
	private Integer idTipoDocumento;

	@Column(name="codigo_sunat")
	private String codigoSunat;

	@Column(name="dsc_tipo_documento")
	private String dscTipoDocumento;

	//bi-directional many-to-one association to Com001
	@OneToMany(mappedBy="tipodocumento")
	private List<Com001> com001s;

	//bi-directional many-to-one association to Gasto001
	@OneToMany(mappedBy="tipodocumento")
	private List<Gasto001> gasto001s;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="tipodocumento")
	private List<Ing001> ing001s;

	//bi-directional many-to-one association to Numerador
	@OneToMany(mappedBy="tipodocumento", cascade={CascadeType.ALL})
	private List<Numerador> numeradors;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="tipodocumento")
	private List<Ven001> ven001s;

	public Tipodocumento() {
	}

	public Integer getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getCodigoSunat() {
		return this.codigoSunat;
	}

	public void setCodigoSunat(String codigoSunat) {
		this.codigoSunat = codigoSunat;
	}

	public String getDscTipoDocumento() {
		return this.dscTipoDocumento;
	}

	public void setDscTipoDocumento(String dscTipoDocumento) {
		this.dscTipoDocumento = dscTipoDocumento;
	}

	public List<Com001> getCom001s() {
		return this.com001s;
	}

	public void setCom001s(List<Com001> com001s) {
		this.com001s = com001s;
	}

	public Com001 addCom001(Com001 com001) {
		getCom001s().add(com001);
		com001.setTipodocumento(this);

		return com001;
	}

	public Com001 removeCom001(Com001 com001) {
		getCom001s().remove(com001);
		com001.setTipodocumento(null);

		return com001;
	}

	public List<Gasto001> getGasto001s() {
		return this.gasto001s;
	}

	public void setGasto001s(List<Gasto001> gasto001s) {
		this.gasto001s = gasto001s;
	}

	public Gasto001 addGasto001(Gasto001 gasto001) {
		getGasto001s().add(gasto001);
		gasto001.setTipodocumento(this);

		return gasto001;
	}

	public Gasto001 removeGasto001(Gasto001 gasto001) {
		getGasto001s().remove(gasto001);
		gasto001.setTipodocumento(null);

		return gasto001;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setTipodocumento(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setTipodocumento(null);

		return ing001;
	}

	public List<Numerador> getNumeradors() {
		return this.numeradors;
	}

	public void setNumeradors(List<Numerador> numeradors) {
		this.numeradors = numeradors;
	}

	public Numerador addNumerador(Numerador numerador) {
		getNumeradors().add(numerador);
		numerador.setTipodocumento(this);

		return numerador;
	}

	public Numerador removeNumerador(Numerador numerador) {
		getNumeradors().remove(numerador);
		numerador.setTipodocumento(null);

		return numerador;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setTipodocumento(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setTipodocumento(null);

		return ven001;
	}

}
