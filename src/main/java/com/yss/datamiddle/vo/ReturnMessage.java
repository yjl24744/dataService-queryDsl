package com.yss.datamiddle.vo;

import java.util.Map;

/**
 *  返回消息对象
 * 
 */
public class ReturnMessage {
	
	private int code = 0;
	private String message = "ok";
	private Map<String,Object> info;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getInfo() {
		return info;
	}
	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
	
}
