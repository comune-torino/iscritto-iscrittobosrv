package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.InfoStepGraduatoria;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class InfoStepGraduatoriaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.InfoStepGraduatoria, InfoStepGraduatoria> {

	@Override
	public InfoStepGraduatoria convert(it.csi.iscritto.serviscritto.dto.domanda.InfoStepGraduatoria source) {
		InfoStepGraduatoria target = null;
		if (source != null) {
			target = new InfoStepGraduatoria();

			target.setCodAnagraficaGra(source.getCodAnagraficaGra());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodStatoGra(source.getCodStatoGra());
			target.setDtStepCon(source.getDtStepCon());
			target.setDtStepGra(source.getDtStepGra());
			target.setFlAmmissioni(source.getFlAmmissioni());
			target.setIdAnagraficaGra(source.getIdAnagraficaGra());
			target.setIdStepGra(source.getIdStepGra());
			target.setIdStepGraCon(source.getIdStepGraCon());
			target.setStep(source.getStep());
		}

		return target;
	}

}
