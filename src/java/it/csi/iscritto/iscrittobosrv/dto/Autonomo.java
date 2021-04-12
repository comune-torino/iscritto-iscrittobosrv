package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Autonomo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String piva;
	private String comune;
	private String provincia;
	private String indirizzo;

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

}
