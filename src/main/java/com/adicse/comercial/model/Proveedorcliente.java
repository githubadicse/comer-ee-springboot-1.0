package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedorcliente database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedorcliente.findAll", query="SELECT p FROM Proveedorcliente p")
public class Proveedorcliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idproveedorcliente;

	private String contacto1;

	private String contacto2;

	private String email;

	private String nrodocumento;

	private String razonsocial;

	private String representante;

	private String telefono;

	//bi-directional many-to-one association to Com001
	@OneToMany(mappedBy="proveedorcliente")
	private List<Com001> com001s;

	//bi-directional many-to-one association to EntregaPorItem
	@OneToMany(mappedBy="proveedorcliente")
	private List<EntregaPorItem> entregaPorItems;

	//bi-directional many-to-one association to Gasto001
	@OneToMany(mappedBy="proveedorcliente")
	private List<Gasto001> gasto001s;

	//bi-directional many-to-one association to GuiaRemision001
	@OneToMany(mappedBy="proveedorcliente")
	private List<GuiaRemision001> guiaRemision001s;

	//bi-directional many-to-one association to Ing001
	@OneToMany(mappedBy="proveedorcliente")
	private List<Ing001> ing001s;

	//bi-directional many-to-one association to Lineacredito
	@OneToMany(mappedBy="proveedorcliente")
	private List<Lineacredito> lineacreditos;

	//bi-directional many-to-one association to Documentoidentificacion
	@ManyToOne
	@JoinColumn(name="iddocumentoidentificacion")
	private Documentoidentificacion documentoidentificacion;

	//bi-directional many-to-one association to Proveedorclientedireccion
	@OneToMany(mappedBy="proveedorcliente", cascade={CascadeType.ALL})
	private List<Proveedorclientedireccion> proveedorclientedireccions;

	//bi-directional many-to-one association to Salida001
	@OneToMany(mappedBy="proveedorcliente")
	private List<Salida001> salida001s;

	//bi-directional many-to-one association to Transportista
	@OneToMany(mappedBy="proveedorcliente")
	private List<Transportista> transportistas;

	//bi-directional many-to-one association to Ven001
	@OneToMany(mappedBy="proveedorcliente")
	private List<Ven001> ven001s;

	public Proveedorcliente() {
	}

	public Integer getIdproveedorcliente() {
		return this.idproveedorcliente;
	}

	public void setIdproveedorcliente(Integer idproveedorcliente) {
		this.idproveedorcliente = idproveedorcliente;
	}

	public String getContacto1() {
		return this.contacto1;
	}

	public void setContacto1(String contacto1) {
		this.contacto1 = contacto1;
	}

	public String getContacto2() {
		return this.contacto2;
	}

	public void setContacto2(String contacto2) {
		this.contacto2 = contacto2;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNrodocumento() {
		return this.nrodocumento;
	}

	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Com001> getCom001s() {
		return this.com001s;
	}

	public void setCom001s(List<Com001> com001s) {
		this.com001s = com001s;
	}

	public Com001 addCom001(Com001 com001) {
		getCom001s().add(com001);
		com001.setProveedorcliente(this);

		return com001;
	}

	public Com001 removeCom001(Com001 com001) {
		getCom001s().remove(com001);
		com001.setProveedorcliente(null);

		return com001;
	}

	public List<EntregaPorItem> getEntregaPorItems() {
		return this.entregaPorItems;
	}

	public void setEntregaPorItems(List<EntregaPorItem> entregaPorItems) {
		this.entregaPorItems = entregaPorItems;
	}

	public EntregaPorItem addEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().add(entregaPorItem);
		entregaPorItem.setProveedorcliente(this);

		return entregaPorItem;
	}

	public EntregaPorItem removeEntregaPorItem(EntregaPorItem entregaPorItem) {
		getEntregaPorItems().remove(entregaPorItem);
		entregaPorItem.setProveedorcliente(null);

		return entregaPorItem;
	}

	public List<Gasto001> getGasto001s() {
		return this.gasto001s;
	}

	public void setGasto001s(List<Gasto001> gasto001s) {
		this.gasto001s = gasto001s;
	}

	public Gasto001 addGasto001(Gasto001 gasto001) {
		getGasto001s().add(gasto001);
		gasto001.setProveedorcliente(this);

		return gasto001;
	}

	public Gasto001 removeGasto001(Gasto001 gasto001) {
		getGasto001s().remove(gasto001);
		gasto001.setProveedorcliente(null);

		return gasto001;
	}

	public List<GuiaRemision001> getGuiaRemision001s() {
		return this.guiaRemision001s;
	}

	public void setGuiaRemision001s(List<GuiaRemision001> guiaRemision001s) {
		this.guiaRemision001s = guiaRemision001s;
	}

	public GuiaRemision001 addGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().add(guiaRemision001);
		guiaRemision001.setProveedorcliente(this);

		return guiaRemision001;
	}

	public GuiaRemision001 removeGuiaRemision001(GuiaRemision001 guiaRemision001) {
		getGuiaRemision001s().remove(guiaRemision001);
		guiaRemision001.setProveedorcliente(null);

		return guiaRemision001;
	}

	public List<Ing001> getIng001s() {
		return this.ing001s;
	}

	public void setIng001s(List<Ing001> ing001s) {
		this.ing001s = ing001s;
	}

	public Ing001 addIng001(Ing001 ing001) {
		getIng001s().add(ing001);
		ing001.setProveedorcliente(this);

		return ing001;
	}

	public Ing001 removeIng001(Ing001 ing001) {
		getIng001s().remove(ing001);
		ing001.setProveedorcliente(null);

		return ing001;
	}

	public List<Lineacredito> getLineacreditos() {
		return this.lineacreditos;
	}

	public void setLineacreditos(List<Lineacredito> lineacreditos) {
		this.lineacreditos = lineacreditos;
	}

	public Lineacredito addLineacredito(Lineacredito lineacredito) {
		getLineacreditos().add(lineacredito);
		lineacredito.setProveedorcliente(this);

		return lineacredito;
	}

	public Lineacredito removeLineacredito(Lineacredito lineacredito) {
		getLineacreditos().remove(lineacredito);
		lineacredito.setProveedorcliente(null);

		return lineacredito;
	}

	public Documentoidentificacion getDocumentoidentificacion() {
		return this.documentoidentificacion;
	}

	public void setDocumentoidentificacion(Documentoidentificacion documentoidentificacion) {
		this.documentoidentificacion = documentoidentificacion;
	}

	public List<Proveedorclientedireccion> getProveedorclientedireccions() {
		return this.proveedorclientedireccions;
	}

	public void setProveedorclientedireccions(List<Proveedorclientedireccion> proveedorclientedireccions) {
		this.proveedorclientedireccions = proveedorclientedireccions;
	}

	public Proveedorclientedireccion addProveedorclientedireccion(Proveedorclientedireccion proveedorclientedireccion) {
		getProveedorclientedireccions().add(proveedorclientedireccion);
		proveedorclientedireccion.setProveedorcliente(this);

		return proveedorclientedireccion;
	}

	public Proveedorclientedireccion removeProveedorclientedireccion(Proveedorclientedireccion proveedorclientedireccion) {
		getProveedorclientedireccions().remove(proveedorclientedireccion);
		proveedorclientedireccion.setProveedorcliente(null);

		return proveedorclientedireccion;
	}

	public List<Salida001> getSalida001s() {
		return this.salida001s;
	}

	public void setSalida001s(List<Salida001> salida001s) {
		this.salida001s = salida001s;
	}

	public Salida001 addSalida001(Salida001 salida001) {
		getSalida001s().add(salida001);
		salida001.setProveedorcliente(this);

		return salida001;
	}

	public Salida001 removeSalida001(Salida001 salida001) {
		getSalida001s().remove(salida001);
		salida001.setProveedorcliente(null);

		return salida001;
	}

	public List<Transportista> getTransportistas() {
		return this.transportistas;
	}

	public void setTransportistas(List<Transportista> transportistas) {
		this.transportistas = transportistas;
	}

	public Transportista addTransportista(Transportista transportista) {
		getTransportistas().add(transportista);
		transportista.setProveedorcliente(this);

		return transportista;
	}

	public Transportista removeTransportista(Transportista transportista) {
		getTransportistas().remove(transportista);
		transportista.setProveedorcliente(null);

		return transportista;
	}

	public List<Ven001> getVen001s() {
		return this.ven001s;
	}

	public void setVen001s(List<Ven001> ven001s) {
		this.ven001s = ven001s;
	}

	public Ven001 addVen001(Ven001 ven001) {
		getVen001s().add(ven001);
		ven001.setProveedorcliente(this);

		return ven001;
	}

	public Ven001 removeVen001(Ven001 ven001) {
		getVen001s().remove(ven001);
		ven001.setProveedorcliente(null);

		return ven001;
	}

}
