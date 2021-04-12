package it.csi.iscritto.iscrittobosrv.business.be;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.iscritto.iscrittobosrv.dto.AmmissioniParam;
import it.csi.iscritto.iscrittobosrv.dto.AnagraficaEta;
import it.csi.iscritto.iscrittobosrv.dto.AnagraficaGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.AnagraficaStepGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.ClasseParam;
import it.csi.iscritto.iscrittobosrv.dto.DatiCondizionePunteggio;
import it.csi.iscritto.iscrittobosrv.dto.ParametriCalcoloGraduatoria;
import it.csi.iscritto.iscrittobosrv.dto.Scuola;

@Path("/domande")
public interface DomandeApi {

	@POST
	@Path("/graduatorie/graduatoria/calcolo")
	@Produces({ "application/json" })
	Response calcolaGraduatoria(
			ParametriCalcoloGraduatoria body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("/anagrafica/eta/elimina-anagrafica-eta/{idEta}")
	@Produces({ "application/json" })
	Response deleteAnagraficaEta(
			@PathParam("idEta") Long idEta,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("/anagrafica/graduatoria/elimina-step-graduatoria/{idStepGra}")
	@Produces({ "application/json" })
	Response deleteAnagraficaStepGraduatoria(
			@PathParam("idStepGra") Long idStepGra,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("/nido/scuole/classi/{idClasse}")
	@Produces({ "application/json" })
	Response deleteClasseNido(
			@PathParam("idClasse") Long idClasse,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("/materna/scuole/classi/{idClasse}")
	@Produces({ "application/json" })
	Response deleteClasseMaterna(
			@PathParam("idClasse") Long idClasse,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/anagrafica/eta/ordine-scuola/{codOrdineScuola}/codice-anagrafica/{codAnagraficaGra}/anno/{codAnnoScolastico}")
	@Produces({ "application/json" })
	Response getAnagraficaEta(
			@PathParam("codOrdineScuola") String codOrdineScuola,
			@PathParam("codAnagraficaGra") String codAnagraficaGra,
			@PathParam("codAnnoScolastico") String codAnnoScolastico,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/anagrafica/graduatoria/ordine-scuola/{codOrdineScuola}/codice-anagrafica/{codAnagraficaGra}/anno/{codAnnoScolastico}")
	@Produces({ "application/json" })
	Response getAnagraficaGraduatoria(
			@PathParam("codOrdineScuola") String codOrdineScuola,
			@PathParam("codAnagraficaGra") String codAnagraficaGra,
			@PathParam("codAnnoScolastico") String codAnnoScolastico,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/anagrafica/graduatorie")
	@Produces({ "application/json" })
	Response getAnagraficaGraduatorie(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/{idDomanda}")
	@Produces({ "application/json" })
	Response getDomandaNido(
			@PathParam("idDomanda") Long idDomanda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/{idDomanda}")
	@Produces({ "application/json" })
	Response getDomandaMaterna(
			@PathParam("idDomanda") Long idDomanda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido")
	@Produces({ "application/json" })
	Response getDomandeNido(
			@QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna")
	@Produces({ "application/json" })
	Response getDomandeMaterna(
			@QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/istruttoria/{cfOperatore}/{codProfilo}")
	@Produces({ "application/json" })
	Response getDomandeIstruttoriaNido(
			@PathParam("cfOperatore") String cfOperatore,
			@PathParam("codProfilo") String codProfilo,
			@QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/istruttoria/{cfOperatore}/{codProfilo}")
	@Produces({ "application/json" })
	Response getDomandeIstruttoriaMaterna(
			@PathParam("cfOperatore") String cfOperatore,
			@PathParam("codProfilo") String codProfilo,
			@QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/da-verificare/{codProfilo}")
	@Produces({ "application/json" })
	Response getDomandeNidoDaVerificare(
			@PathParam("codProfilo") String codProfilo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/da-verificare/{codProfilo}")
	@Produces({ "application/json" })
	Response getDomandeMaternaDaVerificare(
			@PathParam("codProfilo") String codProfilo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/anagrafica/step/graduatoria/ordine-scuola/{codOrdineScuola}/codice-anagrafica/{codAnagraficaGra}/anno/{codAnnoScolastico}")
	@Produces({ "application/json" })
	Response getElencoAnagraficaStepGraduatoria(
			@PathParam("codOrdineScuola") String codOrdineScuola,
			@PathParam("codAnagraficaGra") String codAnagraficaGra,
			@PathParam("codAnnoScolastico") String codAnnoScolastico,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/graduatorie/anni-scolastici")
	@Produces({ "application/json" })
	Response getElencoAnniScolastici(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/scuola/{codScuola}/anno/{codAnno}/classi")
	@Produces({ "application/json" })
	Response getElencoClassiNido(
			@PathParam("codScuola") String codScuola,
			@PathParam("codAnno") String codAnno,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/scuola/{codScuola}/anno/{codAnno}/classi")
	@Produces({ "application/json" })
	Response getElencoClassiMaterna(
			@PathParam("codScuola") String codScuola,
			@PathParam("codAnno") String codAnno,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/anagrafica-graduatorie")
	@Produces({ "application/json" })
	Response getElencoGraduatorieNidi(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/anagrafica-graduatorie")
	@Produces({ "application/json" })
	Response getElencoGraduatorieMaterne(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/domanda/{idDomanda}/nidi")
	@Produces({ "application/json" })
	Response getElencoNidi(
			@PathParam("idDomanda") Long idDomanda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/stati")
	@Produces({ "application/json" })
	Response getElencoStatiDomanda(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/graduatoria/anagrafica/{codiceGraduatoria}/step")
	@Produces({ "application/json" })
	Response getElencoStepGraduatoriaNido(
			@PathParam("codiceGraduatoria") String codiceGraduatoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/graduatoria/anagrafica/{codiceGraduatoria}/step")
	@Produces({ "application/json" })
	Response getElencoStepGraduatoriaMaterna(
			@PathParam("codiceGraduatoria") String codiceGraduatoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatoria/{codiceGraduatoria}/step/{step}/stato-graduatoria/{codiceStatoGraduatoria}/report/graduatoria-approvazione")
	@Produces({ "application/json" })
	Response getGraduatoriaApprovazioneNidi(
			@PathParam("codiceGraduatoria") String codiceGraduatoria,
			@PathParam("step") Integer step,
			@PathParam("codiceStatoGraduatoria") String codiceStatoGraduatoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatoria/{codiceGraduatoria}/step/{step}/stato-graduatoria/{codiceStatoGraduatoria}/report/graduatoria-approvazione")
	@Produces({ "application/json" })
	Response getGraduatoriaApprovazioneMaterne(
			@PathParam("codiceGraduatoria") String codiceGraduatoria,
			@PathParam("step") Integer step,
			@PathParam("codiceStatoGraduatoria") String codiceStatoGraduatoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/report/graduatoria-completa")
	@Produces({ "application/json" })
	Response getGraduatoriaCompletaNidi(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/report/graduatoria-completa")
	@Produces({ "application/json" })
	Response getGraduatoriaCompletaMaterne(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/domanda/{idDomanda}/istruttoria/condizioni/punteggio/{condizionePunteggio}/allegati")
	@Produces({ "application/json" })
	Response getInfoAllegatiDomanda(
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("condizionePunteggio") String condizionePunteggio,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/graduatoria/calcolo/info")
	@Produces({ "application/json" })
	Response getInfoCalcoloGraduatoriaNido(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/graduatoria/calcolo/info")
	@Produces({ "application/json" })
	Response getInfoCalcoloGraduatoriaMaterna(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/info")
	@Produces({ "application/json" })
	Response getInfoGeneraliNido(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/info")
	@Produces({ "application/json" })
	Response getInfoGeneraliMaterna(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/info-step")
	@Produces({ "application/json" })
	Response getInfoStepGraduatorieNido(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/info-step")
	@Produces({ "application/json" })
	Response getInfoStepGraduatorieMaterna(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/istruttoria/verbali/commissione/{tipoCommissione}/{codProfilo}")
	@Produces({ "application/json" })
	Response getInfoVerbaliNido(
			@PathParam("tipoCommissione") String tipoCommissione,
			@PathParam("codProfilo") String codProfilo,
			@NotNull @QueryParam("dataInizio") String dataInizio,
			@NotNull @QueryParam("dataFine") String dataFine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/istruttoria/verbali/commissione/{tipoCommissione}/{codProfilo}")
	@Produces({ "application/json" })
	Response getInfoVerbaliMaterna(
			@PathParam("tipoCommissione") String tipoCommissione,
			@PathParam("codProfilo") String codProfilo,
			@NotNull @QueryParam("dataInizio") String dataInizio,
			@NotNull @QueryParam("dataFine") String dataFine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/graduatoria/anagrafica/{codiceGraduatoria}")
	@Produces({ "application/json" })
	Response getParametriGraduatoriaNido(
			@PathParam("codiceGraduatoria") String codiceGraduatoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/graduatoria/anagrafica/{codiceGraduatoria}")
	@Produces({ "application/json" })
	Response getParametriGraduatoriaMaterna(
			@PathParam("codiceGraduatoria") String codiceGraduatoria,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/graduatoria/anagrafica/ultima")
	@Produces({ "application/json" })
	Response getParametriUltimaGraduatoriaNido(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/graduatoria/anagrafica/ultima")
	@Produces({ "application/json" })
	Response getParametriUltimaGraduatoriaMaterna(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/graduatorie/preferenze-scuole/{idAnagraficaGra}/domanda/{idDomanda}")
	@Produces({ "application/json" })
	Response getPreferenzeScuole(
			@PathParam("idAnagraficaGra") Long idAnagraficaGra,
			@PathParam("idDomanda") Long idDomanda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/graduatorie/report/elenco-posti/{codOrdineScuola}")
	@Produces({ "application/json" })
	Response getReportPostiLiberi(
			@PathParam("codOrdineScuola") String codOrdineScuola,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/graduatorie/report/domande-scuola-residenza")
	@Produces({ "application/json" })
	Response getReportDomandeScuolaResidenza(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/report/residenze-forzate")
	@Produces({ "application/json" })
	Response getResidenzeForzateNidi(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/report/residenze-forzate")
	@Produces({ "application/json" })
	Response getResidenzeForzateMaterne(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/domanda/{idDomanda}/istruttoria/condizioni/punteggio/{condizionePunteggio}/storico")
	@Produces({ "application/json" })
	Response getStoricoCondizioniPunteggio(
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("condizionePunteggio") String condizionePunteggio,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/testataDomanda/{idDomanda}")
	@Produces({ "application/json" })
	Response getTestataDomandaById(
			@PathParam("idDomanda") Long idDomanda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/anagrafica/eta/tipi-fasce-eta")
	@Produces({ "application/json" })
	Response getTipiFasceEta(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/anagrafica/eta/nuova-anagrafica-eta")
	@Produces({ "application/json" })
	Response insertAnagraficaEta(
			AnagraficaEta body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/anagrafica/graduatoria/nuova-graduatoria")
	@Produces({ "application/json" })
	Response insertAnagraficaGraduatoria(
			AnagraficaGraduatoria body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/anagrafica/graduatoria/nuovo-step-graduatoria")
	@Produces({ "application/json" })
	Response insertAnagraficaStepGraduatoria(
			AnagraficaStepGraduatoria body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/nido/scuole/classi/nuova-classe")
	@Produces({ "application/json" })
	Response insertClasseNido(
			ClasseParam body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/materna/scuole/classi/nuova-classe")
	@Produces({ "application/json" })
	Response insertClasseMaterna(
			ClasseParam body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/nido/domanda/{idDomanda}/scuola/{codScuola}/tempo/{codTipoFrequenza}")
	@Produces({ "application/json" })
	Response insertNidoFuoriTermine(
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("codScuola") String codScuola,
			@PathParam("codTipoFrequenza") String codTipoFrequenza,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/materna/domanda/{idDomanda}/scuola/{codScuola}/tempo/{codTipoFrequenza}")
	@Produces({ "application/json" })
	Response insertMaternaFuoriTermine(
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("codScuola") String codScuola,
			@PathParam("codTipoFrequenza") String codTipoFrequenza,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/nido/{idDomanda}/istruttoria/condizioni/punteggio/{condizionePunteggio}/modifica/{count}")
	@Produces({ "application/json" })
	Response modificaCondizionePunteggioNido(
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("condizionePunteggio") String condizionePunteggio,
			@PathParam("count") Integer count,
			DatiCondizionePunteggio body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/materna/{idDomanda}/istruttoria/condizioni/punteggio/{condizionePunteggio}/modifica/{count}")
	@Produces({ "application/json" })
	Response modificaCondizionePunteggioMaterna(
			@PathParam("idDomanda") Long idDomanda,
			@PathParam("condizionePunteggio") String condizionePunteggio,
			@PathParam("count") Integer count,
			DatiCondizionePunteggio body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/domanda/{idDomanda}/nidi/stato")
	@Produces({ "application/json" })
	Response modificaStatoNidi(
			@PathParam("idDomanda") Long idDomanda,
			List<Scuola> body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/domanda/{idDomanda}/materne/stato")
	@Produces({ "application/json" })
	Response modificaStatoMaterne(
			@PathParam("idDomanda") Long idDomanda,
			List<Scuola> body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/graduatorie/modifica-stato-scuola-preferenza/graduatoria/{idGraduatoria}/stato-attuale/{idStatoAttuale}/stato-ripristino/{idStatoRipristino}")
	@Produces({ "application/json" })
	Response modificaStatoScuolaPreferenza(
			@PathParam("idGraduatoria") Long idGraduatoria,
			@PathParam("idStatoAttuale") Long idStatoAttuale,
			@PathParam("idStatoRipristino") Long idStatoRipristino,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie")
	@Produces({ "application/json" })
	Response ricercaGraduatorieNidi(
			@NotNull @QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatorie/dse")
	@Produces({ "application/json" })
	Response ricercaGraduatorieNidiDse(
			@NotNull @QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie")
	@Produces({ "application/json" })
	Response ricercaGraduatorieMaterne(
			@NotNull @QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatorie/dse")
	@Produces({ "application/json" })
	Response ricercaGraduatorieMaterneDse(
			@NotNull @QueryParam("filter") String filter,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/anagrafica/eta/modifica-anagrafica-eta")
	@Produces({ "application/json" })
	Response updateAnagraficaEta(
			AnagraficaEta body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/anagrafica/graduatoria/modifica-graduatoria")
	@Produces({ "application/json" })
	Response updateAnagraficaGraduatoria(
			AnagraficaGraduatoria body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/anagrafica/graduatoria/modifica-step-graduatoria")
	@Produces({ "application/json" })
	Response updateAnagraficaStepGraduatoria(
			AnagraficaStepGraduatoria body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/nido/scuole/classi")
	@Produces({ "application/json" })
	Response updateClasseNido(
			ClasseParam body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/materna/scuole/classi")
	@Produces({ "application/json" })
	Response updateClasseMaterna(
			ClasseParam body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/scuole/classi/ammissioni")
	@Produces({ "application/json" })
	Response updateFlagAmmissioni(
			AmmissioniParam body,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/nido/graduatoria/blocco")
	@Produces({ "application/json" })
	Response getFlagBloccoGraduatoriaNido(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/materna/graduatoria/blocco")
	@Produces({ "application/json" })
	Response getFlagBloccoGraduatoriaMaterna(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
