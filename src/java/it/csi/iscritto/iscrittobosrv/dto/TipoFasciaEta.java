package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class TipoFasciaEta implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codFasciaEta;
	private String descFasciaEta;
	private String codOrdineScuola;

	public String getCodFasciaEta() {
		return codFasciaEta;
	}

	public void setCodFasciaEta(String codFasciaEta) {
		this.codFasciaEta = codFasciaEta;
	}

	public String getDescFasciaEta() {
		return descFasciaEta;
	}

	public void setDescFasciaEta(String descFasciaEta) {
		this.descFasciaEta = descFasciaEta;
	}

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

}
