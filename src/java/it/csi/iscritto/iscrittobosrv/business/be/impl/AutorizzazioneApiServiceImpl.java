package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_ERROR;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildErrorResponse;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildSrvError;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.iscritto.iscrittobosrv.business.be.AutorizzazioneApi;
import it.csi.iscritto.iscrittobosrv.business.be.ErrorCodeEnum;
import it.csi.iscritto.iscrittobosrv.business.be.TipoScuola;
import it.csi.iscritto.iscrittobosrv.dto.Attivita;
import it.csi.iscritto.iscrittobosrv.dto.CondizionePunteggio;
import it.csi.iscritto.iscrittobosrv.dto.Funzione;
import it.csi.iscritto.iscrittobosrv.dto.Profilo;
import it.csi.iscritto.iscrittobosrv.dto.UserInfo;
import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.dto.rest.ResponseBody;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.RestUtils;
import it.csi.iscritto.iscrittobosrv.util.SpringSupportedResource;
import it.csi.iscritto.iscrittobosrv.util.audit.LogAuditOperations;
import it.csi.iscritto.iscrittobosrv.util.builder.CallerInfoBuilder;

public class AutorizzazioneApiServiceImpl extends SpringSupportedResource implements AutorizzazioneApi {
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Autowired
	private AutorizzazioneService autorizzazioneService;

	@Override
	public Response getCurrentUser(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getCurrentUser";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response = null;
		try {
			UserInfo userInfo = autorizzazioneService.getCurrentUser(httpRequest);
			response = Response.ok(userInfo).build();
			if (response == null) {
				log.warn(buildMessage(getClass(), methodName, "Nessun utente loggato"));
				return Response.serverError().entity(
						buildSrvError(Status.NOT_FOUND.getStatusCode(), "UTE-001", "Utente non loggato")).build();
			}
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}

		return response;
	}

	@Override
	public Response getElencoFunzioniByCF(String cf, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getElencoFunzioniByCF";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response = null;
		try {
			// String codiceFiscale = this.autorizzazioneService.getCodiceFiscaleOperatore(httpRequest);
			String codiceProfilo = this.autorizzazioneService.getCodiceProfiloCorrente(httpRequest);

			List<Funzione> funzioni = autorizzazioneService.getElencoFunzioni(cf, codiceProfilo);
			response = Response.ok(new ResponseBody<>(RestUtils.buildSuccessStatus(), funzioni)).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return buildErrorResponse(e.getMessage());
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}

		return response;
	}

	@Override
	public Response getProfiliUtenteCorrente(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getProfiliUtenteCorrente";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response = null;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			String codiceFiscaleUser = this.autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();
			if (codiceFiscaleUser == null || codiceFiscaleUser.length() == 0) {
				log.error(buildMessage(getClass(), methodName, ErrorCodeEnum.UTE_001.getDescription()));
				return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
			}

			boolean isValid = this.autorizzazioneService.checkOperatore(callerInfo, codiceFiscaleUser);
			if (!isValid) {
				log.error(buildMessage(getClass(), methodName, ErrorCodeEnum.UTE_002.getDescription()));
				return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_002)).build();
			}

			List<Profilo> result = this.autorizzazioneService.getProfiliOperatore(callerInfo, codiceFiscaleUser);

			response = Response.ok(result).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getElencoAttivitaByCodiceFiscaleAndCodiceFunzione(String codiceFiscale, String codiceFunzione,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String methodName = "getElencoAttivitaByCodiceFiscaleAndCodiceFunzione";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			// String codiceFiscale = this.autorizzazioneService.getCodiceFiscaleOperatore(httpRequest);
			String codiceProfilo = this.autorizzazioneService.getCodiceProfiloCorrente(httpRequest);

			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<Attivita> attivita = autorizzazioneService.getElencoAttivitaByCodiceFiscaleAndCodiceFunzione(
					callerInfo, codiceFiscale, codiceProfilo, codiceFunzione);

			if (attivita != null && attivita.size() != 0) {
				response = Response.ok(new ResponseBody<>(RestUtils.buildSuccessStatus(), attivita)).build();
			}
			else {
				response = Response.ok(new ResponseBody<>(RestUtils.buildNotFoundStatus(), null)).build();
			}
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return buildErrorResponse(e.getMessage());
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}

		return response;
	}

	@Override
	public Response getAutorizzazioniUtenteCorrente(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getAutorizzazioniUtenteCorrente";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			// String codiceFiscale = this.autorizzazioneService.getCodiceFiscaleOperatore(httpRequest);
			String codiceProfilo = this.autorizzazioneService.getCodiceProfiloCorrente(httpRequest);

			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			// recupero l'utente loggato
			String codiceFiscaleUser = this.autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();
			if (codiceFiscaleUser == null || codiceFiscaleUser.length() == 0) {
				return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
			}

			List<Funzione> autorizzazioni = this.autorizzazioneService.getAutorizzazioniUtenteCorrente(
					callerInfo, codiceFiscaleUser, codiceProfilo);

			response = Response.ok(autorizzazioni).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}

		return response;
	}

	@Override
	public Response getProfiloSelezionato(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getProfiloSelezionato";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Profilo profilo = this.autorizzazioneService.getProfiloCorrente(httpRequest);
		Response response = Response.ok(profilo).build();

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response setProfilo(String codiceProfilo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "setProfilo";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			this.autorizzazioneService.setCodiceProfiloCorrente(httpRequest, codiceProfilo);
			response = Response.ok(null).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return buildErrorResponse(e.getMessage());
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response localLogout(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "localLogout";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));
		try {
			HttpSession session = httpRequest.getSession();
			if (session != null) {
				session.invalidate();
			}
			else {
				log.warn(buildMessage(getClass(), methodName, "Sessione NON presente o scaduta"));
			}
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}

		return null;
	}

	@Override
	public Response getCondizioniPunteggioNidi(String codiceFiscale, String tipoIstruttoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getCondizioniPunteggio(TipoScuola.NID, codiceFiscale, tipoIstruttoria, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getCondizioniPunteggioMaterne(String codiceFiscale, String tipoIstruttoria, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return this.getCondizioniPunteggio(TipoScuola.MAT, codiceFiscale, tipoIstruttoria, securityContext, httpHeaders, httpRequest);
	}

	private Response getCondizioniPunteggio(TipoScuola tipoScuola, String codiceFiscale, String tipoIstruttoria, SecurityContext securityContext,
			HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getCondizioniPunteggio";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response = null;
		try {
			// String codiceFiscale = this.autorizzazioneService.getCodiceFiscaleOperatore(httpRequest);
			String codiceProfilo = this.autorizzazioneService.getCodiceProfiloCorrente(httpRequest);

			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			// recupero l'utente loggato
			String codiceFiscaleUser = autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();
			if (codiceFiscaleUser == null || codiceFiscaleUser.length() == 0) {
				return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
			}

			List<CondizionePunteggio> result = this.autorizzazioneService.getCondizioniPunteggio(
					callerInfo, tipoScuola, codiceFiscale, codiceProfilo, tipoIstruttoria);

			response = Response.ok(result).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}

		return response;
	}

	@Override
	public Response checkOperatore(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "checkOperatore";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response = null;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			String codiceFiscaleUser = autorizzazioneService.getCurrentUser(httpRequest).getCodFisc();
			if (codiceFiscaleUser == null || codiceFiscaleUser.length() == 0) {
				log.error(buildMessage(getClass(), methodName, ErrorCodeEnum.UTE_001.getDescription()));
				return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
			}

			boolean isValid = this.autorizzazioneService.checkOperatore(callerInfo, codiceFiscaleUser);
			if (!isValid) {
				log.error(buildMessage(getClass(), methodName, ErrorCodeEnum.UTE_002.getDescription()));
				return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_002)).build();
			}

			response = Response.ok(null).build();
		}
		catch (ServiceException e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return Response.serverError().entity(buildSrvError(Status.NOT_FOUND, ErrorCodeEnum.UTE_001)).build();
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

}
