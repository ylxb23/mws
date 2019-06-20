package com.zero.mws.opstream.func;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;

import com.zero.mws.opstream.common.FuncStreamPojo;
import com.zero.mws.opstream.common.OpStreamPojo;

/**
 * 
 * @date 2019年6月20日 下午11:42:03
 * @author zero
 */
public class OpFunctionStreamFactory {

	// 1
	public static final String OPCOMOUTERULE_LOGINTIMES = "date@TO_DATE_STRING#DISTINCT_LOAD_LOGIN_REOCRD#COUNT#PRINT";
	// 2
	public static final String OPCOMOUTERULE_LOGINTODAY = "date@TO_DATE_STRING#PRINT";
	// 3
	public static final String OPCOMOUTERULE_MARIOANSWER = "result@LOAD_VALUE#PRINT";
	// 4
	public static final String OPCOMOUTERULE_OPENGAMES = "pkg@DISTINCT_LOAD_OPEN_REOCRD#COUNT#PRINT";
	
	
	
	private static Map<Long, FuncStreamPojo> streamMap = new HashMap<>();
	
	static {
		streamMap.put(1L, buildStream(1L, OPCOMOUTERULE_LOGINTIMES));
		streamMap.put(1L, buildStream(2L, OPCOMOUTERULE_LOGINTODAY));
		streamMap.put(1L, buildStream(3L, OPCOMOUTERULE_MARIOANSWER));
		streamMap.put(1L, buildStream(4L, OPCOMOUTERULE_OPENGAMES));
	}
	
	public static FuncStreamPojo getFuncStream(Long tId) {
		return streamMap.get(tId);
	}
	
	private static FuncStreamPojo buildStream(Long tId, String computeRule) {
		FuncStreamPojo pojo = new FuncStreamPojo();
		String[] items = computeRule.split("@");
		String source = items[0];
		String[] operations = items[1].split("#");
		LinkedList<Function<OpStreamPojo, OpStreamPojo>> opFuncs = new LinkedList<>();
		for(String item : operations) {
			opFuncs.add(OpFunctionFactory.getOperation(item));
		}
		pojo.setSourceKey(source);
		pojo.setOps(opFuncs);
		pojo.setRule(computeRule);
		pojo.settId(tId);
		return pojo;
	}
	
	
	
}
