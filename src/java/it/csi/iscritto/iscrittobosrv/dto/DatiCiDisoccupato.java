package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiCiDisoccupato implements Serializable {
	private static final long serialVersionUID = 1L;

	private String comune;
	private String provincia;

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
