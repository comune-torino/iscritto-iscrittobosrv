package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class ParametriCalcoloGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceGraduatoria;
	private String codiceStatoGraduatoria;
	private Integer stepGraduatoria;
	private String codiceOrdineScuola;
	private String codiceStatoDaCalcolare;

	public String getCodiceGraduatoria() {
		return codiceGraduatoria;
	}

	public void setCodiceGraduatoria(String codiceGraduatoria) {
		this.codiceGraduatoria = codiceGraduatoria;
	}

	public String getCodiceStatoGraduatoria() {
		return codiceStatoGraduatoria;
	}

	public void setCodiceStatoGraduatoria(String codiceStatoGraduatoria) {
		this.codiceStatoGraduatoria = codiceStatoGraduatoria;
	}

	public Integer getStepGraduatoria() {
		return stepGraduatoria;
	}

	public void setStepGraduatoria(Integer stepGraduatoria) {
		this.stepGraduatoria = stepGraduatoria;
	}

	public String getCodiceOrdineScuola() {
		return codiceOrdineScuola;
	}

	public void setCodiceOrdineScuola(String codiceOrdineScuola) {
		this.codiceOrdineScuola = codiceOrdineScuola;
	}

	public String getCodiceStatoDaCalcolare() {
		return codiceStatoDaCalcolare;
	}

	public void setCodiceStatoDaCalcolare(String codiceStatoDaCalcolare) {
		this.codiceStatoDaCalcolare = codiceStatoDaCalcolare;
	}

}
