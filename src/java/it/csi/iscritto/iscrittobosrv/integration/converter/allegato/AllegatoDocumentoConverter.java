package it.csi.iscritto.iscrittobosrv.integration.converter.allegato;

import it.csi.iscritto.iscrittobosrv.dto.Documento;
import it.csi.iscritto.iscrittobosrv.dto.File;
import it.csi.iscritto.iscrittobosrv.dto.allegato.Allegato;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class AllegatoDocumentoConverter extends AbstractConverter<Allegato, Documento> {

	@Override
	public Documento convert(Allegato source) {
		Documento target = null;
		if (source != null) {
			target = new Documento();

			File file = new File();
			file.setName(source.getNomeFile());
			file.setType(source.getMimeType());

			target.setFile(file);
			target.setEliminato(false);
			target.setId(source.getIdAllegato());
			target.setProtocollo(source.getProtocollo());
			target.setDataInserimento(DateUtils.toStringDate(source.getDataInserimento(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
		}

		return target;
	}

}
