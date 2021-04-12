package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.Classe;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class ClasseConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.Classe, Classe> {

	@Override
	public Classe convert(it.csi.iscritto.serviscritto.dto.domanda.Classe source) {
		Classe target = null;
		if (source != null) {
			target = new Classe();

			target.setCodAnnoScolastico(source.getCodAnnoScolastico());
			target.setCodFasciaEta(source.getCodFasciaEta());
			target.setCodOrdineScuola(source.getCodOrdineScuola());
			target.setCodScuola(source.getCodScuola());
			target.setCodTipoFrequenza(source.getCodTipoFrequenza());
			target.setDenominazione(source.getDenominazione());
			target.setFlAmmissioneDis(source.getFlAmmissioneDis());
			target.setIdClasse(source.getIdClasse());
			target.setPostiAccettati(source.getPostiAccettati());
			target.setPostiAmmessi(source.getPostiAmmessi());
			target.setPostiLiberi(source.getPostiLiberi());
		}

		return target;
	}

}
