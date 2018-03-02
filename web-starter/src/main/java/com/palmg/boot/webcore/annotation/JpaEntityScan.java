package com.palmg.boot.webcore.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EntityScan
/**
 * 用于入口类，指定Jpa实体类的扫描路径
 * @author chenkui
 *
 */
public @interface JpaEntityScan {
	String[] value() default {};
}
