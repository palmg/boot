package com.palmg.boot.webstarter.sample.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Config {
	static Logger LOG = LoggerFactory.getLogger(Config.class);

	@Autowired
	DataSource datasource;

	@PostConstruct
	public void init() {
		LOG.info("Init method of Config!");
	}
	
	@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
		//通过@Bean的优先加载特性，获取PlatformTransactionManager类
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
