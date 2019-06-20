package com.zero.mws.opstream.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @date 2019年6月18日 上午12:53:16
 * @author zero
 */
public class DateUtil {
	private static final String PATTERN_yyyyMMdd = "yyyyMMdd";
	// 
	private static final ThreadLocal<SimpleDateFormat> LOCAL_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(PATTERN_yyyyMMdd);
		};
	};
	
	/**
	 * 格式化日期为"yyyyMMdd"格式
	 * @param date
	 * @return
	 */
	public static final String format(Date date) {
		if(date == null) {
			return "";
		}
		return LOCAL_FORMATTER.get().format(date);
	}
}
