package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Attivita implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codice;
	private String codiceFunzione;
	private String descrizione;
	private String link;
	private List<Privilegio> privilegi = new ArrayList<>();

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceFunzione() {
		return codiceFunzione;
	}

	public void setCodiceFunzione(String codiceFunzione) {
		this.codiceFunzione = codiceFunzione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Privilegio> getPrivilegi() {
		return privilegi;
	}

	public void setPrivilegi(List<Privilegio> privilegi) {
		this.privilegi = privilegi;
	}

}
