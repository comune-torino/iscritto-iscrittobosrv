package it.csi.iscritto.iscrittobosrv.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/autorizzazione")
public interface AutorizzazioneApi {

	@GET
	@Path("/operatore")
	@Produces({ "application/json" })
	Response checkOperatore(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/currentUser/elenco-autorizzazioni")
	@Produces({ "application/json" })
	Response getAutorizzazioniUtenteCorrente(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/elenco-condizioni-punteggio/{codiceFiscale}/{tipoIstruttoria}")
	@Produces({ "application/json" })
	Response getCondizioniPunteggioNidi(
			@PathParam("codiceFiscale") String codiceFiscale,
			@PathParam("tipoIstruttoria") String tipoIstruttoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/elenco-condizioni-punteggio/{codiceFiscale}/{tipoIstruttoria}")
	@Produces({ "application/json" })
	Response getCondizioniPunteggioMaterne(
			@PathParam("codiceFiscale") String codiceFiscale,
			@PathParam("tipoIstruttoria") String tipoIstruttoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/currentUser")
	@Produces({ "application/json" })
	Response getCurrentUser(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/elenco-attivita/{codiceFiscale}/{codiceFunzione}")
	@Produces({ "application/json" })
	Response getElencoAttivitaByCodiceFiscaleAndCodiceFunzione(
			@PathParam("codiceFiscale") String codiceFiscale,
			@PathParam("codiceFunzione") String codiceFunzione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/elenco-funzioni/{cf}")
	@Produces({ "application/json" })
	Response getElencoFunzioniByCF(
			@PathParam("cf") String cf,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/currentUser/elenco-profili")
	@Produces({ "application/json" })
	Response getProfiliUtenteCorrente(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/currentUser/profilo-selezionato")
	@Produces({ "application/json" })
	Response getProfiloSelezionato(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/currentUser/profilo-selezionato/{codiceProfilo}")
	@Produces({ "application/json" })
	Response setProfilo(
			@PathParam("codiceProfilo") String codiceProfilo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/localLogout")
	@Produces({ "application/json" })
	Response localLogout(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
