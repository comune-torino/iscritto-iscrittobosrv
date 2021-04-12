package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiAnnoListaAttesa implements Serializable {
	static final long serialVersionUID = 1;

	private String annoScolastico;
	private String scuola;

	public String getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public String getScuola() {
		return scuola;
	}

	public void setScuola(String scuola) {
		this.scuola = scuola;
	}

}
