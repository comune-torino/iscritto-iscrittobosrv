package it.csi.iscritto.iscrittobosrv.integration.converter;

import it.csi.iscritto.iscrittobosrv.dto.TestataDomandaIstruttoria;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.DatiCondizionePunteggioConverter;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class TestataDomandaIstruttoriaConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.TestataDomandaIstruttoria, TestataDomandaIstruttoria> {

	@Override
	public TestataDomandaIstruttoria convert(it.csi.iscritto.serviscritto.dto.domanda.TestataDomandaIstruttoria source) {
		TestataDomandaIstruttoria target = new TestataDomandaIstruttoria();
		if (source != null) {
			target.setCognomeMinore(source.getCognomeMinore());
			target.setDataConsegna(source.getDataConsegna());
			target.setDataUltimaModifica(source.getDataUltimaModifica());
			target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
			target.setIndirizzo(source.getIndirizzo());
			target.setNomeMinore(source.getNomeMinore());
			target.setProtocolloDomandaIscrizione(source.getProtocollo());
			target.setRicorrenza(source.getRicorrenza());
			target.setStatoDomandaCodice(source.getStatoDomandaCodice());
			target.setCodStatoValidazione(DatiCondizionePunteggioConverter.convertValidataFromSrv(source.getValida()));
		}

		return target;
	}

}
