package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Affido implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean stato;
	private List<SoggettoAffido> soggetti = new ArrayList<>();

	public Boolean isStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public List<SoggettoAffido> getSoggetti() {
		return soggetti;
	}

	public void setSoggetti(List<SoggettoAffido> soggetti) {
		this.soggetti = soggetti;
	}

}
