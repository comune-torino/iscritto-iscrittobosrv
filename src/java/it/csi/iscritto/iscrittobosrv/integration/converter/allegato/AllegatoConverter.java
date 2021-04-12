package it.csi.iscritto.iscrittobosrv.integration.converter.allegato;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.iscritto.iscrittobosrv.dto.allegato.Allegato;
import it.csi.iscritto.iscrittobosrv.util.AllegatoUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class AllegatoConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.Allegato, Allegato> {

	@Override
	public Allegato convert(it.csi.iscritto.serviscritto.dto.domanda.Allegato source) {
		Allegato target = null;
		if (source != null) {
			target = new Allegato();

			target.setDataInserimento(source.getDataInserimento());
			target.setDocumento(source.getDocumento());
			target.setIdAllegato(source.getIdAllegato());
			target.setIdAnagraficaSoggetto(source.getIdAnagraficaSoggetto());
			target.setCodTipoAllegato(source.getCodTipoAllegato());
			target.setMimeType(source.getMimeType());
			target.setNomeFile(source.getNomeFile());
			target.setProtocollo(source.getProtocollo());
		}

		return target;
	}

	public static final List<Allegato> buildAllegato(Long idSoggetto, String codTipoAllegato, MultipartFormDataInput input)
			throws IOException {

		List<Allegato> result = new ArrayList<>();
		Map<String, List<InputPart>> form = input.getFormDataMap();

		for (Map.Entry<String, List<InputPart>> item : form.entrySet()) {
			List<InputPart> inputParts = item.getValue();
			if (CollectionUtils.isNotEmpty(inputParts)) {
				for (InputPart inputPart : inputParts) {
					result.add(buildAllegato(idSoggetto, codTipoAllegato, inputPart));
				}
			}
		}

		return result;
	}

	public static final Allegato buildAllegato(Long idSoggetto, String codTipoAllegato, InputPart inputPart)
			throws IOException {

		MultivaluedMap<String, String> header = inputPart.getHeaders();

		String fileName = AllegatoUtils.getFileName(header);
		String mimeType = AllegatoUtils.getMimeType(header);
		byte[] content = IOUtils.toByteArray(inputPart.getBody(InputStream.class, null));

		Allegato target = new Allegato();

		target.setDataInserimento(null);
		target.setDocumento(content);
		target.setIdAllegato(null);
		target.setIdAnagraficaSoggetto(idSoggetto);
		target.setCodTipoAllegato(codTipoAllegato);
		target.setMimeType(mimeType);
		target.setNomeFile(fileName);
		target.setProtocollo(null);

		return target;
	}

}
