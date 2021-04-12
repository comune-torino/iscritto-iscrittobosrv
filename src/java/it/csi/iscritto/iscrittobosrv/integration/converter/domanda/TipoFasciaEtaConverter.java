package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.TipoFasciaEta;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class TipoFasciaEtaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.TipoFasciaEta, TipoFasciaEta> {

	@Override
	public TipoFasciaEta convert(it.csi.iscritto.serviscritto.dto.domanda.TipoFasciaEta source) {
		TipoFasciaEta target = null;
		if (source != null) {
			target = new TipoFasciaEta();

			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setDescFasciaEta(source.getDescFasciaEta());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
		}

		return target;
	}

}
