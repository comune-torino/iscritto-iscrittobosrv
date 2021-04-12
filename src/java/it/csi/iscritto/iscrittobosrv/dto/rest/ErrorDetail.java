package it.csi.iscritto.iscrittobosrv.dto.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ErrorDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private String title;
	private Map<String, String> detail;
	private List<String> links;

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

	public Map<String, String> getDetail() {
		return detail;
	}

	public void setDetail(Map<String, String> detail) {
		this.detail = detail;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

}
