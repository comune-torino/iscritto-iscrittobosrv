package it.csi.iscritto.iscrittobosrv.dto.rest;

import java.io.Serializable;

public class CallerInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ipAddress;
	private String idApp;
	private String utente;
	private String operazione;
	private String oggOper;
	private String keyOper;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIdApp() {
		return idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public String getOggOper() {
		return oggOper;
	}

	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	public String getKeyOper() {
		return keyOper;
	}

	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

}
