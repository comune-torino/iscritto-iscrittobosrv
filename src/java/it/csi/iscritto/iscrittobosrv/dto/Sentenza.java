package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Sentenza implements Serializable {
	private static final long serialVersionUID = 1L;

	private String numero;
	private String data;
	private String tribunale;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTribunale() {
		return tribunale;
	}

	public void setTribunale(String tribunale) {
		this.tribunale = tribunale;
	}

}
