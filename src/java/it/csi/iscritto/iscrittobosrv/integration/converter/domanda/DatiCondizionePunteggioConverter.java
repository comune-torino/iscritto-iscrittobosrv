package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import org.apache.commons.lang3.StringUtils;

import it.csi.iscritto.iscrittobosrv.dto.DatiCondizionePunteggio;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class DatiCondizionePunteggioConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.DatiCondizionePunteggio, DatiCondizionePunteggio> {

	@Override
	public DatiCondizionePunteggio convert(it.csi.iscritto.serviscritto.dto.domanda.DatiCondizionePunteggio source) {
		DatiCondizionePunteggio target = null;
		if (source != null) {
			target = new DatiCondizionePunteggio();

			target.setCognomeOperatore(source.getCognomeOperatore());
			target.setDataFineValidita(DateUtils.toStringDate(source.getDataFineValidita(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setDataInizioValidita(DateUtils.toStringDate(source.getDataInizioValidita(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setNomeOperatore(source.getNomeOperatore());
			target.setNote(source.getNote());
			target.setRicorrenza(source.getRicorrenza());
			target.setValidata(convertValidataFromSrv(source.getValidata()));
			target.setIdDomandaIscrizione(source.getIdDomanda());
			target.setCodCondizionePunteggio(source.getCodCondizionePunteggio());

			// in lettura non viene utilizzato
			target.setTipoValidazione(null);
		}

		return target;
	}

	public static final it.csi.iscritto.serviscritto.dto.domanda.DatiCondizionePunteggio buildSrv(
			Long idDomanda, String condizionePunteggio, String cfOperatore, DatiCondizionePunteggio source) {

		it.csi.iscritto.serviscritto.dto.domanda.DatiCondizionePunteggio dto = null;
		if (source != null) {
			dto = new it.csi.iscritto.serviscritto.dto.domanda.DatiCondizionePunteggio();

			dto.setIdDomanda(idDomanda);
			dto.setCodCondizionePunteggio(condizionePunteggio);
			dto.setCodiceFiscaleOperatore(cfOperatore);
			dto.setValidata(convertValidataToSrv(source.getValidata()));
			dto.setNote(source.getNote());
			dto.setRicorrenza(source.getRicorrenza());
			dto.setTipoValidazione(source.getTipoValidazione());
		}

		return dto;
	}

	public static final Boolean convertValidataToSrv(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}

		Boolean result;
		switch (value) {
			case "VAL":
				result = true;
				break;
			case "RIF":
				result = false;
				break;
			default:
				result = null;
				break;
		}

		return result;
	}

	public static final String convertValidataFromSrv(Boolean value) {
		if (value == null) {
			return null;
		}

		return value ? "VAL" : "RIF";
	}

}
