package it.csi.iscritto.iscrittobosrv.integration.converter.allegato;

import it.csi.iscritto.iscrittobosrv.dto.allegato.RicevutaAllegato;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class RicevutaAllegatoConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.RicevutaAllegato, RicevutaAllegato> {

	@Override
	public RicevutaAllegato convert(it.csi.iscritto.serviscritto.dto.domanda.RicevutaAllegato source) {
		RicevutaAllegato target = null;
		if (source != null) {
			target = new RicevutaAllegato();

			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setCognomeMinore(source.getCognomeMinore());
			target.setDataOperazione(source.getDataOperazione());
			target.setDescTipoAllegato(source.getDescTipoAllegato());
			target.setNomeFile(source.getNomeFile());
			target.setNomeMinore(source.getNomeMinore());
			target.setProtocolloAllegato(source.getProtocolloAllegato());
			target.setProtocolloDomanda(source.getProtocolloDomanda());
		}

		return target;
	}

}
