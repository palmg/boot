package com.palmg.boot.webstarter.sample.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	static Logger LOG = LoggerFactory.getLogger(Config.class);
	@PostConstruct
	public void init() {
		LOG.info("Init method of Config!"); 
	}
}
