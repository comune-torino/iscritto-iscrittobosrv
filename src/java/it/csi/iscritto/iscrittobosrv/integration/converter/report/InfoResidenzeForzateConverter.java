package it.csi.iscritto.iscrittobosrv.integration.converter.report;

import it.csi.iscritto.iscrittobosrv.dto.InfoResidenzeForzate;
import it.csi.iscritto.iscrittobosrv.dto.ResidenzaForzata;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class InfoResidenzeForzateConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.InfoResidenzeForzate, InfoResidenzeForzate> {

	@Override
	public InfoResidenzeForzate convert(it.csi.iscritto.serviscritto.dto.domanda.InfoResidenzeForzate source) {
		InfoResidenzeForzate target = new InfoResidenzeForzate();
		if (source != null) {
			target.setCodGraduatoria(source.getCodGraduatoria());
			target.setCodTipoScuola(source.getCodTipoScuola());
			target.setIdStepGra(source.getIdStepGra());
			target.setIdStepGraCon(source.getIdStepGraCon());
			target.setResidenzeForzate(new ResidenzaForzataConverter().convertAll(source.getResidenzeForzate()));
		}

		return target;
	}

	private static final class ResidenzaForzataConverter
			extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.ResidenzaForzata, ResidenzaForzata> {

		@Override
		public ResidenzaForzata convert(it.csi.iscritto.serviscritto.dto.domanda.ResidenzaForzata source) {
			ResidenzaForzata target = null;
			if (source != null) {
				target = new ResidenzaForzata();

				target.setCodiceFiscale(source.getCodiceFiscale());
				target.setCognome(source.getCognome());
				target.setNome(source.getNome());
				target.setProtocollo(source.getProtocollo());
				target.setCodFasciaEta(source.getCodFasciaEta());
				target.setCodScuola(source.getCodScuola());
				target.setDescScuola(source.getDescScuola());
				target.setIndirizzo(source.getIndirizzo());
				target.setCodOrdineScuola(source.getCodOrdineScuola());
			}

			return target;
		}
	}

}
