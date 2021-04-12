package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestataGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String posizione;
	private Integer punteggio;
	private String isee;
	private String protocollo;
	private String cognomeMinore;
	private String nomeMinore;
	private String dataNascitaMinore;
	private String tipologiaFrequenza;
	private String preferenza;
	private String sceltaPreferenza;
	private String statoPreferenza;
	private List<Scuola> preferenze = new ArrayList<>();

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

	public String getIsee() {
		return isee;
	}

	public void setIsee(String isee) {
		this.isee = isee;
	}

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public String getCognomeMinore() {
		return cognomeMinore;
	}

	public void setCognomeMinore(String cognomeMinore) {
		this.cognomeMinore = cognomeMinore;
	}

	public String getNomeMinore() {
		return nomeMinore;
	}

	public void setNomeMinore(String nomeMinore) {
		this.nomeMinore = nomeMinore;
	}

	public String getDataNascitaMinore() {
		return dataNascitaMinore;
	}

	public void setDataNascitaMinore(String dataNascitaMinore) {
		this.dataNascitaMinore = dataNascitaMinore;
	}

	public String getTipologiaFrequenza() {
		return tipologiaFrequenza;
	}

	public void setTipologiaFrequenza(String tipologiaFrequenza) {
		this.tipologiaFrequenza = tipologiaFrequenza;
	}

	public String getPreferenza() {
		return preferenza;
	}

	public void setPreferenza(String preferenza) {
		this.preferenza = preferenza;
	}

	public String getSceltaPreferenza() {
		return sceltaPreferenza;
	}

	public void setSceltaPreferenza(String sceltaPreferenza) {
		this.sceltaPreferenza = sceltaPreferenza;
	}

	public String getStatoPreferenza() {
		return statoPreferenza;
	}

	public void setStatoPreferenza(String statoPreferenza) {
		this.statoPreferenza = statoPreferenza;
	}

	public List<Scuola> getPreferenze() {
		return preferenze;
	}

	public void setPreferenze(List<Scuola> preferenze) {
		this.preferenze = preferenze;
	}

}
