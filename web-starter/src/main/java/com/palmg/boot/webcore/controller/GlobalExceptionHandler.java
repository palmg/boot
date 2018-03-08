package com.palmg.boot.webcore.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palmg.boot.webcore.controller.impl.Singleton;

@ControllerAdvice
public class GlobalExceptionHandler {

	 private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	    /**
	     * 系统异常处理，比如：404,500
	     * @param req
	     * @param e
	     * @return
	     * @throws Exception
	     */
	    @ExceptionHandler(value = Exception.class)
	    @ResponseBody
	    public Singleton defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

	        logger.error("exception", e);
	        Singleton response = new Singleton();
	        response.setMsg(e.getMessage());
	        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
	        	response.setCode(HttpStatus.http_status_not_found.getErrorCode());
	        } else {
	        	response.setCode(HttpStatus.http_status_internal_server_error.getErrorCode());
	        }
	        return response;
	    }
	    
}
