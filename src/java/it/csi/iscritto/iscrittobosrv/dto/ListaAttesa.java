package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class ListaAttesa implements Serializable {
	static final long serialVersionUID = 1;

	private Boolean stato;
	private DatiListaAttesa dati;

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public DatiListaAttesa getDati() {
		return dati;
	}

	public void setDati(DatiListaAttesa dati) {
		this.dati = dati;
	}

}
