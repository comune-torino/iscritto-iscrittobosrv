package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import java.io.Serializable;

import it.csi.iscritto.iscrittobosrv.dto.TestataGraduatoria;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;
import it.csi.iscritto.iscrittobosrv.util.converter.ConvertUtils;

public class TestataGraduatoriaConverter extends AbstractConverter<TestataGraduatoriaConverter.Data, TestataGraduatoria> {
	private boolean dse;

	public TestataGraduatoriaConverter(boolean dse) {
		this.dse = dse;
	}

	@Override
	public TestataGraduatoria convert(Data data) {
		TestataGraduatoria target = new TestataGraduatoria();
		if (data != null && data.getTestata() != null) {
			it.csi.iscritto.serviscritto.dto.domanda.TestataGraduatoria testata = data.getTestata();

			target.setCognomeMinore(testata.getCognomeMinore());
			target.setDataNascitaMinore(DateUtils.toStringDate(testata.getDataNascitaMinore(), DateUtils.ISO_8601_FORMAT));
			target.setIsee(ConvertUtils.toStringValue(testata.getIsee()));
			target.setNomeMinore(testata.getNomeMinore());
			target.setPosizione(ConvertUtils.toStringValue(testata.getOrdinamento()));
			target.setPreferenza(testata.getDescrizioneScuola());
			target.setProtocollo(testata.getProtocolloDomanda());
			target.setPunteggio(testata.getPunteggio());
			target.setSceltaPreferenza(ConvertUtils.toStringValue(testata.getOrdinePreferenza()));
			target.setStatoPreferenza(dse ? testata.getCodStatoScuolaDse() : testata.getCodStatoScuolaEco());
			target.setTipologiaFrequenza(testata.getCodTipoFrequenza());

			target.setPreferenze(new DomandaConverters.NidoConverter().convertAll(data.getNidi()));
		}

		return target;
	}

	public static final class Data implements Serializable {
		private static final long serialVersionUID = 1L;

		private it.csi.iscritto.serviscritto.dto.domanda.TestataGraduatoria testata;
		private it.csi.iscritto.serviscritto.dto.domanda.Nido[] nidi;

		public it.csi.iscritto.serviscritto.dto.domanda.TestataGraduatoria getTestata() {
			return testata;
		}

		public void setTestata(it.csi.iscritto.serviscritto.dto.domanda.TestataGraduatoria testata) {
			this.testata = testata;
		}

		public it.csi.iscritto.serviscritto.dto.domanda.Nido[] getNidi() {
			return nidi;
		}

		public void setNidi(it.csi.iscritto.serviscritto.dto.domanda.Nido[] nidi) {
			this.nidi = nidi;
		}
	}

}
