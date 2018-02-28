package com.palmg.boot.webcore.annotation;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan
@EnableJpaRepositories
/**
 * 用于入口类，指定资源包(xml)的加载路径
 * @author chenkui
 *
 */
public @interface ResourceScan{
	String[] value() default {};
}	