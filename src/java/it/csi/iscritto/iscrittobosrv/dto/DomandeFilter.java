package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonValue;

public class DomandeFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceFiscaleMinore;
	private String cognomeMinore;
	private String nomeMinore;
	private String codiceScuola;
	private String codiceStatoDomanda;
	private String codiceCondizionePunteggio;
	private String dataInizio;
	private String dataFine;
	private StatoCondizionePunteggioEnum statoCondizionePunteggio;
	private String protocollo;

	public enum StatoCondizionePunteggioEnum {
		ALL("ALL"),
		DAI("DAI"),
		CON("CON"),
		INV("INV");

		private String value;

		StatoCondizionePunteggioEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}
	}

	public String getCodiceFiscaleMinore() {
		return codiceFiscaleMinore;
	}

	public void setCodiceFiscaleMinore(String codiceFiscaleMinore) {
		this.codiceFiscaleMinore = codiceFiscaleMinore;
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

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public String getCodiceStatoDomanda() {
		return codiceStatoDomanda;
	}

	public void setCodiceStatoDomanda(String codiceStatoDomanda) {
		this.codiceStatoDomanda = codiceStatoDomanda;
	}

	public String getCodiceCondizionePunteggio() {
		return codiceCondizionePunteggio;
	}

	public void setCodiceCondizionePunteggio(String codiceCondizionePunteggio) {
		this.codiceCondizionePunteggio = codiceCondizionePunteggio;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public StatoCondizionePunteggioEnum getStatoCondizionePunteggio() {
		return statoCondizionePunteggio;
	}

	public void setStatoCondizionePunteggio(StatoCondizionePunteggioEnum statoCondizionePunteggio) {
		this.statoCondizionePunteggio = statoCondizionePunteggio;
	}

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

}
