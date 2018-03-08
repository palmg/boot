package com.palmg.boot.webcore.controller.impl;

import com.palmg.boot.webcore.controller.Result;

/**
 * Controller层返回的数据项。包含三个域元素
 * {@code }
 * @author chenkui
 */
public class Singleton implements Result {
	private static final long serialVersionUID = -4579989945816167755L;
	private int code; 
	private String msg;
	private Object data;

	public Singleton() {
		
	}
	
	public Singleton(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultEntiry [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
