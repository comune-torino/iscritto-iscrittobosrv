package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.AnagraficaGraduatoria;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class AnagraficaGraduatoriaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.AnagraficaGraduatoria, AnagraficaGraduatoria> {

	@Override
	public AnagraficaGraduatoria convert(it.csi.iscritto.serviscritto.dto.domanda.AnagraficaGraduatoria source) {
		AnagraficaGraduatoria target = null;
		if (source != null) {
			target = new AnagraficaGraduatoria();

			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setDataFineIscrizioni(source.getDataFineIscrizioni());
			target.setDataInizioIscrizioni(source.getDataInizioIscrizioni());
			target.setDataScadenzaGraduatoria(source.getDataScadenzaGraduatoria());
			target.setDataScadenzaIscrizioni(source.getDataScadenzaIscrizioni());
			target.setDataScadenzaRicorsi(source.getDataScadenzaRicorsi());
			target.setIdAnagraficaGraduatoria(source.getIdAnagraficaGraduatoria());
		}
		return target;
	}

	public static final it.csi.iscritto.serviscritto.dto.domanda.AnagraficaGraduatoria toSrv(AnagraficaGraduatoria source) {
		it.csi.iscritto.serviscritto.dto.domanda.AnagraficaGraduatoria target = null;
		if (source != null) {
			target = new it.csi.iscritto.serviscritto.dto.domanda.AnagraficaGraduatoria();

			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setDataFineIscrizioni(source.getDataFineIscrizioni());
			target.setDataInizioIscrizioni(source.getDataInizioIscrizioni());
			target.setDataScadenzaGraduatoria(source.getDataScadenzaGraduatoria());
			target.setDataScadenzaIscrizioni(source.getDataScadenzaIscrizioni());
			target.setDataScadenzaRicorsi(source.getDataScadenzaRicorsi());
			target.setIdAnagraficaGraduatoria(source.getIdAnagraficaGraduatoria());
		}

		return target;
	}

}
