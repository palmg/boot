package com.palmg.boot.webcore.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    @Bean
    public ResultWraperFactory getResponseBodyWrap() {
    	return new ResultWraperFactory();
    }
}