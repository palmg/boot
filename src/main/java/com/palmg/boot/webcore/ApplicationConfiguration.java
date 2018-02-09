package com.palmg.boot.webcore;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:/palmg/config/application-context.xml", "classpath:/palmg/config/application-config-*.xml"})
public class ApplicationConfiguration {
	
}
