package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class InfoScuola implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codScuola;
	private String descrizione;
	private String indirizzo;
	private String codCategoriaScuola;
	private String descCategoriaScuola;
	private String codOrdineScuola;
	private String descOrdineScuola;
	private String codCircoscrizione;
	private String descCircoscrizione;
	private String annoScolastico;
	private Boolean alert;
	private Boolean tempoBreve;
	private Boolean tempoLungo;
	private Boolean tempoIntermedio;

	public String getCodScuola() {
		return codScuola;
	}

	public void setCodScuola(String codScuola) {
		this.codScuola = codScuola;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCodCategoriaScuola() {
		return codCategoriaScuola;
	}

	public void setCodCategoriaScuola(String codCategoriaScuola) {
		this.codCategoriaScuola = codCategoriaScuola;
	}

	public String getDescCategoriaScuola() {
		return descCategoriaScuola;
	}

	public void setDescCategoriaScuola(String descCategoriaScuola) {
		this.descCategoriaScuola = descCategoriaScuola;
	}

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

	public String getDescOrdineScuola() {
		return descOrdineScuola;
	}

	public void setDescOrdineScuola(String descOrdineScuola) {
		this.descOrdineScuola = descOrdineScuola;
	}

	public String getCodCircoscrizione() {
		return codCircoscrizione;
	}

	public void setCodCircoscrizione(String codCircoscrizione) {
		this.codCircoscrizione = codCircoscrizione;
	}

	public String getDescCircoscrizione() {
		return descCircoscrizione;
	}

	public void setDescCircoscrizione(String descCircoscrizione) {
		this.descCircoscrizione = descCircoscrizione;
	}

	public String getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public Boolean isAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	public Boolean isTempoBreve() {
		return tempoBreve;
	}

	public void setTempoBreve(Boolean tempoBreve) {
		this.tempoBreve = tempoBreve;
	}

	public Boolean isTempoLungo() {
		return tempoLungo;
	}

	public void setTempoLungo(Boolean tempoLungo) {
		this.tempoLungo = tempoLungo;
	}

	public Boolean isTempoIntermedio() {
		return tempoIntermedio;
	}

	public void setTempoIntermedio(Boolean tempoIntermedio) {
		this.tempoIntermedio = tempoIntermedio;
	}

}
