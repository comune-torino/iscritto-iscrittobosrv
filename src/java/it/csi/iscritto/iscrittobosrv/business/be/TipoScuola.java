package it.csi.iscritto.iscrittobosrv.business.be;

import org.apache.commons.lang3.StringUtils;

public enum TipoScuola {
	NID("NID", "Nido"),
	MAT("MAT", "Scuola dell'infanzia");

	private String cod;
	private String desc;

	private TipoScuola(String cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public static TipoScuola findByCod(String cod) {
		if (StringUtils.isNotBlank(cod)) {
			for (TipoScuola e : values()) {
				if (cod.equalsIgnoreCase(e.getCod())) {
					return e;
				}
			}
		}

		return null;
	}

	public String getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

}
