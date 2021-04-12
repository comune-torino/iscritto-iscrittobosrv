package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatiCondizioneOccupazionale implements Serializable {
	private static final long serialVersionUID = 1L;

	private Dipendente dipendente;
	private Autonomo autonomo;
	private Disoccupato disoccupato;
	private List<NonOccupato> nonOccupato = new ArrayList<>();
	private Studente studente;

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}

	public Disoccupato getDisoccupato() {
		return disoccupato;
	}

	public void setDisoccupato(Disoccupato disoccupato) {
		this.disoccupato = disoccupato;
	}

	public List<NonOccupato> getNonOccupato() {
		return nonOccupato;
	}

	public void setNonOccupato(List<NonOccupato> nonOccupato) {
		this.nonOccupato = nonOccupato;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

}
