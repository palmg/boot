package com.palmg.boot.webcore;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.palmg.boot")
@EnableJpaRepositories("com.palmg.boot")
@Configuration
@ImportResource(locations = { "classpath:/palmg/config/application-context.xml",
		"classpath:/palmg/config/application-config-*.xml" })
public class ApplicationConfiguration {
}
