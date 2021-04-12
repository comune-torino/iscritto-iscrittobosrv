package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Stato implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codice;
	private String descrizione;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
