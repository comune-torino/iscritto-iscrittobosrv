package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.ParametriGraduatoria;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class ParametriGraduatoriaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.ParametriGraduatoria, ParametriGraduatoria> {

	@Override
	public ParametriGraduatoria convert(it.csi.iscritto.serviscritto.dto.domanda.ParametriGraduatoria source) {
		ParametriGraduatoria target = new ParametriGraduatoria();
		if (source != null) {
			target.setAmmissioni(source.getAmmissioni());
			target.setCodice(source.getCodice());
			target.setCodiceStato(source.getCodiceStato());
			target.setDataUltimoCalcolo(DateUtils.toStringDate(source.getDataUltimoCalcolo(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
			target.setStep(source.getStep() == null ? null : String.valueOf(source.getStep()));
		}

		return target;
	}

}
