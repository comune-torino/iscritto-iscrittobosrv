package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildErrorResponse;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildSrvError;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.iscritto.iscrittobosrv.business.be.ErrorCodeEnum;
import it.csi.iscritto.iscrittobosrv.business.be.ScuoleApi;
import it.csi.iscritto.iscrittobosrv.business.be.TipoScuola;
import it.csi.iscritto.iscrittobosrv.dto.DomandeFilter;
import it.csi.iscritto.iscrittobosrv.dto.InfoScuola;
import it.csi.iscritto.iscrittobosrv.dto.ScuoleFilter;
import it.csi.iscritto.iscrittobosrv.dto.SrvError;
import it.csi.iscritto.iscrittobosrv.dto.UserInfo;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.DateUtils;
import it.csi.iscritto.iscrittobosrv.util.SpringSupportedResource;

public class ScuoleApiServiceImpl extends SpringSupportedResource implements ScuoleApi {

	@Autowired
	private ScuoleService scuoleService;

	@Autowired
	private AutorizzazioneService autorizzazioneService;

	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Override
	public Response getScuoleNido(String dataNascita, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getScuoleNido";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			String codAnno = this.scuoleService.getCodAnnoNidi(new Date());
			Date dataNascitaMinore = getDataNascitaMinore(dataNascita);

			List<InfoScuola> result = this.scuoleService.getElencoScuole(TipoScuola.NID, codAnno, dataNascitaMinore);
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
	public Response getScuoleMaterna(String dataNascita, String codAnno, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		final String methodName = "getScuoleMaterna";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			Date dataNascitaMinore = getDataNascitaMinore(dataNascita);

			List<InfoScuola> result = this.scuoleService.getElencoScuole(TipoScuola.MAT, codAnno, dataNascitaMinore);
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
	public Response getScuoleByUtente(String filter, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String methodName = "getScuoleByUtente";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		ScuoleFilter scuoleFilter = null;
		Response response = null;
		List<InfoScuola> infoScuole = new ArrayList<InfoScuola>();
		try {
			// parsifica il filter se necessario
			if (filter != null && filter.length() > 0) {
				log.info(buildMessage(getClass(), methodName, "Filtro composto: " + filter));
				try {
					scuoleFilter = parseFilter(filter);
				}
				catch (IllegalArgumentException e) {
					// filtro non parsificabile => errore
					SrvError error = buildSrvError(Status.BAD_REQUEST, ErrorCodeEnum.SCU_FILTER_001);
					error.setDetail(e.getMessage());

					log.error(buildMessage(getClass(), methodName, e.getMessage()));
					return Response.serverError().entity(error).build();
				}
			}
			else { // filtro di ricerca non valorizzato
				log.error(buildMessage(getClass(), methodName, ErrorCodeEnum.SCU_FILTER_002.getDescription()));
				return Response.serverError().entity(buildSrvError(Status.BAD_REQUEST, ErrorCodeEnum.SCU_FILTER_002)).build();
			}

			String codiceTipologiaScuola;
			// devo controllare la presenza del filtro di ricerca per ordine scuola (nidi o materne)
			if (scuoleFilter.getCodiceTipologiaScuola() != null && scuoleFilter.getCodiceTipologiaScuola().toString() != null) {
				codiceTipologiaScuola = scuoleFilter.getCodiceTipologiaScuola().toString();
			}
			else {
				log.error(buildMessage(getClass(), methodName, ErrorCodeEnum.SCU_FILTER_003.getDescription()));
				return Response.serverError().entity(buildSrvError(Status.BAD_REQUEST, ErrorCodeEnum.SCU_FILTER_003)).build();
			}

			// devo recuperare il CF dell'utente loggato
			UserInfo userInfo = this.autorizzazioneService.getCurrentUser(httpRequest);
			if (userInfo == null || StringUtils.isBlank(userInfo.getCodFisc())) {
				return buildErrorResponse("Nessun utente presente in sessione");
			}

			String cfOperatore = userInfo.getCodFisc();
			String codProfilo = this.autorizzazioneService.getCodiceProfiloCorrente(httpRequest);

			if (StringUtils.isBlank(codProfilo)) {
				return buildErrorResponse("Nessun profilo presente in sessione");
			}

			// invoco il servizio pd/pa di backend
			try {
				infoScuole = scuoleService.getElencoScuoleByUtente(cfOperatore, codProfilo, codiceTipologiaScuola);
			}
			catch (ServiceException e) {
				log.error(buildMessage(getClass(), methodName, e.getMessage()));
				return buildErrorResponse(e.getMessage());
			}

			if (infoScuole == null || infoScuole.size() == 0) {
				log.info(buildMessage(getClass(), methodName, "nessuna scuola trovata"));
			}
			else {
				log.info(buildMessage(getClass(), methodName, "Scuole trovate: [" + infoScuole.size() + "]"));
			}

			response = Response.ok(infoScuole).build();
			return response;

		}
		finally {
			log.debug(buildMessage(getClass(), methodName, LOG_END));
		}
	}

	/**
	 * parsifica la serializzazione JSON del filter. Serve per gestire i filter in formato JSON passati come query parameter
	 *
	 * @param filterString
	 *            il filtro in formato JSON
	 * @return
	 */
	private ScuoleFilter parseFilter(String filterString) {
		final String methodName = "parseFilter";

		ObjectMapper mapper = new ObjectMapper();
		ScuoleFilter f = null;
		try {
			f = mapper.reader(ScuoleFilter.class).readValue(filterString.getBytes());
			return f;
		}
		catch (IOException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new IllegalArgumentException("Filtro errato per " + DomandeFilter.class.getSimpleName() + ": "
					+ filterString + ". " + e.getMessage(), e);
		}
	}

	private static Date getDataNascitaMinore(String dataNascita) throws ParseException {
		return StringUtils.isNotBlank(dataNascita)
				? DateUtils.toDate(dataNascita, DateUtils.ISO_8601_FORMAT)
				: null;
	}

}
