package it.csi.iscritto.iscrittobosrv.integration.converter.profilazione;

import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.profilazione.CallerInfoProfilazione;

public class CallerInfoProfilazioneConverter extends AbstractConverter<CallerInfo, CallerInfoProfilazione> {

	@Override
	public CallerInfoProfilazione convert(CallerInfo source) {
		CallerInfoProfilazione target = null;
		if (source != null) {
			target = new CallerInfoProfilazione();

			target.setIdApp(source.getIdApp());
			target.setIpAddress(source.getIpAddress());
			target.setKeyOper(source.getKeyOper());
			target.setOggOper(source.getOggOper());
			target.setOperazione(source.getOperazione());
			target.setUtente(source.getUtente());
		}

		return target;
	}

}
