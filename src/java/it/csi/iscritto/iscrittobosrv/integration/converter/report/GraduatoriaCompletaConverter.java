package it.csi.iscritto.iscrittobosrv.integration.converter.report;

import it.csi.iscritto.iscrittobosrv.dto.GraduatoriaCompleta;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class GraduatoriaCompletaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.GraduatoriaCompleta, GraduatoriaCompleta> {

	@Override
	public GraduatoriaCompleta convert(it.csi.iscritto.serviscritto.dto.domanda.GraduatoriaCompleta source) {
		GraduatoriaCompleta target = null;
		if (source != null) {
			target = new GraduatoriaCompleta();

			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setCodiceFiscale(source.getCodiceFiscale());
			target.setCognome(source.getCognome());
			target.setIsee(source.getIsee());
			target.setNome(source.getNome());
			target.setProtocollo(source.getProtocollo());
			target.setPunteggio(source.getPunteggio());
			target.setDataConsegna(source.getDataConsegna());
			target.setDataNascita(source.getDataNascita());
			target.setOraNascita(source.getOraNascita());

			target.setPref1(source.getPref1());
			target.setPref2(source.getPref2());
			target.setPref3(source.getPref3());
			target.setPref4(source.getPref4());
			target.setPref5(source.getPref5());
			target.setPref6(source.getPref6());
			target.setPref7(source.getPref7());
			target.setPref8(source.getPref8());
			target.setPref9(source.getPref9());
			target.setPref10(source.getPref10());
		}

		return target;
	}

}
