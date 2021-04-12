package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import static it.csi.iscritto.iscrittobosrv.util.converter.ConvertUtils.nullToEmpty;

import it.csi.iscritto.iscrittobosrv.dto.VerbaleCommissione;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class VerbaleCommissioneConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.VerbaleCommissione, VerbaleCommissione> {

	@Override
	public VerbaleCommissione convert(it.csi.iscritto.serviscritto.dto.domanda.VerbaleCommissione source) {
		VerbaleCommissione target = null;
		if (source != null) {
			target = new VerbaleCommissione();

			target.setCodiceFiscaleMinore(nullToEmpty(source.getCodiceFiscaleMinore()));
			target.setCognomeMinore(nullToEmpty(source.getCognomeMinore()));
			target.setDataRifiuto(DateUtils.toStringDate(source.getDataRifiuto(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setDataValidazione(DateUtils.toStringDate(source.getDataValidazione(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setEsito(nullToEmpty(source.getEsito()));
			target.setIndirizzoScuolaPrimaScelta(nullToEmpty(source.getIndirizzoScuolaPrimaScelta()));
			target.setNomeMinore(nullToEmpty(source.getNomeMinore()));
			target.setNomeScuolaPrimaScelta(nullToEmpty(source.getNomeScuolaPrimaScelta()));
			target.setProtocollo(nullToEmpty(source.getProtocollo()));
		}

		return target;
	}

}
