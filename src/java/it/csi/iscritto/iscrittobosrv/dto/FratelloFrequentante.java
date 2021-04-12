package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class FratelloFrequentante implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean stato;
	private String tipo;
	private Anagrafica anagrafica;

	public Boolean isStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

}
