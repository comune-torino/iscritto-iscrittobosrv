package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiSpostamento implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stato;
	private String dataVariazione;
	private String dataAppuntamento;
	private String residenzaFutura;
	private String indirizzo;


	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDataVariazione() {
		return dataVariazione;
	}

	public void setDataVariazione(String dataVariazione) {
		this.dataVariazione = dataVariazione;
	}

	public String getDataAppuntamento() {
		return dataAppuntamento;
	}

	public void setDataAppuntamento(String dataAppuntamento) {
		this.dataAppuntamento = dataAppuntamento;
	}

	public String getResidenzaFutura() { return residenzaFutura;	}

	public void setResidenzaFutura(String residenzaFutura) {
		this.residenzaFutura = residenzaFutura;
	}

	public String getIndirizzo() { return indirizzo; }

	public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo;}

}
