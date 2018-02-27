package com.palmg.boot.webcore.annotation;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan
@EnableJpaRepositories
public @interface ResourceScan {
	String[] valuse() default {};
}
