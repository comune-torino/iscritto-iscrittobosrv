package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiCondizionePunteggio implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idDomandaIscrizione;
	private String codCondizionePunteggio;
	private String validata;
	private String tipoValidazione;
	private String note;
	private String dataInizioValidita;
	private String dataFineValidita;
	private Integer ricorrenza;
	private String nomeOperatore;
	private String cognomeOperatore;

	public Long getIdDomandaIscrizione() {
		return idDomandaIscrizione;
	}

	public void setIdDomandaIscrizione(Long idDomandaIscrizione) {
		this.idDomandaIscrizione = idDomandaIscrizione;
	}

	public String getCodCondizionePunteggio() {
		return codCondizionePunteggio;
	}

	public void setCodCondizionePunteggio(String codCondizionePunteggio) {
		this.codCondizionePunteggio = codCondizionePunteggio;
	}

	public String getValidata() {
		return validata;
	}

	public void setValidata(String validata) {
		this.validata = validata;
	}

	public String getTipoValidazione() {
		return tipoValidazione;
	}

	public void setTipoValidazione(String tipoValidazione) {
		this.tipoValidazione = tipoValidazione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Integer getRicorrenza() {
		return ricorrenza;
	}

	public void setRicorrenza(Integer ricorrenza) {
		this.ricorrenza = ricorrenza;
	}

	public String getNomeOperatore() {
		return nomeOperatore;
	}

	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

	public String getCognomeOperatore() {
		return cognomeOperatore;
	}

	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
	}

}
