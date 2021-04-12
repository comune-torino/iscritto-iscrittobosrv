package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class ClasseParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idClasse;
	private Integer postiLiberi;
	private Integer postiAmmessi;
	private String denominazione;
	private Boolean ammissioneDis;
	private String codFasciaEta;
	private String codScuola;
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

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Boolean isAmmissioneDis() {
		return ammissioneDis;
	}

	public void setAmmissioneDis(Boolean ammissioneDis) {
		this.ammissioneDis = ammissioneDis;
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
