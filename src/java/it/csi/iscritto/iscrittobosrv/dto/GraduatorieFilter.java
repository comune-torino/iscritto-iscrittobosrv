package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class GraduatorieFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceGraduatoria;
	private String codiceStepGraduatoria;
	private String codiceScuola;
	private String codiceTipologiaFrequenza;
	private String codiceFasciaEta;

	public String getCodiceGraduatoria() {
		return codiceGraduatoria;
	}

	public void setCodiceGraduatoria(String codiceGraduatoria) {
		this.codiceGraduatoria = codiceGraduatoria;
	}

	public String getCodiceStepGraduatoria() {
		return codiceStepGraduatoria;
	}

	public void setCodiceStepGraduatoria(String codiceStepGraduatoria) {
		this.codiceStepGraduatoria = codiceStepGraduatoria;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public String getCodiceTipologiaFrequenza() {
		return codiceTipologiaFrequenza;
	}

	public void setCodiceTipologiaFrequenza(String codiceTipologiaFrequenza) {
		this.codiceTipologiaFrequenza = codiceTipologiaFrequenza;
	}

	public String getCodiceFasciaEta() {
		return codiceFasciaEta;
	}

	public void setCodiceFasciaEta(String codiceFasciaEta) {
		this.codiceFasciaEta = codiceFasciaEta;
	}

}
