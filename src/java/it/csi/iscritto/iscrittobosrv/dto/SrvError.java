package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

public class SrvError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String code;
	private String title;
	private String detail;
	private String links;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

}
