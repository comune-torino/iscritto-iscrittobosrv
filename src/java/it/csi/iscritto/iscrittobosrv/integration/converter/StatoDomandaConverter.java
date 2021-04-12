package it.csi.iscritto.iscrittobosrv.integration.converter;

import org.springframework.beans.BeanUtils;

import it.csi.iscritto.iscrittobosrv.dto.StatoDomanda;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class StatoDomandaConverter
		extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.Codifica, StatoDomanda> {

	@Override
	public StatoDomanda convert(it.csi.iscritto.serviscritto.dto.domanda.Codifica source) {
		StatoDomanda target = new StatoDomanda();
		BeanUtils.copyProperties(source, target);

		return target;
	}

}
