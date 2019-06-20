package com.zero.mws.opstream.func;

import java.util.Date;

import com.zero.mws.opstream.util.DateUtil;

/**
 * Mark for OpStream operator function
 * @date 2019年6月18日 上午12:15:24
 * @author zero
 */
public class OpFunction {
	
	/**
	 * Operator name, eg: {@code TO_DATE_STRING}
	 * @return
	 */
	String apply(Date date) {
		return DateUtil.format(date);
	}
}
