package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Scuola implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codCircoscrizione;
	private String codScuola;
	private String descrizione;
	private String indirizzo;
	private String codTipoFrequenza;
	private String codCategoriaScuola;
	private String codStatoScuola;
	private Integer punteggio;
	private Integer posizione;
	private Boolean fuoriTermine;
	private Boolean rinuncia;
	private Boolean ammissibile;
	private NidoContiguo contiguo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodCircoscrizione() {
		return codCircoscrizione;
	}

	public void setCodCircoscrizione(String codCircoscrizione) {
		this.codCircoscrizione = codCircoscrizione;
	}

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

	public String getCodTipoFrequenza() {
		return codTipoFrequenza;
	}

	public void setCodTipoFrequenza(String codTipoFrequenza) {
		this.codTipoFrequenza = codTipoFrequenza;
	}

	public String getCodCategoriaScuola() {
		return codCategoriaScuola;
	}

	public void setCodCategoriaScuola(String codCategoriaScuola) {
		this.codCategoriaScuola = codCategoriaScuola;
	}

	public String getCodStatoScuola() {
		return codStatoScuola;
	}

	public void setCodStatoScuola(String codStatoScuola) {
		this.codStatoScuola = codStatoScuola;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

	public Integer getPosizione() {
		return posizione;
	}

	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}

	public Boolean isFuoriTermine() {
		return fuoriTermine;
	}

	public void setFuoriTermine(Boolean fuoriTermine) {
		this.fuoriTermine = fuoriTermine;
	}

	public Boolean isRinuncia() {
		return rinuncia;
	}

	public void setRinuncia(Boolean rinuncia) {
		this.rinuncia = rinuncia;
	}

	public Boolean isAmmissibile() {
		return ammissibile;
	}

	public void setAmmissibile(Boolean ammissibile) {
		this.ammissibile = ammissibile;
	}

	public NidoContiguo getContiguo() {
		return contiguo;
	}

	public void setContiguo(NidoContiguo contiguo) {
		this.contiguo = contiguo;
	}

}
