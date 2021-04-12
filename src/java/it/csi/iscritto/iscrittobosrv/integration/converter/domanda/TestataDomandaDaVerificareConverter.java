package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.TestataDomandaDaVerificare;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class TestataDomandaDaVerificareConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.TestataDomandaDaVerificare, TestataDomandaDaVerificare> {

	@Override
	public TestataDomandaDaVerificare convert(it.csi.iscritto.serviscritto.dto.domanda.TestataDomandaDaVerificare source) {
		TestataDomandaDaVerificare target = null;
		if (source != null) {
			target = new TestataDomandaDaVerificare();

			target.setCodiceFiscaleMinore(source.getCodiceFiscaleMinore());
			target.setCodStatoDomanda(source.getCodStatoDomanda());
			target.setCognomeMinore(source.getCognomeMinore());
			target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
			target.setNomeMinore(source.getNomeMinore());
			target.setPaDis(Boolean.TRUE.equals(source.getPaDis()));
			target.setPaPrbSal(Boolean.TRUE.equals(source.getPaPrbSal()));
			target.setProtocollo(source.getProtocollo());
		}

		return target;
	}

}
