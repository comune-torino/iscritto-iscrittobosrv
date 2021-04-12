package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_ERROR;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildErrorResponse;
import static it.csi.iscritto.iscrittobosrv.util.RestUtils.buildSuccessStatus;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.iscritto.iscrittobosrv.business.be.AllegatiApi;
import it.csi.iscritto.iscrittobosrv.dto.Documento;
import it.csi.iscritto.iscrittobosrv.dto.allegato.Allegato;
import it.csi.iscritto.iscrittobosrv.dto.allegato.RicevutaAllegato;
import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.dto.rest.ResponseBody;
import it.csi.iscritto.iscrittobosrv.integration.converter.allegato.AllegatoDocumentoConverter;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.RestUtils;
import it.csi.iscritto.iscrittobosrv.util.SpringSupportedResource;
import it.csi.iscritto.iscrittobosrv.util.audit.LogAuditOperations;
import it.csi.iscritto.iscrittobosrv.util.builder.CallerInfoBuilder;

public class AllegatiApiServiceImpl extends SpringSupportedResource implements AllegatiApi {
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Autowired
	private DomandeService domandeService;

	@Override
	public Response uploadAllegato(
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest,
			Long idDomanda, Long idSoggetto, String codTipologia, String cfRichiedente, String cfOperatore, MultipartFormDataInput input) {

		final String methodName = "uploadAllegato";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			// validazione parametri di input
			if (idDomanda == null) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro idDomanda non presente");
			}
			if (idSoggetto == null) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro idSoggetto non presente");
			}
			if (codTipologia == null || codTipologia.isEmpty()) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro codTipologia non presente");
			}
			if (cfRichiedente == null || cfRichiedente.isEmpty()) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro codice fiscale Richiedente non presente");
			}
			if (cfOperatore == null || cfOperatore.isEmpty()) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro cfOperatore non presente");
			}
			if (input == null || CollectionUtils.isEmpty(input.getParts())) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro multipart file non presente");
			}

			CallerInfo callerInfoInsert = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.INSERT)
					.oggOper(methodName)
					.build();

			List<Long> idAllegatoList = this.domandeService.insertAllegati(
					callerInfoInsert, cfRichiedente, cfOperatore, idDomanda, idSoggetto, codTipologia, input);

			CallerInfo callerInfoRead = CallerInfoBuilder.from(httpRequest)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			List<Documento> result = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(idAllegatoList)) {
				for (Long idAllegato : idAllegatoList) {
					Allegato allegato = this.domandeService.findAllegato(
							callerInfoRead, cfRichiedente, idDomanda, idAllegato, false);

					result.add(new AllegatoDocumentoConverter().convert(allegato));
				}
			}

			response = Response.ok(new ResponseBody<>(buildSuccessStatus(), result)).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return buildErrorResponse(e.getMessage());
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response downloadAllegato(SecurityContext sc, HttpServletRequest request, Long idDomanda, Long idAllegato, String cfRichiedente) {
		final String methodName = "downloadAllegato";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			if (idDomanda == null) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro idDomanda non presente");
			}
			if (idAllegato == null) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro idAllegato non presente");
			}
			if (StringUtils.isBlank(cfRichiedente)) {
				return RestUtils.buildErrorResponseBadRequest("DOM-ALL-01", "parametro cfRichiedente non presente");
			}

			CallerInfo callerInfo = CallerInfoBuilder.from(request)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			Allegato result = this.domandeService.findAllegato(
					callerInfo, cfRichiedente, idDomanda, idAllegato, true);

			if (result == null || result.getDocumento() == null) {
				log.error(buildMessage(getClass(), methodName, "file non trovato"));
				return Response.status(Status.NOT_FOUND).build();
			}

			response = Response.ok()
					.entity(result.getDocumento())
					.type(result.getMimeType())
					.header("Content-Length", result.getDocumento().length)
					.header("content-disposition", String.format("attachment; filename=\"%s\"", result.getNomeFile()))
					.build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

	@Override
	public Response getRicevutaAllegato(SecurityContext sc, HttpServletRequest request, Long idAllegato) {
		final String methodName = "getRicevutaAllegato";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Response response;
		try {
			CallerInfo callerInfo = CallerInfoBuilder.from(request)
					.operazione(LogAuditOperations.READ)
					.oggOper(methodName)
					.build();

			RicevutaAllegato result = this.domandeService.generaRicevutaAllegato(callerInfo, idAllegato);

			response = Response.ok(result).build();
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			return buildErrorResponse(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return response;
	}

}
