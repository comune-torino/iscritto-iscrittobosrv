package it.csi.iscritto.iscrittobosrv.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/allegati")
public interface AllegatiApi {

	@POST
	@Path("/{idDomanda}/{idSoggetto}/{codTipologia}/{cfRichiedente}/{cfOperatore}")
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	Response uploadAllegato(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest,
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("idSoggetto") Long idSoggetto,
			@PathParam("codTipologia") String codTipologia,
			@PathParam("cfRichiedente") String cfRichiedente,
			@PathParam("cfOperatore") String cfOperatore,
			MultipartFormDataInput input);

	@GET
	@Path("/{idDomanda}/{idAllegato}/{cfRichiedente}")
	@Produces({ "application/octet-stream" })
	Response downloadAllegato(
			@Context SecurityContext sc, @Context HttpServletRequest request,
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("idAllegato") Long idAllegato,
			@PathParam("cfRichiedente") String cfRichiedente);

	@GET
	@Path("/ricevuta/allegato/{idAllegato}")
	@Produces({ "application/json" })
	Response getRicevutaAllegato(
			@Context SecurityContext sc, @Context HttpServletRequest request,
			@PathParam("idAllegato") Long idAllegato);

}
