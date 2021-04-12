package it.csi.iscritto.iscrittobosrv.business.be;

public enum ErrorCodeEnum {
	// errori di sistema non recuperabili
	SYS_001("SYS-001", "errore interno"),

	// controlli formali
	FRM_001("FRM-001", "oggetto nullo"),
	FRM_002("FRM-002", "formato data non valido"),

	VAL_CF_001("VAL-CF-001", "codice fiscale vuoto"),
	VAL_CF_002("VAL-CF-002", "lunghezza codice fiscale non valida"),
	VAL_CF_003("VAL-CF-003", "formato codice fiscale numerico non valido"),
	VAL_CF_004("VAL-CF-004", "formato codice fiscale alfanumerico non valido"),
	VAL_CF_005("VAL-CF-005", "carattere di controllo non valido"),

	UTE_001("UTE-001", "Sessione utente mancante/non valida"),
	UTE_002("UTE-002", "operatore non presente in anagrafica"),

	DOM_001("DOM-001", "Domanda NON trovata per chiave fisica"),
	DOM_FILTER_001("DOM-FILTER_001", "formato del filtro di ricerca domande NON valido"),
	DOM_FILTER_002("DOM-FILTER_002", "filtro di ricerca domande NON presente"),

	SCU_FILTER_001("SCU-FILTER-001", "formato del filtro di scuole scuole NON valido"),
	SCU_FILTER_002("SCU-FILTER_002", "filtro di ricerca scuole NON presente"),
	SCU_FILTER_003("SCU-FILTER-003", "Filtro di ricerca scuole NON valorizzato correttamente: manca il parametro <codiceOrdineScuola>"),

	GRA_FILTER_001("GRA-FILTER_001", "formato del filtro di ricerca graduatorie NON valido"),
	GRA_FILTER_002("GRA-FILTER_002", "filtro di ricerca graduatorie NON presente"),
	;

	private String code;
	private String description;

	private ErrorCodeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
