package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Studente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String istituto;
	private String corso;

	public String getIstituto() {
		return istituto;
	}

	public void setIstituto(String istituto) {
		this.istituto = istituto;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

}
