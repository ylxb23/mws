package com.zero.mw.dynamic.mybatis;

/**
 * 
 * @author zero
 */
public final class DynamicDataSourceHolder {
	private DynamicDataSourceHolder() {}
	
	private static final ThreadLocal<String> RWD_HOLDER = new ThreadLocal<>();

	public static String getHoldedRwd() {
		return RWD_HOLDER.get();
	}
	public static void putRwdHolder(String rwd) {
		RWD_HOLDER.set(rwd);
	}
	
	public static void clearRwdHolder() {
		RWD_HOLDER.remove();
	}
	
}
