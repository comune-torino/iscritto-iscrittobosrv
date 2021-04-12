package it.csi.iscritto.iscrittobosrv.util;

import org.apache.commons.lang3.Validate;

public final class LoggingUtils {
	public static final String TEMPLATE = "[%s::%s] %s ";

	public static final String LOG_BEGIN = "BEGIN";
	public static final String LOG_END = "END";

	public static final String LOG_BEGIN_QUERY = "BEGIN QUERY";
	public static final String LOG_END_QUERY = "END QUERY";

	public static final String LOG_ERROR = "ERROR";

	private LoggingUtils() {
		/* NOP */
	}

	public static String buildMessage(Class<?> clazz, String method, String message) {
		Validate.notNull(clazz);
		Validate.notBlank(method);

		return String.format(TEMPLATE, clazz.getSimpleName(), method, message);
	}

}
