package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.InfoAllegatoSoggetto;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class InfoAllegatoSoggettoConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.InfoAllegatoSoggetto, InfoAllegatoSoggetto> {

	@Override
	public InfoAllegatoSoggetto convert(it.csi.iscritto.serviscritto.dto.domanda.InfoAllegatoSoggetto source) {
		InfoAllegatoSoggetto target = null;
		if (source != null) {
			target = new InfoAllegatoSoggetto();

			target.setCodCondizione(source.getCodCondizione());
			target.setCodiceFiscaleRichiedente(source.getCodiceFiscaleRichiedente());
			target.setCodiceFiscaleSoggetto(source.getCodiceFiscaleSoggetto());
			target.setCodTipoAllegato(source.getCodTipoAllegato());
			target.setCodTipoAllegatoRed(source.getCodTipoAllegatoRed());
			target.setCodTipoSoggetto(source.getCodTipoSoggetto());
			target.setCognome(source.getCognome());
			target.setIdAllegato(source.getIdAllegato());
			target.setIdAnagraficaSoggetto(source.getIdAnagraficaSoggetto());
			target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
			target.setMimeType(source.getMimeType());
			target.setNome(source.getNome());
			target.setNomeFile(source.getNomeFile());
			target.setProtocollo(source.getProtocollo());
			target.setDataInserimento(DateUtils.toStringDate(source.getDataInserimento(), DateUtils.DEFAULT_TIMESTAMP_FORMAT));
		}

		return target;
	}

}
