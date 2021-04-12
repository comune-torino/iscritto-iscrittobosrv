package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;
import java.util.Date;

public class AnagraficaGraduatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idAnagraficaGraduatoria;
	private String codAnagraficaGraduatoria;
	private String codAnnoScolastico;
	private String codOrdineScuola;
	private Date dataInizioIscrizioni;
	private Date dataScadenzaIscrizioni;
	private Date dataFineIscrizioni;
	private Date dataScadenzaGraduatoria;
	private Date dataScadenzaRicorsi;

	public Long getIdAnagraficaGraduatoria() {
		return idAnagraficaGraduatoria;
	}

	public void setIdAnagraficaGraduatoria(Long idAnagraficaGraduatoria) {
		this.idAnagraficaGraduatoria = idAnagraficaGraduatoria;
	}

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

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

	public Date getDataInizioIscrizioni() {
		return dataInizioIscrizioni;
	}

	public void setDataInizioIscrizioni(Date dataInizioIscrizioni) {
		this.dataInizioIscrizioni = dataInizioIscrizioni;
	}

	public Date getDataScadenzaIscrizioni() {
		return dataScadenzaIscrizioni;
	}

	public void setDataScadenzaIscrizioni(Date dataScadenzaIscrizioni) {
		this.dataScadenzaIscrizioni = dataScadenzaIscrizioni;
	}

	public Date getDataFineIscrizioni() {
		return dataFineIscrizioni;
	}

	public void setDataFineIscrizioni(Date dataFineIscrizioni) {
		this.dataFineIscrizioni = dataFineIscrizioni;
	}

	public Date getDataScadenzaGraduatoria() {
		return dataScadenzaGraduatoria;
	}

	public void setDataScadenzaGraduatoria(Date dataScadenzaGraduatoria) {
		this.dataScadenzaGraduatoria = dataScadenzaGraduatoria;
	}

	public Date getDataScadenzaRicorsi() {
		return dataScadenzaRicorsi;
	}

	public void setDataScadenzaRicorsi(Date dataScadenzaRicorsi) {
		this.dataScadenzaRicorsi = dataScadenzaRicorsi;
	}

}
