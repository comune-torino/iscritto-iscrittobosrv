package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cognome;
	private String codFisc;
	private String ente;
	private String ruolo;
	private String codRuolo;
	private String idIride;

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

	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getCodRuolo() {
		return codRuolo;
	}

	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}

	public String getIdIride() {
		return idIride;
	}

	public void setIdIride(String idIride) {
		this.idIride = idIride;
	}

}
