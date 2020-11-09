package com.rumahbangsa.draftbox.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static final String COMMON_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern(COMMON_DATETIME_PATTERN);
	
	public static String localDateTimeToString(LocalDateTime dt) {
		if (null == dt) {
			return "";
		}
		return dtf.format(dt);
	}
}
