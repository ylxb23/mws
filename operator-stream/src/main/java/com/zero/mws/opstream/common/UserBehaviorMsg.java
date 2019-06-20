package com.zero.mws.opstream.common;

import java.util.Map;

/**
 * 
 * @date 2019年6月18日 上午1:32:32
 * @author zero
 */
public class UserBehaviorMsg {
	
	private String userId;
	private String process;
	private Map<String, Object> data;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}
