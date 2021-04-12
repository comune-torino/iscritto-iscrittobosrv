package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class InfoStepGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idAnagraficaGra;
	private String codAnagraficaGra;
	private String codAnnoScolastico;
	private Long idStepGra;
	private Integer step;
	private Date dtStepGra;
	private Long idStepGraCon;
	private Date dtStepCon;
	private String flAmmissioni;
	private String codStatoGra;

	public Long getIdAnagraficaGra() {
		return idAnagraficaGra;
	}

	public void setIdAnagraficaGra(Long idAnagraficaGra) {
		this.idAnagraficaGra = idAnagraficaGra;
	}

	public String getCodAnagraficaGra() {
		return codAnagraficaGra;
	}

	public void setCodAnagraficaGra(String codAnagraficaGra) {
		this.codAnagraficaGra = codAnagraficaGra;
	}

	public String getCodAnnoScolastico() {
		return codAnnoScolastico;
	}

	public void setCodAnnoScolastico(String codAnnoScolastico) {
		this.codAnnoScolastico = codAnnoScolastico;
	}

	public Long getIdStepGra() {
		return idStepGra;
	}

	public void setIdStepGra(Long idStepGra) {
		this.idStepGra = idStepGra;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Date getDtStepGra() {
		return dtStepGra;
	}

	public void setDtStepGra(Date dtStepGra) {
		this.dtStepGra = dtStepGra;
	}

	public Long getIdStepGraCon() {
		return idStepGraCon;
	}

	public void setIdStepGraCon(Long idStepGraCon) {
		this.idStepGraCon = idStepGraCon;
	}

	public Date getDtStepCon() {
		return dtStepCon;
	}

	public void setDtStepCon(Date dtStepCon) {
		this.dtStepCon = dtStepCon;
	}

	public String getFlAmmissioni() {
		return flAmmissioni;
	}

	public void setFlAmmissioni(String flAmmissioni) {
		this.flAmmissioni = flAmmissioni;
	}

	public String getCodStatoGra() {
		return codStatoGra;
	}

	public void setCodStatoGra(String codStatoGra) {
		this.codStatoGra = codStatoGra;
	}

}
