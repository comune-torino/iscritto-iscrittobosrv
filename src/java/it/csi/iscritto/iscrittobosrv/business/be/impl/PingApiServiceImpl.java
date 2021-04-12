package it.csi.iscritto.iscrittobosrv.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.iscritto.iscrittobosrv.business.be.PingApi;
import it.csi.iscritto.iscrittobosrv.util.SpringSupportedResource;

public class PingApiServiceImpl extends SpringSupportedResource implements PingApi {
	public Response ping(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(getMessage()).build();
	}

	public String getMessage() {
		return "ping!!!!!";
	}

}
