package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.StepGraduatoria;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.iscrittobosrv.util.converter.ConvertUtils;

public class StepGraduatoriaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.StepGraduatoria, StepGraduatoria> {

	@Override
	public StepGraduatoria convert(it.csi.iscritto.serviscritto.dto.domanda.StepGraduatoria source) {
		StepGraduatoria target = new StepGraduatoria();
		if (source != null) {
			target.setCodice(ConvertUtils.toStringValue(source.getStep()));
			target.setDescrizione(ConvertUtils.toStringValue(source.getStep()));
		}

		return target;
	}

}
