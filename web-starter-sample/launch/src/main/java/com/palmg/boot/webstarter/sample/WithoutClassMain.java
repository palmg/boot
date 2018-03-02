package com.palmg.boot.webstarter.sample;

import com.palmg.boot.webcore.Aaron;

/**
 * 不使用类，自行根据运行栈推导启动。
 * @author chenkui
 */
public class WithoutClassMain {
	public static void main(String[] args) {
		new Aaron().launch(args);
	}
}
