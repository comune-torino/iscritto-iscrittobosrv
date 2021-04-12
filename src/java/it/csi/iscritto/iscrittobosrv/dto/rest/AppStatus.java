package it.csi.iscritto.iscrittobosrv.dto.rest;

import java.io.Serializable;
import java.util.List;

public class AppStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;
	private List<ErrorDetail> errors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}

}
