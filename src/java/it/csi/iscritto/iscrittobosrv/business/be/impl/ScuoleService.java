package it.csi.iscritto.iscrittobosrv.business.be.impl;

import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_BEGIN;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.LOG_END;
import static it.csi.iscritto.iscrittobosrv.util.LoggingUtils.buildMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.csi.wrapper.CSIException;
import it.csi.iscritto.iscrittobosrv.business.be.TipoScuola;
import it.csi.iscritto.iscrittobosrv.dto.InfoScuola;
import it.csi.iscritto.iscrittobosrv.exception.ServiceException;
import it.csi.iscritto.iscrittobosrv.integration.converter.scuola.InfoScuolaConverter;
import it.csi.iscritto.iscrittobosrv.integration.service.ScuoleSrvBean;
import it.csi.iscritto.iscrittobosrv.util.Constants;
import it.csi.iscritto.iscrittobosrv.util.RestUtils;
import it.csi.iscritto.serviscritto.dto.scuole.CriterioRicerca;
import it.csi.iscritto.serviscritto.exception.domanda.ValidationException;
import it.csi.iscritto.serviscritto.exception.scuole.ScuoleUserException;

@Component
public class ScuoleService {

	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME + ".business");

	@Autowired
	private ScuoleSrvBean scuoleSrvBean;

	public List<InfoScuola> getElencoScuole(TipoScuola tipoScuola, String codAnno, Date dataNascitaMinore) throws ServiceException {
		final String methodName = "getElencoScuole";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "tipoScuola:" + tipoScuola));
		Validate.notNull(tipoScuola);

		List<InfoScuola> result;
		try {
			CriterioRicerca criterio = new CriterioRicerca();
			criterio.setTipoScuola(tipoScuola.getCod());
			criterio.setCodAnno(codAnno);
			criterio.setDataNascita(dataNascitaMinore);

			result = new InfoScuolaConverter().convertAll(
					this.scuoleSrvBean.getServiceInterface().getElencoScuole(criterio));
		}
		catch (ValidationException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(RestUtils.getErrorCode(e), e.getMessage(), e);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

	/**
	 * Rimappa la chiamata al servizio di ricerca esterno implementato sulla componente serviscritto (esposto tramite cooperazione applicativa pd/pa) Il metodo
	 * rimappa filtro ed eventuale lista degli oggetti di ritorno dalgi oggetti nativi del servizi a quelli esposti sull'API Rest della attuale componente.
	 *
	 * @param cfOperatore
	 * @param codProfilo
	 * @param codiceTipologiaScuola
	 * @return
	 * @throws ServiceException
	 */
	public List<InfoScuola> getElencoScuoleByUtente(String cfOperatore, String codProfilo, String codiceTipologiaScuola) throws ServiceException {
		final String methodName = "getElencoScuoleByUtente";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		log.info(buildMessage(getClass(), methodName, "codiceTipologiaScuola: " + codiceTipologiaScuola));

		// l'oggetto che incapsula il criterio di ricerca da compilare per l'invio al servizio di backend che effettua la ricerca per filtro
		CriterioRicerca criterio = new CriterioRicerca();
		criterio.setCodiceFiscaleUtente(cfOperatore);
		criterio.setCodiceProfiloUtente(codProfilo);
		criterio.setTipoScuola(codiceTipologiaScuola);

		List<InfoScuola> elencoScuole = new ArrayList<InfoScuola>();

		try {
			elencoScuole = new InfoScuolaConverter().convertAll(scuoleSrvBean.getServiceInterface().getElencoScuole(criterio));
		}
		catch (ScuoleUserException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Eccezione di Business", e);
		}
		catch (CSIException e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException("Eccezione Imprevista", e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return elencoScuole;
	}

	public String getCodAnnoNidi(Date data) throws ServiceException {
		final String methodName = "getCodAnnoNidi";
		log.debug(buildMessage(getClass(), methodName, LOG_BEGIN));

		Validate.notNull(data);

		String result;
		try {
			result = this.scuoleSrvBean.getServiceInterface().getCodAnnoNidi(data);
		}
		catch (Exception e) {
			log.error(buildMessage(getClass(), methodName, e.getMessage()));
			throw new ServiceException(e);
		}

		log.debug(buildMessage(getClass(), methodName, LOG_END));
		return result;
	}

}
