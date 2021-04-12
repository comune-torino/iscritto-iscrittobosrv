package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.GraduatoriaApprovazione;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class GraduatoriaApprovazioneConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.GraduatoriaApprovazione, GraduatoriaApprovazione> {

	@Override
	public GraduatoriaApprovazione convert(it.csi.iscritto.serviscritto.dto.domanda.GraduatoriaApprovazione source) {
		GraduatoriaApprovazione target = null;
		if (source != null) {
			target = new GraduatoriaApprovazione();

			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setCognome(source.getCognome());
			target.setDataConsegna(source.getDataConsegna());
			target.setDataNascita(source.getDataNascita());
			target.setFlFuoriTermine(source.getFlFuoriTermine());
			target.setIsee(source.getIsee());
			target.setNome(source.getNome());
			target.setOraNascita(source.getOraNascita());
			target.setProtocollo(source.getProtocollo());
			target.setPunteggio(source.getPunteggio());
			target.setPunteggioPrimaScelta(source.getPunteggioPrimaScelta());
			target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
		}

		return target;
	}

}
