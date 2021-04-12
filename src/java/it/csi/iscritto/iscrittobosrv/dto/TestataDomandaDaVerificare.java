package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class TestataDomandaDaVerificare implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idDomandaIscrizione;
	private String protocollo;
	private String codStatoDomanda;
	private String nomeMinore;
	private String cognomeMinore;
	private String codiceFiscaleMinore;
	private Boolean paDis;
	private Boolean paPrbSal;

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

	public String getCodStatoDomanda() {
		return codStatoDomanda;
	}

	public void setCodStatoDomanda(String codStatoDomanda) {
		this.codStatoDomanda = codStatoDomanda;
	}

	public String getNomeMinore() {
		return nomeMinore;
	}

	public void setNomeMinore(String nomeMinore) {
		this.nomeMinore = nomeMinore;
	}

	public String getCognomeMinore() {
		return cognomeMinore;
	}

	public void setCognomeMinore(String cognomeMinore) {
		this.cognomeMinore = cognomeMinore;
	}

	public String getCodiceFiscaleMinore() {
		return codiceFiscaleMinore;
	}

	public void setCodiceFiscaleMinore(String codiceFiscaleMinore) {
		this.codiceFiscaleMinore = codiceFiscaleMinore;
	}

	public Boolean isPaDis() {
		return paDis;
	}

	public void setPaDis(Boolean paDis) {
		this.paDis = paDis;
	}

	public Boolean isPaPrbSal() {
		return paPrbSal;
	}

	public void setPaPrbSal(Boolean paPrbSal) {
		this.paPrbSal = paPrbSal;
	}

}
