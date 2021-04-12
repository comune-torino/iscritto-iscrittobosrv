package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.domanda.Materna;

public class MaternaSrvConverter extends AbstractConverter<it.csi.iscritto.iscrittobosrv.dto.Scuola, Materna> {

	@Override
	public Materna convert(it.csi.iscritto.iscrittobosrv.dto.Scuola source) {
		Materna target = null;
		if (source != null) {
			target = new Materna();

			target.setCodCategoriaScuola(source.getCodCategoriaScuola());
			target.setCodCircoscrizione(source.getCodCircoscrizione());
			target.setCodScuola(source.getCodScuola());
			target.setCodStatoScuola(source.getCodStatoScuola());
			target.setCodTipoFrequenza(source.getCodTipoFrequenza());
			target.setDescrizione(source.getDescrizione());
			target.setId(null);
			target.setIndirizzo(source.getIndirizzo());
		}

		return target;
	}

}
