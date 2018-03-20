package com.palmg.boot.webcore.controller.impl;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.palmg.boot.webcore.annotation.contorller.ReturnBind;
import com.palmg.boot.webcore.controller.ConverSupport;
import com.palmg.boot.webcore.controller.Result;

@Component
public class EntityConverSupport implements ConverSupport {
	public static final String SUCCESS_MSG = "Success";
	public static final int SUCCESS_CODE = 0;
	public static final String ERROR_MSG = "Error";
	public static final int ERROR_CODE = -1;

	@Override
	public Result conver(Object returnValue, ReturnBind annot) {
		Class<?> klass = returnValue.getClass();
		Result result;
		if (Collection.class.isAssignableFrom(klass)) {
			@SuppressWarnings("unchecked")
			Collection<? extends Object> collection = (Collection<? extends Object>) returnValue;
			result = new MulitiEntity(SUCCESS_CODE, SUCCESS_MSG, returnValue, collection.size());
		} else if (Page.class.isAssignableFrom(klass)) {
			@SuppressWarnings("unchecked")
			Page<? extends Object> page = (Page<? extends Object>) returnValue;
			result = new PageEntity(SUCCESS_CODE, SUCCESS_MSG, page.getContent(), page.getTotalElements(),
					page.getNumber(), page.getSize());
		} else if (Result.class.isAssignableFrom(klass)) {
			result = (Result) returnValue;
		} else {
			result = new Singleton(SUCCESS_CODE, SUCCESS_MSG, returnValue);
		}
		return result;
	}
}
