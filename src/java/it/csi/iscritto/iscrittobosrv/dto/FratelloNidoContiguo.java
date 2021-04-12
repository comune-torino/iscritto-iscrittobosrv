package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class FratelloNidoContiguo implements Serializable {
	static final long serialVersionUID = 1;

	private Boolean stato;
	private Anagrafica anagrafica;
	private NidoContiguo nidoContiguo;

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public NidoContiguo getNidoContiguo() {
		return nidoContiguo;
	}

	public void setNidoContiguo(NidoContiguo nidoContiguo) {
		this.nidoContiguo = nidoContiguo;
	}

}
