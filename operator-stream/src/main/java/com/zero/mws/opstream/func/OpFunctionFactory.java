package com.zero.mws.opstream.func;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.alibaba.fastjson.JSON;
import com.zero.mws.opstream.common.OpStreamPojo;
import com.zero.mws.opstream.util.DateUtil;

/**
 * 
 * @date 2019年6月18日 上午12:44:00
 * @author zero
 */
public class OpFunctionFactory {
	// COUNT
	// LOAD_RECORD_DISTINCT
	// TO_DATE_STRING
	private static Map<String, Function<OpStreamPojo, OpStreamPojo>> opFunctionMap = new HashMap<>();
	
	
	static {
		opFunctionMap.put("TO_DATE_STRING", new Function<OpStreamPojo, OpStreamPojo>() {
			@Override
			public OpStreamPojo apply(OpStreamPojo data) {
				System.out.println("start TO_DATE_STRING:" + JSON.toJSONString(data));
				Date date = (Date) data.getMsg().getData().get("DATE");
				data.setArg(date);
				data.setResult(DateUtil.format(date));
				System.out.println("end TO_DATE_STRING:" + JSON.toJSONString(data));
				return data;
			}
		});
		
		opFunctionMap.put("DISTINCT_LOAD_LOGIN_REOCRD", new Function<OpStreamPojo, OpStreamPojo>() {
			@Override
			public OpStreamPojo apply(OpStreamPojo data) {
				System.out.println("start DISTINCT_LOAD_LOGIN_REOCRD:" + JSON.toJSONString(data));
				String userId = (String) data.getMsg().getUserId();
				String current = (String) data.getResult();
				data.setArg(userId);
				data.setResult(loadRecord(userId, current));
				System.out.println("end DISTINCT_LOAD_LOGIN_REOCRD:" + JSON.toJSONString(data));
				return data;
			}
		});
		
		opFunctionMap.put("DISTINCT_LOAD_OPEN_REOCRD", new Function<OpStreamPojo, OpStreamPojo>() {
			@Override
			public OpStreamPojo apply(OpStreamPojo data) {
				System.out.println("start DISTINCT_LOAD_OPEN_REOCRD:" + JSON.toJSONString(data));
				String userId = (String) data.getMsg().getUserId();
				String current = (String) data.getResult();
				data.setArg(userId);
				data.setResult(loadOpenRecord(userId, current));
				System.out.println("end DISTINCT_LOAD_OPEN_REOCRD:" + JSON.toJSONString(data));
				return data;
			}

		});
		
		opFunctionMap.put("COUNT", new Function<OpStreamPojo, OpStreamPojo>() {
			@Override
			public OpStreamPojo apply(OpStreamPojo data) {
				System.out.println("start COUNT:" + JSON.toJSONString(data));
				Collection<?> collection = (Collection<?>) data.getResult();
				data.setArg(collection);
				data.setResult(collection.size());
				System.out.println("end COUNT:" + JSON.toJSONString(data));
				return data;
			}
		});
		
		opFunctionMap.put("PRINT", new Function<OpStreamPojo, OpStreamPojo>() {
			@Override
			public OpStreamPojo apply(OpStreamPojo data) {
				System.out.println("start PRINT:" + JSON.toJSONString(data));
				int count = (int) data.getResult();
				data.setArg(count);
				data.setResult(null);
				System.out.println("end PRINT:" + JSON.toJSONString(data));
				return data;
			}
		});
		
		
	}

	public static Function<OpStreamPojo, OpStreamPojo> getOperation(String operation) {
		Function<OpStreamPojo, OpStreamPojo> func = opFunctionMap.get(operation);
		return func;
	}
	

	private static Set<String> loadRecord(String userId, String current) {
		Set<String> set = new HashSet<>();
		set.add("20190611");
		set.add("20190617");
		set.add("20190618");
		set.add("20190619");
		set.add(current);
		return set;
	}
	
	private static Object loadOpenRecord(String userId, String current) {
		Set<String> set = new HashSet<>();
		set.add("kkk");
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		set.add(current);
		return set;
	}
}
