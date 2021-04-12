package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String protocollo;
	private String dataInserimento;
	private Boolean eliminato;
	private File file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public String getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Boolean isEliminato() {
		return eliminato;
	}

	public void setEliminato(Boolean eliminato) {
		this.eliminato = eliminato;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
