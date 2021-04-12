package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class AnagraficaComponente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String dataNascita;
	private String oraMinutiNascita;
	private String sesso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getOraMinutiNascita() {
		return oraMinutiNascita;
	}

	public void setOraMinutiNascita(String oraMinutiNascita) {
		this.oraMinutiNascita = oraMinutiNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

}
