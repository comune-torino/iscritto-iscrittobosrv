package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class RecordDomandeScuolaResidenza implements Serializable {
	private static final long serialVersionUID = 1L;

	private String protocollo;
	private String codiceFiscaleMinore;
	private String cognomeMinore;
	private String nomeMinore;
	private String codiceScuola;
	private String descScuola;
	private String indirizzoScuola;

	public String getProtocollo() {
		return protocollo;
	}
	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
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
	public String getDescScuola() {
		return descScuola;
	}
	public void setDescScuola(String descScuola) {
		this.descScuola = descScuola;
	}
	public String getIndirizzoScuola() {
		return indirizzoScuola;
	}
	public void setIndirizzoScuola(String indirizzoScuola) {
		this.indirizzoScuola = indirizzoScuola;
	}

}
