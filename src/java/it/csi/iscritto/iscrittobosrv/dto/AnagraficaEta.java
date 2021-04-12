package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class AnagraficaEta implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codAnagraficaGraduatoria;
	private String codAnnoScolastico;
	private String codFasciaEta;
	private String codOrdineScuola;
	private Date dataA;
	private Date dataDa;
	private Long idEta;

	public String getCodAnagraficaGraduatoria() {
		return codAnagraficaGraduatoria;
	}

	public void setCodAnagraficaGraduatoria(String codAnagraficaGraduatoria) {
		this.codAnagraficaGraduatoria = codAnagraficaGraduatoria;
	}

	public String getCodAnnoScolastico() {
		return codAnnoScolastico;
	}

	public void setCodAnnoScolastico(String codAnnoScolastico) {
		this.codAnnoScolastico = codAnnoScolastico;
	}

	public String getCodFasciaEta() {
		return codFasciaEta;
	}

	public void setCodFasciaEta(String codFasciaEta) {
		this.codFasciaEta = codFasciaEta;
	}

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

	public Date getDataA() {
		return dataA;
	}

	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}

	public Date getDataDa() {
		return dataDa;
	}

	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}

	public Long getIdEta() {
		return idEta;
	}

	public void setIdEta(Long idEta) {
		this.idEta = idEta;
	}

}
