package it.csi.iscritto.iscrittobosrv.integration.converter;

import it.csi.iscritto.iscrittobosrv.dto.Result;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.iscrittobosrv.util.converter.ConvertUtils;

public class ResultConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.Result, Result> {

	@Override
	public Result convert(it.csi.iscritto.serviscritto.dto.domanda.Result source) {
		Result target = null;
		if (source != null) {
			target = new Result();
			target.setStatus(source.getStatus());
			target.setMessages(ConvertUtils.toList(source.getMessages()));
		}

		return target;
	}

}
