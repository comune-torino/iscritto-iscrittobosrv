package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class StatoDomanda implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codice;
	private String descrizione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
