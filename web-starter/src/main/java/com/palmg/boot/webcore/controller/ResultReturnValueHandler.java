package com.palmg.boot.webcore.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.palmg.boot.webcore.annotation.contorller.ReturnBind;
import com.palmg.boot.webcore.controller.impl.EntityConverSupport;

public class ResultReturnValueHandler implements HandlerMethodReturnValueHandler {

	private final HandlerMethodReturnValueHandler delegate;
	
	private ConverSupport converSupport;

	public ResultReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
		this.delegate = delegate;
		converSupport = new EntityConverSupport();
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		Method method = returnType.getMethod();
		if(null != method) {
			Class<?> klass = method.getDeclaringClass();
			Annotation annot = klass.getAnnotation(RestController.class);
			return null != annot;
		}else {
			return false;
		}
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		ReturnBind annot = returnType.getMethodAnnotation(ReturnBind.class);
		ReturnBind.Type type = null == annot ? ReturnBind.Type.Entity : annot.value();
		switch (type) {
		case Entity:
			returnValue = converSupport.conver(returnValue, annot);
			break;
		case None:
		default:
			break;
		}
		delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
	}
}
