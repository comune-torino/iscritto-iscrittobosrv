package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class RecordPostiLiberi implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codOrdineScuola;
	private String codAnagraficaGraduatoria;
	private Date dataCreazione;
	private String codScuola;
	private String indirizzoScuola;
	private String descFasciaEta;
	private String descTipoFrequenza;
	private String postiLiberi;
	private String postiAmmessi;

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

	public String getCodAnagraficaGraduatoria() {
		return codAnagraficaGraduatoria;
	}

	public void setCodAnagraficaGraduatoria(String codAnagraficaGraduatoria) {
		this.codAnagraficaGraduatoria = codAnagraficaGraduatoria;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getCodScuola() {
		return codScuola;
	}

	public void setCodScuola(String codScuola) {
		this.codScuola = codScuola;
	}

	public String getIndirizzoScuola() {
		return indirizzoScuola;
	}

	public void setIndirizzoScuola(String indirizzoScuola) {
		this.indirizzoScuola = indirizzoScuola;
	}

	public String getDescFasciaEta() {
		return descFasciaEta;
	}

	public void setDescFasciaEta(String descFasciaEta) {
		this.descFasciaEta = descFasciaEta;
	}

	public String getDescTipoFrequenza() {
		return descTipoFrequenza;
	}

	public void setDescTipoFrequenza(String descTipoFrequenza) {
		this.descTipoFrequenza = descTipoFrequenza;
	}

	public String getPostiLiberi() {
		return postiLiberi;
	}

	public void setPostiLiberi(String postiLiberi) {
		this.postiLiberi = postiLiberi;
	}

	public String getPostiAmmessi() {
		return postiAmmessi;
	}

	public void setPostiAmmessi(String postiAmmessi) {
		this.postiAmmessi = postiAmmessi;
	}

}
