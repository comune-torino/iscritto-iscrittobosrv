package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComponentiNucleo implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<SoggettoNucleo> soggetti = new ArrayList<>();

	public List<SoggettoNucleo> getSoggetti() {
		return soggetti;
	}

	public void setSoggetti(List<SoggettoNucleo> soggetti) {
		this.soggetti = soggetti;
	}

}
