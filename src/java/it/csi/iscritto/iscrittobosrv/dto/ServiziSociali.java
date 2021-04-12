package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiziSociali implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean stato;
	private DatiServiziSociali dati;
	private List<Documento> documenti = new ArrayList<>();

	public Boolean isStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public DatiServiziSociali getDati() {
		return dati;
	}

	public void setDati(DatiServiziSociali dati) {
		this.dati = dati;
	}

	public List<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}

}
