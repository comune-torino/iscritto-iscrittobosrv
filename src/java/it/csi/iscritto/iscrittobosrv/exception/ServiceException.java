package it.csi.iscritto.iscrittobosrv.exception;

public final class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	protected String errorCode;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ServiceException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
