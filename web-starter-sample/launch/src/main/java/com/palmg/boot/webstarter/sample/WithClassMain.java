package com.palmg.boot.webstarter.sample;

import com.palmg.boot.webcore.Aaron;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;

/**
 * 使用指定的类启动
 * @author chenkui
 *
 */
@BeanScan("com.palmg.boot.webstarter.sample")
@JpaEntityScan("com.palmg.boot.webstarter.sample.entity")
@JpaDaoScan("com.palmg.boot.webstarter.sample.dao")
public class WithClassMain {
	public static void main(String[] args) {
		new Aaron(WithClassMain.class).launch(args);
	}
}
