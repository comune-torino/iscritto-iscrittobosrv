package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Isee implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean stato;
	private DatiIsee dati;

	public Boolean isStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public DatiIsee getDati() {
		return dati;
	}

	public void setDati(DatiIsee dati) {
		this.dati = dati;
	}

}
