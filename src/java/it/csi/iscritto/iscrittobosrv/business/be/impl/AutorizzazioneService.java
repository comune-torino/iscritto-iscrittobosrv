package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_ERROR;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.iscritto.iscrittobosrv.business.be.TipoScuola;
import it.csi.iscritto.iscrittobosrv.dto.Attivita;
import it.csi.iscritto.iscrittobosrv.dto.CondizionePunteggio;
import it.csi.iscritto.iscrittobosrv.dto.Funzione;
import it.csi.iscritto.iscrittobosrv.dto.Profilo;
import it.csi.iscritto.iscrittobosrv.dto.UserInfo;
import it.csi.iscritto.iscrittobosrv.dto.rest.CallerInfo;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.filter.IrideIdAdapterFilter;
import it.csi.iscritto.iscrittobosrv.integration.converter.AttivitaConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.CondizionePunteggioConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.FunzioneConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.profilazione.CallerInfoProfilazioneConverter;
import it.csi.iscritto.iscrittobosrv.integration.converter.profilazione.ProfiloConverter;
import it.csi.iscritto.iscrittobosrv.integration.service.ProfilazioneSrvBean;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.serviscritto.dto.profilazione.CallerInfoProfilazione;
import it.csi.iscritto.serviscritto.dto.profilazione.Operatore;
import it.csi.iscritto.serviscritto.dto.profilazione.PrivilegioOperatore;

/**
 *
 * @author 630
 *
 */
@Component
public class AutorizzazioneService {
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Autowired
	private ProfilazioneSrvBean profilazioneSrvBean;

	public UserInfo getCurrentUser(HttpServletRequest req) {
		UserInfo currentUser = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		return currentUser;
	}

	public void localLogout(HttpServletRequest req) {
		req.getSession().invalidate();
	}

	public List<Funzione> getElencoFunzioni(String codiceFiscale, String codiceProfilo) throws ServiceException {
		final String methodName = "getElencoFunzioni";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<Funzione> funzioni;
		try {
			funzioni = new FunzioneConverter().convertAll(
					this.profilazioneSrvBean.getServiceInterface().getFunzioniAbilitateOperatore(codiceFiscale, codiceProfilo));
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException("", e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return funzioni;
	}

	public List<Attivita> getElencoAttivitaByCodiceFiscaleAndCodiceFunzione(
			CallerInfo callerInfo, String codiceFiscale, String codiceProfilo, String codiceFunzione) throws ServiceException {

		final String methodName = "getElencoAttivitaByCodiceFiscaleAndCodiceFunzione";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<Attivita> attivita;
		try {
			CallerInfoProfilazione callerInfoProfilazione = new CallerInfoProfilazioneConverter().convert(callerInfo);
			PrivilegioOperatore[] privilegiOperatore = this.profilazioneSrvBean.getServiceInterface().getPrivilegiOperatore(
					callerInfoProfilazione, codiceFiscale, codiceProfilo);

			attivita = new AttivitaConverter()
					.withPrivilegi(privilegiOperatore)
					.withCodFunzione(codiceFunzione)
					.convertAll(this.profilazioneSrvBean.getServiceInterface().getAttivitaAbilitateOperatore(
							codiceFiscale, codiceProfilo, codiceFunzione));
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException(e.getMessage(), e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return attivita;
	}

	public Profilo getProfiloUtenteByCF(String codFisc) {
		return null;
	}

	/**
	 * Valorizza l'elenco delle abilitazioni concesse all'utente identificato dal codice fiscale in input. Per ogni entry di tipo macro-funzione e' associata la
	 * lista delle attivita' concesse (funzioni di secondo livello o sotto-menu' applicativi)
	 *
	 * @param callerInfo
	 * @param codiceFiscale
	 * @param codiceProfilo
	 * @return
	 * @throws ServiceException
	 */
	public List<Funzione> getAutorizzazioniUtenteCorrente(CallerInfo callerInfo, String codiceFiscale, String codiceProfilo) throws ServiceException {
		final String methodName = "getAutorizzazioniUtenteCorrente";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<Funzione> autorizzazioni = this.getElencoFunzioni(codiceFiscale, codiceProfilo);
		for (Funzione f : autorizzazioni) {
			f.setAttivita(this.getElencoAttivitaByCodiceFiscaleAndCodiceFunzione(callerInfo, codiceFiscale, codiceProfilo, f.getCodice()));
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return autorizzazioni;
	}

	public List<CondizionePunteggio> getCondizioniPunteggio(
			CallerInfo callerInfo, TipoScuola tipoScuola, String codiceFiscale, String codiceProfilo, String tipoIstruttoria) throws ServiceException {

		final String methodName = "getCondizioniPunteggio";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<CondizionePunteggio> result;
		try {
			CallerInfoProfilazione callerInfoProfilazione = new CallerInfoProfilazioneConverter().convert(callerInfo);

			switch (tipoScuola) {
				case NID:
					result = new CondizionePunteggioConverter().convertAll(
							this.profilazioneSrvBean.getServiceInterface().getCondizioniPunteggioNidi(
									callerInfoProfilazione, codiceFiscale, codiceProfilo, tipoIstruttoria));
					break;
				case MAT:
					result = new CondizionePunteggioConverter().convertAll(
							this.profilazioneSrvBean.getServiceInterface().getCondizioniPunteggioMaterne(
									callerInfoProfilazione, codiceFiscale, codiceProfilo, tipoIstruttoria));
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException(e.getMessage(), e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public List<Profilo> getProfiliOperatore(CallerInfo callerInfo, String codiceFiscale) throws ServiceException {
		final String methodName = "getProfiliOperatore";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		List<Profilo> result;
		try {
			CallerInfoProfilazione callerInfoProfilazione = new CallerInfoProfilazioneConverter().convert(callerInfo);

			result = new ProfiloConverter().convertAll(
					this.profilazioneSrvBean.getServiceInterface().getProfiliOperatore(callerInfoProfilazione, codiceFiscale));
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException(e.getMessage(), e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public boolean checkOperatore(CallerInfo callerInfo, String codiceFiscale) throws ServiceException {
		final String methodName = "checkOperatore";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		boolean result;
		try {
			CallerInfoProfilazione callerInfoProfilazione = new CallerInfoProfilazioneConverter().convert(callerInfo);

			Operatore operatore = this.profilazioneSrvBean.getServiceInterface().getOperatoreByCodiceFiscale(
					callerInfoProfilazione, codiceFiscale);

			result = operatore != null;
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException(e.getMessage(), e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	public String getCodiceFiscaleOperatore(HttpServletRequest request) {
		UserInfo currentUser = (UserInfo) request.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		if (currentUser != null) {
			return currentUser.getCodFisc();
		}

		return null;
	}

	public Profilo getProfiloCorrente(HttpServletRequest httpRequest) {
		Profilo profilo = (Profilo) httpRequest.getSession().getAttribute(Constants.PROFILO_SELEZIONATO);

		// FIXME: workaround: prendo il primo profilo.
		if (profilo == null) {
			String cfOperatore = this.getCodiceFiscaleOperatore(httpRequest);
			try {
				it.csi.iscritto.serviscritto.dto.profilazione.Profilo[] profiliOperatore =
						this.profilazioneSrvBean.getServiceInterface().getProfiliOperatore(null, cfOperatore);

				if (ArrayUtils.isNotEmpty(profiliOperatore)) {
					Profilo p = new Profilo();
					p.setCodice(profiliOperatore[0].getCodProfilo());
					p.setDescrizione(profiliOperatore[0].getDescProfilo());

					profilo = p;
				}
			}
			catch (Exception e) {
				return null;
			}
		}

		return profilo;
	}

	public String getCodiceProfiloCorrente(HttpServletRequest httpRequest) {
		Profilo profilo = this.getProfiloCorrente(httpRequest);
		return profilo == null ? null : profilo.getCodice();
	}

	public void setCodiceProfiloCorrente(HttpServletRequest httpRequest, String codiceProfilo) throws ServiceException {
		final String methodName = "setCodiceProfiloCorrente";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Profilo result = null;
		try {
			String cfOperatore = this.getCodiceFiscaleOperatore(httpRequest);

			it.csi.iscritto.serviscritto.dto.profilazione.Profilo[] profiliOperatore =
					this.profilazioneSrvBean.getServiceInterface().getProfiliOperatore(null, cfOperatore);

			if (ArrayUtils.isNotEmpty(profiliOperatore)) {
				for (it.csi.iscritto.serviscritto.dto.profilazione.Profilo profiloOperatore : profiliOperatore) {
					if (profiloOperatore.getCodProfilo().equals(codiceProfilo)) {
						result = new Profilo();
						result.setCodice(profiloOperatore.getCodProfilo());
						result.setDescrizione(profiloOperatore.getDescProfilo());
						break;
					}
				}
			}
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, LOG_ERROR), e);
			throw new ServiceException(e.getMessage(), e);
		}

		if (result == null) {
			throw new ServiceException("profilo non valido");
		}

		httpRequest.getSession().setAttribute(Constants.PROFILO_SELEZIONATO, result);
		log.debug(buildMessage(getClass(), methodName, LOG_END));
	}

}
