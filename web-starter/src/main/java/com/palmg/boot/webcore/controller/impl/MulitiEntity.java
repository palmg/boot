package com.palmg.boot.webcore.controller.impl;

public class MulitiEntity extends Singleton {
	private static final long serialVersionUID = -2991144807044212214L;
	private int count;
	public MulitiEntity(int code, String msg, Object data, int count) {
		super(code, msg, data);
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
