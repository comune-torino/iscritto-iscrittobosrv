package it.csi.iscritto.iscrittobosrv.integration.converter;

import it.csi.iscritto.iscrittobosrv.dto.TestataDomanda;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class TestataDomandaConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.TestataDomanda, TestataDomanda> {

	@Override
	public TestataDomanda convert(it.csi.iscritto.serviscritto.dto.domanda.TestataDomanda source) {
		TestataDomanda target = new TestataDomanda();
		target.setProtocolloDomandaIscrizione(source.getProtocollo());
		target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
		target.setStatoDomandaCodice(source.getStatoDomandaCodice());
		target.setStatoDomandaDescrizione(source.getStatoDomandaDescrizione());
		target.setIdSoggettoMinore(source.getIdSoggettoMinore());
		target.setCodiceFiscaleMinore(source.getCodiceFiscaleMinore());
		target.setCognomeMinore(source.getCognomeMinore());
		target.setNomeMinore(source.getNomeMinore());
		target.setDataUltimaModifica(DateUtils.toStringDate(source.getDataUltimaModifica(), DateUtils.DEFAULT_FORMAT));
		target.setCognomeUtenteUltimaModifica(source.getCognomeUtenteUltimaModifica());
		target.setNomeUtenteUltimaModifica(source.getNomeUtenteUltimaModifica());

		return target;
	}

}
