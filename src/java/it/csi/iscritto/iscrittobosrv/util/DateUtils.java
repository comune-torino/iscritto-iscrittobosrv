package it.csi.iscritto.iscrittobosrv.util;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public final class DateUtils {
	public static final String DEFAULT_TIMESTAMP_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String DEFAULT_FORMAT = "dd/MM/yyyy";
	public static final String ISO_8601_FORMAT = "yyyy-MM-dd";

	private DateUtils() {
		/* NOP */
	}

	public static Date toDate(String date, String format) throws ParseException {
		Validate.notNull(format);

		if (StringUtils.isBlank(date)) {
			return null;
		}

		return new Date(getDateFormat(format).parse(date).getTime());
	}

	public static String toStringDate(Date date, String format) {
		Validate.notNull(format);

		if (date == null) {
			return null;
		}

		return getDateFormat(format).format(date);
	}

	public static String changeFormat(String date, String fromFormat, String toFormat) throws ParseException {
		Validate.notBlank(fromFormat);
		Validate.notBlank(toFormat);

		if (StringUtils.isBlank(date)) {
			return null;
		}

		return toStringDate(toDate(date, fromFormat), toFormat);
	}

	public static boolean checkFormat(String date, String regex) {
		Validate.notBlank(regex);

		if (StringUtils.isBlank(date)) {
			return false;
		}

		if (!Pattern.matches(regex, date)) {
			return false;
		}

		return true;
	}

	public static Integer getDiffYears(Date from) {
		Validate.notNull(from);

		return getDiffYears(from, new Date());
	}

	public static Integer getDiffYears(Date from, Date to) {
		Validate.notNull(from);
		Validate.notNull(to);

		Calendar a = toCalendar(from);
		int yearA = a.get(YEAR);
		int monthA = a.get(MONTH);
		int dateA = a.get(DATE);

		Calendar b = toCalendar(to);
		int yearB = b.get(YEAR);
		int monthB = b.get(MONTH);
		int dateB = b.get(DATE);

		int diff = yearB - yearA;

		if (monthA > monthB || (monthA == monthB && dateA > dateB)) {
			diff--;
		}

		return diff;
	}

	public static Calendar toCalendar(Date date) {
		Validate.notNull(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}

	public static Date addYears(Date date, int years) {
		Validate.notNull(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);

		return cal.getTime();
	}

	public static boolean isValidRange(Date startDate, Date endDate) {
		Validate.notNull(startDate);
		Validate.notNull(endDate);

		return startDate.compareTo(endDate) <= 0;
	}

	private static DateFormat getDateFormat(String format) {
		Validate.notNull(format);

		DateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);

		return sdf;
	}

}
