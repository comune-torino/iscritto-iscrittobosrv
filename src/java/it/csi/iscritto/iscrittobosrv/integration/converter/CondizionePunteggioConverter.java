package it.csi.iscritto.iscrittobosrv.integration.converter;

import it.csi.iscritto.iscrittobosrv.dto.CondizionePunteggio;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class CondizionePunteggioConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.profilazione.CondizionePunteggio, CondizionePunteggio> {

	@Override
	public CondizionePunteggio convert(it.csi.iscritto.serviscritto.dto.profilazione.CondizionePunteggio source) {
		CondizionePunteggio target = null;
		if (source != null) {
			target = new CondizionePunteggio();

			CondizionePunteggio.TipoIstruttoriaEnum tipoIstruttoria = null;
			for (CondizionePunteggio.TipoIstruttoriaEnum e : CondizionePunteggio.TipoIstruttoriaEnum.values()) {
				if (e.toString().equals(source.getIstruttoria())) {
					tipoIstruttoria = e;
					break;
				}
			}

			target.setCodice(source.getCodCondizione());
			target.setCodTipoIstruttoria(source.getCodTipoIstruttoria());
			target.setCodTipoAllegato(source.getCodTipoAllegato());
			target.setCognomeUtente(null);
			target.setDataInizioValidita(null);
			target.setDescrizione(source.getDescrizione());
			target.setNote(null);
			target.setRicorrenza(null);
			target.setTipoIstruttoria(tipoIstruttoria);
			target.setValidata(null);
		}

		return target;
	}

}
