package com.palmg.boot.webcore.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class ResultWraperFactory implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

	@Override
	public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers = adapter.getReturnValueHandlers();
        HandlerMethodReturnValueHandler[] list = new HandlerMethodReturnValueHandler[handlers.size()];
        int index = 0;
        for(HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
            	handler = new ResultReturnValueHandler(handler);
            }
        	list[index++] = handler;
        }
        adapter.setReturnValueHandlers(Arrays.asList(list));
	}
}
