package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Soggetto2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean presenzaNucleo;
	private Anagrafica anagrafica;
	private LuogoNascita luogoNascita;
	private Residenza residenza;
	private Gravidanza gravidanza;
	private ProblemiSalute problemiSalute;
	private CondizioneOccupazionale condizioneOccupazionale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isPresenzaNucleo() {
		return presenzaNucleo;
	}

	public void setPresenzaNucleo(Boolean presenzaNucleo) {
		this.presenzaNucleo = presenzaNucleo;
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

	public Gravidanza getGravidanza() {
		return gravidanza;
	}

	public void setGravidanza(Gravidanza gravidanza) {
		this.gravidanza = gravidanza;
	}

	public ProblemiSalute getProblemiSalute() {
		return problemiSalute;
	}

	public void setProblemiSalute(ProblemiSalute problemiSalute) {
		this.problemiSalute = problemiSalute;
	}

	public CondizioneOccupazionale getCondizioneOccupazionale() {
		return condizioneOccupazionale;
	}

	public void setCondizioneOccupazionale(CondizioneOccupazionale condizioneOccupazionale) {
		this.condizioneOccupazionale = condizioneOccupazionale;
	}

}
