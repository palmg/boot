package com.palmg.boot.webstarter.sample.component;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.palmg.boot.webstarter.sample.dao.MyRepository;

@Component
public class MyComponent {
	static Logger LOG = LoggerFactory.getLogger(MyComponent.class);
	
	@Autowired
	MyRepository dao;
	
	@PostConstruct
	public void init() {
		LOG.info("MyComponent init success!");
		LOG.info(dao.toString());
	}
}
