package it.csi.iscritto.iscrittobosrv.dto.allegato;

import java.io.Serializable;
import java.util.Date;

public class RicevutaAllegato implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codOrdineScuola;
	private String codAnnoScolastico;
	private String protocolloDomanda;
	private String cognomeMinore;
	private String nomeMinore;
	private String descTipoAllegato;
	private String protocolloAllegato;
	private String nomeFile;
	private Date dataOperazione;

	public String getCodOrdineScuola() {
		return codOrdineScuola;
	}

	public void setCodOrdineScuola(String codOrdineScuola) {
		this.codOrdineScuola = codOrdineScuola;
	}

	public String getCodAnnoScolastico() {
		return codAnnoScolastico;
	}

	public void setCodAnnoScolastico(String codAnnoScolastico) {
		this.codAnnoScolastico = codAnnoScolastico;
	}

	public String getProtocolloDomanda() {
		return protocolloDomanda;
	}

	public void setProtocolloDomanda(String protocolloDomanda) {
		this.protocolloDomanda = protocolloDomanda;
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

	public String getDescTipoAllegato() {
		return descTipoAllegato;
	}

	public void setDescTipoAllegato(String descTipoAllegato) {
		this.descTipoAllegato = descTipoAllegato;
	}

	public String getProtocolloAllegato() {
		return protocolloAllegato;
	}

	public void setProtocolloAllegato(String protocolloAllegato) {
		this.protocolloAllegato = protocolloAllegato;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Date getDataOperazione() {
		return dataOperazione;
	}

	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}

}
