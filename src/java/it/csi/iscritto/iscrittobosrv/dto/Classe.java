package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Classe implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idClasse;
	private Integer postiLiberi;
	private Integer postiAmmessi;
	private Integer postiAccettati;
	private String denominazione;
	private String flAmmissioneDis;
	private String codFasciaEta;
	private String codScuola;
	private String codOrdineScuola;
	private String codAnnoScolastico;
	private String codTipoFrequenza;

	public Long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Long idClasse) {
		this.idClasse = idClasse;
	}

	public Integer getPostiLiberi() {
		return postiLiberi;
	}

	public void setPostiLiberi(Integer postiLiberi) {
		this.postiLiberi = postiLiberi;
	}

	public Integer getPostiAmmessi() {
		return postiAmmessi;
	}

	public void setPostiAmmessi(Integer postiAmmessi) {
		this.postiAmmessi = postiAmmessi;
	}

	public Integer getPostiAccettati() {
		return postiAccettati;
	}

	public void setPostiAccettati(Integer postiAccettati) {
		this.postiAccettati = postiAccettati;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getFlAmmissioneDis() {
		return flAmmissioneDis;
	}

	public void setFlAmmissioneDis(String flAmmissioneDis) {
		this.flAmmissioneDis = flAmmissioneDis;
	}

	public String getCodFasciaEta() {
		return codFasciaEta;
	}

	public void setCodFasciaEta(String codFasciaEta) {
		this.codFasciaEta = codFasciaEta;
	}

	public String getCodScuola() {
		return codScuola;
	}

	public void setCodScuola(String codScuola) {
		this.codScuola = codScuola;
	}

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

	public String getCodAnnoScolastico() {
		return codAnnoScolastico;
	}

	public void setCodAnnoScolastico(String codAnnoScolastico) {
		this.codAnnoScolastico = codAnnoScolastico;
	}

	public String getCodTipoFrequenza() {
		return codTipoFrequenza;
	}

	public void setCodTipoFrequenza(String codTipoFrequenza) {
		this.codTipoFrequenza = codTipoFrequenza;
	}

}
