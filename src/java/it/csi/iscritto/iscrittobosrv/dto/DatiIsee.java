package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiIsee implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double valore;
	private String dataAttestazione;

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getDataAttestazione() {
		return dataAttestazione;
	}

	public void setDataAttestazione(String dataAttestazione) {
		this.dataAttestazione = dataAttestazione;
	}

}
