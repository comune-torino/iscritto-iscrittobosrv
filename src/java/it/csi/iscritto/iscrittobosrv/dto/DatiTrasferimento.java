package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class DatiTrasferimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private String data;
	private String indirizzoVecchio;
	private String indirizzoNuovo;
	private String indirizzoNido;
	private String dataDal;
	private String dataAl;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIndirizzoVecchio() {
		return indirizzoVecchio;
	}

	public void setIndirizzoVecchio(String indirizzoVecchio) {
		this.indirizzoVecchio = indirizzoVecchio;
	}

	public String getIndirizzoNuovo() {
		return indirizzoNuovo;
	}

	public void setIndirizzoNuovo(String indirizzoNuovo) {
		this.indirizzoNuovo = indirizzoNuovo;
	}

	public String getIndirizzoNido() {
		return indirizzoNido;
	}

	public void setIndirizzoNido(String indirizzoNido) {
		this.indirizzoNido = indirizzoNido;
	}

	public String getDataDal() {
		return dataDal;
	}

	public void setDataDal(String dataDal) {
		this.dataDal = dataDal;
	}

	public String getDataAl() {
		return dataAl;
	}

	public void setDataAl(String dataAl) {
		this.dataAl = dataAl;
	}

}
