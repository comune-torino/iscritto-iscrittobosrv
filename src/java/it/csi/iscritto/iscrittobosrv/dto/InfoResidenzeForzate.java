package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoResidenzeForzate implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codGraduatoria;
	private String codTipoScuola;
	private Long idStepGra;
	private Long idStepGraCon;
	private List<ResidenzaForzata> residenzeForzate = new ArrayList<>();

	public String getCodGraduatoria() {
		return codGraduatoria;
	}

	public void setCodGraduatoria(String codGraduatoria) {
		this.codGraduatoria = codGraduatoria;
	}

	public String getCodTipoScuola() {
		return codTipoScuola;
	}

	public void setCodTipoScuola(String codTipoScuola) {
		this.codTipoScuola = codTipoScuola;
	}

	public Long getIdStepGra() {
		return idStepGra;
	}

	public void setIdStepGra(Long idStepGra) {
		this.idStepGra = idStepGra;
	}

	public Long getIdStepGraCon() {
		return idStepGraCon;
	}

	public void setIdStepGraCon(Long idStepGraCon) {
		this.idStepGraCon = idStepGraCon;
	}

	public List<ResidenzaForzata> getResidenzeForzate() {
		return residenzeForzate;
	}

	public void setResidenzeForzate(List<ResidenzaForzata> residenzeForzate) {
		this.residenzeForzate = residenzeForzate;
	}

}
