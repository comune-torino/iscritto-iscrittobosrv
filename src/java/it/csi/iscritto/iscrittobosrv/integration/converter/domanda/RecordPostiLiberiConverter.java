package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.RecordPostiLiberi;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class RecordPostiLiberiConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.RecordPostiLiberi, RecordPostiLiberi> {

	@Override
	public RecordPostiLiberi convert(it.csi.iscritto.serviscritto.dto.domanda.RecordPostiLiberi source) {
		RecordPostiLiberi target = null;
		if (source != null) {
			target = new RecordPostiLiberi();

			target.setCodAnagraficaGraduatoria(source.getCodAnagraficaGraduatoria());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setCodScuola(source.getCodScuola());
			target.setDataCreazione(source.getDataCreazione());
			target.setDescFasciaEta(source.getDescFasciaEta());
			target.setDescTipoFrequenza(source.getDescTipoFrequenza());
			target.setIndirizzoScuola(source.getIndirizzoScuola());
			target.setPostiAmmessi(source.getPostiAmmessi());
			target.setPostiLiberi(source.getPostiLiberi());
		}

		return target;
	}

}
