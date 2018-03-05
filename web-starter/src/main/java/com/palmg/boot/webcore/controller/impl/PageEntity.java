package com.palmg.boot.webcore.controller.impl;

public class PageEntity extends Singleton {
	private static final long serialVersionUID = -8246009318325934911L;

	private long total;//总数
	private long current;//当前页
	private long size;//单页长度
	
	public PageEntity(int code, String msg, Object data, long total, long current, long size) {
		super(code, msg, data);
		this.total = total;
		this.current = current;
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
