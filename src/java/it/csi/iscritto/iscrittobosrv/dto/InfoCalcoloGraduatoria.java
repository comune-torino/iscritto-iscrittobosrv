package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoCalcoloGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer domandeNonIstruite;
	private List<InfoVerifiche> verifichePreventivePendenti = new ArrayList<>();

	public Integer getDomandeNonIstruite() {
		return domandeNonIstruite;
	}

	public void setDomandeNonIstruite(Integer domandeNonIstruite) {
		this.domandeNonIstruite = domandeNonIstruite;
	}

	public List<InfoVerifiche> getVerifichePreventivePendenti() {
		return verifichePreventivePendenti;
	}

	public void setVerifichePreventivePendenti(List<InfoVerifiche> verifichePreventivePendenti) {
		this.verifichePreventivePendenti = verifichePreventivePendenti;
	}

}
