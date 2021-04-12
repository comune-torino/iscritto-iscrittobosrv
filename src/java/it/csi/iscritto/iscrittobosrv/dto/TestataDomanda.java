package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class TestataDomanda implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idDomandaIscrizione;
	private Long idSoggettoMinore;
	private String protocolloDomandaIscrizione;
	private String codiceFiscaleMinore;
	private String cognomeMinore;
	private String nomeMinore;
	private String statoDomandaCodice;
	private String statoDomandaDescrizione;
	private String dataUltimaModifica;
	private String cognomeUtenteUltimaModifica;
	private String nomeUtenteUltimaModifica;

	public Long getIdDomandaIscrizione() {
		return idDomandaIscrizione;
	}

	public void setIdDomandaIscrizione(Long idDomandaIscrizione) {
		this.idDomandaIscrizione = idDomandaIscrizione;
	}

	public Long getIdSoggettoMinore() {
		return idSoggettoMinore;
	}

	public void setIdSoggettoMinore(Long idSoggettoMinore) {
		this.idSoggettoMinore = idSoggettoMinore;
	}

	public String getProtocolloDomandaIscrizione() {
		return protocolloDomandaIscrizione;
	}

	public void setProtocolloDomandaIscrizione(String protocolloDomandaIscrizione) {
		this.protocolloDomandaIscrizione = protocolloDomandaIscrizione;
	}

	public String getCodiceFiscaleMinore() {
		return codiceFiscaleMinore;
	}

	public void setCodiceFiscaleMinore(String codiceFiscaleMinore) {
		this.codiceFiscaleMinore = codiceFiscaleMinore;
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

	public String getStatoDomandaDescrizione() {
		return statoDomandaDescrizione;
	}

	public void setStatoDomandaDescrizione(String statoDomandaDescrizione) {
		this.statoDomandaDescrizione = statoDomandaDescrizione;
	}

	public String getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(String dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public String getCognomeUtenteUltimaModifica() {
		return cognomeUtenteUltimaModifica;
	}

	public void setCognomeUtenteUltimaModifica(String cognomeUtenteUltimaModifica) {
		this.cognomeUtenteUltimaModifica = cognomeUtenteUltimaModifica;
	}

	public String getNomeUtenteUltimaModifica() {
		return nomeUtenteUltimaModifica;
	}

	public void setNomeUtenteUltimaModifica(String nomeUtenteUltimaModifica) {
		this.nomeUtenteUltimaModifica = nomeUtenteUltimaModifica;
	}

}
