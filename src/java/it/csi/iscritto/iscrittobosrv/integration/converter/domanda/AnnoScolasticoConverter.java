package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.AnnoScolastico;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class AnnoScolasticoConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.AnnoScolastico, AnnoScolastico> {

	@Override
	public AnnoScolastico convert(it.csi.iscritto.serviscritto.dto.domanda.AnnoScolastico source) {
		AnnoScolastico target = null;
		if (source != null) {
			target = new AnnoScolastico();

			target.setCodiceAnnoScolastico(source.getCodAnnoScolastico());
			target.setDataA(DateUtils.toStringDate(source.getDataA(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setDataDa(DateUtils.toStringDate(source.getDataDa(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setDescrizione(source.getDescrizione());
		}

		return target;
	}

}
