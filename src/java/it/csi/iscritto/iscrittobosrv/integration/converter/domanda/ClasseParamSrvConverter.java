package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.ClasseParam;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class ClasseParamSrvConverter extends AbstractConverter<ClasseParam, it.csi.iscritto.serviscritto.dto.domanda.ClasseParam> {

	@Override
	public it.csi.iscritto.serviscritto.dto.domanda.ClasseParam convert(ClasseParam source) {
		it.csi.iscritto.serviscritto.dto.domanda.ClasseParam target = null;
		if (source != null) {
			target = new it.csi.iscritto.serviscritto.dto.domanda.ClasseParam();

			target.setAmmissioneDis(source.isAmmissioneDis());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setCodScuola(source.getCodScuola());
			target.setCodTipoFrequenza(source.getCodTipoFrequenza());
			target.setDenominazione(source.getDenominazione());
			target.setIdClasse(source.getIdClasse());
			target.setPostiAmmessi(source.getPostiAmmessi());
			target.setPostiLiberi(source.getPostiLiberi());
		}

		return target;
	}

}
