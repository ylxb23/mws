package com.zero.mws.opstream.func;

import java.util.Collection;

/**
 * 
 * @date 2019年6月18日 上午1:22:03
 * @author zero
 */
@FunctionalInterface
public interface CountFunction {
	
	int apply(Collection<?> collection);
}
