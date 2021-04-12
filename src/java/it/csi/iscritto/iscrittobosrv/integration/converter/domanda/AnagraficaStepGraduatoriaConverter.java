package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.AnagraficaStepGraduatoria;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class AnagraficaStepGraduatoriaConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.AnagraficaStepGraduatoria, AnagraficaStepGraduatoria> {

	@Override
	public AnagraficaStepGraduatoria convert(it.csi.iscritto.serviscritto.dto.domanda.AnagraficaStepGraduatoria source) {
		AnagraficaStepGraduatoria target = null;
		if (source != null) {
			target = new AnagraficaStepGraduatoria();

			target.setDtAllegati(source.getDtAllegati());
			target.setDtDomInvA(source.getDtDomInvA());
			target.setDtDomInvDa(source.getDtDomInvDa());
			target.setDtStepGra(source.getDtStepGra());
			target.setIdStepGra(source.getIdStepGra());
			target.setStep(source.getStep());
			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
		}

		return target;
	}

	public static final it.csi.iscritto.serviscritto.dto.domanda.AnagraficaStepGraduatoria toSrv(AnagraficaStepGraduatoria source) {
		it.csi.iscritto.serviscritto.dto.domanda.AnagraficaStepGraduatoria target = null;
		if (source != null) {
			target = new it.csi.iscritto.serviscritto.dto.domanda.AnagraficaStepGraduatoria();

			target.setDtAllegati(source.getDtAllegati());
			target.setDtDomInvA(source.getDtDomInvA());
			target.setDtDomInvDa(source.getDtDomInvDa());
			target.setDtStepGra(source.getDtStepGra());
			target.setIdStepGra(source.getIdStepGra());
			target.setStep(source.getStep());
			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
		}

		return target;
	}

}
