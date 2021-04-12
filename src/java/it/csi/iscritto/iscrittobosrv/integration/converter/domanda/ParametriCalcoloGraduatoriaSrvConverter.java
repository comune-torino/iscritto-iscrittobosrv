package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.domanda.ParametriCalcoloGraduatoria;

public class ParametriCalcoloGraduatoriaSrvConverter extends
		AbstractConverter<it.csi.iscritto.iscrittobosrv.dto.ParametriCalcoloGraduatoria, ParametriCalcoloGraduatoria> {

	@Override
	public ParametriCalcoloGraduatoria convert(it.csi.iscritto.iscrittobosrv.dto.ParametriCalcoloGraduatoria source) {
		ParametriCalcoloGraduatoria target = new ParametriCalcoloGraduatoria();
		if (source != null) {
			target.setCodiceGraduatoria(source.getCodiceGraduatoria());
			target.setCodiceStato(source.getCodiceStatoGraduatoria());
			target.setCodiceStatoDaCalcolare(source.getCodiceStatoDaCalcolare());
			target.setOrdineScuola(source.getCodiceOrdineScuola());
			target.setStep(source.getStepGraduatoria());
		}

		return target;
	}

}
