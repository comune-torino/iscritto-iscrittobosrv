package it.csi.iscritto.iscrittobosrv.util.builder;

import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.Builder;

import it.csi.iscritto.iscrittobosrv.dto.rest.AppStatus;
import it.csi.iscritto.iscrittobosrv.dto.rest.ErrorDetail;

public final class AppStatusBuilder implements Builder<AppStatus> {
	private AppStatus status;

	private AppStatusBuilder() {
		this.status = new AppStatus();
	}

	public static AppStatusBuilder newInstance() {
		return new AppStatusBuilder();
	}

	public AppStatusBuilder whithStatus(String status) {
		this.status.setStatus(status);
		return this;
	}

	public AppStatusBuilder addError(ErrorDetail error) {
		if (this.status.getErrors() == null) {
			this.status.setErrors(new ArrayList<ErrorDetail>());
		}

		this.status.getErrors().add(error);
		return this;
	}

	@Override
	public AppStatus build() {
		return SerializationUtils.clone(this.status);
	}

}
