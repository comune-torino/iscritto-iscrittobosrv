package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class TestataDomandaIstruttoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idDomandaIscrizione;
	private String protocolloDomandaIscrizione;
	private String cognomeMinore;
	private String nomeMinore;
	private String statoDomandaCodice;
	private String dataConsegna;
	private String dataUltimaModifica;
	private String indirizzo;
	private Integer ricorrenza;
	private String codStatoValidazione;

	public Long getIdDomandaIscrizione() {
		return idDomandaIscrizione;
	}

	public void setIdDomandaIscrizione(Long idDomandaIscrizione) {
		this.idDomandaIscrizione = idDomandaIscrizione;
	}

	public String getProtocolloDomandaIscrizione() {
		return protocolloDomandaIscrizione;
	}

	public void setProtocolloDomandaIscrizione(String protocolloDomandaIscrizione) {
		this.protocolloDomandaIscrizione = protocolloDomandaIscrizione;
	}

	public String getCognomeMinore() {
		return cognomeMinore;
	}

	public void setCognomeMinore(String cognomeMinore) {
		this.cognomeMinore = cognomeMinore;
	}

	public String getNomeMinore() {
		return nomeMinore;
	}

	public void setNomeMinore(String nomeMinore) {
		this.nomeMinore = nomeMinore;
	}

	public String getStatoDomandaCodice() {
		return statoDomandaCodice;
	}

	public void setStatoDomandaCodice(String statoDomandaCodice) {
		this.statoDomandaCodice = statoDomandaCodice;
	}

	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(String dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Integer getRicorrenza() {
		return ricorrenza;
	}

	public void setRicorrenza(Integer ricorrenza) {
		this.ricorrenza = ricorrenza;
	}

	public String getCodStatoValidazione() {
		return codStatoValidazione;
	}

	public void setCodStatoValidazione(String codStatoValidazione) {
		this.codStatoValidazione = codStatoValidazione;
	}

}
