package com.adicse.comercial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the control_procesos database table.
 * 
 */
@Entity
@Table(name="control_procesos")
@NamedQuery(name="ControlProceso.findAll", query="SELECT c FROM ControlProceso c")
public class ControlProceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_control_procesos")
	private String idControlProcesos;

	@Column(name="dsc_proceso")
	private String dscProceso;

	private Integer estado;

	public ControlProceso() {
	}

	public String getIdControlProcesos() {
		return this.idControlProcesos;
	}

	public void setIdControlProcesos(String idControlProcesos) {
		this.idControlProcesos = idControlProcesos;
	}

	public String getDscProceso() {
		return this.dscProceso;
	}

	public void setDscProceso(String dscProceso) {
		this.dscProceso = dscProceso;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}