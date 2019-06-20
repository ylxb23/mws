package com.zero.mws.opstream.func;

import java.util.Set;

/**
 * 
 * @date 2019年6月20日 下午11:08:35
 * @author zero
 */
@FunctionalInterface
public interface DistinctLoadLoginRecordFunction {
	
	Set<String> apply(String userId, Long qId, String current);
	
}
