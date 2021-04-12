package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Trasferimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean stato;
	private DatiTrasferimento dati;

	public Boolean isStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public DatiTrasferimento getDati() {
		return dati;
	}

	public void setDati(DatiTrasferimento dati) {
		this.dati = dati;
	}

}
