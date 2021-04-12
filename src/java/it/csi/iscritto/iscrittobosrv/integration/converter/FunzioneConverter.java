package it.csi.iscritto.iscrittobosrv.integration.converter;

import org.springframework.beans.BeanUtils;

import it.csi.iscritto.iscrittobosrv.dto.Funzione;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class FunzioneConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.profilazione.Funzione, Funzione> {

	@Override
	public Funzione convert(it.csi.iscritto.serviscritto.dto.profilazione.Funzione source) {
		Funzione target = new Funzione();
		BeanUtils.copyProperties(source, target);

		return target;
	}

}
