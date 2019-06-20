package com.zero.mws.opstream.func;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import com.zero.mws.opstream.common.FuncStreamPojo;
import com.zero.mws.opstream.common.OpStreamPojo;
import com.zero.mws.opstream.common.UserBehaviorMsg;

/**
 * 
 * @date 2019年6月18日 上午1:09:23
 * @author zero
 * @param <R>
 */
public class OpFuncStreamRunner {

	public static void main(String[] args) {
		FuncStreamPojo fsPojo = OpFunctionStreamFactory.getFuncStream(1L);
		String userId = "7777777";
		Map<String, Object> data = new HashMap<>();
		data.put("date", new Date());
		data.put("pkg", "zzz");
		UserBehaviorMsg msg = buildMsg(userId, data);
		stream(fsPojo, msg);
	}
	
	private static UserBehaviorMsg buildMsg(String userId, Map<String, Object> data) {
		UserBehaviorMsg msg = new UserBehaviorMsg();
		msg.setData(data);
		msg.setProcess("kkk");
		msg.setUserId(userId);
		return msg;
	}

	static void stream(FuncStreamPojo fsPojo, UserBehaviorMsg msg) {
		String sourceKey = fsPojo.getSourceKey();
		LinkedList<Function<OpStreamPojo, OpStreamPojo>> ops = fsPojo.getOps();
		OpStreamPojo data = new OpStreamPojo();
		data.setArg(msg.getData());
		data.setMsg(msg);
		data.setResult(msg.getData().get(sourceKey));
		
//		Stream<OpStreamPojo> stream = Stream.of(data);
		OpStreamPojo result = data;
		for(Function<OpStreamPojo, OpStreamPojo> func : ops) {
			result = func.apply(result);
		}
//		stream.close();
	}
	
}
