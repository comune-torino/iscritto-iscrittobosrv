package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Disoccupato implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dataDichiarazione;
	private String soggettoDichiarazione;
	private DatiCiDisoccupato datiCi;

	public String getDataDichiarazione() {
		return dataDichiarazione;
	}

	public void setDataDichiarazione(String dataDichiarazione) {
		this.dataDichiarazione = dataDichiarazione;
	}

	public String getSoggettoDichiarazione() {
		return soggettoDichiarazione;
	}

	public void setSoggettoDichiarazione(String soggettoDichiarazione) {
		this.soggettoDichiarazione = soggettoDichiarazione;
	}

	public DatiCiDisoccupato getDatiCi() {
		return datiCi;
	}

	public void setDatiCi(DatiCiDisoccupato datiCi) {
		this.datiCi = datiCi;
	}

}
