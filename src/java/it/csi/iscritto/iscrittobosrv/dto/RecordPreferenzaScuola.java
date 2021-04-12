package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class RecordPreferenzaScuola implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idGraduatoria;
	private Long idDomandaIscrizione;
	private Integer ordinePreferenza;
	private String codScuola;
	private String descScuola;
	private String indirizzo;
	private Long idTipoFrequenza;
	private String codTipoFrequenza;
	private String descTipoFre;
	private Long idStatoScu;
	private String codStatoScu;
	private String descStatoScu;
	private Date dtStato;

	public Long getIdGraduatoria() {
		return idGraduatoria;
	}

	public void setIdGraduatoria(Long idGraduatoria) {
		this.idGraduatoria = idGraduatoria;
	}

	public Long getIdDomandaIscrizione() {
		return idDomandaIscrizione;
	}

	public void setIdDomandaIscrizione(Long idDomandaIscrizione) {
		this.idDomandaIscrizione = idDomandaIscrizione;
	}

	public Integer getOrdinePreferenza() {
		return ordinePreferenza;
	}

	public void setOrdinePreferenza(Integer ordinePreferenza) {
		this.ordinePreferenza = ordinePreferenza;
	}

	public String getCodScuola() {
		return codScuola;
	}

	public void setCodScuola(String codScuola) {
		this.codScuola = codScuola;
	}

	public String getDescScuola() {
		return descScuola;
	}

	public void setDescScuola(String descScuola) {
		this.descScuola = descScuola;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Long getIdTipoFrequenza() {
		return idTipoFrequenza;
	}

	public void setIdTipoFrequenza(Long idTipoFrequenza) {
		this.idTipoFrequenza = idTipoFrequenza;
	}

	public String getCodTipoFrequenza() {
		return codTipoFrequenza;
	}

	public void setCodTipoFrequenza(String codTipoFrequenza) {
		this.codTipoFrequenza = codTipoFrequenza;
	}

	public String getDescTipoFre() {
		return descTipoFre;
	}

	public void setDescTipoFre(String descTipoFre) {
		this.descTipoFre = descTipoFre;
	}

	public Long getIdStatoScu() {
		return idStatoScu;
	}

	public void setIdStatoScu(Long idStatoScu) {
		this.idStatoScu = idStatoScu;
	}

	public String getCodStatoScu() {
		return codStatoScu;
	}

	public void setCodStatoScu(String codStatoScu) {
		this.codStatoScu = codStatoScu;
	}

	public String getDescStatoScu() {
		return descStatoScu;
	}

	public void setDescStatoScu(String descStatoScu) {
		this.descStatoScu = descStatoScu;
	}

	public Date getDtStato() {
		return dtStato;
	}

	public void setDtStato(Date dtStato) {
		this.dtStato = dtStato;
	}

}
