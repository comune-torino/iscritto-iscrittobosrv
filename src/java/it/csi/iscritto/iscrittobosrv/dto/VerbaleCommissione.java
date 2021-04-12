package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class VerbaleCommissione implements Serializable {
	private static final long serialVersionUID = 1L;

	private String protocollo;
	private String nomeMinore;
	private String cognomeMinore;
	private String codiceFiscaleMinore;
	private String esito;
	private String dataValidazione;
	private String dataRifiuto;
	private String nomeScuolaPrimaScelta;
	private String indirizzoScuolaPrimaScelta;

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public String getNomeMinore() {
		return nomeMinore;
	}

	public void setNomeMinore(String nomeMinore) {
		this.nomeMinore = nomeMinore;
	}

	public String getCognomeMinore() {
		return cognomeMinore;
	}

	public void setCognomeMinore(String cognomeMinore) {
		this.cognomeMinore = cognomeMinore;
	}

	public String getCodiceFiscaleMinore() {
		return codiceFiscaleMinore;
	}

	public void setCodiceFiscaleMinore(String codiceFiscaleMinore) {
		this.codiceFiscaleMinore = codiceFiscaleMinore;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getDataValidazione() {
		return dataValidazione;
	}

	public void setDataValidazione(String dataValidazione) {
		this.dataValidazione = dataValidazione;
	}

	public String getDataRifiuto() {
		return dataRifiuto;
	}

	public void setDataRifiuto(String dataRifiuto) {
		this.dataRifiuto = dataRifiuto;
	}

	public String getNomeScuolaPrimaScelta() {
		return nomeScuolaPrimaScelta;
	}

	public void setNomeScuolaPrimaScelta(String nomeScuolaPrimaScelta) {
		this.nomeScuolaPrimaScelta = nomeScuolaPrimaScelta;
	}

	public String getIndirizzoScuolaPrimaScelta() {
		return indirizzoScuolaPrimaScelta;
	}

	public void setIndirizzoScuolaPrimaScelta(String indirizzoScuolaPrimaScelta) {
		this.indirizzoScuolaPrimaScelta = indirizzoScuolaPrimaScelta;
	}

}
