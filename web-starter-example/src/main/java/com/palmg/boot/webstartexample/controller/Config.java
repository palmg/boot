package com.palmg.boot.webstartexample.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class Config {
	static Logger LOG = LoggerFactory.getLogger(Config.class);
	@PostConstruct
	public void init() {
		LOG.info("Init method of Config!"); 
	}
}
