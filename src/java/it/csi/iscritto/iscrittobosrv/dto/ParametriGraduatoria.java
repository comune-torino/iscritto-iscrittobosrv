package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class ParametriGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codice;
	private String step;
	private String codiceStato;
	private String dataUltimoCalcolo;
	private Boolean ammissioni;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getCodiceStato() {
		return codiceStato;
	}

	public void setCodiceStato(String codiceStato) {
		this.codiceStato = codiceStato;
	}

	public String getDataUltimoCalcolo() {
		return dataUltimoCalcolo;
	}

	public void setDataUltimoCalcolo(String dataUltimoCalcolo) {
		this.dataUltimoCalcolo = dataUltimoCalcolo;
	}

	public Boolean isAmmissioni() {
		return ammissioni;
	}

	public void setAmmissioni(Boolean ammissioni) {
		this.ammissioni = ammissioni;
	}

}
