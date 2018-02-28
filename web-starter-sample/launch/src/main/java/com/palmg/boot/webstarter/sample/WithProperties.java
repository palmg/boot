package com.palmg.boot.webstarter.sample;

import com.palmg.boot.webcore.Aaron;

public class WithProperties {
	public static void main(String[] args) {
		System.setProperty("palmg.config.location.file",
				"classpath:my-root-config1.properties,classpath:my-root-config2.properties");
		new Aaron().launch(args);
	}
}
