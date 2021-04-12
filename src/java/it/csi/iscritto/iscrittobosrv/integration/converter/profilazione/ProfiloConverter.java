package it.csi.iscritto.iscrittobosrv.integration.converter.profilazione;

import it.csi.iscritto.iscrittobosrv.dto.Profilo;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class ProfiloConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.profilazione.Profilo, Profilo> {

	@Override
	public Profilo convert(it.csi.iscritto.serviscritto.dto.profilazione.Profilo source) {
		Profilo target = null;
		if (source != null) {
			target = new Profilo();

			target.setCodice(source.getCodProfilo());
			target.setDescrizione(source.getDescProfilo());
		}

		return target;
	}

}
