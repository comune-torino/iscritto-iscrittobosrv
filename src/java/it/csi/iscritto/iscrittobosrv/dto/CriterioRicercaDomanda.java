package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class CriterioRicercaDomanda implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String codiceStato;

	public CriterioRicercaDomanda(String codiceFiscale, String cognome, String nome, String codiceStato) {
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.nome = nome;
		this.codiceStato = codiceStato;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceStato() {
		return codiceStato;
	}

	public void setCodiceStato(String codiceStato) {
		this.codiceStato = codiceStato;
	}

}
