package it.csi.iscritto.iscrittobosrv.exception;

/**
 * Eccezione che rimappa eccezioni di business sollevate da componente a servizi pd/pa serviscritto
 * @author 630
 *
 */
public final class BusinessServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public BusinessServiceException() {
		super();
	}

	public BusinessServiceException(String message) {
		super(message);
	}

	public BusinessServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessServiceException(Throwable cause) {
		super(cause);
	}

}
