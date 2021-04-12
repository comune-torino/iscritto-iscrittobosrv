package it.csi.iscritto.iscrittobosrv.integration.converter;

import it.csi.iscritto.iscrittobosrv.dto.DomandeFilter;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.domanda.CriterioRicerca;

public class DomandeFilterConverter extends AbstractConverter<DomandeFilter, CriterioRicerca> {

	@Override
	public CriterioRicerca convert(DomandeFilter source) {
		CriterioRicerca target = new CriterioRicerca();

		if (source != null) {
			target.setCodiceFiscaleMinore(source.getCodiceFiscaleMinore());
			target.setCodiceFiscaleRichiedente(null); // usato dal FE
			target.setCodiceScuola(source.getCodiceScuola());
			target.setCodiceStatoDomanda(source.getCodiceStatoDomanda());
			target.setCognomeMinore(source.getCognomeMinore());
			target.setNomeMinore(source.getNomeMinore());
			target.setProtocollo(source.getProtocollo());
		}

		return target;
	}

}
