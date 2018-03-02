package com.palmg.boot.webcore.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
/**
 * 用于入口类，指定spring bean的扫描路径。
 * @author chenkui
 *
 */
public @interface BeanScan {
	/**
	 * 包扫描路径
	 * @return
	 */
	String[] value() default {};
}
