package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class CondizioneOccupazionale implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stato;
	private DatiCondizioneOccupazionale dati;

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public DatiCondizioneOccupazionale getDati() {
		return dati;
	}

	public void setDati(DatiCondizioneOccupazionale dati) {
		this.dati = dati;
	}

}
