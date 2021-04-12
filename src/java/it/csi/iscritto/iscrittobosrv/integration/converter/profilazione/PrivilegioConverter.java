package it.csi.iscritto.iscrittobosrv.integration.converter.profilazione;

import it.csi.iscritto.iscrittobosrv.dto.Privilegio;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.serviscritto.dto.profilazione.PrivilegioOperatore;

public class PrivilegioConverter extends AbstractConverter<PrivilegioOperatore, Privilegio> {

	@Override
	public Privilegio convert(PrivilegioOperatore source) {
		Privilegio target = null;
		if (source != null) {
			target = new Privilegio();

			target.setCodice(source.getCodicePrivilegio());
			target.setDescrizione(source.getDescPrivilegio());
		}

		return target;
	}

}
