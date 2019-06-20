package com.zero.mws.opstream.func;

import java.util.Date;

/**
 * Date to String function
 * @date 2019年6月18日 上午12:19:28
 * @author zero
 */
@FunctionalInterface
public interface ToDateStringFunction {
	
	String apply(Date date);
}
