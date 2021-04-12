package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.InfoGenerali;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class InfoGeneraliConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.InfoGenerali, InfoGenerali> {

	@Override
	public InfoGenerali convert(it.csi.iscritto.serviscritto.dto.domanda.InfoGenerali source) {
		InfoGenerali target = null;
		if (source != null) {
			target = new InfoGenerali();

			target.setDataInizioIscrizioniNidi(DateUtils.toStringDate(source.getDataInizioIscrizioniNidi(), DateUtils.ISO_8601_FORMAT));
			target.setDataScadenzaIscrizioniNidi(DateUtils.toStringDate(source.getDataScadenzaIscrizioniNidi(), DateUtils.ISO_8601_FORMAT));
			target.setDataFineIscrizioniNidi(DateUtils.toStringDate(source.getDataFineIscrizioniNidi(), DateUtils.ISO_8601_FORMAT));
		}

		return target;
	}

}
