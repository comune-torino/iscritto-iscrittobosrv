package it.csi.iscritto.iscrittobosrv.integration.converter;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;

import java.text.ParseException;

import org.apache.log4j.Logger;

import it.csi.iscritto.iscrittobosrv.dto.DomandeFilter;
import it.csi.iscritto.iscrittobosrv.dto.DomandeFilter.StatoCondizionePunteggioEnum;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.domanda.CriterioRicercaIstruttoria;

public class DomandeIstruttoriaFilterConverter extends AbstractConverter<DomandeFilter, CriterioRicercaIstruttoria> {
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Override
	public CriterioRicercaIstruttoria convert(DomandeFilter source) {
		final String methodName = "convert";

		CriterioRicercaIstruttoria target = new CriterioRicercaIstruttoria();
		if (source != null) {
			target.setCodiceCondizionePunteggio(source.getCodiceCondizionePunteggio());
			target.setCognomeMinore(source.getCognomeMinore());
			target.setNomeMinore(source.getNomeMinore());

			StatoCondizionePunteggioEnum condizionePunteggio = source.getStatoCondizionePunteggio();
			if (condizionePunteggio != null) {
				target.setStatoCondizionePunteggio(condizionePunteggio.toString());
			}

			try {
				target.setDataInizio(DateUtils.toDate(source.getDataInizio(), DateUtils.ISO_8601_FORMAT));
				target.setDataFine(DateUtils.toDate(source.getDataFine(), DateUtils.ISO_8601_FORMAT));
			}
			catch (ParseException e) {
				log.error(buildMessage(getClass(), methodName, e.getMessage()));
			}
		}

		return target;
	}

}
