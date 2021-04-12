package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Dipendente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String azienda;
	private String comune;
	private String provincia;
	private String indirizzo;

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
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
