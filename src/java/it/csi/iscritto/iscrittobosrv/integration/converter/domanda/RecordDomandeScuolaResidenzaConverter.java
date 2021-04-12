package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.RecordDomandeScuolaResidenza;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class RecordDomandeScuolaResidenzaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.RecordDomandeScuolaResidenza, RecordDomandeScuolaResidenza> {

	@Override
	public RecordDomandeScuolaResidenza convert(it.csi.iscritto.serviscritto.dto.domanda.RecordDomandeScuolaResidenza source) {
		RecordDomandeScuolaResidenza target = null;
		if (source != null) {
			target = new RecordDomandeScuolaResidenza();

			target.setProtocollo(source.getProtocollo());
			target.setCodiceFiscaleMinore(source.getCodiceFiscaleMinore());
			target.setCognomeMinore(source.getCognomeMinore());
			target.setNomeMinore(source.getNomeMinore());
			target.setCodiceScuola(source.getCodiceScuola());
			target.setDescScuola(source.getDescScuola());
			target.setIndirizzoScuola(source.getIndirizzoScuola());

		}

		return target;
	}

}
