package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.domanda.Nido;

public class NidoSrvConverter extends AbstractConverter<it.csi.iscritto.iscrittobosrv.dto.Scuola, Nido> {

	@Override
	public Nido convert(it.csi.iscritto.iscrittobosrv.dto.Scuola source) {
		Nido target = null;
		if (source != null) {
			target = new Nido();

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
