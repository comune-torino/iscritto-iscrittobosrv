package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.AnagraficaEta;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class AnagraficaEtaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.AnagraficaEta, AnagraficaEta> {

	@Override
	public AnagraficaEta convert(it.csi.iscritto.serviscritto.dto.domanda.AnagraficaEta source) {
		AnagraficaEta target = null;
		if (source != null) {
			target = new AnagraficaEta();

			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setDataA(source.getDataA());
			target.setDataDa(source.getDataDa());
			target.setIdEta(source.getIdEta());
		}

		return target;
	}

	public static final it.csi.iscritto.serviscritto.dto.domanda.AnagraficaEta toSrv(AnagraficaEta source) {
		it.csi.iscritto.serviscritto.dto.domanda.AnagraficaEta target = null;
		if (source != null) {
			target = new it.csi.iscritto.serviscritto.dto.domanda.AnagraficaEta();

			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setDataA(source.getDataA());
			target.setDataDa(source.getDataDa());
			target.setIdEta(source.getIdEta());
		}

		return target;
	}

}
