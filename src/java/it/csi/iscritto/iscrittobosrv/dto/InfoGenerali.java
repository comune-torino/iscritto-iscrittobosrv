package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class InfoGenerali implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dataInizioIscrizioniNidi;
	private String dataScadenzaIscrizioniNidi;
	private String dataFineIscrizioniNidi;

	public String getDataInizioIscrizioniNidi() {
		return dataInizioIscrizioniNidi;
	}

	public void setDataInizioIscrizioniNidi(String dataInizioIscrizioniNidi) {
		this.dataInizioIscrizioniNidi = dataInizioIscrizioniNidi;
	}

	public String getDataScadenzaIscrizioniNidi() {
		return dataScadenzaIscrizioniNidi;
	}

	public void setDataScadenzaIscrizioniNidi(String dataScadenzaIscrizioniNidi) {
		this.dataScadenzaIscrizioniNidi = dataScadenzaIscrizioniNidi;
	}

	public String getDataFineIscrizioniNidi() {
		return dataFineIscrizioniNidi;
	}

	public void setDataFineIscrizioniNidi(String dataFineIscrizioniNidi) {
		this.dataFineIscrizioniNidi = dataFineIscrizioniNidi;
	}

}
