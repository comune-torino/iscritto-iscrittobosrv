package it.csi.iscritto.iscrittobosrv.util.converter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.Validate;

public final class ConvertUtils {
	private ConvertUtils() {
		/* NOP */
	}

	public static BigDecimal toBigDecimal(Integer value) {
		return value == null ? null : BigDecimal.valueOf(value);
	}

	public static BigDecimal toBigDecimal(Double value) {
		return value == null ? null : BigDecimal.valueOf(value);
	}

	public static Integer toInteger(BigDecimal value) {
		return value == null ? null : value.intValue();
	}

	public static Integer toInteger(String value) {
		return value == null ? null : Integer.valueOf(value);
	}

	public static String toStringValue(BigDecimal value) {
		return value == null ? null : value.toString();
	}

	public static String toStringValue(Integer value) {
		return value == null ? null : value.toString();
	}

	public static String toStringValue(Double value) {
		return value == null ? null : value.toString();
	}

	public static Double toDouble(BigDecimal value) {
		return value == null ? null : value.doubleValue();
	}

	public static Long toLong(BigDecimal value) {
		return value == null ? null : value.longValue();
	}

	public static String toSN(Boolean b) {
		return Boolean.TRUE.equals(b) ? "S" : "N";
	}

	public static Boolean fromSN(String value) {
		return value == null ? null : "S".equalsIgnoreCase(value);
	}

	public static String nullToEmpty(String value) {
		return value == null ? "" : value;
	}

	public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
		List<T> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(items)) {
			Iterable<T> filtered = IterableUtils.filteredIterable(items, predicate);
			result.addAll(IteratorUtils.toList(filtered.iterator()));
		}

		return result;
	}

	public static <T> void remove(List<T> items, Predicate<T> predicate) {
		Validate.notNull(predicate, "predicate is null");

		if (CollectionUtils.isEmpty(items)) {
			return;
		}

		for (Iterator<T> it = items.iterator(); it.hasNext();) {
			T item = it.next();
			if (predicate.evaluate(item)) {
				it.remove();
			}
		}
	}

	public static <T> List<T> toList(T[] values) {
		List<T> result = new ArrayList<>();
		if (values != null) {
			result.addAll(Arrays.asList(values));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> values, Class<T> clazz) {
		Validate.notNull(clazz, "clazz is null");
		return values == null ? null : values.toArray((T[]) Array.newInstance(clazz, 0));
	}

}
