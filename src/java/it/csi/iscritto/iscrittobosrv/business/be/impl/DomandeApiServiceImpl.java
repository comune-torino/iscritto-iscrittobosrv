package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_ERROR;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildErrorResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.iscritto.iscrittobosrv.business.be.DomandeApi;
import it.csi.iscritto.iscrittobosrv.business.be.ErrorCodeEnum;
import it.csi.iscritto.iscrittobosrv.business.be.TipoScuola;
import it.csi.iscritto.iscrittobosrv.dto.AmmissioniParam;
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
import it.csi.iscritto.iscrittobosrv.dto.SrvError;
import it.csi.iscritto.iscrittobosrv.dto.StatoDomanda;
import it.csi.iscritto.iscrittobosrv.dto.StepGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.TestataDomanda;
import it.csi.iscritto.iscrittobosrv.dto.TestataDomandaDaVerificare;
import it.csi.iscritto.iscrittobosrv.dto.TestataDomandaIstruttoria;
import it.csi.iscritto.iscrittobosrv.dto.TestataGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.TipoFasciaEta;
import it.csi.iscritto.iscrittobosrv.dto.VerbaleCommissione;
import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.exception.BusinessServiceException;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.SpringSupportedResource;
import it.csi.iscritto.iscrittobosrv.util.audit.LogAuditOperations;
import it.csi.iscritto.iscrittobosrv.util.builder.CallerInfoBuilder;

public class DomandeApiServiceImpl extends SpringSupportedResource implements DomandeApi {
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Autowired
	private DomandeService domandeService;

	@Autowired
	private AutorizzazioneService autorizzazioneService;

	@Override
	public Response getTestataDomandaById(Long idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getTestataDomandaById";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response = null;
		TestataDomanda testata = null;
		try {
			testata = domandeService.getTestataDomandaById(idDomanda);
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return buildErrorResponse(e.getMessage());
		}
		catch (BusinessServiceException e) {
			// domanda non trovata per id
			log.error(buildMessage(getClass(), methodName, "Nessuna domanda trovata con id = <" + idDomanda + ">"));
			return Response.serverError().entity(buildNotFoundException(ErrorCodeEnum.DOM_001)).build();
		}

		// condizione che non dovrebbe piu' manifestarsi (gestita nel catch della BusinessServiceException)
		if (testata == null) {
			log.error(buildMessage(getClass(), methodName, "Nessuna domanda trovata con id = <" + idDomanda + ">"));
			return Response.serverError().entity(buildNotFoundException(ErrorCodeEnum.DOM_001)).build();
		}

		response = Response.ok(testata).build();

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getDomandeNido(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getDomande(TipoScuola.NID, filter, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getDomandeMaterna(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getDomande(TipoScuola.MAT, filter, securityContext, httpHeaders, httpRequest);
	}

	private Response getDomande(TipoScuola tipoScuola, String filter, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getDomande";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		DomandeFilter domandeFilter = null;
		Response response = null;
		List<TestataDomanda> testataDomande = new ArrayList<TestataDomanda>();
		try {
			// parsifica il filter se necessario
			if (filter != null && filter.length() > 0) {
				log.info(buildMessage(getClass(), methodName, "Filtro composto: " + filter));
				try {
					domandeFilter = parseDomandeFilter(filter);
				}
				catch (IllegalArgumentException e) {
					// filtro non parsificabile => errore
					log.error(buildMessage(getClass(), methodName, e.getMessage()));
					return Response.serverError().entity(buildBadRequestException(ErrorCodeEnum.DOM_FILTER_001, e)).build();
				}
			}
			else {
				log.error(buildMessage(getClass(), methodName, "Filtro di ricerca NON valorizzato"));
				return Response.serverError().entity(buildBadRequestException(ErrorCodeEnum.DOM_FILTER_002, null)).build();
			}

			// richiamo il servizio di backend per l'applicazione del filtro di ricerca
			List<Long> elencoIdDomande;
			try {
				CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
						.operazione(LogAuditOperations.READ)
						.oggOper(methodName)
						.build();

				String codiceFiscaleOperatore = this.autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();
				String codProfilo = this.autorizzazioneService.getCodiceProfiloCorrente(httpRequest);

				elencoIdDomande = this.domandeService.getElencoDomande(
						callerInfo, tipoScuola, codiceFiscaleOperatore, codProfilo, domandeFilter);
			}
			catch (ServiceException e) {
				log.error(buildMessage(getClass(), methodName, e.getMessage()));
				return buildErrorResponse(e.getMessage());
			}

			if (elencoIdDomande == null || elencoIdDomande.size() == 0) {
				log.info(buildMessage(getClass(), methodName, "nessuna domanda trovata"));
			}
			else {
				log.info(buildMessage(getClass(), methodName, "Domande trovate: [" + elencoIdDomande.size() + "]"));
			}

			// ciclo sulle chiavi domanda trovate e per ognuna richiamo la sua testata (blocco di informazioni di base)
			for (Long idDomanda : elencoIdDomande) {
				try {
					testataDomande.add(domandeService.getTestataDomandaById(idDomanda));
				}
				catch (ServiceException | BusinessServiceException e) {
					log.error(buildMessage(getClass(), methodName, e.getMessage()));
					return buildErrorResponse(e.getMessage());
				}
			}

			response = Response.ok(testataDomande).build();
			return response;
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}
	}

	@Override
	public Response getDomandeIstruttoriaNido(String cfOperatore, String codProfilo, String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getDomandeIstruttoria(TipoScuola.NID, filter, cfOperatore, codProfilo, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getDomandeIstruttoriaMaterna( String cfOperatore , String codProfilo, String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return this.getDomandeIstruttoria(TipoScuola.MAT, filter, cfOperatore, codProfilo, securityContext, httpHeaders, httpRequest);
	}

	private Response getDomandeIstruttoria(TipoScuola tipoScuola, String filter, String cfOperatore, String codProfilo,SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getDomandeIstruttoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		DomandeFilter domandeFilter = null;
		Response response;

		if (filter != null && filter.length() > 0) {
			log.info(buildMessage(getClass(), methodName, "Filtro composto: " + filter));


			try {
				domandeFilter = parseDomandeFilter(filter);
			}
			catch (IllegalArgumentException e) {
				// filtro non parsificabile => errore
				log.error(buildMessage(getClass(), methodName, e.getMessage()));
				return Response.serverError().entity(buildBadRequestException(ErrorCodeEnum.DOM_FILTER_001, e)).build();
			}
		}
		else {
			log.error(buildMessage(getClass(), methodName, "Filtro di ricerca NON valorizzato"));
			return Response.serverError().entity(buildBadRequestException(ErrorCodeEnum.DOM_FILTER_002, null)).build();
		}

		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<TestataDomandaIstruttoria> result = this.domandeService.getElencoDomandeIstruttoria(callerInfo, tipoScuola, cfOperatore, codProfilo , domandeFilter);
			response = Response.ok(result).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e.getMessage());
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoStatiDomanda(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getElencoStatiDomanda";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		// richiamo il servizio di backend per l'applicazione del filtro di ricerca
		List<StatoDomanda> elencoStatiDomanda;
		try {
			elencoStatiDomanda = domandeService.getElencoStatiDomanda();
			return Response.ok(elencoStatiDomanda).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e.getMessage());
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}
	}

	@Override
	public Response getDomandaNido(Long idDomandaIscrizione, SecurityContext sc, HttpHeaders httpHeaders, HttpServletRequest request) {
		return this.getDomanda(TipoScuola.NID, idDomandaIscrizione, sc, httpHeaders, request);
	}

	@Override
	public Response getDomandaMaterna(Long idDomandaIscrizione, SecurityContext sc, HttpHeaders httpHeaders, HttpServletRequest request) {
		return this.getDomanda(TipoScuola.MAT, idDomandaIscrizione, sc, httpHeaders, request);
	}

	private Response getDomanda(TipoScuola tipoScuola, Long idDomandaIscrizione, SecurityContext sc, HttpHeaders httpHeaders, HttpServletRequest request) {
		final String methodName = "getDomanda";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(request)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			Domanda result = this.domandeService.getDomanda(callerInfo, tipoScuola, idDomandaIscrizione);

			// il servizio DEVE restituire un codice HTTP 404 se non trovata
			if (result == null) {
				log.error(buildMessage(getClass(), methodName, "Nessuna domanda trovata con id = <" + idDomandaIscrizione + ">"));
				return Response.serverError().entity(buildNotFoundException(ErrorCodeEnum.DOM_001)).build();
			}

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e.getMessage());
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getStoricoCondizioniPunteggio(Long idDomanda, String condizionePunteggio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getStoricoCondizioniPunteggio";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<DatiCondizionePunteggio> result =
					this.domandeService.getStoricoCondizioniPunteggio(callerInfo, idDomanda, condizionePunteggio);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response modificaCondizionePunteggioNido(Long idDomanda, String condizionePunteggio, Integer count, DatiCondizionePunteggio body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return modificaCondizionePunteggio(TipoScuola.NID, idDomanda, condizionePunteggio, count, body, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response modificaCondizionePunteggioMaterna(Long idDomanda, String condizionePunteggio, Integer count, DatiCondizionePunteggio body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return modificaCondizionePunteggio(TipoScuola.MAT, idDomanda, condizionePunteggio, count, body, securityContext, httpHeaders, httpRequest);
	}

	private Response modificaCondizionePunteggio(TipoScuola tipoScuola, Long idDomanda, String condizionePunteggio, Integer count, DatiCondizionePunteggio body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "modificaCondizionePunteggio";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			String codiceFiscaleOperatore = autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();
			this.domandeService.modificaCondizionePunteggio(
					callerInfo, tipoScuola, idDomanda, condizionePunteggio, codiceFiscaleOperatore, count, body);

			response = Response.ok().build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getInfoAllegatiDomanda(Long idDomanda, String condizionePunteggio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getInfoAllegatiDomanda";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<InfoAllegatoSoggetto> result = this.domandeService.getInfoAllegatiDomanda(callerInfo, idDomanda, condizionePunteggio);
			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getInfoVerbaliNido(String tipoCommissione, String codProfilo, String dataInizio, String dataFine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getInfoVerbali(TipoScuola.NID, tipoCommissione, codProfilo, dataInizio, dataFine, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getInfoVerbaliMaterna(String tipoCommissione, String codProfilo, String dataInizio, String dataFine, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getInfoVerbali(TipoScuola.MAT, tipoCommissione, codProfilo, dataInizio, dataFine, securityContext, httpHeaders, httpRequest);
	}

	private Response getInfoVerbali(TipoScuola tipoScuola, String tipoCommissione, String codProfilo, String dataInizio, String dataFine, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "getInfoVerbali";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<VerbaleCommissione> result = this.domandeService.getInfoVerbali(
					callerInfo,
					codProfilo,
					tipoScuola,
					tipoCommissione,
					DateUtils.toDate(dataInizio, DateUtils.ISO_8601_FORMAT),
					DateUtils.toDate(dataFine, DateUtils.ISO_8601_FORMAT));

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getInfoGeneraliNido(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getInfoGenerali(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getInfoGeneraliMaterna(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getInfoGenerali(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getInfoGenerali(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getInfoGenerali";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			InfoGenerali result = this.domandeService.getInfoGenerali(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getDomandeNidoDaVerificare(String codProfilo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getDomandeDaVerificare(TipoScuola.NID, codProfilo, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getDomandeMaternaDaVerificare(String codProfilo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getDomandeDaVerificare(TipoScuola.MAT, codProfilo, securityContext, httpHeaders, httpRequest);
	}

	private Response getDomandeDaVerificare(TipoScuola tipoScuola, String codProfilo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getDomandeDaVerificare";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<TestataDomandaDaVerificare> result = this.domandeService.getDomandeDaVerificare(callerInfo, tipoScuola, codProfilo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoNidi(Long idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getElencoNidi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<Scuola> result = this.domandeService.getElencoNidi(callerInfo, idDomanda);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response modificaStatoNidi(Long idDomanda, List<Scuola> scuole, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.modificaStatoScuole(TipoScuola.NID, idDomanda, scuole, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response modificaStatoMaterne(Long idDomanda, List<Scuola> scuole, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.modificaStatoScuole(TipoScuola.MAT, idDomanda, scuole, securityContext, httpHeaders, httpRequest);
	}

	private Response modificaStatoScuole(TipoScuola tipoScuola, Long idDomanda, List<Scuola> scuole, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "modificaStatoScuole";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			this.domandeService.modificaStatoScuole(callerInfo, tipoScuola, idDomanda, scuole);

			response = Response.ok().build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getParametriUltimaGraduatoriaNido(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getParametriUltimaGraduatoria(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getParametriUltimaGraduatoriaMaterna(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getParametriUltimaGraduatoria(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getParametriUltimaGraduatoria(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getParametriUltimaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			ParametriGraduatoria result = this.domandeService.getParametriUltimaGraduatoria(tipoScuola, callerInfo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoGraduatorieNidi(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getElencoGraduatorie(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getElencoGraduatorieMaterne(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getElencoGraduatorie(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getElencoGraduatorie(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getElencoGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<Graduatoria> result = this.domandeService.getElencoGraduatorie(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoStepGraduatoriaNido(String codiceGraduatoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getElencoStepGraduatoria(TipoScuola.NID, codiceGraduatoria, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getElencoStepGraduatoriaMaterna(String codiceGraduatoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getElencoStepGraduatoria(TipoScuola.MAT, codiceGraduatoria, securityContext, httpHeaders, httpRequest);
	}

	private Response getElencoStepGraduatoria(TipoScuola tipoScuola, String codiceGraduatoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getElencoStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<StepGraduatoria> result = this.domandeService.getElencoStepGraduatoria(callerInfo, tipoScuola, codiceGraduatoria);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getParametriGraduatoriaNido(String codiceGraduatoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getParametriGraduatoria(TipoScuola.NID, codiceGraduatoria, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getParametriGraduatoriaMaterna(String codiceGraduatoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getParametriGraduatoria(TipoScuola.MAT, codiceGraduatoria, securityContext, httpHeaders, httpRequest);
	}

	private Response getParametriGraduatoria(TipoScuola tipoScuola, String codiceGraduatoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getParametriGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			ParametriGraduatoria result = this.domandeService.getParametriGraduatoria(callerInfo, tipoScuola, codiceGraduatoria);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response ricercaGraduatorieNidi(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.ricercaGraduatorie(TipoScuola.NID, filter, securityContext, httpHeaders, httpRequest, false);
	}

	@Override
	public Response ricercaGraduatorieNidiDse(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.ricercaGraduatorie(TipoScuola.NID, filter, securityContext, httpHeaders, httpRequest, true);
	}

	@Override
	public Response ricercaGraduatorieMaterne(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.ricercaGraduatorie(TipoScuola.MAT, filter, securityContext, httpHeaders, httpRequest, false);
	}

	@Override
	public Response ricercaGraduatorieMaterneDse(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.ricercaGraduatorie(TipoScuola.MAT, filter, securityContext, httpHeaders, httpRequest, true);
	}

	private Response ricercaGraduatorie(TipoScuola tipoScuola, String filter, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, boolean dse) {

		final String methodName = "ricercaGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		GraduatorieFilter graduatorieFilter;
		if (filter != null && filter.length() > 0) {
			log.info(buildMessage(getClass(), methodName, "Filtro composto: " + filter));
			try {
				graduatorieFilter = parseGraduatorieFilter(filter);
			}
			catch (IllegalArgumentException e) {
				log.error(buildMessage(getClass(), methodName, e.getMessage()));
				return Response.serverError().entity(buildBadRequestException(ErrorCodeEnum.GRA_FILTER_001, e)).build();
			}
		}
		else {
			log.error(buildMessage(getClass(), methodName, "Filtro di ricerca NON valorizzato"));
			return Response.serverError().entity(buildBadRequestException(ErrorCodeEnum.GRA_FILTER_002, null)).build();
		}

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<TestataGraduatoria> result = this.domandeService.ricercaGraduatorie(callerInfo, graduatorieFilter, tipoScuola, dse);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getInfoCalcoloGraduatoriaNido(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getInfoCalcoloGraduatoria(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getInfoCalcoloGraduatoriaMaterna(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getInfoCalcoloGraduatoria(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getInfoCalcoloGraduatoria(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getInfoCalcoloGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			InfoCalcoloGraduatoria result = this.domandeService.getInfoCalcoloGraduatoria(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response calcolaGraduatoria(ParametriCalcoloGraduatoria body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "calcolaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			this.domandeService.calcolaGraduatoria(callerInfo, body);

			response = Response.ok(null).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoAnniScolastici(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getElencoAnniScolastici";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<AnnoScolastico> result = this.domandeService.getElencoAnniScolastici(callerInfo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoClassiNido(String codScuola, String codAnno, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getElencoClassi(TipoScuola.NID, codScuola, codAnno, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getElencoClassiMaterna(String codScuola, String codAnno, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getElencoClassi(TipoScuola.MAT, codScuola, codAnno, securityContext, httpHeaders, httpRequest);
	}

	private Response getElencoClassi(TipoScuola tipoScuola, String codScuola, String codAnno, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getElencoClassi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<Classe> result = this.domandeService.getElencoClassi(callerInfo, tipoScuola, codScuola, codAnno);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response insertClasseNido(ClasseParam body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.insertClasse(TipoScuola.NID, body, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response insertClasseMaterna(ClasseParam body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.insertClasse(TipoScuola.MAT, body, securityContext, httpHeaders, httpRequest);
	}

	private Response insertClasse(TipoScuola tipoScuola, ClasseParam body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "insertClasse";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			Long result = this.domandeService.insertClasse(callerInfo, tipoScuola, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response updateClasseNido(ClasseParam body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.updateClasse(TipoScuola.NID, body, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response updateClasseMaterna(ClasseParam body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.updateClasse(TipoScuola.MAT, body, securityContext, httpHeaders, httpRequest);
	}

	private Response updateClasse(TipoScuola tipoScuola, ClasseParam body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "updateClasse";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.updateClasse(callerInfo, tipoScuola, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response deleteClasseNido(Long idClasse, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.deleteClasse(TipoScuola.NID, idClasse, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response deleteClasseMaterna(Long idClasse, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.deleteClasse(TipoScuola.MAT, idClasse, securityContext, httpHeaders, httpRequest);
	}

	private Response deleteClasse(TipoScuola tipoScuola, Long idClasse, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "deleteClasse";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.DELETE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.deleteClasse(callerInfo, tipoScuola, idClasse);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getResidenzeForzateNidi(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getResidenzeForzate(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getResidenzeForzateMaterne(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getResidenzeForzate(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getResidenzeForzate(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getResidenzeForzate";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			InfoResidenzeForzate result = this.domandeService.getResidenzeForzate(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getGraduatoriaCompletaNidi(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getGraduatoriaCompleta(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getGraduatoriaCompletaMaterne(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getGraduatoriaCompleta(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getGraduatoriaCompleta(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getGraduatoriaCompleta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<GraduatoriaCompleta> result = this.domandeService.getGraduatoriaCompleta(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response updateFlagAmmissioni(AmmissioniParam body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "updateFlagAmmissioni";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			List<Long> idClasseList = body.getIdClasseList();
			Boolean value = body.isValue();

			Integer result = this.domandeService.updateFlagAmmissioni(callerInfo, idClasseList, value);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getFlagBloccoGraduatoriaNido(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getFlagBloccoGraduatoria(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	private Response getFlagBloccoGraduatoria(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getFlagBloccoGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			Boolean result = this.domandeService.getFlagBloccoGraduatoria(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getFlagBloccoGraduatoriaMaterna(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getFlagBloccoGraduatoria(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getInfoStepGraduatorieNido(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getInfoStepGraduatorie(TipoScuola.NID, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getInfoStepGraduatorieMaterna(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return this.getInfoStepGraduatorie(TipoScuola.MAT, securityContext, httpHeaders, httpRequest);
	}

	private Response getInfoStepGraduatorie(TipoScuola tipoScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getInfoStepGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<InfoStepGraduatoria> result = this.domandeService.getInfoStepGraduatorie(callerInfo, tipoScuola);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getGraduatoriaApprovazioneNidi(String codiceGraduatoria, Integer step, String codiceStatoGraduatoria, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return this.getGraduatoriaApprovazione(TipoScuola.NID, codiceGraduatoria, step, codiceStatoGraduatoria, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getGraduatoriaApprovazioneMaterne(String codiceGraduatoria, Integer step, String codiceStatoGraduatoria, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return this.getGraduatoriaApprovazione(TipoScuola.MAT, codiceGraduatoria, step, codiceStatoGraduatoria, securityContext, httpHeaders, httpRequest);
	}

	private Response getGraduatoriaApprovazione(TipoScuola tipoScuola, String codiceGraduatoria, Integer step, String codiceStatoGraduatoria,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "getGraduatoriaApprovazione";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<GraduatoriaApprovazione> result = this.domandeService.getGraduatoriaApprovazione(
					callerInfo, tipoScuola, codiceGraduatoria, step, codiceStatoGraduatoria);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response insertNidoFuoriTermine(Long idDomanda, String codScuola, String codTipoFrequenza, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return this.insertScuolaFuoriTermine(TipoScuola.NID, idDomanda, codScuola, codTipoFrequenza, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response insertMaternaFuoriTermine(Long idDomanda, String codScuola, String codTipoFrequenza, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return this.insertScuolaFuoriTermine(TipoScuola.MAT, idDomanda, codScuola, codTipoFrequenza, securityContext, httpHeaders, httpRequest);
	}

	private Response insertScuolaFuoriTermine(TipoScuola tipoScuola, Long idDomanda, String codScuola, String codTipoFrequenza, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "insertScuolaFuoriTermine";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			String cfOperatore = autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();

			this.domandeService.insertScuolaFuoriTermine(
					callerInfo, tipoScuola, idDomanda, cfOperatore, codScuola, codTipoFrequenza);

			response = Response.ok(null).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getAnagraficaGraduatorie(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getAnagraficaGraduatorie";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<AnagraficaGraduatoria> result = this.domandeService.getAnagraficaGraduatorie(callerInfo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getAnagraficaGraduatoria(String codOrdineScuola, String codAnagraficaGra, String codAnnoScolastico, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "getAnagraficaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			AnagraficaGraduatoria result = this.domandeService.getAnagraficaGraduatoria(callerInfo, codOrdineScuola, codAnagraficaGra, codAnnoScolastico);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response insertAnagraficaGraduatoria(AnagraficaGraduatoria body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "insertAnagraficaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			Long result = this.domandeService.insertAnagraficaGraduatoria(callerInfo, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response updateAnagraficaGraduatoria(AnagraficaGraduatoria body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "updateAnagraficaGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.updateAnagraficaGraduatoria(callerInfo, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response deleteAnagraficaStepGraduatoria(Long idStepGra, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "deleteAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.DELETE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.deleteAnagraficaStepGraduatoria(callerInfo, idStepGra);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoAnagraficaStepGraduatoria(String codOrdineScuola, String codAnagraficaGra, String codAnnoScolastico,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "getElencoAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<AnagraficaStepGraduatoria> result = this.domandeService.getElencoAnagraficaStepGraduatoria(
					callerInfo, codOrdineScuola, codAnagraficaGra, codAnnoScolastico);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response insertAnagraficaStepGraduatoria(AnagraficaStepGraduatoria body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "insertAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			Long result = this.domandeService.insertAnagraficaStepGraduatoria(callerInfo, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response updateAnagraficaStepGraduatoria(AnagraficaStepGraduatoria body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "updateAnagraficaStepGraduatoria";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.updateAnagraficaStepGraduatoria(callerInfo, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getAnagraficaEta(String codOrdineScuola, String codAnagraficaGra, String codAnnoScolastico, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "getAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<AnagraficaEta> result = this.domandeService.getAnagraficaEta(
					callerInfo, codOrdineScuola, codAnagraficaGra, codAnnoScolastico);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response insertAnagraficaEta(AnagraficaEta body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "insertAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			Long result = this.domandeService.insertAnagraficaEta(callerInfo, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response updateAnagraficaEta(AnagraficaEta body, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "updateAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.updateAnagraficaEta(callerInfo, body);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response deleteAnagraficaEta(Long idEta, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "deleteAnagraficaEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.DELETE)
					.oggOper(methodName)
					.build();

			Integer result = this.domandeService.deleteAnagraficaEta(callerInfo, idEta);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getTipiFasceEta(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getTipiFasceEta";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<TipoFasciaEta> result = this.domandeService.getTipiFasceEta(callerInfo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getReportPostiLiberi(String codOrdineScuola, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getReportPostiLiberi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<RecordPostiLiberi> result = this.domandeService.getReportPostiLiberi(codOrdineScuola, callerInfo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getReportDomandeScuolaResidenza(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "ReportDomandeScuolaResidenza";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<RecordDomandeScuolaResidenza> result = this.domandeService.getReportDomandeScuolaResidenza(callerInfo);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}


	@Override
	public Response getPreferenzeScuole(Long idAnagraficaGra, Long idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getPreferenzeScuole";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<RecordPreferenzaScuola> result = this.domandeService.getPreferenzeScuole(callerInfo, idAnagraficaGra, idDomanda);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response modificaStatoScuolaPreferenza(Long idGraduatoria, Long idStatoAttuale, Long idStatoRipristino, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "modificaStatoScuolaPreferenza";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.UPDATE)
					.oggOper(methodName)
					.build();

			Result result = this.domandeService.modificaStatoScuolaPreferenza(callerInfo, idGraduatoria, idStatoAttuale, idStatoRipristino);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	/**
	 * parsifica la serializzazione JSON del filter. Serve per gestire i filter in formato JSON passati come query parameter
	 *
	 * @param filterString
	 *            il filtro in formato JSON
	 * @return
	 */
	private static DomandeFilter parseDomandeFilter(String filterString) {
		ObjectMapper mapper = new ObjectMapper();
		DomandeFilter f = null;
		try {
			f = mapper.reader(DomandeFilter.class).readValue(filterString.getBytes());
			return f;
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Filtro errato per " + DomandeFilter.class.getSimpleName() + ": "
					+ filterString + ". " + e.getMessage(), e);
		}
	}

	private static GraduatorieFilter parseGraduatorieFilter(String filterString) {
		ObjectMapper mapper = new ObjectMapper();
		GraduatorieFilter f = null;
		try {
			f = mapper.reader(GraduatorieFilter.class).readValue(filterString.getBytes());
			return f;
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Filtro errato per " + GraduatorieFilter.class.getSimpleName() + ": "
					+ filterString + ". " + e.getMessage(), e);
		}
	}

	private static SrvError buildNotFoundException(ErrorCodeEnum errorCode) {
		SrvError error = new SrvError();
		error.setStatus(Status.NOT_FOUND.getStatusCode());
		if (errorCode != null) {
			error.setCode(errorCode.getCode());
			error.setTitle(errorCode.getDescription());
		}

		return error;
	}

	private static SrvError buildBadRequestException(ErrorCodeEnum errorCode, Exception e) {
		SrvError error = new SrvError();
		error.setStatus(Status.BAD_REQUEST.getStatusCode());
		if (errorCode != null) {
			error.setCode(errorCode.getCode());
			error.setTitle(errorCode.getDescription());
		}

		if (e != null) {
			error.setDetail(e.getMessage());
		}

		return error;
	}

}
