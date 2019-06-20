package com.zero.mws.opstream.common;

/**
 * 
 * @date 2019年6月20日 下午11:22:48
 * @author zero
 */
public class OpStreamPojo {
	private UserBehaviorMsg msg;
	private Object arg;
	private Object result;
	
	public UserBehaviorMsg getMsg() {
		return msg;
	}
	public void setMsg(UserBehaviorMsg msg) {
		this.msg = msg;
	}
	public Object getArg() {
		return arg;
	}
	public void setArg(Object arg) {
		this.arg = arg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
