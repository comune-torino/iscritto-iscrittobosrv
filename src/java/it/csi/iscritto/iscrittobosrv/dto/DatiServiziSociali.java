package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiServiziSociali implements Serializable {
	private static final long serialVersionUID = 1L;

	private String assistente;
	private String nome;
	private String indirizzo;
	private String telefono;

	public String getAssistente() {
		return assistente;
	}

	public void setAssistente(String assistente) {
		this.assistente = assistente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
