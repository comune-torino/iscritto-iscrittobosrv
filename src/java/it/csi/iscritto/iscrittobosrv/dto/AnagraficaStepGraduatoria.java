package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class AnagraficaStepGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer step;
	private Long idStepGra;
	private Date dtStepGra;
	private Date dtDomInvDa;
	private Date dtDomInvA;
	private Date dtAllegati;
	private String codAnagraficaGraduatoria;
	private String codAnnoScolastico;
	private String codOrdineScuola;

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Long getIdStepGra() {
		return idStepGra;
	}

	public void setIdStepGra(Long idStepGra) {
		this.idStepGra = idStepGra;
	}

	public Date getDtStepGra() {
		return dtStepGra;
	}

	public void setDtStepGra(Date dtStepGra) {
		this.dtStepGra = dtStepGra;
	}

	public Date getDtDomInvDa() {
		return dtDomInvDa;
	}

	public void setDtDomInvDa(Date dtDomInvDa) {
		this.dtDomInvDa = dtDomInvDa;
	}

	public Date getDtDomInvA() {
		return dtDomInvA;
	}

	public void setDtDomInvA(Date dtDomInvA) {
		this.dtDomInvA = dtDomInvA;
	}

	public Date getDtAllegati() {
		return dtAllegati;
	}

	public void setDtAllegati(Date dtAllegati) {
		this.dtAllegati = dtAllegati;
	}

	public String getCodAnagraficaGraduatoria() {
		return codAnagraficaGraduatoria;
	}

	public void setCodAnagraficaGraduatoria(String codAnagraficaGraduatoria) {
		this.codAnagraficaGraduatoria = codAnagraficaGraduatoria;
	}

	public String getCodAnnoScolastico() {
		return codAnnoScolastico;
	}

	public void setCodAnnoScolastico(String codAnnoScolastico) {
		this.codAnnoScolastico = codAnnoScolastico;
	}

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

}
