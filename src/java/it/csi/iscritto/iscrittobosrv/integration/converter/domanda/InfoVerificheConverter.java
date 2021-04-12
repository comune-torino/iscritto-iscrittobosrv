package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.InfoVerifiche;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class InfoVerificheConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.InfoVerifiche, InfoVerifiche> {

	@Override
	public InfoVerifiche convert(it.csi.iscritto.serviscritto.dto.domanda.InfoVerifiche source) {
		InfoVerifiche target = null;
		if (source != null) {
			target = new InfoVerifiche();

			target.setCodiceCondizione(source.getCodCondizione());
			target.setCodiceTipoVerifica(source.getCodTipoVerifica());
			target.setDescrizioneCondizione(source.getDescCondizione());
			target.setOccorrenze(source.getOccorrenze());
		}

		return target;
	}

}
