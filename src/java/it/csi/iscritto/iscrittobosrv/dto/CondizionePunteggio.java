package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonValue;

public class CondizionePunteggio implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codice;
	private String descrizione;
	private Long ricorrenza;
	private Boolean validata;

	private String dataInizioValidita;
	private String cognomeUtente;
	private String note;

	private String codTipoIstruttoria;
	private String codTipoAllegato;

	private TipoIstruttoriaEnum tipoIstruttoria;

	public enum TipoIstruttoriaEnum {
		S("S"),
		N("N"),
		P("P");

		private String value;

		TipoIstruttoriaEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}
	}

	public String getCodTipoIstruttoria() {
		return codTipoIstruttoria;
	}

	public void setCodTipoIstruttoria(String codTipoIstruttoria) {
		this.codTipoIstruttoria = codTipoIstruttoria;
	}

	public String getCodTipoAllegato() {
		return codTipoAllegato;
	}

	public void setCodTipoAllegato(String codTipoAllegato) {
		this.codTipoAllegato = codTipoAllegato;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getRicorrenza() {
		return ricorrenza;
	}

	public void setRicorrenza(Long ricorrenza) {
		this.ricorrenza = ricorrenza;
	}

	public TipoIstruttoriaEnum getTipoIstruttoria() {
		return tipoIstruttoria;
	}

	public void setTipoIstruttoria(TipoIstruttoriaEnum tipoIstruttoria) {
		this.tipoIstruttoria = tipoIstruttoria;
	}

	public Boolean getValidata() {
		return validata;
	}

	public void setValidata(Boolean validata) {
		this.validata = validata;
	}

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCognomeUtente() {
		return cognomeUtente;
	}

	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}

}
