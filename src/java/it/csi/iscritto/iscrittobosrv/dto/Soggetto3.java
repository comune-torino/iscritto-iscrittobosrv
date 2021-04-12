package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Soggetto3 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean stato;
	private DatiSoggetto3 dati;

	public Boolean isStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public DatiSoggetto3 getDati() {
		return dati;
	}

	public void setDati(DatiSoggetto3 dati) {
		this.dati = dati;
	}

}
