package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class InfoVerifiche implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceCondizione;
	private String descrizioneCondizione;
	private String codiceTipoVerifica;
	private Integer occorrenze;

	public String getCodiceCondizione() {
		return codiceCondizione;
	}

	public void setCodiceCondizione(String codiceCondizione) {
		this.codiceCondizione = codiceCondizione;
	}

	public String getDescrizioneCondizione() {
		return descrizioneCondizione;
	}

	public void setDescrizioneCondizione(String descrizioneCondizione) {
		this.descrizioneCondizione = descrizioneCondizione;
	}

	public String getCodiceTipoVerifica() {
		return codiceTipoVerifica;
	}

	public void setCodiceTipoVerifica(String codiceTipoVerifica) {
		this.codiceTipoVerifica = codiceTipoVerifica;
	}

	public Integer getOccorrenze() {
		return occorrenze;
	}

	public void setOccorrenze(Integer occorrenze) {
		this.occorrenze = occorrenze;
	}

}
