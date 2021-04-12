package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiListaAttesa implements Serializable {
	static final long serialVersionUID = 1;

	private DatiAnnoListaAttesa primoAnno;
	private DatiAnnoListaAttesa secondoAnno;

	public DatiAnnoListaAttesa getPrimoAnno() {
		return primoAnno;
	}

	public void setPrimoAnno(DatiAnnoListaAttesa primoAnno) {
		this.primoAnno = primoAnno;
	}

	public DatiAnnoListaAttesa getSecondoAnno() {
		return secondoAnno;
	}

	public void setSecondoAnno(DatiAnnoListaAttesa secondoAnno) {
		this.secondoAnno = secondoAnno;
	}

}
