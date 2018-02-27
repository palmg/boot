package com.palmg.boot.webcore.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableJpaRepositories
public @interface JpaDaoScan {
	String[] valuse() default {};
}
