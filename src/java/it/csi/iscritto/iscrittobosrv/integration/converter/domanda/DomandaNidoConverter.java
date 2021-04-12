package it.csi.iscritto.iscrittobosrv.integration.converter.domanda;

import it.csi.iscritto.iscrittobosrv.dto.Domanda;
import it.csi.iscritto.iscrittobosrv.util.converter.AbstractConverter;

public class DomandaNidoConverter extends AbstractConverter<it.csi.iscritto.serviscritto.dto.domanda.DomandaNido, Domanda> {

	@Override
	public Domanda convert(it.csi.iscritto.serviscritto.dto.domanda.DomandaNido source) {
		Domanda target = null;
		if (source != null) {
			target = new Domanda();
			target.setAffido(new DomandaConverters.AffidoConverter().convert(source.getAffido()));
			target.setAltriComponenti(new DomandaConverters.AltriComponentiConverter().convert(source.getAltriComponenti()));
			target.setComponentiNucleo(new DomandaConverters.ComponentiNucleoConverter().convert(source.getComponentiNucleo()));
			target.setElencoNidi(new DomandaConverters.NidoConverter().convertAll(source.getElencoNidi()));
			target.setIdDomandaIscrizione(source.getIdDomandaIscrizione());
			target.setProtocolloDomandaIscrizione(source.getProtocollo());
			target.setInfoAutocertificazione(source.getInfoAutocertificazione());
			target.setInfoGdpr(source.getInfoGdpr());
			target.setIsee(new DomandaConverters.IseeConverter().convert(source.getIsee()));
			target.setMinore(new DomandaConverters.MinoreConverter().convert(source.getMinore()));
			target.setOrdineScuola(source.getOrdineScuola());
			target.setResponsabilitaGenitoriale(source.getResponsabilitaGenitoriale());
			target.setRichiedente(new DomandaConverters.RichiedenteConverter().convert(source.getRichiedente()));
			target.setSoggetto1(new DomandaConverters.Soggetto1Converter().convert(source.getSoggetto1()));
			target.setSoggetto2(new DomandaConverters.Soggetto2Converter().convert(source.getSoggetto2()));
			target.setSoggetto3(new DomandaConverters.Soggetto3Converter().convert(source.getSoggetto3()));
			target.setStatoDomanda(source.getStatoDomanda());
			target.setCondizioniPunteggio(new DomandaConverters.CondizionePunteggioConverter().convertAll(source.getCondizioniPunteggio()));
		}

		return target;
	}

}
