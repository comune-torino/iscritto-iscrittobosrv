package it.csi.iscritto.iscrittobosrv.integration.converter.allegato;

import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.domanda.Allegato;

public class AllegatoSrvConverter extends AbstractConverter<it.csi.iscritto.iscrittobosrv.dto.allegato.Allegato, Allegato> {

	@Override
	public Allegato convert(it.csi.iscritto.iscrittobosrv.dto.allegato.Allegato source) {
		Allegato target = null;
		if (source != null) {
			target = new Allegato();

			target.setDataInserimento(source.getDataInserimento());
			target.setDocumento(source.getDocumento());
			target.setIdAllegato(source.getIdAllegato());
			target.setIdAnagraficaSoggetto(source.getIdAnagraficaSoggetto());
			target.setCodTipoAllegato(source.getCodTipoAllegato());
			target.setMimeType(source.getMimeType());
			target.setNomeFile(source.getNomeFile());
			target.setProtocollo(source.getProtocollo());
		}

		return target;
	}

}
