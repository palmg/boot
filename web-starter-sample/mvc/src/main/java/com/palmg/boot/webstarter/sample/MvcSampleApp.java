package com.palmg.boot.webstarter.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.palmg.boot.webcore.Aaron;

/**
 * MVC案例
 * @author chenkui
 *
 */
public class MvcSampleApp {
	static Logger LOG = LoggerFactory.getLogger(MvcSampleApp.class);

	public static void main(String[] args) {
		new Aaron(MvcSampleApp.class).launch(args);
	}
}
