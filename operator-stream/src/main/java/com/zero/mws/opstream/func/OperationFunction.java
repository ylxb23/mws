package com.zero.mws.opstream.func;

import com.zero.mws.opstream.common.OpStreamPojo;

/**
 * 
 * @date 2019年6月20日 下午11:12:22
 * @author zero
 */
@FunctionalInterface
public interface OperationFunction {
	
	OpStreamPojo apply(OpStreamPojo data);
	
}
