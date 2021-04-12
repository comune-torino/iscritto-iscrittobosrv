package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiSoggetto3 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Anagrafica anagrafica;
	private LuogoNascita luogoNascita;
	private Residenza residenza;

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public LuogoNascita getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(LuogoNascita luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Residenza getResidenza() {
		return residenza;
	}

	public void setResidenza(Residenza residenza) {
		this.residenza = residenza;
	}

}
