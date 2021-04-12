package it.csi.iscritto.iscrittobosrv.integration.converter.scuola;

import it.csi.iscritto.iscrittobosrv.dto.InfoScuola;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.scuole.Scuola;

public class InfoScuolaConverter extends AbstractConverter<Scuola, InfoScuola> {

	@Override
	public InfoScuola convert(Scuola source) {
		InfoScuola target = null;
		if (source != null) {
			target = new InfoScuola();

			target.setCodCategoriaScuola(source.getCodCategoriaScuola());
			target.setCodCircoscrizione(source.getCodCircoscrizione());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setCodScuola(source.getCodScuola());
			target.setDescCategoriaScuola(source.getDescCategoriaScuola());
			target.setDescCircoscrizione(source.getDescCircoscrizione());
			target.setDescOrdineScuola(source.getDescOrdineScuola());
			target.setDescrizione(source.getDescrizione());
			target.setIndirizzo(source.getIndirizzo());
			target.setAnnoScolastico(source.getAnnoScolastico());
			target.setAlert(source.getAlert());
			target.setTempoBreve(source.getTempoBreve());
			target.setTempoLungo(source.getTempoLungo());
			target.setTempoIntermedio(source.getTempoIntermedio());
		}

		return target;
	}

}
