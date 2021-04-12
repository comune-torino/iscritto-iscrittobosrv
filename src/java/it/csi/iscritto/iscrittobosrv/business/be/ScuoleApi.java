package it.csi.iscritto.iscrittobosrv.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/scuole")
public interface ScuoleApi {

	@GET
	@Path("/currentUser")
	@Produces({ "application/json" })
	Response getScuoleByUtente(
			@QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido")
	@Produces({ "application/json" })
	Response getScuoleNido(
			@QueryParam("dataNascita") String dataNascita,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/anno/{codAnno}")
	@Produces({ "application/json" })
	Response getScuoleMaterna(
			@QueryParam("dataNascita") String dataNascita,
			@PathParam("codAnno") String codAnno,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
