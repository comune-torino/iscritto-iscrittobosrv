package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class ResidenzaFamiglia implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codNazione;
	private String descNazione;
	private String codRegione;
	private String descRegione;
	private String codProvincia;
	private String descProvincia;
	private String codComune;
	private String descComune;
	private Integer cap;
	private String indirizzo;
	private Integer idCircoscrizione;
	private String descCircoscrizione;

	public String getCodNazione() {
		return codNazione;
	}

	public void setCodNazione(String codNazione) {
		this.codNazione = codNazione;
	}

	public String getDescNazione() {
		return descNazione;
	}

	public void setDescNazione(String descNazione) {
		this.descNazione = descNazione;
	}

	public String getCodRegione() {
		return codRegione;
	}

	public void setCodRegione(String codRegione) {
		this.codRegione = codRegione;
	}

	public String getDescRegione() {
		return descRegione;
	}

	public void setDescRegione(String descRegione) {
		this.descRegione = descRegione;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getDescProvincia() {
		return descProvincia;
	}

	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	public String getCodComune() {
		return codComune;
	}

	public void setCodComune(String codComune) {
		this.codComune = codComune;
	}

	public String getDescComune() {
		return descComune;
	}

	public void setDescComune(String descComune) {
		this.descComune = descComune;
	}

	public Integer getCap() {
		return cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Integer getIdCircoscrizione() {
		return idCircoscrizione;
	}

	public void setIdCircoscrizione(Integer idCircoscrizione) {
		this.idCircoscrizione = idCircoscrizione;
	}

	public String getDescCircoscrizione() {
		return descCircoscrizione;
	}

	public void setDescCircoscrizione(String descCircoscrizione) {
		this.descCircoscrizione = descCircoscrizione;
	}

}
