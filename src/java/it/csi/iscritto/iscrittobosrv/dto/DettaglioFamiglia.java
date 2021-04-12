package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DettaglioFamiglia implements Serializable {
	private static final long serialVersionUID = 1L;

	private ComponenteFamiglia richiedente;
	private ComponenteFamiglia coniuge;
	private List<ComponenteFamiglia> maggiorenni = new ArrayList<>();
	private List<ComponenteFamiglia> minorenni = new ArrayList<>();
	private ResidenzaFamiglia residenzaFamiglia;

	public ComponenteFamiglia getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(ComponenteFamiglia richiedente) {
		this.richiedente = richiedente;
	}

	public ComponenteFamiglia getConiuge() {
		return coniuge;
	}

	public void setConiuge(ComponenteFamiglia coniuge) {
		this.coniuge = coniuge;
	}

	public List<ComponenteFamiglia> getMaggiorenni() {
		return maggiorenni;
	}

	public void setMaggiorenni(List<ComponenteFamiglia> maggiorenni) {
		this.maggiorenni = maggiorenni;
	}

	public List<ComponenteFamiglia> getMinorenni() {
		return minorenni;
	}

	public void setMinorenni(List<ComponenteFamiglia> minorenni) {
		this.minorenni = minorenni;
	}

	public ResidenzaFamiglia getResidenzaFamiglia() {
		return residenzaFamiglia;
	}

	public void setResidenzaFamiglia(ResidenzaFamiglia residenzaFamiglia) {
		this.residenzaFamiglia = residenzaFamiglia;
	}

}
