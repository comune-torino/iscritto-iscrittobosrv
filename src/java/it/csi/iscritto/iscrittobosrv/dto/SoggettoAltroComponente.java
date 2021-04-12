package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class SoggettoAltroComponente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String relazioneConMinore;
	private Anagrafica anagrafica;
	private LuogoNascita luogoNascita;
	private Residenza residenza;
	private ProblemiSalute problemiSalute;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelazioneConMinore() {
		return relazioneConMinore;
	}

	public void setRelazioneConMinore(String relazioneConMinore) {
		this.relazioneConMinore = relazioneConMinore;
	}

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

	public ProblemiSalute getProblemiSalute() {
		return problemiSalute;
	}

	public void setProblemiSalute(ProblemiSalute problemiSalute) {
		this.problemiSalute = problemiSalute;
	}

}
