package it.csi.iscritto.iscrittobosrv.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import it.csi.iscritto.iscrittobosrv.business.be.ErrorCodeEnum;
import it.csi.iscritto.iscrittobosrv.dto.SrvError;
import it.csi.iscritto.iscrittobosrv.dto.rest.AppStatus;
import it.csi.iscritto.iscrittobosrv.dto.rest.ResponseBody;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.util.builder.AppStatusBuilder;
import it.csi.iscritto.iscrittobosrv.util.builder.ErrorDetailBuilder;
import it.csi.iscritto.serviscritto.exception.domanda.ValidationException;

public final class RestUtils {
	private RestUtils() {
		/* NOP */
	}

	public static AppStatus buildSuccessStatus() {
		return AppStatusBuilder.newInstance()
				.whithStatus(String.valueOf(Status.OK.getStatusCode()))
				.build();
	}

	public static AppStatus buildNotFoundStatus() {
		return AppStatusBuilder.newInstance()
				.whithStatus(String.valueOf(Status.NOT_FOUND.getStatusCode()))
				.build();
	}

	public static AppStatus buildErrorStatus(String errorMessage) {
		return AppStatusBuilder.newInstance()
				.whithStatus(String.valueOf(Status.INTERNAL_SERVER_ERROR.getStatusCode()))
				.addError(ErrorDetailBuilder.newInstance().addDetail("message", errorMessage).build())
				.build();
	}

	public static Response buildErrorResponse(String errorMessage) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ResponseBody<>(buildErrorStatus(errorMessage), null))
				.build();
	}

	public static Response buildErrorResponse(Exception e) {
		Validate.notNull(e);

		if (e instanceof ServiceException) {
			String errorCode = ((ServiceException) e).getErrorCode();
			if (StringUtils.isNotBlank(errorCode)) {
				return buildErrorResponse(errorCode, e.getMessage());
			}
		}

		return buildErrorResponse(ErrorCodeEnum.SYS_001.getCode(), e.getMessage());
	}

	/**
	 * Costruisce una Response di errore causata da errata valorizzazione di parametri di input di un servizio
	 */
	public static Response buildErrorResponseBadRequest(String applicationErrorCode, String applicationErrorDescription) {
		SrvError error = new SrvError();
		error.setStatus(Status.BAD_REQUEST.getStatusCode());
		error.setCode(applicationErrorCode);
		error.setTitle(applicationErrorDescription);
		return Response.serverError().entity(error).build();
	}

	public static Response buildErrorResponse(String applicationErrorCode, String applicationErrorDescription) {
		SrvError error = new SrvError();
		error.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		error.setCode(applicationErrorCode);
		error.setTitle(applicationErrorDescription);
		return Response.serverError().entity(error).build();
	}

	public static String getErrorCode(ValidationException ve) {
		if (ve == null) {
			return null;
		}

		return getErrorCode(ve.getMessage());
	}

	public static SrvError buildSrvError(Status status, ErrorCodeEnum error) {
		return buildSrvError(status.getStatusCode(), error.getCode(), error.getDescription());
	}

	public static SrvError buildSrvError(Integer statusCode, String code, String title) {
		SrvError error = new SrvError();
		error.setStatus(statusCode);
		error.setCode(code);
		error.setTitle(title);

		return error;
	}

	//////////////////////////////////////////////////////////////////////

	private static String getErrorCode(String message) {
		if (StringUtils.isBlank(message)) {
			return null;
		}

		StringTokenizer tokenizer = new StringTokenizer(message, "|");
		List<String> tokens = new ArrayList<>();
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}

		String code;
		switch (tokens.size()) {
			case 2:
			case 3:
				code = tokens.get(0);
				break;
			default:
				code = ErrorCodeEnum.SYS_001.getCode();
				break;
		}

		return code;
	}

}
