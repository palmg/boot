package com.palmg.boot.webcore.annotation.contorller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ReturnBind {
	static enum Type{
		Entity, // 返回默认绑定参数，默认值
		Collection, //集合类型
		None //不绑定任何返回值
	}
	
	Type value() default Type.Entity;
}
