package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class GraduatoriaApprovazione implements Serializable {
	private static final long serialVersionUID = 1L;

	private String protocollo;
	private Date dataConsegna;
	private String codFasciaEta;
	private String cognome;
	private String nome;
	private Date dataNascita;
	private String oraNascita;
	private Double isee;
	private Integer punteggioPrimaScelta;
	private Integer punteggio;
	private String flFuoriTermine;
	private Long idDomandaIscrizione;

	public Long getIdDomandaIscrizione() {
		return idDomandaIscrizione;
	}

	public void setIdDomandaIscrizione(Long idDomandaIscrizione) {
		this.idDomandaIscrizione = idDomandaIscrizione;
	}



	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getCodFasciaEta() {
		return codFasciaEta;
	}

	public void setCodFasciaEta(String codFasciaEta) {
		this.codFasciaEta = codFasciaEta;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getOraNascita() {
		return oraNascita;
	}

	public void setOraNascita(String oraNascita) {
		this.oraNascita = oraNascita;
	}

	public Double getIsee() {
		return isee;
	}

	public void setIsee(Double isee) {
		this.isee = isee;
	}

	public Integer getPunteggioPrimaScelta() {
		return punteggioPrimaScelta;
	}

	public void setPunteggioPrimaScelta(Integer punteggioPrimaScelta) {
		this.punteggioPrimaScelta = punteggioPrimaScelta;
	}

	public Integer getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}

	public String getFlFuoriTermine() {
		return flFuoriTermine;
	}

	public void setFlFuoriTermine(String flFuoriTermine) {
		this.flFuoriTermine = flFuoriTermine;
	}

}
