package it.csi.iscritto.iscrittobosrv.integration.converter;

import java.util.ArrayList;
import java.util.List;

import it.csi.iscritto.iscrittobosrv.dto.Attivita;
import it.csi.iscritto.iscrittobosrv.integration.converter.profilazione.PrivilegioConverter;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.profilazione.PrivilegioOperatore;

public class AttivitaConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.profilazione.Attivita, Attivita> {
	private PrivilegioOperatore[] privilegi;
	private String codFunzione;

	public AttivitaConverter withPrivilegi(PrivilegioOperatore[] privilegi) {
		this.privilegi = privilegi;
		return this;
	}

	public AttivitaConverter withCodFunzione(String codFunzione) {
		this.codFunzione = codFunzione;
		return this;
	}

	@Override
	public Attivita convert(it.csi.iscritto.serviscritto.dto.profilazione.Attivita source) {
		Attivita target = new Attivita();
		if (source != null) {
			target.setCodice(source.getCodice());
			target.setCodiceFunzione(this.codFunzione);
			target.setDescrizione(source.getDescrizione());
			target.setLink(source.getLink());
			target.setPrivilegi(new PrivilegioConverter().convertAll(this.getPrivilegi(source.getId())));
		}

		return target;
	}

	private List<PrivilegioOperatore> getPrivilegi(Long idAttivita) {
		List<PrivilegioOperatore> result = new ArrayList<>();
		if (this.privilegi != null && idAttivita != null) {
			for (PrivilegioOperatore privilegio : this.privilegi) {
				if (idAttivita.equals(privilegio.getIdAttivita())) {
					result.add(privilegio);
				}
			}
		}

		return result;
	}

}
