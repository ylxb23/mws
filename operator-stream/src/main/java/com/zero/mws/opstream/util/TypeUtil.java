package com.zero.mws.opstream.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @date 2019年6月20日 下午10:54:15
 * @author zero
 */
public class TypeUtil {

	private static final String INT = "int";
	private static final String LONG = "long";
	private static final String BOOLEAN = "boolean";
	private static final String DOUBLE = "double";
	private static final String STRING = "string";
	private static final String DATE = "date";
	
	private static final Map<String, Class<?>>TYPE_MAP = new HashMap<>();
	static {
		TYPE_MAP.put(INT, int.class);
		TYPE_MAP.put(LONG, long.class);
		TYPE_MAP.put(BOOLEAN, boolean.class);
		TYPE_MAP.put(DOUBLE, double.class);
		TYPE_MAP.put(STRING, String.class);
		TYPE_MAP.put(DATE, Date.class);
	}
	
	public static <T> T toType(Object obj, Class<T> clazz) {
		return (T) obj;
	}
	
	public static <T> T toType(Object obj, String type) {
		return (T) toType(obj, TYPE_MAP.get(type));
	}
	
}
