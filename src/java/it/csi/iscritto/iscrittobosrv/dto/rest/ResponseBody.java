package it.csi.iscritto.iscrittobosrv.dto.rest;

import java.io.Serializable;

public class ResponseBody<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private AppStatus status;
	private T result;

	public ResponseBody(AppStatus status, T result) {
		this.status = status;
		this.result = result;
	}

	public AppStatus getStatus() {
		return status;
	}

	public void setStatus(AppStatus status) {
		this.status = status;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
