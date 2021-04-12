package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class GenitoreSolo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stato;
	private Sentenza sentenza;

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Sentenza getSentenza() {
		return sentenza;
	}

	public void setSentenza(Sentenza sentenza) {
		this.sentenza = sentenza;
	}

}
