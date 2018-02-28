package com.palmg.boot.webstarter.sample;

import org.springframework.context.ApplicationContext;

import com.palmg.boot.webcore.Aaron;
import com.palmg.boot.webcore.annotation.BeanScan;
import com.palmg.boot.webcore.annotation.JpaDaoScan;
import com.palmg.boot.webcore.annotation.JpaEntityScan;
import com.palmg.boot.webstarter.sample.service.MyService;

/**
 * 使用指定的类启动
 * @author chenkui
 *
 */
@BeanScan("com.palmg.boot.webstarter.sample")
@JpaEntityScan("com.palmg.boot.webstarter.sample.entity")
@JpaDaoScan("com.palmg.boot.webstarter.sample.dao")
public class JapSampleApp {
	public static void main(String[] args) {
		ApplicationContext context = new Aaron(JapSampleApp.class).launch(args);
		MyService service = context.getBean(MyService.class);
		service.addOneRaw();
		service.addMulitRaw();
	}
}
