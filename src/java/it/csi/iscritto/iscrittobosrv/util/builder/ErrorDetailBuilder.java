package it.csi.iscritto.iscrittobosrv.util.builder;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.Builder;

import it.csi.iscritto.iscrittobosrv.dto.rest.ErrorDetail;

public final class ErrorDetailBuilder implements Builder<ErrorDetail> {
	private ErrorDetail errorDetail;

	private ErrorDetailBuilder() {
		this.errorDetail = new ErrorDetail();
	}

	public static ErrorDetailBuilder newInstance() {
		return new ErrorDetailBuilder();
	}

	public ErrorDetailBuilder whithCode(String code) {
		this.errorDetail.setCode(code);
		return this;
	}

	public ErrorDetailBuilder whithTitle(String title) {
		this.errorDetail.setTitle(title);
		return this;
	}

	public ErrorDetailBuilder addDetail(String key, String value) {
		if (this.errorDetail.getDetail() == null) {
			this.errorDetail.setDetail(new LinkedHashMap<String, String>());
		}

		this.errorDetail.getDetail().put(key, value);
		return this;
	}

	public ErrorDetailBuilder addLink(String link) {
		if (this.errorDetail.getLinks() == null) {
			this.errorDetail.setLinks(new ArrayList<String>());
		}

		this.errorDetail.getLinks().add(link);
		return this;
	}

	@Override
	public ErrorDetail build() {
		return SerializationUtils.clone(this.errorDetail);
	}

}
