package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class AnnoScolastico implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceAnnoScolastico;
	private String descrizione;
	private String dataDa;
	private String dataA;

	public String getCodiceAnnoScolastico() {
		return codiceAnnoScolastico;
	}

	public void setCodiceAnnoScolastico(String codiceAnnoScolastico) {
		this.codiceAnnoScolastico = codiceAnnoScolastico;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDataDa() {
		return dataDa;
	}

	public void setDataDa(String dataDa) {
		this.dataDa = dataDa;
	}

	public String getDataA() {
		return dataA;
	}

	public void setDataA(String dataA) {
		this.dataA = dataA;
	}

}
