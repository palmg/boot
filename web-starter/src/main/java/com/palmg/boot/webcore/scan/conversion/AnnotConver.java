package com.palmg.boot.webcore.scan.conversion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.palmg.boot.webcore.scan.PackageScan;

abstract public class AnnotConver {
	abstract public void conver(Class<?> converClass, PackageScan scan) throws AnnotConverExceprion;

	protected <T extends Annotation> void modify(Class<?> baseClass, Class<T> annotClass, String paramsName,
			String[] value) throws AnnotConverExceprion {
		if (null != value) {
			T annot = baseClass.getDeclaredAnnotation(annotClass);
			InvocationHandler h = Proxy.getInvocationHandler(annot);
			Field hField;
			try {
				hField = h.getClass().getDeclaredField("memberValues");
				hField.setAccessible(true);
				@SuppressWarnings("unchecked")
				Map<Object, Object> memberValues = (Map<Object, Object>) hField.get(h);
				memberValues.put(paramsName, value);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				throw new AnnotConverExceprion(e);
			}
		}
	}
}
