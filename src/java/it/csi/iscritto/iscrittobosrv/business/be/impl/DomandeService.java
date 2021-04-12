package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_ERROR;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.csi.wrapper.CSIException;
import it.csi.iscritto.iscrittobosrv.business.be.TipoScuola;
import it.csi.iscritto.iscrittobosrv.dto.AnagraficaEta;
import it.csi.iscritto.iscrittobosrv.dto.AnagraficaGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.AnagraficaStepGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.AnnoScolastico;
import it.csi.iscritto.iscrittobosrv.dto.Classe;
import it.csi.iscritto.iscrittobosrv.dto.ClasseParam;
import it.csi.iscritto.iscrittobosrv.dto.DatiCondizionePunteggio;
import it.csi.iscritto.iscrittobosrv.dto.Domanda;
import it.csi.iscritto.iscrittobosrv.dto.DomandeFilter;
import it.csi.iscritto.iscrittobosrv.dto.Graduatoria;
import it.csi.iscritto.iscrittobosrv.dto.GraduatoriaApprovazione;
import it.csi.iscritto.iscrittobosrv.dto.GraduatoriaCompleta;
import it.csi.iscritto.iscrittobosrv.dto.GraduatorieFilter;
import it.csi.iscritto.iscrittobosrv.dto.InfoAllegatoSoggetto;
import it.csi.iscritto.iscrittobosrv.dto.InfoCalcoloGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.InfoGenerali;
import it.csi.iscritto.iscrittobosrv.dto.InfoResidenzeForzate;
import it.csi.iscritto.iscrittobosrv.dto.InfoStepGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.ParametriCalcoloGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.ParametriGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.RecordDomandeScuolaResidenza;
import it.csi.iscritto.iscrittobosrv.dto.RecordPostiLiberi;
import it.csi.iscritto.iscrittobosrv.dto.RecordPreferenzaScuola;
import it.csi.iscritto.iscrittobosrv.dto.Result;
import it.csi.iscritto.iscrittobosrv.dto.Scuola;
import it.csi.iscritto.iscrittobosrv.dto.StatoDomanda;
import it.csi.iscritto.iscrittobosrv.dto.StepGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.TestataDomanda;
import it.csi.iscritto.iscrittobosrv.dto.TestataDomandaDaVerificare;
import it.csi.iscritto.iscrittobosrv.dto.TestataDomandaIstruttoria;
import it.csi.iscritto.iscrittobosrv.dto.TestataGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.TipoFasciaEta;
import it.csi.iscritto.iscrittobosrv.dto.VerbaleCommissione;
import it.csi.iscritto.iscrittobosrv.dto.allegato.Allegato;
import it.csi.iscritto.iscrittobosrv.dto.allegato.RicevutaAllegato;
import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.exception.BusinessServiceException;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.integration.converter.DomandeFilterConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.DomandeIstruttoriaFilterConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.ResultConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.StatoDomandaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.TestataDomandaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.TestataDomandaIstruttoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.allegato.AllegatoConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.allegato.AllegatoSrvConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.allegato.RicevutaAllegatoConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.AnagraficaEtaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.AnagraficaGraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.AnagraficaStepGraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.AnnoScolasticoConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.CallerInfoDomandaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.ClasseConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.ClasseParamSrvConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.DatiCondizionePunteggioConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.DomandaConverters;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.DomandaMaternaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.DomandaNidoConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.GraduatoriaApprovazioneConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.GraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.InfoAllegatoSoggettoConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.InfoGeneraliConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.InfoStepGraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.InfoVerificheConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.MaternaSrvConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.NidoSrvConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.ParametriCalcoloGraduatoriaSrvConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.ParametriGraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.RecordDomandeScuolaResidenzaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.RecordPostiLiberiConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.RecordPreferenzaScuolaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.StepGraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.TestataDomandaDaVerificareConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.TestataGraduatoriaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.TipoFasciaEtaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.domanda.VerbaleCommissioneConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.report.GraduatoriaCompletaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.report.InfoResidenzeForzateConverter;
import it.csi.iscritto.iscrittobosrv.integration.service.DomandaSrvBean;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.RestUtils;
import it.csi.iscritto.iscrittobosrv.util.converter.ConvertUtils;
import it.csi.iscritto.serviscritto.dto.domanda.CallerInfoDomanda;
import it.csi.iscritto.serviscritto.dto.domanda.Codifica;
import it.csi.iscritto.serviscritto.dto.domanda.CriterioRicerca;
import it.csi.iscritto.serviscritto.dto.domanda.CriterioRicercaIstruttoria;
import it.csi.iscritto.serviscritto.dto.domanda.RicercaGraduatorieFilter;
import it.csi.iscritto.serviscritto.exception.domanda.DomandaUserException;
import it.csi.iscritto.serviscritto.exception.domanda.InvalidFilterException;
import it.csi.iscritto.serviscritto.exception.domanda.ValidationException;

@Component
public class DomandeService {
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Autowired
	private DomandaSrvBean domandaSrvBean;

	/**
	 * Operazione di Business per la ricerca domande con filtro delegata sul servizio remoto esposto in pd/pa
	 *
	 * @param callerInfo
	 * @param tipoScuola
	 * @param cfOperatore
	 * @param codProfilo
	 * @param filter
	 * @return
	 * @throws ServiceException
	 */
	public List<Long> getElencoDomande(CallerInfo callerInfo, TipoScuola tipoScuola, String cfOperatore, String codProfilo, DomandeFilter filter)
			throws ServiceException {

		final String methodName = "getElencoDomande";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<Long> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			CriterioRicerca criterio = new DomandeFilterConverter().convert(filter);

			switch (tipoScuola) {
				case NID:
					result = ConvertUtils.toList(domandaSrvBean.getServiceInterface().getElencoDomandeNidi(
							callerInfoDomanda, cfOperatore, codProfilo, criterio));
					break;
				case MAT:
					result = ConvertUtils.toList(domandaSrvBean.getServiceInterface().getElencoDomandeMaterne(
							callerInfoDomanda, cfOperatore, codProfilo, criterio));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (InvalidFilterException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Filtro di ricerca NON valido", e);
		}
		catch (DomandaUserException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Eccezione di Business", e);
		}
		catch (CSIException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Eccezione Imprevista", e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<TestataDomandaIstruttoria> getElencoDomandeIstruttoria(CallerInfo callerInfo, TipoScuola tipoScuola, String cfOperatore, String codProfilo, DomandeFilter filter)
			throws ServiceException {
		final String methodName = "getElencoDomandeIstruttoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		log.info(buildMessage(getClass(), methodName, "cfOperatore:" + cfOperatore));
		log.info(buildMessage(getClass(), methodName, "codProfilo:" + codProfilo));

		Validate.notNull(tipoScuola);

		List<TestataDomandaIstruttoria> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			CriterioRicercaIstruttoria criterio = new DomandeIstruttoriaFilterConverter().convert(filter);

			switch (tipoScuola) {
				case NID:
					result = new TestataDomandaIstruttoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getDomandeIstruttoriaNidi(callerInfoDomanda, criterio, cfOperatore, codProfilo));
					break;
				case MAT:
					result = new TestataDomandaIstruttoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getDomandeIstruttoriaMaterne(callerInfoDomanda, criterio, cfOperatore, codProfilo));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (InvalidFilterException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Filtro di ricerca NON valido", e);
		}
		catch (DomandaUserException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Eccezione di Business", e);
		}
		catch (CSIException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Eccezione Imprevista", e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public TestataDomanda getTestataDomandaById(Long idDomanda) throws ServiceException, BusinessServiceException {
		final String methodName = "getTestataDomandaById";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		TestataDomanda testata;
		try {
			testata = new TestataDomandaConverter().convert(
					domandaSrvBean.getServiceInterface().getTestataDomanda(null, idDomanda, "RIC"));
		}
		catch (CSIException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			if (e instanceof DomandaUserException) {
				throw new BusinessServiceException("Domanda NON trovata");
			}

			throw new ServiceException("", e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return testata;
	}

	/**
	 * Recupera l'elenco degli stato possibili della domanda di iscrizione. Per le necessita' del BO si deve escludere dall'elenco lo stato BOZZA
	 *
	 * 22/03/2019: il metodo restituisce anche lo stato BOZZA
	 *
	 * @return
	 * @throws ServiceException
	 */
	public List<StatoDomanda> getElencoStatiDomanda() throws ServiceException {
		final String methodName = "getElencoStatiDomanda";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<StatoDomanda> elencoStatiDomanda = new ArrayList<StatoDomanda>();
		StatoDomandaConverter converter = new StatoDomandaConverter();
		try {
			Codifica[] _elenco;
			_elenco = domandaSrvBean.getServiceInterface().getElencoStatiDomanda(null);
			for (int i = 0; i < _elenco.length; i++) {
				Codifica _codifica = _elenco[i];
				// if (!_codifica.getCodice().equals(Constants.STATO_DOMANDA_BOZZA)) {
				// elencoStatiDomanda.add(converter.convert(_codifica));
				// }

				elencoStatiDomanda.add(converter.convert(_codifica));
			}
			// elencoStatiDomanda = new StatoDomandaConverter().convertAll(domandaSrvBean.getServiceInterface().getElencoStatiDomanda());
		}
		catch (CSIException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e.getMessage(), e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return elencoStatiDomanda;
	}

	public Domanda getDomanda(CallerInfo callerInfo, TipoScuola tipoScuola, Long idDomanda) throws ServiceException {
		final String methodName = "getDomanda";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		Domanda result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new DomandaNidoConverter().convert(
							domandaSrvBean.getServiceInterface().getDomandaNido(callerInfoDomanda, idDomanda));
					break;
				case MAT:
					result = new DomandaMaternaConverter().convert(
							domandaSrvBean.getServiceInterface().getDomandaMaterna(callerInfoDomanda, idDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (CSIException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e.getMessage(), e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	/**
	 *
	 * @param callerInfo
	 * @param cfRichiedente
	 * @param cfOperatore
	 * @param idDomanda
	 * @param idSoggetto
	 * @param codTipoAllegato
	 * @param input
	 * @return
	 * @throws ServiceException
	 */
	public List<Long> insertAllegati(
			CallerInfo callerInfo, String cfRichiedente, String cfOperatore,
			Long idDomanda, Long idSoggetto, String codTipoAllegato, MultipartFormDataInput input)
			throws ServiceException {

		final String methodName = "insertAllegati";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<Long> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			List<Allegato> allegati = AllegatoConverter.buildAllegato(idSoggetto, codTipoAllegato, input);

			it.csi.iscritto.serviscritto.dto.domanda.Allegato[] allegatiArray = ConvertUtils.toArray(
					new AllegatoSrvConverter().convertAll(allegati),
					it.csi.iscritto.serviscritto.dto.domanda.Allegato.class);

			result = ConvertUtils.toList(
					this.domandaSrvBean.getServiceInterface().insertAllegatoList(callerInfoDomanda, cfRichiedente, cfOperatore, idDomanda, allegatiArray));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Allegato findAllegato(CallerInfo callerInfo, String cfRichiedente, Long idDomanda, Long idAllegato, boolean withContent) throws ServiceException {
		final String methodName = "findAllegato";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Allegato result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new AllegatoConverter().convert(
					this.domandaSrvBean.getServiceInterface().getAllegato(callerInfoDomanda, cfRichiedente, idDomanda, idAllegato, withContent));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<DatiCondizionePunteggio> getStoricoCondizioniPunteggio(CallerInfo callerInfo, Long idDomanda, String condizionePunteggio)
			throws ServiceException {

		final String methodName = "getStoricoCondizioniPunteggio";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<DatiCondizionePunteggio> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new DatiCondizionePunteggioConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getStoricoCondizioniPunteggio(callerInfoDomanda, idDomanda, condizionePunteggio));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public void modificaCondizionePunteggio(CallerInfo callerInfo, TipoScuola tipoScuola, Long idDomanda, String condizionePunteggio, String cfOperatore,
			Integer count, DatiCondizionePunteggio dati) throws ServiceException {

		final String methodName = "modificaCondizionePunteggio";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			it.csi.iscritto.serviscritto.dto.domanda.DatiCondizionePunteggio dto =
					DatiCondizionePunteggioConverter.buildSrv(idDomanda, condizionePunteggio, cfOperatore, dati);

			switch (tipoScuola) {
				case NID:
					this.domandaSrvBean.getServiceInterface().modificaCondizionePunteggioNidi(
							callerInfoDomanda, idDomanda, condizionePunteggio, cfOperatore, count, dto);
					break;
				case MAT:
					this.domandaSrvBean.getServiceInterface().modificaCondizionePunteggioMaterne(
							callerInfoDomanda, idDomanda, condizionePunteggio, cfOperatore, count, dto);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
	}

	public List<InfoAllegatoSoggetto> getInfoAllegatiDomanda(CallerInfo callerInfo, Long idDomanda, String condizionePunteggio) throws ServiceException {
		final String methodName = "getInfoAllegatiDomanda";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<InfoAllegatoSoggetto> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new InfoAllegatoSoggettoConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getInfoAllegatiDomanda(callerInfoDomanda, idDomanda, condizionePunteggio));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<VerbaleCommissione> getInfoVerbali(CallerInfo callerInfo, String codProfilo, TipoScuola tipoScuola, String tipoCommissione, Date dataInizio, Date dataFine)
			throws ServiceException {

		final String methodName = "getInfoVerbali";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		log.info(buildMessage(getClass(), methodName, "##### operatore:" + callerInfo.getUtente()));
		log.info(buildMessage(getClass(), methodName, "##### codProfilo:" + codProfilo));

		Validate.notNull(tipoScuola);

		List<VerbaleCommissione> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new VerbaleCommissioneConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getInfoVerbaliNidi(callerInfoDomanda, codProfilo, tipoCommissione, dataInizio, dataFine));
					break;
				case MAT:
					result = new VerbaleCommissioneConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getInfoVerbaliMaterne(callerInfoDomanda, codProfilo, tipoCommissione, dataInizio, dataFine));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public InfoGenerali getInfoGenerali(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "getInfoGenerali";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		InfoGenerali result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new InfoGeneraliConverter().convert(
							this.domandaSrvBean.getServiceInterface().getInfoGeneraliNidi(callerInfoDomanda));
					break;
				case MAT:
					result = new InfoGeneraliConverter().convert(
							this.domandaSrvBean.getServiceInterface().getInfoGeneraliMaterne(callerInfoDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<TestataDomandaDaVerificare> getDomandeDaVerificare(CallerInfo callerInfo, TipoScuola tipoScuola, String codProfilo) throws ServiceException {
		final String methodName = "getDomandeDaVerificare";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		log.info(buildMessage(getClass(), methodName, "codice Profilo:" + codProfilo));
		Validate.notNull(tipoScuola);

		List<TestataDomandaDaVerificare> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new TestataDomandaDaVerificareConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getDomandeNidoDaVerificare(callerInfoDomanda, codProfilo));
					break;
				case MAT:
					result = new TestataDomandaDaVerificareConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getDomandeMaternaDaVerificare(callerInfoDomanda, codProfilo));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<Scuola> getElencoNidi(CallerInfo callerInfo, Long idDomanda) throws ServiceException {
		final String methodName = "getElencoNidi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<Scuola> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new DomandaConverters.NidoConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getElencoNidi(callerInfoDomanda, idDomanda));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public void modificaStatoScuole(CallerInfo callerInfo, TipoScuola tipoScuola, Long idDomanda, List<Scuola> elencoNidi) throws ServiceException {
		final String methodName = "modificaStatoScuole";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					it.csi.iscritto.serviscritto.dto.domanda.Nido[] nidi = ConvertUtils.toArray(
							new NidoSrvConverter().convertAll(elencoNidi), it.csi.iscritto.serviscritto.dto.domanda.Nido.class);

					this.domandaSrvBean.getServiceInterface().modificaStatoNidi(callerInfoDomanda, idDomanda, nidi);
					break;
				case MAT:
					it.csi.iscritto.serviscritto.dto.domanda.Materna[] materne = ConvertUtils.toArray(
							new MaternaSrvConverter().convertAll(elencoNidi), it.csi.iscritto.serviscritto.dto.domanda.Materna.class);

					this.domandaSrvBean.getServiceInterface().modificaStatoMaterne(callerInfoDomanda, idDomanda, materne);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
	}

	public ParametriGraduatoria getParametriUltimaGraduatoria(TipoScuola tipoScuola, CallerInfo callerInfo) throws ServiceException {
		final String methodName = "getParametriUltimaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		ParametriGraduatoria result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			//TODO verificare se rimuovere 654, 655
			result = new ParametriGraduatoriaConverter().convert(
					this.domandaSrvBean.getServiceInterface().getParametriUltimaGraduatoriaNidi(callerInfoDomanda));

			switch (tipoScuola) {
				case NID:
					result = new ParametriGraduatoriaConverter().convert(
							this.domandaSrvBean.getServiceInterface().getParametriUltimaGraduatoriaNidi(callerInfoDomanda));
					break;
				case MAT:
					result = new ParametriGraduatoriaConverter().convert(
							this.domandaSrvBean.getServiceInterface().getParametriUltimaGraduatoriaMaterne(callerInfoDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<Graduatoria> getElencoGraduatorie(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "getElencoGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<Graduatoria> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new GraduatoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getElencoGraduatorieNidi(callerInfoDomanda));
					break;
				case MAT:
					result = new GraduatoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getElencoGraduatorieMaterne(callerInfoDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<StepGraduatoria> getElencoStepGraduatoria(CallerInfo callerInfo, TipoScuola tipoScuola, String codiceGraduatoria) throws ServiceException {
		final String methodName = "getElencoStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<StepGraduatoria> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new StepGraduatoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getElencoStepGraduatoriaNidi(callerInfoDomanda, codiceGraduatoria));
					break;
				case MAT:
					result = new StepGraduatoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getElencoStepGraduatoriaMaterne(callerInfoDomanda, codiceGraduatoria));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public ParametriGraduatoria getParametriGraduatoria(CallerInfo callerInfo, TipoScuola tipoScuola, String codiceGraduatoria) throws ServiceException {
		final String methodName = "getParametriGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		ParametriGraduatoria result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new ParametriGraduatoriaConverter().convert(
							this.domandaSrvBean.getServiceInterface().getParametriGraduatoriaNidi(callerInfoDomanda, codiceGraduatoria));
					break;
				case MAT:
					result = new ParametriGraduatoriaConverter().convert(
							this.domandaSrvBean.getServiceInterface().getParametriGraduatoriaMaterne(callerInfoDomanda, codiceGraduatoria));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<TestataGraduatoria> ricercaGraduatorie(CallerInfo callerInfo, GraduatorieFilter graduatorieFilter, TipoScuola tipoScuola, boolean dse)
			throws ServiceException {

		final String methodName = "ricercaGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		log.info(buildMessage(getClass(), methodName, "dse:" + dse));

		Validate.notNull(tipoScuola);

		List<TestataGraduatoria> result = new ArrayList<>();
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			RicercaGraduatorieFilter filter = buildRicercaGraduatorieFilter(graduatorieFilter, tipoScuola);

			it.csi.iscritto.serviscritto.dto.domanda.TestataGraduatoria[] graduatorie =
					this.domandaSrvBean.getServiceInterface().ricercaGraduatoria(callerInfoDomanda, filter);

			if (ArrayUtils.isNotEmpty(graduatorie)) {
				for (it.csi.iscritto.serviscritto.dto.domanda.TestataGraduatoria graduatoria : graduatorie) {
					TestataGraduatoriaConverter.Data data = new TestataGraduatoriaConverter.Data();

					data.setTestata(graduatoria);
					data.setNidi(dse
							? this.domandaSrvBean.getServiceInterface().getElencoNidiDse(null, graduatoria.getIdDomandaIscrizione())
							: this.domandaSrvBean.getServiceInterface().getElencoNidi(null, graduatoria.getIdDomandaIscrizione()));

					result.add(new TestataGraduatoriaConverter(dse).convert(data));
				}
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public InfoCalcoloGraduatoria getInfoCalcoloGraduatoria(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "ricercaGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		InfoCalcoloGraduatoria result = new InfoCalcoloGraduatoria();
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			Long[] idDomande;
			switch (tipoScuola) {
				case NID:
					idDomande = this.domandaSrvBean.getServiceInterface().getDomandeNidoNonIstruite(callerInfoDomanda);
					break;
				case MAT:
					idDomande = this.domandaSrvBean.getServiceInterface().getDomandeMaternaNonIstruite(callerInfoDomanda);
					break;
				default:
					throw new UnsupportedOperationException();
			}

			int nonIstruite = CollectionUtils.size(idDomande);

			result.setDomandeNonIstruite(nonIstruite);
			if (nonIstruite > 0) {
				result.setVerifichePreventivePendenti(new InfoVerificheConverter().convertAll(
						this.domandaSrvBean.getServiceInterface().getVerifichePreventivePendenti(callerInfoDomanda, idDomande)));
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public void calcolaGraduatoria(CallerInfo callerInfo, ParametriCalcoloGraduatoria body) throws ServiceException {
		final String methodName = "calcolaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			it.csi.iscritto.serviscritto.dto.domanda.ParametriCalcoloGraduatoria params =
					new ParametriCalcoloGraduatoriaSrvConverter().convert(body);
			this.domandaSrvBean.getServiceInterface().calcolaGraduatoria(callerInfoDomanda, params);
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
	}

	public List<AnnoScolastico> getElencoAnniScolastici(CallerInfo callerInfo) throws ServiceException {
		final String methodName = "getElencoAnniScolastici";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<AnnoScolastico> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new AnnoScolasticoConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getElencoAnniScolastici(callerInfoDomanda));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<Classe> getElencoClassi(CallerInfo callerInfo, TipoScuola tipoScuola, String codScuola, String codAnno) throws ServiceException {
		final String methodName = "getElencoClassi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<Classe> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new ClasseConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getElencoClassiNido(callerInfoDomanda, codScuola, codAnno));
					break;
				case MAT:
					result = new ClasseConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getElencoClassiMaterna(callerInfoDomanda, codScuola, codAnno));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Long insertClasse(CallerInfo callerInfo, TipoScuola tipoScuola, ClasseParam body) throws ServiceException {
		final String methodName = "insertClasse";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		Long result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			it.csi.iscritto.serviscritto.dto.domanda.ClasseParam param = new ClasseParamSrvConverter().convert(body);

			switch (tipoScuola) {
				case NID:
					result = this.domandaSrvBean.getServiceInterface().insertClasseNido(callerInfoDomanda, param);
					break;
				case MAT:
					result = this.domandaSrvBean.getServiceInterface().insertClasseMaterna(callerInfoDomanda, param);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer updateClasse(CallerInfo callerInfo, TipoScuola tipoScuola, ClasseParam body) throws ServiceException {
		final String methodName = "updateClasse";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);
			it.csi.iscritto.serviscritto.dto.domanda.ClasseParam param = new ClasseParamSrvConverter().convert(body);

			switch (tipoScuola) {
				case NID:
					result = this.domandaSrvBean.getServiceInterface().updateClasseNido(callerInfoDomanda, param);
					break;
				case MAT:
					result = this.domandaSrvBean.getServiceInterface().updateClasseMaterna(callerInfoDomanda, param);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer deleteClasse(CallerInfo callerInfo, TipoScuola tipoScuola, Long idClasse) throws ServiceException {
		final String methodName = "deleteClasse";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = this.domandaSrvBean.getServiceInterface().deleteClasseNido(callerInfoDomanda, idClasse);
					break;
				case MAT:
					result = this.domandaSrvBean.getServiceInterface().deleteClasseMaterna(callerInfoDomanda, idClasse);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public InfoResidenzeForzate getResidenzeForzate(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "getResidenzeForzate";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		InfoResidenzeForzate result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new InfoResidenzeForzateConverter().convert(
							this.domandaSrvBean.getServiceInterface().getResidenzeForzateNidi(callerInfoDomanda));
					break;
				case MAT:
					result = new InfoResidenzeForzateConverter().convert(
							this.domandaSrvBean.getServiceInterface().getResidenzeForzateMaterne(callerInfoDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<GraduatoriaCompleta> getGraduatoriaCompleta(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "getGraduatoriaCompleta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<GraduatoriaCompleta> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new GraduatoriaCompletaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getGraduatoriaCompletaNidi(callerInfoDomanda));
					break;
				case MAT:
					result = new GraduatoriaCompletaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getGraduatoriaCompletaMaterne(callerInfoDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer updateFlagAmmissioni(CallerInfo callerInfo, List<Long> idClasseList, Boolean value) throws ServiceException {
		final String methodName = "updateFlagAmmissioni";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().updateFlagAmmissioni(
					callerInfoDomanda,
					ConvertUtils.toArray(idClasseList, Long.class),
					value);
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<InfoStepGraduatoria> getInfoStepGraduatorie(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "getInfoStepGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<InfoStepGraduatoria> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new InfoStepGraduatoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getInfoStepGraduatorieNido(callerInfoDomanda));
					break;
				case MAT:
					result = new InfoStepGraduatoriaConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getInfoStepGraduatorieMaterna(callerInfoDomanda));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<GraduatoriaApprovazione> getGraduatoriaApprovazione(
			CallerInfo callerInfo, TipoScuola tipoScuola, String codiceGraduatoria, Integer step, String codiceStatoGraduatoria) throws ServiceException {

		final String methodName = "getGraduatoriaApprovazione";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<GraduatoriaApprovazione> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new GraduatoriaApprovazioneConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getGraduatoriaApprovazioneNidi(
									callerInfoDomanda, codiceGraduatoria, step, codiceStatoGraduatoria));
					break;
				case MAT:
					result = new GraduatoriaApprovazioneConverter().convertAll(
							this.domandaSrvBean.getServiceInterface().getGraduatoriaApprovazioneMaterne(
									callerInfoDomanda, codiceGraduatoria, step, codiceStatoGraduatoria));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public void insertScuolaFuoriTermine(CallerInfo callerInfo, TipoScuola tipoScuola, Long idDomanda, String cfOperatore, String codScuola,
			String codTipoFrequenza) throws ServiceException {

		final String methodName = "insertNidoFuoriTermine";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					this.domandaSrvBean.getServiceInterface().insertNidoFuoriTermine(
							callerInfoDomanda, idDomanda, cfOperatore, codScuola, codTipoFrequenza);
					break;
				case MAT:
					this.domandaSrvBean.getServiceInterface().insertMaternaFuoriTermine(
							callerInfoDomanda, idDomanda, cfOperatore, codScuola, codTipoFrequenza);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
	}

	public RicevutaAllegato generaRicevutaAllegato(CallerInfo callerInfo, Long idAllegato) throws ServiceException {
		final String methodName = "generaRicevutaAllegato";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		RicevutaAllegato result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new RicevutaAllegatoConverter().convert(
					this.domandaSrvBean.getServiceInterface().generaRicevuta(callerInfoDomanda, idAllegato));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<AnagraficaGraduatoria> getAnagraficaGraduatorie(CallerInfo callerInfo) throws ServiceException {
		final String methodName = "getAnagraficaGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<AnagraficaGraduatoria> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new AnagraficaGraduatoriaConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getAnagraficaGraduatorie(callerInfoDomanda));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public AnagraficaGraduatoria getAnagraficaGraduatoria(CallerInfo callerInfo, String codOrdineScuola, String codAnagraficaGra, String codAnnoScolastico)
			throws ServiceException {

		final String methodName = "getAnagraficaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		AnagraficaGraduatoria result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new AnagraficaGraduatoriaConverter().convert(
					this.domandaSrvBean.getServiceInterface().getAnagraficaGraduatoria(
							callerInfoDomanda, codOrdineScuola, codAnagraficaGra, codAnnoScolastico));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Long insertAnagraficaGraduatoria(CallerInfo callerInfo, AnagraficaGraduatoria body) throws ServiceException {
		final String methodName = "insertAnagraficaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Long result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().insertAnagraficaGraduatoria(
					callerInfoDomanda, AnagraficaGraduatoriaConverter.toSrv(body));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer updateAnagraficaGraduatoria(CallerInfo callerInfo, AnagraficaGraduatoria body) throws ServiceException {
		final String methodName = "updateAnagraficaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().updateAnagraficaGraduatoria(
					callerInfoDomanda, AnagraficaGraduatoriaConverter.toSrv(body));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer deleteAnagraficaStepGraduatoria(CallerInfo callerInfo, Long idStepGra) throws ServiceException {
		final String methodName = "deleteAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().deleteAnagraficaStepGraduatoria(callerInfoDomanda, idStepGra);
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<AnagraficaStepGraduatoria> getElencoAnagraficaStepGraduatoria(
			CallerInfo callerInfo, String codOrdineScuola, String codAnagraficaGra, String codAnnoScolastico) throws ServiceException {

		final String methodName = "getElencoAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<AnagraficaStepGraduatoria> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new AnagraficaStepGraduatoriaConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getAnagraficaStepGraduatoria(
							callerInfoDomanda, codOrdineScuola, codAnagraficaGra, codAnnoScolastico));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Long insertAnagraficaStepGraduatoria(CallerInfo callerInfo, AnagraficaStepGraduatoria body) throws ServiceException {
		final String methodName = "insertAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Long result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().insertAnagraficaStepGraduatoria(
					callerInfoDomanda, AnagraficaStepGraduatoriaConverter.toSrv(body));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer updateAnagraficaStepGraduatoria(CallerInfo callerInfo, AnagraficaStepGraduatoria body) throws ServiceException {
		final String methodName = "updateAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().updateAnagraficaStepGraduatoria(
					callerInfoDomanda, AnagraficaStepGraduatoriaConverter.toSrv(body));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<AnagraficaEta> getAnagraficaEta(CallerInfo callerInfo, String codOrdineScuola, String codAnagraficaGra, String codAnnoScolastico)
			throws ServiceException {

		final String methodName = "getAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<AnagraficaEta> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new AnagraficaEtaConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getAnagraficaEta(
							callerInfoDomanda, codOrdineScuola, codAnagraficaGra, codAnnoScolastico));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Long insertAnagraficaEta(CallerInfo callerInfo, AnagraficaEta body) throws ServiceException {
		final String methodName = "insertAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Long result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().insertAnagraficaEta(
					callerInfoDomanda, AnagraficaEtaConverter.toSrv(body));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer updateAnagraficaEta(CallerInfo callerInfo, AnagraficaEta body) throws ServiceException {
		final String methodName = "updateAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().updateAnagraficaEta(
					callerInfoDomanda, AnagraficaEtaConverter.toSrv(body));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Integer deleteAnagraficaEta(CallerInfo callerInfo, Long idEta) throws ServiceException {
		final String methodName = "deleteAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Integer result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = this.domandaSrvBean.getServiceInterface().deleteAnagraficaEta(callerInfoDomanda, idEta);
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<TipoFasciaEta> getTipiFasceEta(CallerInfo callerInfo) throws ServiceException {
		final String methodName = "getTipiFasceEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<TipoFasciaEta> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new TipoFasciaEtaConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getTipiFasceEta(callerInfoDomanda));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<RecordPostiLiberi> getReportPostiLiberi(String codOrdineScuola, CallerInfo callerInfo) throws ServiceException {
		final String methodName = "getReportPostiLiberi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<RecordPostiLiberi> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new RecordPostiLiberiConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getReportPostiLiberi(callerInfoDomanda, codOrdineScuola));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<RecordDomandeScuolaResidenza> getReportDomandeScuolaResidenza(CallerInfo callerInfo) throws ServiceException {
		final String methodName = "getReportDomandeScuolaResidenza";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<RecordDomandeScuolaResidenza> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new RecordDomandeScuolaResidenzaConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getReportDomandeScuolaResidenza(callerInfoDomanda));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<RecordPreferenzaScuola> getPreferenzeScuole(CallerInfo callerInfo, Long idAnagraficaGra, Long idDomanda) throws ServiceException {
		final String methodName = "getPreferenzeScuole";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<RecordPreferenzaScuola> result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new RecordPreferenzaScuolaConverter().convertAll(
					this.domandaSrvBean.getServiceInterface().getPreferenzeScuole(callerInfoDomanda, idAnagraficaGra, idDomanda));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public Result modificaStatoScuolaPreferenza(CallerInfo callerInfo, Long idGraduatoria, Long idStatoAttuale, Long idStatoRipristino)
			throws ServiceException {

		final String methodName = "modificaStatoScuolaPreferenza";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Result result;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);

			result = new ResultConverter().convert(
					this.domandaSrvBean.getServiceInterface().modificaStatoScuolaPreferenza(
							callerInfoDomanda, idGraduatoria, idStatoAttuale, idStatoRipristino));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	//////////////////////////////////////////////////////////////////////

	private static RicercaGraduatorieFilter buildRicercaGraduatorieFilter(GraduatorieFilter graduatorieFilter, TipoScuola tipoScuola) {
		RicercaGraduatorieFilter filter = new RicercaGraduatorieFilter();

		filter.setCodFasciaEta(graduatorieFilter.getCodiceFasciaEta());
		filter.setCodGraduatoria(graduatorieFilter.getCodiceGraduatoria());
		filter.setCodOrdineScuola(tipoScuola.getCod());
		filter.setCodScuola(graduatorieFilter.getCodiceScuola());
		filter.setCodTipologiaFrequenza(graduatorieFilter.getCodiceTipologiaFrequenza());
		filter.setStepGraduatoria(ConvertUtils.toInteger(graduatorieFilter.getCodiceStepGraduatoria()));

		return filter;
	}

    public Boolean getFlagBloccoGraduatoria(CallerInfo callerInfo, TipoScuola tipoScuola) throws ServiceException {
		final String methodName = "getFlagBloccoGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Boolean result ;
		try {
			CallerInfoDomanda callerInfoDomanda = new CallerInfoDomandaConverter().convert(callerInfo);


			switch (tipoScuola) {
				case NID:
					result = this.domandaSrvBean.getServiceInterface().getFlagBloccoGraduatoriaNidi(callerInfoDomanda);
					break;
				case MAT:
					result = this.domandaSrvBean.getServiceInterface().getFlagBloccoGraduatoriaMaterne(callerInfoDomanda);
					break;
				default:
					throw new UnsupportedOperationException();

			}

		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;



	}
}
