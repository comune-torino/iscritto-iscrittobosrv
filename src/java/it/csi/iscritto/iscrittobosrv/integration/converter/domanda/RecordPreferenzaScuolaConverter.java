package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.RecordPreferenzaScuola;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class RecordPreferenzaScuolaConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.RecordPreferenzaScuola, RecordPreferenzaScuola> {

	@Override
	public RecordPreferenzaScuola convert(it.csi.iscritto.serviscritto.dto.domanda.RecordPreferenzaScuola source) {
		RecordPreferenzaScuola target = null;
		if (source != null) {
			target = new RecordPreferenzaScuola();

			target.setCodScuola(source.getCodScuola());
			target.setCodStatoScu(source.getCodStatoScu());
			target.setCodTipoFrequenza(source.getCodTipoFrequenza());
			target.setDescScuola(source.getDescScuola());
			target.setDescStatoScu(source.getDescStatoScu());
			target.setDescTipoFre(source.getDescTipoFre());
			target.setDtStato(source.getDtStato());
			target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
			target.setIdGraduatoria(source.getIdGraduatoria());
			target.setIdStatoScu(source.getIdStatoScu());
			target.setIdTipoFrequenza(source.getIdTipoFrequenza());
			target.setIndirizzo(source.getIndirizzo());
			target.setOrdinePreferenza(source.getOrdinePreferenza());
		}

		return target;
	}

}
