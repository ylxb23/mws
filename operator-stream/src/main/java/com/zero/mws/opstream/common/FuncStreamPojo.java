package com.zero.mws.opstream.common;

import java.util.LinkedList;
import java.util.function.Function;

/**
 * 
 * @date 2019年6月20日 下午11:46:01
 * @author zero
 */
public class FuncStreamPojo {
	private Long tId;
	private String rule;
	private LinkedList<Function<OpStreamPojo, OpStreamPojo>> ops;
	private String sourceKey;
	
	public Long gettId() {
		return tId;
	}
	public void settId(Long tId) {
		this.tId = tId;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public LinkedList<Function<OpStreamPojo, OpStreamPojo>> getOps() {
		return ops;
	}
	public void setOps(LinkedList<Function<OpStreamPojo, OpStreamPojo>> ops) {
		this.ops = ops;
	}
	public String getSourceKey() {
		return sourceKey;
	}
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}
	
}
