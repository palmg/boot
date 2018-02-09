package com.palmg.boot.webcore;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import com.palmg.security.properties.PropertyCrypto;
import com.palmg.security.properties.exception.FileLoadException;

public class Aaron {
	static Logger LOG = LoggerFactory.getLogger(Aaron.class);
	public static void main(String[] args) {
		new Aaron().run(args);
	}
	
	public void run(String[] args) {
		Properties properties = null;
		try {
			properties = PropertyCrypto.getNew().decryptFile();
		} catch (FileLoadException e) {
			LOG.error("Load crypto properties file error! exit(0)");
			System.exit(0);
		}
		properties.put("spring.config.name", "palmg-boot");
    	SpringApplication app = new SpringApplication(Application.class);
    	app.setDefaultProperties(properties);
    	app.setBannerMode(Banner.Mode.OFF);
    	app.run(args);
	}
}
