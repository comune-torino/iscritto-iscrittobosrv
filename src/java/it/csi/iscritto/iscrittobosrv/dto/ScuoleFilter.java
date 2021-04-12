package it.csi.iscritto.iscrittobosrv.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonValue;

public class ScuoleFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	private CodiceTipologiaScuolaEnum codiceTipologiaScuola;

	public enum CodiceTipologiaScuolaEnum {
		NID("NID"),
		MAT("MAT");

		private String value;

		CodiceTipologiaScuolaEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}
	}

	public CodiceTipologiaScuolaEnum getCodiceTipologiaScuola() {
		return codiceTipologiaScuola;
	}

	public void setCodiceTipologiaScuola(CodiceTipologiaScuolaEnum codiceTipologiaScuola) {
		this.codiceTipologiaScuola = codiceTipologiaScuola;
	}

}
