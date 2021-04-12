package it.csi.iscritto.iscrittobosrv.dto.allegato;

import java.io.Serializable;
import java.util.Date;

public class Allegato implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idAllegato;
	private byte[] documento;
	private String protocollo;
	private Long idAnagraficaSoggetto;
	private String codTipoAllegato;
	private Date dataInserimento;
	private String nomeFile;
	private String mimeType;

	public Long getIdAllegato() {
		return idAllegato;
	}

	public void setIdAllegato(Long idAllegato) {
		this.idAllegato = idAllegato;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public Long getIdAnagraficaSoggetto() {
		return idAnagraficaSoggetto;
	}

	public void setIdAnagraficaSoggetto(Long idAnagraficaSoggetto) {
		this.idAnagraficaSoggetto = idAnagraficaSoggetto;
	}

	public String getCodTipoAllegato() {
		return codTipoAllegato;
	}

	public void setCodTipoAllegato(String codTipoAllegato) {
		this.codTipoAllegato = codTipoAllegato;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
