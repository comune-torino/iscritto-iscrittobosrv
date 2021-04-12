package it.csi.iscritto.iscrittobosrv.util;

import javax.ws.rs.core.MultivaluedMap;

public final class AllegatoUtils {
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String FILE_NAME_KEY = "filename";

	private AllegatoUtils() {
		/* NOP */
	}

	public static String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst(CONTENT_DISPOSITION).split(";");

		for (String value : contentDisposition) {
			if (value.trim().startsWith(FILE_NAME_KEY)) {
				String[] name = value.split("=");
				if (name.length > 1) {
					return name[1].trim().replaceAll("\"", "");
				}
			}
		}

		return null;
	}

	public static String getMimeType(MultivaluedMap<String, String> header) {
		String[] contentType = header.getFirst(CONTENT_TYPE).split(";");

		for (String value : contentType) {
			if (!value.contains("=")) {
				return value.trim();
			}
		}

		return null;
	}

}
