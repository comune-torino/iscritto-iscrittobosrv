package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.Graduatoria;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class GraduatoriaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.Graduatoria, Graduatoria> {

	@Override
	public Graduatoria convert(it.csi.iscritto.serviscritto.dto.domanda.Graduatoria source) {
		Graduatoria target = new Graduatoria();
		if (source != null) {
			target.setCodice(source.getCodice());
			target.setDescrizione(String.format("%s-%s", source.getCodice(), source.getCodOrdineScuola()));
		}

		return target;
	}

}
